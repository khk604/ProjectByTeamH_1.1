package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.BoardTO;

public class DeleteOkAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("deleteOkaction 호출");
		
		int flag=2;
		String seq = request.getParameter("seq");
		System.out.println(seq);
		String pw = request.getParameter("password");
		BoardTO to=new BoardTO();
		BoardDAO dao=new BoardDAO();
		
		to.setSeq(seq);
		to.setPassword(pw);
		
		flag=dao.boardDeleteOk(to);
		request.setAttribute("flag", flag);
	}

}
