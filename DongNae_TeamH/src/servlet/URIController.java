package servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2_class.BoardAction;
import model2_class.DeleteAction;
import model2_class.DeleteOkAction;
import model2_class.ListAction;
import model2_class.ModifyAction;
import model2_class.ModifyOkAction;
import model2_class.ViewAction;
import model2_class.WriteAction;
import model2_class.WriteOkAction2;
import model2_class.delRepAction;
import model2_class.writeRepAction;

/**
 * Servlet implementation class URIController
 */
@WebServlet("*.do")
public class URIController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doProcess(HttpServletRequest request, HttpServletResponse response) {
		try {
			// 한글 처리
			request.setCharacterEncoding("utf-8");
			// URI 방식
			String path = request.getRequestURI().replaceAll(request.getContextPath(), "");
			String url = "";
			BoardAction boardaction = null;
			
			if (path.equals("/*.do") || path.equals("/list.do")) {
				boardaction = new ListAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_list1.jsp";

			} else if (path.equals("/view.do")) {
				boardaction = new ViewAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_view1.jsp";

			} else if (path.equals("/write.do")) {
				boardaction = new WriteAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_write1.jsp";

			} else if (path.equals("/write_ok.do")) {
				boardaction = new WriteOkAction2();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_write_ok.jsp";

			} else if (path.equals("/modify.do")) {
				boardaction = new ModifyAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_modify1.jsp";

			} else if (path.equals("/modify_ok.do")) {
				boardaction = new ModifyOkAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_modify1_ok.jsp";

			} else if (path.equals("/delete.do")) {
				boardaction = new DeleteAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_delete1.jsp";

			} else if (path.equals("/delete_ok.do")) {
				boardaction = new DeleteOkAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/board_delete1_ok1.jsp";

			} else if (path.equals("/write_rep.do")) {
				boardaction = new writeRepAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/rep_write_ok.jsp";

			} else if (path.equals("/rep_del.do")) {
				boardaction = new delRepAction();
				boardaction.execute(request, response);

				url = "/WEB-INF/model2/rep_del_ok.jsp";

			} else {
				System.out.println("요청 오류");
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(url);
			dispatcher.forward(request, response);

		} catch (UnsupportedEncodingException e) {
			System.out.println(e.getMessage());
		} catch (ServletException e) {
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

	}
}
