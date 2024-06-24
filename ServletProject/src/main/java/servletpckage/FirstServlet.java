package servletpckage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FirstServlet
 */

@WebServlet("/Register")
public class FirstServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public FirstServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PrintWriter out = response.getWriter();

		String name = request.getParameter("username");
		String password = request.getParameter("password");
		String repassword = request.getParameter("repassword");
		String email = request.getParameter("email");
		String country = request.getParameter("country");

		out.println("<html>\n" + "<head>\n" + "<meta charset=\"UTF-8\">\n" + "<title>Registration Page</title>\n" + "</head>");

		if (!name.equals("") && !password.equals("") && !repassword.equals("") && !email.equals("") && !country.equals("")) {
			if (password.equals(repassword)) {
				out.println("");
				try {
					Connection conn = DriverManager.getConnection("jdbc:mysql:///servletdb", "root", "root");
					String query = "INSERT INTO register\n" + "VALUES (?, ?, ?, ?, ?); ";
					PreparedStatement st = conn.prepareStatement(query);
					st.setString(1, name);
					st.setString(2, password);
					st.setString(3, repassword);
					st.setString(4, email);
					st.setString(5, country);

					st.executeUpdate();

					out.println("<h6 style=\"color:green\">User Registered Successfully</h6>");
					out.println("<h3>Click here to <a href=\"login.html\">Login</a></h3>");

				} catch (SQLException e) {

					e.printStackTrace();
				}

			} else {
				out.println("<body bgcolor=blue>\n" + "<h3>Registration Failed! Check your password</h3>\n" + "\n"
						+ "</body>");

			}
		} else {
			out.println("<h3 style=\"color:red\">All Data is mandatory</h3>");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
