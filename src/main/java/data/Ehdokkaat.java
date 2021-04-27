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

	public int getEhdokasId() {
		return this.ehdokasId;
	}

	public void setEhdokasId(int ehdokasId) {
		this.ehdokasId = ehdokasId;
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

	public String getKotipaikkakunta() {
		return this.kotipaikkakunta;
	}

	public void setKotipaikkakunta(String kotipaikkakunta) {
		this.kotipaikkakunta = kotipaikkakunta;
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