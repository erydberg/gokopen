package se.gokopen.model;

import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name="patrol")
public class PatrolImpl implements Patrol, Comparable<PatrolImpl> {
	private Integer patrolId;
	private String patrolName;
	private String troop;
	private Track track;
	private String startTime;
	private String endTime;
	private String members;
	private String note;
	private Set <ScoreImpl> scores = new LinkedHashSet<ScoreImpl>();
	private String leaderContact;
	
	
	public PatrolImpl(){
		
	}
	
	
	

	@Id
	@GeneratedValue
	@Column(name="patrolid", nullable=false)
	public Integer getPatrolId() {
		return patrolId;
	}




	public void setPatrolId(Integer patrolId) {
		this.patrolId = patrolId;
	}



	@Column(name="patrolname", length=120)
	public String getPatrolName() {
		return patrolName;
	}




	public void setPatrolName(String patrolName) {
		this.patrolName = patrolName;
	}



	@Column(name="troop", length=100)
	public String getTroop() {
		return troop;
	}




	public void setTroop(String troop) {
		this.troop = troop;
	}




	@ManyToOne
	@JoinColumn(name="fk_track")
	public Track getTrack() {
		return track;
	}




	public void setTrack(Track track) {
		this.track = track;
	}



	@Column(name="starttime", length=10)
	public String getStartTime() {
		return startTime;
	}




	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}



	@Column(name="endtime", length=10)
	public String getEndTime() {
		return endTime;
	}




	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}



	@Column(name="members", length=500)
	public String getMembers() {
		return members;
	}

	public void setMembers(String members) {
		this.members = members;
	}

	@Column(name="note", length=500)
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER, orphanRemoval = true)
	 @Cascade(org.hibernate.annotations.CascadeType.DELETE)
	@OrderBy("station asc")
	@JoinColumn(name="fk_patrol")
	@Override
	public Set<ScoreImpl> getScores() {
		return scores;
	}
	public void setScores(Set<ScoreImpl> scores) {
		this.scores = scores;
	}

	@Override
	public void addScore() {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteScore(ScoreImpl scoreRemove) {
		//find in set of scores and delete it
		Iterator<ScoreImpl> itt = scores.iterator();
		while(itt.hasNext()){
			ScoreImpl s = itt.next();
			if(s.getScoreId().equals(scoreRemove.getScoreId())){
				System.out.println("found scoreID" + s.getScoreId() + " in scores and removed it");
				itt.remove();
			}
		}
	}

	@Override
	public void updateScore() {
		// TODO Auto-generated method stub

	}

	@Override
	@Transient
	public ScoreImpl getScore() {
		// TODO Auto-generated method stub
		return null;
	}



	@Column(name="leadercontact", length=100)
	public String getLeaderContact() {
		return leaderContact;
	}




	public void setLeaderContact(String leaderContact) {
		this.leaderContact = leaderContact;
	}
	
	@Override
	public String toString(){
		return String.valueOf(patrolId);
	}
	
	@Transient
	public String getPatrolInfo(){
		return getPatrolName() + " - " + (track != null ? track.getTrackName() : "(odefinierad klass)") + " (" + getTroop() + ")";
	}
	
	@Transient
	public Integer getTotalScorePoint(){
		Integer points = 0;
		for (ScoreImpl score:scores){
			points = points + score.getScorePoint();
		}
		return points;
	}
	
	@Transient
	public Integer getTotalStylePoint(){
		Integer points = 0;
		for (ScoreImpl score:scores){
			points = points + score.getStylePoint();
		}
		return points;
	}
	
	@Transient
	public Integer getTotalReportedStations(){
		return scores.size();
	}
	
	@Transient
	public Integer getTotalScore(){
		return this.getTotalStylePoint() + this.getTotalScorePoint();
//		Integer points = 0;
//		for (ScoreImpl score:scores){
//			points = points + score.getScorePoint();
//		}
//	
//		Integer stylePoints = 0;
//		for (ScoreImpl score:scores){
//			stylePoints = stylePoints + score.getStylePoint();
//		}
//		return points + stylePoints;
		
	}


	@Override
	public int compareTo(PatrolImpl p) {
		int comp = p.getTotalScore().compareTo(getTotalScore());
//		System.out.println("compare: " + p.getTotalScore() + " " + getTotalScore() + comp);
//		if(p.getTotalScore().equals(getTotalScore())){
		if (comp==0){
			comp = p.getTotalScorePoint().compareTo(getTotalScorePoint());
		}
		//här borde finnas logik för att hitta flest högpoänger
		return comp;
	}
	
	
}
