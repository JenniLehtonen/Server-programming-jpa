package data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the ehdokkaat database table.
 * 
 */
@Entity
@NamedQuery(name="Ehdokkaat.findAll", query="SELECT e FROM Ehdokkaat e")
public class Ehdokkaat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EHDOKAS_ID")
	private int ehdokasId;

	private String ammatti;

	private String etunimi;

	private int ika;

	private String kotipaikkakunta;

	private String kuva;

	@Column(name="MIKSI_EDUSKUNTAAN")
	private String miksiEduskuntaan;

	@Column(name="MITA_ASIOITA_HALUAT_EDISTAA")
	private String mitaAsioitaHaluatEdistaa;

	private String puolue;

	private String sukunimi;

	//bi-directional many-to-one association to Vastaukset
	@OneToMany(mappedBy="ehdokkaat")
	private List<Vastaukset> vastauksets;

	public Ehdokkaat() {
	}

	public Ehdokkaat(String sukunimi, String etunimi, String puolue, String koti, int ika, String miksi, String mita, String ammatti)
	{
		this.sukunimi = sukunimi;
		this.etunimi = etunimi;
		this.puolue = puolue;
		this.kotipaikkakunta = koti;
		setIka(ika);
		this.miksiEduskuntaan = miksi;
		this.mitaAsioitaHaluatEdistaa = mita;
		this.ammatti = ammatti;
	}
	
	public Ehdokkaat(int id, String sukunimi, String etunimi, String puolue, String koti, int ika, String miksi, String mita, String ammatti)
	{
		setEhdokasId(id);
		this.sukunimi = sukunimi;
		this.etunimi = etunimi;
		this.puolue = puolue;
		this.kotipaikkakunta = koti;
		setIka(ika);
		this.miksiEduskuntaan = miksi;
		this.mitaAsioitaHaluatEdistaa = mita;
		this.ammatti = ammatti;
	}
	
	public Ehdokkaat(int id, String sukunimi, String etunimi, String puolue, String koti, int ika, String miksi, String mita, String ammatti, String kuva)
	{
		setEhdokasId(id);
		this.sukunimi = sukunimi;
		this.etunimi = etunimi;
		this.puolue = puolue;
		this.kotipaikkakunta = koti;
		setIka(ika);
		this.miksiEduskuntaan = miksi;
		this.mitaAsioitaHaluatEdistaa = mita;
		this.ammatti = ammatti;
		this.kuva = kuva;
	}
	
	public int getEhdokasId() {
		return this.ehdokasId;
	}

	public void setEhdokasId(int ehdokasId) {
		this.ehdokasId = ehdokasId;
	}
	
	public void setEhdokasId(String ehdokasId) {
		this.ehdokasId = Integer.valueOf(ehdokasId);
	}

	public String getAmmatti() {
		return this.ammatti;
	}

	public void setAmmatti(String ammatti) {
		this.ammatti = ammatti;
	}

	public String getEtunimi() {
		return this.etunimi;
	}

	public void setEtunimi(String etunimi) {
		this.etunimi = etunimi;
	}

	public int getIka() {
		return this.ika;
	}

	public void setIka(int ika) {
		this.ika = ika;
	}
	
	public void setIka(String ika) {
		try
		{
			this.ika = Integer.valueOf(ika);
		}
		catch(Exception e)
		{
			System.out.println("Error at adding candidate's age is "+ e);
			this.ika = 0;
		}
	}

	public String getKotipaikkakunta() {
		return this.kotipaikkakunta;
	}

	public void setKotipaikkakunta(String kotipaikkakunta) {
		this.kotipaikkakunta = kotipaikkakunta;
	}

	public String getKuva() {
		return this.kuva;
	}

	public void setKuva(String kuva) {
		this.kuva = kuva;
	}

	public String getMiksiEduskuntaan() {
		return this.miksiEduskuntaan;
	}

	public void setMiksiEduskuntaan(String miksiEduskuntaan) {
		this.miksiEduskuntaan = miksiEduskuntaan;
	}

	public String getMitaAsioitaHaluatEdistaa() {
		return this.mitaAsioitaHaluatEdistaa;
	}

	public void setMitaAsioitaHaluatEdistaa(String mitaAsioitaHaluatEdistaa) {
		this.mitaAsioitaHaluatEdistaa = mitaAsioitaHaluatEdistaa;
	}

	public String getPuolue() {
		return this.puolue;
	}

	public void setPuolue(String puolue) {
		this.puolue = puolue;
	}

	public String getSukunimi() {
		return this.sukunimi;
	}

	public void setSukunimi(String sukunimi) {
		this.sukunimi = sukunimi;
	}

	public List<Vastaukset> getVastauksets() {
		return this.vastauksets;
	}

	public void setVastauksets(List<Vastaukset> vastauksets) {
		this.vastauksets = vastauksets;
	}

	public Vastaukset addVastaukset(Vastaukset vastaukset) {
		getVastauksets().add(vastaukset);
		vastaukset.setEhdokkaat(this);

		return vastaukset;
	}

	public Vastaukset removeVastaukset(Vastaukset vastaukset) {
		getVastauksets().remove(vastaukset);
		vastaukset.setEhdokkaat(null);

		return vastaukset;
	}

}