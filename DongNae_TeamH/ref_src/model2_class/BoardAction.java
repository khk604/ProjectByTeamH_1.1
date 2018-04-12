package model2_class;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardAction {
	public abstract void execute(HttpServletRequest request,HttpServletResponse response);
}
