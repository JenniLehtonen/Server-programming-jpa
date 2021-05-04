package app;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.Dao;
import data.Question;

@WebServlet("/updatecandidateanswer")

/**
 * @author Sanna Nieminen-Vuorio
 * 
 * This class will update the candidate's answers to database. 
 * It gets the candidates id as a parameter.
 * It will pass to the success.jsp page a string parameter which tells if the updating was done successfully or not.
 *
 */
public class updateCandidateAnswer extends HttpServlet{


	private static final long serialVersionUID = 1L;
	
	private Dao dao;
	public void init() {
		dao=new Dao("jdbc:mysql://localhost:3306/vaalikone", "admin", "salasana");
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) 
		     throws IOException
		     {
				response.sendRedirect("index.html");
		     }
	
	public void doPost(HttpServletRequest request, HttpServletResponse response) 
	     throws IOException, ServletException {
		
		String id = request.getParameter("ehdokas_id");
		int ehdokas_id = Integer.valueOf(id);
		ArrayList<Question> questionlist = null;
		
		if (dao.getConnection()) {
			questionlist = dao.readAllQuestion();
		} else {
			System.out.println("No connection to database");
		}
			
		ArrayList<Integer> candidateanswerlist = new ArrayList<>();
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < questionlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			answer = Integer.valueOf(answer_string);
			candidateanswerlist.add(answer);
		}
		
		String done = dao.updateCandidateAnswer(candidateanswerlist, ehdokas_id);

		request.setAttribute("success", done);
		RequestDispatcher rd=request.getRequestDispatcher("/jsp/success.jsp");
		rd.forward(request, response);
	}

}
