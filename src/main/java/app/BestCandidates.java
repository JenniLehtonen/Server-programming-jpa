package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;

import dao.Dao;
import data.*;

/**
 * @author Riikka Siukola 
 * 
 * This servlet will determine the five best candidates based on the user's answers.
 */

@WebServlet(name = "bestCandidates", urlPatterns = { "/bestcandidates" })
public class BestCandidates extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void init() {

	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BestCandidates() {
		super();
		// TODO Auto-generated constructor stub
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Dao dao = new Dao();

		/**
		 * Get the question list
		 */
		List<Kysymykset> questionlist = null;

			questionlist = dao.getAllQuestions();


		/**
		 * Get candidate list
		 */
		List<Ehdokkaat> candidatelist = dao.getAllCandidates();

//		candidatelist = (ArrayList<Ehdokkaat>) dao.getAllCandidates();
//		for (int i = 0; i < candidatelist.size(); i++) {
//			Ehdokkaat x = candidatelist.get(i);
//		}

		/**
		 * Construct a list out of user's answers
		 */
		ArrayList<Integer> useranswerlist = new ArrayList<>();
		String answer_string = null;
		int answer = 0;

		for (int i = 0; i < questionlist.size(); i++) {
			answer_string = request.getParameter("" + (i + 1));
			if (answer_string != null) {

				answer = Integer.valueOf(answer_string);
				useranswerlist.add(answer);

				/**
				 * If user didn't answer a question, default to zero
				 */
			} else {
				answer = 0;
				useranswerlist.add(answer);
			}

		}

		int difference = 0;
		int differenceSum = 0;
		Vastaukset candidateAnswer = null;
		List<Vastaukset> candidatesAnswers = null;

		ArrayList<Points> pointsAndCandidates = new ArrayList<>();

		/**
		 * Read each particular candidate's answers and put them in a list
		 */
		for (int i = 0; i < candidatelist.size(); i++) {
			Points points = new Points();
			differenceSum = 0;

			Ehdokkaat c = candidatelist.get(i);
			candidatesAnswers = dao.getCandidateById(c.getEhdokasId()).getVastauksets();

			/**
			 * Check if a candidate has any answers
			 */
			if (candidatesAnswers.size() != 0) {

				/**
				 * If so, go through them and subtract them from the user's answers to get a
				 * score
				 */
				for (int j = 0; j < questionlist.size(); j++) {

					/**
					 * Check if the user has answered a particular question. If not, skip.
					 */
					if (useranswerlist.get(j) != 0 && candidatesAnswers.get(j).getVastaus() != 0) {
						candidateAnswer = candidatesAnswers.get(j);
						difference = useranswerlist.get(j) - candidateAnswer.getVastaus();
						differenceSum = differenceSum + Math.abs(difference);

						System.out.println("ID: " + candidatelist.get(i).getEhdokasId() + ", vastaus: "
								+ candidatesAnswers.get(j).getVastaus());
					}

				}
				points.setCandidate_id(candidatelist.get(i).getEhdokasId());
				points.setPointAmount(differenceSum);
				points.setCandidateFirstName(candidatelist.get(i).getEtunimi());
				points.setCandidateLastName(candidatelist.get(i).getSukunimi());
				pointsAndCandidates.add(points);
				System.out.println("ID: " + points.getCandidate_id() + ", points: " + points.getPointAmount());

			}

		}

		/**
		 * Sort the list of points so best candidates are on top /Sanna
		 */
		Collections.sort(pointsAndCandidates);

		/**
		 * Remove everyone beyond the fifth slot
		 */
		pointsAndCandidates.subList(5, pointsAndCandidates.size()).clear();

		request.setAttribute("useranswers", useranswerlist);
		request.setAttribute("pointsAndCandidates", pointsAndCandidates);

		RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/bestCandidates.jsp");

		dispatcher.forward(request, response);

	}

}