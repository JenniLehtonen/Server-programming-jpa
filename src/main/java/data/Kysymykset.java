package data;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the kysymykset database table.
 * 
 */
@Entity
@NamedQuery(name="Kysymykset.findAll", query="SELECT k FROM Kysymykset k")
public class Kysymykset implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="KYSYMYS_ID")
	private int kysymysId;

	private String kysymys;
	
	
	public Kysymykset(int id, String kysymys) {
		
		setKysymysId(id);
		
		this.kysymys = kysymys;
	}
	//bi-directional many-to-one association to Vastaukset
	@OneToMany(mappedBy="kysymykset")
	private List<Vastaukset> vastauksets;

	public Kysymykset() {
	}
	
	public Kysymykset(String kysymys) {
		// TODO Auto-generated constructor stub
	}

	public int getKysymysId() {
		return this.kysymysId;
	}

	public void setKysymysId(int kysymysId) {
		this.kysymysId = kysymysId;
	}

	public String getKysymys() {
		return this.kysymys;
	}

	public void setKysymys(String kysymys) {
		this.kysymys = kysymys;
	}

	public List<Vastaukset> getVastauksets() {
		return this.vastauksets;
	}

	public void setVastauksets(List<Vastaukset> vastauksets) {
		this.vastauksets = vastauksets;
	}

	public Vastaukset addVastaukset(Vastaukset vastaukset) {
		getVastauksets().add(vastaukset);
		vastaukset.setKysymykset(this);

		return vastaukset;
	}

	public Vastaukset removeVastaukset(Vastaukset vastaukset) {
		getVastauksets().remove(vastaukset);
		vastaukset.setKysymykset(null);

		return vastaukset;
	}

}