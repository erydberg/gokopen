package se.gokopen.model;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class ScoreImpl implements Score {
	private Integer scoreId = null;
	private Station station;
	private int scorePoint;
	private int stylePoint;
	private PatrolImpl patrol;
	

	public ScoreImpl(){
		
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

	@Override
	public void setScorePoint(int scorePoint) {
		this.scorePoint = scorePoint;
	}


	@Column(name="stylepoint")
	public int getStylePoint() {
		return stylePoint;
	}


	@Override
	public void setStylePoint(int stylePoint) {
		this.stylePoint = stylePoint;
	}

	public void setPatrol(PatrolImpl patrol) {
		this.patrol = patrol;
	}

	@ManyToOne
	@JoinColumn(name="fk_patrol")
	public PatrolImpl getPatrol() {
		return patrol;
	}
}
