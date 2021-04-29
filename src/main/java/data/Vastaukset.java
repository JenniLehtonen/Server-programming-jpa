package data;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the vastaukset database table.
 * 
 */
@Entity
@NamedQuery(name="Vastaukset.findAll", query="SELECT v FROM Vastaukset v")
public class Vastaukset implements Serializable {
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private VastauksetPK id;

	private String kommentti;

	private int vastaus;

	//bi-directional many-to-one association to Ehdokkaat
	@ManyToOne
	@JoinColumn(name="EHDOKAS_ID", insertable=false, updatable=false)
	private Ehdokkaat ehdokkaat;

	//bi-directional many-to-one association to Kysymykset
	@ManyToOne
	@JoinColumn(name="KYSYMYS_ID", insertable=false, updatable=false)
	private Kysymykset kysymykset;

	public Vastaukset() {
	}

	public VastauksetPK getId() {
		return this.id;
	}

	public void setId(VastauksetPK id) {
		this.id = id;
	}

	public String getKommentti() {
		return this.kommentti;
	}

	public void setKommentti(String kommentti) {
		this.kommentti = kommentti;
	}

	public int getVastaus() {
		return this.vastaus;
	}

	public void setVastaus(int vastaus) {
		this.vastaus = vastaus;
	}

	public Ehdokkaat getEhdokkaat() {
		return this.ehdokkaat;
	}

	public void setEhdokkaat(Ehdokkaat ehdokkaat) {
		this.ehdokkaat = ehdokkaat;
	}

	public Kysymykset getKysymykset() {
		return this.kysymykset;
	}

	public void setKysymykset(Kysymykset kysymykset) {
		this.kysymykset = kysymykset;
	}

}