package data;

import java.io.Serializable;
import javax.persistence.*;


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

	public Kysymykset() {
	}

	
	public Kysymykset(int kysymysId, String kysymys) {
		super();
		this.kysymysId = kysymysId;
		this.kysymys = kysymys;
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

}