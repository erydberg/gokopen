package se.gokopen.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;


@Entity
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name="score")
public class Score {
	private Integer scoreId = null;
	private Station station;
	private int scorePoint;
	private int stylePoint;
	private Patrol patrol;
	private Date lastSaved;
	

	public Score(){
		
	}

	@Id
	@GeneratedValue
	@Column(name="scoreid", nullable=false)
	public Integer getScoreId() {
		return scoreId;
	}


	public void setScoreId(Integer scoreId) {
		this.scoreId = scoreId;
	}


	@ManyToOne
	@JoinColumn(name="fk_station")
	public Station getStation() {
		return station;
	}


	public void setStation(Station station) {
		this.station = station;
	}

	@Column(name="scorepoint")
	public int getScorePoint() {
		return scorePoint;
	}

	public void setScorePoint(int scorePoint) {
		this.scorePoint = scorePoint;
	}


	@Column(name="stylepoint")
	public int getStylePoint() {
		return stylePoint;
	}


	public void setStylePoint(int stylePoint) {
		this.stylePoint = stylePoint;
	}
	
	@Column(name="lastSaved")
	public Date getLastSaved() {
        return lastSaved;
    }

    public void setLastSaved(Date lastSaved) {
        this.lastSaved = lastSaved;
    }

    public void setPatrol(Patrol patrol) {
		this.patrol = patrol;
	}

	@ManyToOne
	@JoinColumn(name="fk_patrol")
	public Patrol getPatrol() {
		return patrol;
	}
}
