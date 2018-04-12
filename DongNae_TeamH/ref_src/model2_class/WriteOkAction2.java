package model2_class;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

import model2.BoardDAO;
import model2.BoardTO;

public class WriteOkAction2 implements BoardAction {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("writeokaction 호출");

		String uploadPath = "C:\\JSP\\eclipse-workspace\\WebProjectEx01\\WebContent\\upload";
		int maxSize = 1024 * 1024 * 2;
		String encoding = "utf-8";

		try {
			MultipartRequest multi = new MultipartRequest(request, uploadPath, maxSize, encoding,
					new DefaultFileRenamePolicy());

			BoardTO to = new BoardTO();
			to.setSubject(multi.getParameter("subject"));
			to.setWriter(multi.getParameter("writer"));
			to.setMail(multi.getParameter("mail1")+"@"+multi.getParameter("mail2"));
			to.setPassword(multi.getParameter("password"));
			to.setContent(multi.getParameter("content"));
			to.setFileName(multi.getFilesystemName("upload"));
			to.setWip(request.getRemoteAddr());

			BoardDAO dao = new BoardDAO();
			int flag = dao.boardWriteOk(to);

			request.setAttribute("flag", flag);
		} catch (IOException e) {
			System.out.println("error wriaction  : "+e.getMessage());
		}
	}

}
