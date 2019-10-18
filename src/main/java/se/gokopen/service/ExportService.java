package se.gokopen.service;

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;
import se.gokopen.model.Station;

import java.util.List;

public class ExportService {
    public static final String DELIMITER = ";";
    List<Station> stations;
    private Patrol patrol;

    public ExportService(List<Station> stations) {
        this.stations = stations;
    }

    public String generateHeadlineShort(){
        StringBuilder headline = new StringBuilder();
        addCommonHeadlinesTo(headline);
        addSumHeadlinesTo(headline);
        return headline.toString();
    }

    public String generateHeadlineFromStations() {
        StringBuilder headline = new StringBuilder();
        addCommonHeadlinesTo(headline);

        for (Station station : stations) {
            if(Boolean.TRUE.equals(station.getWaypoint())){
                headline.append("Milstolpe " + station.getStationNumber());
            }else {
                headline.append("K" + station.getStationNumber() + "-poäng");
                headline.append(DELIMITER);
                headline.append("K" + station.getStationNumber() + "-stil");
            }
            headline.append(DELIMITER);
        }
        addSumHeadlinesTo(headline);
        return headline.toString();
    }

    public String generateRowFor(int position, Patrol patrol) {
        this.patrol = patrol;
        StringBuilder row = new StringBuilder();
        addPatrolInfoTo(row, position);
        addPatrolScoreTo(row);
        addPatrolTotalScoresTo(row);
        return row.toString();
    }

    private void addPatrolTotalScoresTo(StringBuilder row) {
        row.append(patrol.getTotalScorePoint());
        row.append(DELIMITER);
        row.append(patrol.getTotalStylePoint());
        row.append(DELIMITER);
        row.append(patrol.getTotalScore());
        row.append(DELIMITER);
    }

    private void addPatrolInfoTo(StringBuilder row, int position) {
        row.append(position);
        row.append(DELIMITER);
        row.append(escapeCsv(patrol.getPatrolName()));
        row.append(DELIMITER);
        row.append(escapeCsv(patrol.getTroop()));
        row.append(DELIMITER);
    }

    private void addPatrolScoreTo(StringBuilder row) {
        for (Station station : stations) {
            boolean noScoreOnStation = true;
            for (Score score : patrol.getScores()) {
                if (score.getStation().getStationNumber() == station.getStationNumber()) {
                    if(Boolean.TRUE.equals(score.getStation().getWaypoint())){
                        row.append(printWaypoint(score.isVisitedWaypoint()));
                    }else{
                        row.append(score.getScorePoint());
                        row.append(DELIMITER);
                        row.append(score.getStylePoint());
                    }
                    row.append(DELIMITER);
                    noScoreOnStation = false;
                }
            }
            if (noScoreOnStation) {
                addNoScoreTo(row, station);
            }
        }
    }

    private void addNoScoreTo(StringBuilder row, Station station) {
        if(Boolean.TRUE.equals(station.getWaypoint())){
            row.append(DELIMITER);
        }else{
            row.append(DELIMITER);
            row.append(DELIMITER);
        }
    }

    private String printWaypoint(boolean visitedWaypoint) {
        if(visitedWaypoint){
            return "passerat";
        }else{
            return "";
        }
    }

    private void addCommonHeadlinesTo(StringBuilder headline) {
        headline.append("Plats");
        headline.append(DELIMITER);
        headline.append("Patrullnamn");
        headline.append(DELIMITER);
        headline.append("Scoutkår");
        headline.append(DELIMITER);
    }

    private void addSumHeadlinesTo(StringBuilder headline) {
        headline.append("Poäng");
        headline.append(DELIMITER);
        headline.append("Stilpoäng");
        headline.append(DELIMITER);
        headline.append("Poäng totalt");
        headline.append(DELIMITER);
    }

    String escapeCsv(String text){
        return text.replace(";"," ");
    }
}
