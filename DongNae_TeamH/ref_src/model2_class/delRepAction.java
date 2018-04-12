package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.ReplyTO;

public class delRepAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("delRepAction 호출");

		ReplyTO to=new ReplyTO();
		to.setSeq(request.getParameter("seq"));
		to.setPassword(request.getParameter("cpassword1"));
		to.setPseq(request.getParameter("pseq"));
		System.out.println(to.getSeq());
		System.out.println(to.getPassword());
		
		int flag=2;
		BoardDAO dao=new BoardDAO();
		flag=dao.deleteReplay(to);
		
		request.setAttribute("flag", flag);
		request.setAttribute("seq", to.getPseq());
	}

}
