package se.gokopen.model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "patrol")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patrol implements Comparable<Patrol> {
    private Integer patrolId;
    private String externalId;
    private String patrolName;
    private String troop;
    private Track track;
    private String startTime;
    private String endTime;
    private String members;
    private String note;
    private Set<Score> scores = new LinkedHashSet<Score>();
    private String leaderContact;
    private Status status;
    private Boolean paid = false;
    private Score latestScore;
    private Station startStation;

    public Patrol() {

    }

    @Id
    @GeneratedValue
    @Column(name = "patrolid", nullable = false)
    public Integer getPatrolId() {
        return patrolId;
    }

    public void setPatrolId(Integer patrolId) {
        this.patrolId = patrolId;
    }

    @Column(name = "externalid")
    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Column(name = "patrolname", length = 120)
    public String getPatrolName() {
        return patrolName;
    }

    public void setPatrolName(String patrolName) {
        this.patrolName = patrolName;
    }

    @Column(name = "troop", length = 100)
    public String getTroop() {
        return troop;
    }

    public void setTroop(String troop) {
        this.troop = troop;
    }

    @ManyToOne
    @JoinColumn(name = "fk_track")
    public Track getTrack() {
        return track;
    }

    public void setTrack(Track track) {
        this.track = track;
    }

    @Column(name = "starttime", length = 10)
    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    @Column(name = "endtime", length = 10)
    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Column(name = "members", length = 500)
    public String getMembers() {
        return members;
    }

    public void setMembers(String members) {
        this.members = members;
    }

    @Column(name = "note", length = 500)
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.DELETE)
    // @OrderBy("station asc")
    @OrderBy("lastSaved desc")
    @JoinColumn(name = "fk_patrol")
    public Set<Score> getScores() {
        return scores;
    }

    public void setScores(Set<Score> scores) {
        this.scores = scores;
    }

    public void deleteScore(Score scoreRemove) {
        // find in set of scores and delete it
        Iterator<Score> itt = scores.iterator();
        while (itt.hasNext()) {
            Score s = itt.next();
            if (s.getScoreId().equals(scoreRemove.getScoreId())) {
                System.out.println("found scoreID" + s.getScoreId() + " in scores and removed it");
                itt.remove();
            }
        }
    }

    @Column(name = "leadercontact", length = 100)
    public String getLeaderContact() {
        return leaderContact;
    }

    public void setLeaderContact(String leaderContact) {
        this.leaderContact = leaderContact;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "paid")
    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    @Override
    public String toString() {
        return String.valueOf(patrolId);
    }

    @Transient
    public String getPatrolInfo() {
        return getPatrolName() + " (" + getPatrolId() + ") - "
                + (track != null ? track.getTrackName() : "(odefinierad klass)") + " (" + getTroop() + ")";
    }

    @Transient
    public Integer getTotalScorePoint() {
        Integer points = 0;
        for (Score score : scores) {
            points = points + score.getScorePoint();
        }
        return points;
    }

    @Transient
    public Integer getTotalStylePoint() {
        Integer points = 0;
        for (Score score : scores) {
            points = points + score.getStylePoint();
        }
        return points;
    }

    @Transient
    public Integer getTotalReportedStations() {
        return scores.size();
    }

    @Transient
    public Integer getTotalScore() {
        return this.getTotalStylePoint() + this.getTotalScorePoint();

    }

    @Transient
    public Score getLatestScore() {
        Calendar myCalendar = new GregorianCalendar(2014, Calendar.JANUARY, 1);
        Date lastSaved = myCalendar.getTime();
        Score lastSavedScore = new Score();
        lastSavedScore.setLastSaved(lastSaved);

        for (Score score : scores) {
            if (score.getLastSaved() != null && score.getLastSaved().after(lastSaved)) {
                lastSaved = score.getLastSaved();
                lastSavedScore = score;
            }
        }
        return lastSavedScore;
    }
    
    @Transient
    public Integer getNumberOfMaxPoints() {
        int numberOfMaxPoints = 0;
        for(Score score:scores) {
            Station currentStation = score.getStation();
            if(null == currentStation.getWaypoint()) {
                currentStation.setWaypoint(false);
            }
            if(!score.getStation().getWaypoint()) {
                int maxScoreOnStation = score.getStation().getMaxScore();
                if(score.getScorePoint() == maxScoreOnStation) {
                    numberOfMaxPoints++;
                }
            }
        }
        System.out.println("antal max" + numberOfMaxPoints);
        return numberOfMaxPoints;
    }

    @Override
    public int compareTo(Patrol p) {
        int comp = p.getTotalScore().compareTo(getTotalScore());
        if (comp == 0) {
            comp = p.getTotalScorePoint().compareTo(getTotalScorePoint());
        }
        if(comp == 0) {
            //samma antal poäng, samma antal stilpoäng, nu jämför vi antal 10-or
            comp = p.getNumberOfMaxPoints().compareTo(getNumberOfMaxPoints());
        }
        return comp;
    }

    @ManyToOne
    @JoinColumn(name = "fk_station")
    public Station getStartStation() {
        return startStation;
    }

    public void setStartStation(Station startStation) {
        this.startStation = startStation;
    }
}
