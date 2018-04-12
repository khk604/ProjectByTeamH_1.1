package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.ReplyTO;

public class writeRepAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		int flag=2;
		System.out.println(request.getParameter("seq"));
		String writer=request.getParameter("cwriter");
		String pw=request.getParameter("cpassword");
		String content=request.getParameter("ccontent");
		
		ReplyTO to=new ReplyTO();
		to.setPseq(request.getParameter("seq"));
		to.setWriter(writer);
		to.setPassword(pw);
		to.setContent(content);
		
		BoardDAO dao=new BoardDAO();
		flag=dao.writeReply(to);
		
		request.setAttribute("flag", flag);
		request.setAttribute("to", to.getPseq());
	}

}
