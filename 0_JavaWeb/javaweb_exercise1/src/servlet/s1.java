package servlet;

import java.io.IOException;
import java.net.HttpCookie;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.Session;

/**
 * Servlet implementation class s1
 */
@WebServlet("/s1")
public class s1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;utf-8");
		
		String username = request.getParameter("username");
		String age = request.getParameter("age");
		String school = request.getParameter("school");
//		System.out.println(username+age+school);
		
		//session
		HttpSession session = request.getSession();
		session.setAttribute("username", username);
		//cookie
		Cookie cookie = new Cookie("age", age);
		response.addCookie(cookie);
		//application
		this.getServletContext().setAttribute("school", school);
		
		response.sendRedirect("/javaweb_exercise1/reguser2.jsp?username=" + URLEncoder.encode(username,"utf-8") + "&age=" + URLEncoder.encode(age,"utf-8") + "&school=" + URLEncoder.encode(school,"utf-8"));
	}

}
