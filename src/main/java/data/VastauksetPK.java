package data;

import java.io.Serializable;
import javax.persistence.*;

/**
 * The primary key class for the vastaukset database table.
 * 
 */
@Embeddable
public class VastauksetPK implements Serializable {
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	@Column(name="EHDOKAS_ID", insertable=false, updatable=false)
	private int ehdokasId;

	@Column(name="KYSYMYS_ID", insertable=false, updatable=false)
	private int kysymysId;

	public VastauksetPK() {
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
	
	public int getKysymysId() {
		return this.kysymysId;
	}
	public void setKysymysId(int kysymysId) {
		this.kysymysId = kysymysId;
	}

	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof VastauksetPK)) {
			return false;
		}
		VastauksetPK castOther = (VastauksetPK)other;
		return 
			(this.ehdokasId == castOther.ehdokasId)
			&& (this.kysymysId == castOther.kysymysId);
	}

	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.ehdokasId;
		hash = hash * prime + this.kysymysId;
		
		return hash;
	}
}