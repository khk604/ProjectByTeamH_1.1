package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.BoardTO;

public class ModifyAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("modifyaction 호출");

		String seq = request.getParameter("seq");

		BoardTO to = new BoardTO();
		BoardDAO dao = new BoardDAO();

		to.setSeq(seq);
		to = dao.boardModify(to);
		
		String mail[];
		if (to.getMail().indexOf("@") != -1) {
			System.out.println("존재");
			mail = to.getMail().split("@");
			request.setAttribute("mail1", mail[0]);
			request.setAttribute("mail2", mail[1]);
		} else {
			System.out.println("mail 없음");
			mail = new String[] { "", "" };
			System.out.println(mail);
			request.setAttribute("mail1", mail[0]);
			request.setAttribute("mail2", mail[1]);
		}
		request.setAttribute("to", to);
	}

}
