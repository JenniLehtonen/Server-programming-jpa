package data;

public class Points implements Comparable<Points> {
	private int candidate_id;
	private int pointAmount;
	private String candidateFirstName;
	private String candidateLastName;
	
	public Points() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Points(int candidate_id, int pointAmount, String candidateFirstName, String candidateLastName) {
		super();
		this.candidate_id = candidate_id;
		this.pointAmount = pointAmount;
		this.candidateFirstName = candidateFirstName;
		this.candidateLastName = candidateLastName;
	}

	public int getCandidate_id() {
		return candidate_id;
	}

	public void setCandidate_id(int candidate_id) {
		this.candidate_id = candidate_id;
	}

	public int getPointAmount() {
		return pointAmount;
	}

	public void setPointAmount(int pointAmount) {
		this.pointAmount = pointAmount;
	}

	public String getCandidateFirstName() {
		return candidateFirstName;
	}

	public void setCandidateFirstName(String candidateFirstName) {
		this.candidateFirstName = candidateFirstName;
	}

	public String getCandidateLastName() {
		return candidateLastName;
	}

	public void setCandidateLastName(String candidateLastName) {
		this.candidateLastName = candidateLastName;
	}
	
	/**
	 * Verrataan pisteit�, jotta voidaan j�rjest�� lista niiden mukaan
	 * @param arg0
	 * @return
	 */
	public int compareTo(Points p) {
		
		int compare = Integer.compare(pointAmount, p.pointAmount);
		
		return compare;
	}
	
	
}
