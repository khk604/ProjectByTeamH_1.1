package model2_class;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.BoardTO;
import model2.ReplyTO;

public class ViewAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("viewaction 호출");
		
		BoardTO to = new BoardTO();
		to.setSeq(request.getParameter("seq"));
		// 게시판 자세히 보기
		to = new BoardDAO().boardView(to);
		request.setAttribute("to", to);
		
		
		// 코멘트 보기
		ArrayList<ReplyTO> list=new ArrayList<>();
		ReplyTO to2=new ReplyTO();
		to2.setSeq(request.getParameter("seq"));
		list=new BoardDAO().replyView(to2);
		request.setAttribute("to2", list);
		request.setAttribute("pseq", request.getParameter("seq"));
	}

}
