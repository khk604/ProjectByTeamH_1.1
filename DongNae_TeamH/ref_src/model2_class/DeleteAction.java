package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.BoardTO;

public class DeleteAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("deleteaction 호출");
		
		String seq = request.getParameter("seq");

		BoardTO to=new BoardTO();
		to.setSeq(seq);
		
		BoardDAO dao=new BoardDAO();
		
		to=dao.boardDelete(to);
		
		request.setAttribute("to", to);
	}

}
