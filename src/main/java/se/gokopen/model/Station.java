package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Table(name="station")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Station {
	private Integer stationId;
	private int stationNumber;
	private String stationName;
	private int minScore;
	private int maxScore;
	private int minStyleScore;
	private int maxStyleScore;
	private String stationContact;
	private String stationPhonenumber;
	private String stationUser;
	private boolean waypoint;
	
	public Station(){
		
	}

	@Id
	@GeneratedValue
	@Column(name="stationid", nullable=false)
	public Integer getStationId() {
		return stationId;
	}

	public void setStationId(Integer stationId) {
		this.stationId = stationId;
	}

	@Column(name="stationnumber", length=4)
	public int getStationNumber() {
		return stationNumber;
	}

	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}

	@Column(name="stationname", length=100)
	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	@Column(name="minscore", length=4)
	public int getMinScore() {
		return minScore;
	}

	public void setMinScore(int minScore) {
		this.minScore = minScore;
	}

	@Column(name="maxscore", length=4)
	public int getMaxScore() {
		return maxScore;
	}

	public void setMaxScore(int maxScore) {
		this.maxScore = maxScore;
	}

	@Column(name="stationcontact", length=50)
	public String getStationContact() {
		return stationContact;
	}

	public void setStationContact(String stationContact) {
		this.stationContact = stationContact;
	}

	@Column(name="stationphone", length=50)
	public String getStationPhonenumber() {
		return stationPhonenumber;
	}

	public void setStationPhonenumber(String stationPhonenumber) {
		this.stationPhonenumber = stationPhonenumber;
	}
	
	
	@Column(name="minstylescore", length=4)
	public int getMinStyleScore() {
		return minStyleScore;
	}

	public void setMinStyleScore(int minStyleScore) {
		this.minStyleScore = minStyleScore;
	}

	@Column(name="maxstylescore", length=4)
	public int getMaxStyleScore() {
		return maxStyleScore;
	}

	public void setMaxStyleScore(int maxStyleScore) {
		this.maxStyleScore = maxStyleScore;
	}
	
	
	public void setStationUser(String stationUser) {
		this.stationUser = stationUser;
	}

	@Column(name="stationuser", length=16)
	public String getStationUser() {
		return stationUser;
	}
	
	@Column(name="waypoint")
	public boolean isWaypoint() {
		return waypoint;
	}

	public void setWaypoint(boolean waypoint) {
		this.waypoint = waypoint;
	}
	
	@Override
    public String toString() {
        return "Station [stationId=" + stationId + ", stationNumber=" + stationNumber + ", stationName=" + stationName
                + ", minScore=" + minScore + ", maxScore=" + maxScore + ", minStyleScore=" + minStyleScore
                + ", maxStyleScore=" + maxStyleScore + ", stationContact=" + stationContact + ", stationPhonenumber="
                + stationPhonenumber + ", stationUser=" + stationUser + ", waypoint=" + waypoint + "]";
    }
}
