package model2;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

// 페이지 내부의 데이터 생성
public class BoardDAO {
	private DataSource dataSource;

	public BoardDAO() {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");
			dataSource = (DataSource) envCtx.lookup("jdbc/oracle1");
		} catch (Exception e) {
			System.out.println("ERROr DAO: " + e.getMessage());
		}
	}

	public int boardWriteOk(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;

		try {
			con = dataSource.getConnection();
			String sql = "insert into alb_board values(alb_board_seq.nextval,?,?,?,?,?,?,0,0,?,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSubject());
			ps.setString(2, to.getWriter());
			ps.setString(3, to.getMail());
			ps.setString(4, to.getPassword());
			ps.setString(5, to.getContent());
			ps.setString(6, to.getFileName());
			ps.setString(7, to.getWip());

			int result = ps.executeUpdate();
			if (result == 1) {
				flag = 0;
			}
		} catch (Exception e) {
			System.out.println("ERROr : " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
		return flag;
	}

	// 페이징
	public BoardListTO boardList(BoardListTO listTO) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			BoardListTO bto = new BoardListTO();
			// 현재 페이지
			int cpage = listTO.getCpage();
			// 페이지당 게시물 수
			int recordPerPage = listTO.getRecordPerPage();
			// 페이지당 출력 페이지 갯수
			int blockPerPage = listTO.getBlockPerPage();

			con = dataSource.getConnection();

			String sql = "select seq, subject, writer, to_char(wdate,'YYYY.MM.DD HH:MI') wdate , hit, trunc(sysdate-wdate) wgap, filename,cmt from alb_board order by seq desc";
			ps = con.prepareStatement(sql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

			rs = ps.executeQuery();

			rs.last();
			listTO.setTotalRecord(rs.getRow());
			rs.beforeFirst();

			listTO.setTotalPage(((listTO.getTotalRecord() - 1) / listTO.getRecordPerPage()) + 1);

			int skip = (listTO.getCpage() - 1) * listTO.getRecordPerPage();
			if (skip != 0)
				rs.absolute(skip);
			int count = 0;
			ArrayList<BoardTO> boardLists = new ArrayList<>();
			for (int i = 0; i < listTO.getRecordPerPage() && rs.next(); i++) {
				BoardTO to = new BoardTO();
				to.setSeq(rs.getString("seq"));
				to.setSubject(rs.getString("subject"));
				to.setWdate(rs.getString("wdate"));
				to.setWriter(rs.getString("writer"));
				to.setHit(rs.getString("hit"));
				to.setWgap(rs.getShort("wgap"));
				to.setFileName(rs.getString("filename"));

				count = countRep(to.getSeq());

				to.setCmt(count);

				boardLists.add(to);
			}
			listTO.setBoardLists(boardLists);
			listTO.setStartBlock(((listTO.getCpage() - 1) / listTO.getBlockPerPage()) * listTO.getBlockPerPage() + 1);

			listTO.setEndBlock(((listTO.getCpage() - 1) / listTO.getBlockPerPage()) * listTO.getBlockPerPage()
					+ listTO.getBlockPerPage());

			if (listTO.getEndBlock() >= listTO.getTotalPage()) {
				listTO.setEndBlock(listTO.getTotalPage());
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}

		return listTO;
	}

	// 자세히 보기
	public BoardTO boardView(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			con = dataSource.getConnection();
			// 조회수 올리기
			String sql = "update alb_board set hit=hit+1 where seq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			ps.executeUpdate();
			// 리스트 목록 보기
			sql = "select subject,writer,mail,wip,wdate,hit,content,filename from alb_board where seq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			rs = ps.executeQuery();

			if (rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setWdate(rs.getString("wdate"));
				to.setWip(rs.getString("wip"));
				to.setHit(rs.getString("hit"));
				to.setMail(rs.getString("mail").equals("@") ? "" : "(" + rs.getString("mail") + ")");
				to.setContent(rs.getString("content") == null ? "" : rs.getString("content").replaceAll("\n", "<br>"));
				to.setFileName(rs.getString("filename"));
			}

		} catch (SQLException e) {
			System.out.println("ERROR DAO: " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return to;
	}

	// 게시글 수정폼 넘어가기
	public BoardTO boardModify(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			con = dataSource.getConnection();

			String sql = "select subject,writer,content,mail,filename from alb_board where seq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			rs = ps.executeQuery();

			if (rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
				to.setMail(rs.getString("mail").equals("@") ? "" : rs.getString("mail"));
				System.out.println(to.getMail());
				to.setContent(rs.getString("content") == null ? "" : rs.getString("content").replaceAll("\n", "<br>"));
				to.setFileName(rs.getString("filename"));

			}
		} catch (SQLException e) {
			System.out.println("ERROR : DAO D" + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return to;
	}

	// 게시글 수정하기
	public int boardModifyOk(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;

		int flag = 2;
		try {

			con = dataSource.getConnection();

			String sql = "update alb_board set subject=?, content=?, mail=?, writer=?, filename=? where seq=? and password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSubject());
			ps.setString(2, to.getContent());
			ps.setString(3, to.getMail());
			ps.setString(4, to.getWriter());
			ps.setString(5, to.getFileName());
			ps.setString(6, to.getSeq());
			ps.setString(7, to.getPassword());

			int result = ps.executeUpdate();

			if (result == 0) {
				// 비밀번호 오류
				flag = 1;
			} else if (result == 1) {
				// 정상 실행
				flag = 0;
			}
		} catch (SQLException e) {
			System.out.println("ERROR : DAO DO    " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	// 게시글 삭제폼으로
	public BoardTO boardDelete(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {

			con = dataSource.getConnection();

			String sql = "select subject,writer from alb_board where seq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			rs = ps.executeQuery();

			if (rs.next()) {
				to.setSubject(rs.getString("subject"));
				to.setWriter(rs.getString("writer"));
			}
		} catch (SQLException e) {
			System.out.println("ERROR : ");
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return to;
	}

	// 게시글 삭제
	public int boardDeleteOk(BoardTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		int flag = 2;
		try {
			con = dataSource.getConnection();
			String sql = "select filename from alb_board where seq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());

			rs = ps.executeQuery();
			String filename = null;
			if (rs.next()) {
				filename = rs.getString("filename");
			}

			sql = "delete from alb_board where seq=? and password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			ps.setString(2, to.getPassword());

			deleteReplayWithoutPw(to.getSeq());

			int result = ps.executeUpdate();

			if (result == 0) {
				// 비밀번호 오류
				flag = 1;
			} else if (result == 1) {
				// 정상 실행
				flag = 0;
				if (filename != null) {
					// 파일 삭제
					File file = new File("C:\\JSP\\eclipse-workspace\\WebProjectEx01\\WebContent\\upload", filename);
					file.delete();
				}
			}
		} catch (SQLException e) {
			System.out.println("ERROR : DAO" + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}

	////////////////////////////////////////////////////////////////////////////////////////
	//////////////// 코멘트 \\\\\\\\\\\\\\\\\\\\\\\\\\\\
	public int writeReply(ReplyTO to) {
		Connection con = null;
		PreparedStatement ps = null;
		int flag = 0;

		try {
			con = dataSource.getConnection();
			String sql = "insert into alb_reply values(alb_reply_seq.nextval,?,?,?,?,0,sysdate)";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getPseq());
			ps.setString(2, to.getWriter());
			ps.setString(3, to.getPassword());
			ps.setString(4, to.getContent());
			int result = ps.executeUpdate();
			if (result == 1) {
				flag = 0;
			}
		} catch (Exception e) {
			System.out.println("ERROr :wr 에서 발생 " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
				}
		}
		return flag;
	}
	// 코멘트 보기 메서드
	public ArrayList<ReplyTO> replyView(ReplyTO to2) {
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		ArrayList<ReplyTO> list = new ArrayList<>();
		try {

			con = dataSource.getConnection();
			// 코멘트 보기
			String sql = "select seq,writer,content,to_char(wdate,'YYYY.MM.DD HH:MI') wdate from alb_reply where pseq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to2.getSeq());
			rs = ps.executeQuery();

			while (rs.next()) {
				ReplyTO to = new ReplyTO();
				to.setSeq(rs.getString("seq"));
				to.setWriter(rs.getString("writer"));
				to.setWdate(rs.getString("wdate"));
				to.setContent(rs.getString("content").replaceAll("\n", "<br>"));

				list.add(to);
			}

		} catch (SQLException e) {
			System.out.println("ERROR DAO: " + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	// 게시글 삭제 메서드
	public int deleteReplay(ReplyTO to) {
		Connection con = null;
		PreparedStatement ps = null;

		int flag = 2;
		try {
			con = dataSource.getConnection();

			String sql = "delete from alb_reply where seq=? and password=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, to.getSeq());
			ps.setString(2, to.getPassword());

			int result = ps.executeUpdate();

			if (result == 0) {
				// 비밀번호 오류
				flag = 1;
			} else if (result == 1) {
				// 정상 실행
				flag = 0;
			}
		} catch (SQLException e) {
			System.out.println("ERROR : DAO" + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return flag;
	}
	// 리스트에서 해당 게시글의 코멘트 수 구하는 메서드
	public int countRep(String seq) {
		int count = 0;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			con = dataSource.getConnection();
			System.out.println("pseq " + seq);
			String sql = "select count(*) as count from alb_reply where pseq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, seq);

			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("1111카운듵" + rs.getInt("count"));
				count = rs.getInt("count");
			}
			System.out.println("카운트 이거 " + count);
		} catch (SQLException e) {
			System.out.println("ERROR : DAO" + e.getMessage());
		} finally {
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return count;
	}

	// 부모 게시글 삭제시 코멘트도 같이 삭제
	private void deleteReplayWithoutPw(String seq) {
		Connection con = null;
		PreparedStatement ps = null;
		System.out.println("코멘트 삭제 대기");
		try {
			con = dataSource.getConnection();
			String sql = "delete from alb_reply where pseq=?";
			ps = con.prepareStatement(sql);
			ps.setString(1, seq);

			int result = ps.executeUpdate();
			System.out.println(result + " 코멘트 삭제 완료");
		} catch (SQLException e) {
			System.out.println("ERROR : DAO" + e.getMessage());
		} finally {
			if (ps != null)
				try {
					ps.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
}