package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model2.BoardDAO;
import model2.BoardListTO;

public class ListAction implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("listaction 호출");
		
		
		//cpage 검사

		int cpage = 1;
		System.out.println(request.getParameter("cpage"));
		if (request.getParameter("cpage") != null && !request.getParameter("cpage").equals("")) {
			cpage = Integer.parseInt(request.getParameter("cpage"));
		}
		// 리스트
		BoardListTO listTO=new BoardListTO();		
		listTO.setCpage(cpage);
		
		BoardDAO dao = new BoardDAO();
		listTO = dao.boardList(listTO);
		
		
		request.setAttribute("list", listTO);
		
		
	}

}
