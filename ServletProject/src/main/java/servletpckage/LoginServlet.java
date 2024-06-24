package servletpckage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		
		String name = request.getParameter("username");
		String password = request.getParameter("password");
		
		
		if (LoginValidation.validate(name, password)) {
			/*
			 * RequestDispatcher rd = request.getRequestDispatcher("WelcomeServletPage");
			 * rd.forward(request, response);
			 */
			out.println("<h6>Go to second page<a href=\"WelcomeServletPage\">second page</a></h6>");
			HttpSession session = request.getSession();
			session.setAttribute("user", name);
			response.sendRedirect("WelcomeServletPage");
			
		} else {
			out.println("<h6 style=\"color:blue;\">check your username and password!</h6>"
					+ "Retry again");
			RequestDispatcher rd = request.getRequestDispatcher("login.html");
			rd.include(request, response);

		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
