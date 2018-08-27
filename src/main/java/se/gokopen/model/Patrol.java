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
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.JoinColumn;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.SafeHtml;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "patrol")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Patrol implements Comparable<Patrol> {
    private Integer patrolId;
    private String externalId;
    @NotEmpty(message = "Fyll i ett namn på patrullen")
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String patrolName;
    @NotEmpty(message = "Fyll i patrullens scoutkår")
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String troop;
    private Track track;
    private String startTime;
    private String endTime;
    private String members;
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String note;
    private Set<Score> scores = new LinkedHashSet<Score>();
    @NotEmpty (message = "Missa inte att fylla i kontaktperson")
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String leaderContact;
    @NotEmpty (message = "E-postadress krävs")
    @Email (message = "Se till att e-postadressen är korrekt")
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String leaderContactMail;
    @NotEmpty(message = "Ett telefonnummer vill vi ha också")
    @SafeHtml(message = "ingen knepig html eller js tack")
    private String leaderContactPhone;
    private Status status;
    private Date dateRegistered;
    private Boolean paid = false;
    private Score latestScore;
    private Station startStation;

    public Patrol() { 

    }

    @Id
    @SequenceGenerator(name = "patrolSeqGen", sequenceName = "PATROL_SEQ", initialValue = 100, allocationSize = 5)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "patrolSeqGen")
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

    @Column(name = "contactmail", length = 250)
    public String getLeaderContactMail() {
        return leaderContactMail;
    }

    public void setLeaderContactMail(String leaderContactMail) {
        this.leaderContactMail = leaderContactMail;
    }

    public String getLeaderContactPhone() {
        return leaderContactPhone;
    }

    public void setLeaderContactPhone(String leaderContactPhone) {
        this.leaderContactPhone = leaderContactPhone;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name="date_registered")
    public Date getDateRegistered() {
        return dateRegistered;
    }

    public void setDateRegistered(Date dateRegistered) {
        this.dateRegistered = dateRegistered;
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
        return getNumberOfXPoints(0);
    }
    
    @Transient
    public Integer getNumberOfXPoints(int x) {
        int numberOfXPoints = 0;
        for(Score score:scores) {
            Station currentStation = score.getStation();
            if(null == currentStation.getWaypoint()) {
                currentStation.setWaypoint(false);
            }
            if((!score.getStation().getWaypoint()) && ((score.getStation().getMaxScore()-x>0))){
                int maxScoreOnStation = score.getStation().getMaxScore()-x;
                if(score.getScorePoint() == maxScoreOnStation) {
                    numberOfXPoints++;
                }
            }
        }
        return numberOfXPoints;
    }

    @Override
    public int compareTo(Patrol p) {
        int comp = p.getTotalScore().compareTo(getTotalScore());
        if (comp == 0) {
            comp = p.getTotalScorePoint().compareTo(getTotalScorePoint());
        }
        
        if(comp == 0) {
            //loopar igenom så många poäng vi vill, satt till max 50
            for(int i = 0;i<=50;i++) {
                comp = p.getNumberOfXPoints(i).compareTo(getNumberOfXPoints(i));
                if(comp != 0) {
                    break;
                }
            }
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
