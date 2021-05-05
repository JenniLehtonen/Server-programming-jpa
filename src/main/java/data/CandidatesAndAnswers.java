package data;

import java.util.ArrayList;

public class CandidatesAndAnswers {
	
	private int ehdokas_id;
	private int vastaus;
	private int kysymys_id;
	private String ehdokasEtunimi;
	private String ehdokasSukunimi;
	
	
	

	public String getEhdokasSukunimi() {
		return ehdokasSukunimi;
	}

	public void setEhdokasSukunimi(String ehdokasSukunimi) {
		this.ehdokasSukunimi = ehdokasSukunimi;
	}

	public CandidatesAndAnswers()
	{
		
	}

	public CandidatesAndAnswers(int ehdokas_id, int vastaus, int kysymys_id){
		this.ehdokas_id = ehdokas_id;
		this.vastaus = vastaus;
		this.kysymys_id = kysymys_id;
		
	}

	public int getEhdokas_id() {
		return ehdokas_id;
	}

	public void setEhdokas_id(int ehdokas_id) {
		this.ehdokas_id = ehdokas_id;
	}

	public int getVastaus() {
		return vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public int getKysymys_id() {
		return kysymys_id;
	}

	public void setKysymys_id(int kysymys_id) {
		this.kysymys_id = kysymys_id;
	}

	public String getEhdokasEtunimi() {
		return ehdokasEtunimi;
	}

	public void setEhdokasEtunimi(String ehdokasEtunimi) {
		this.ehdokasEtunimi = ehdokasEtunimi;
	}


	
	
	

}
