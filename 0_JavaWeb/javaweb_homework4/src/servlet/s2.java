package servlet;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class h2
 */
@WebServlet("/s2")
public class s2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//输入未乱码，修改response编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		
		if(Integer.parseInt(age) < 20) {
			response.getWriter().print("欢迎你，少年");
		}
		else if(Integer.parseInt(age) > 40) {
			//使用get形式传递参数，需要使用URLEncoder.encode进行中文编码
			response.sendRedirect(request.getContextPath()+"/redirect.jsp?username=" + URLEncoder.encode(username,"utf-8"));
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//输入乱码，修改response编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		
		if(Integer.parseInt(age) < 20) {
			response.getWriter().print("欢迎你，少年");
		}
		else if(Integer.parseInt(age) > 40) {
			response.getWriter().print("欢迎您，老先生"+username);
		}
	}
}
