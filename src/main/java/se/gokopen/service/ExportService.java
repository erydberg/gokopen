package se.gokopen.service;

import se.gokopen.model.Patrol;
import se.gokopen.model.Score;
import se.gokopen.model.Station;

import java.util.List;

public class ExportService {
    public static final String DELIMITER = ";";
    List<Station> stations;

    public ExportService(List<Station> stations) {
        this.stations = stations;
    }

    public String generateHeadlineFromStations() {
        StringBuilder headline = new StringBuilder();
        headline.append("Plats");
        headline.append(DELIMITER);
        headline.append("Patrullnamn");
        headline.append(DELIMITER);
        headline.append("Scoutkår");
        headline.append(DELIMITER);

        for (Station station : stations) {
            headline.append("K" + station.getStationNumber() + "-poäng");
            headline.append(DELIMITER);
            headline.append("K" + station.getStationNumber() + "-stil");
            headline.append(DELIMITER);
        }
        headline.append("Poäng");
        headline.append(DELIMITER);
        headline.append("Stilpoäng");
        headline.append(DELIMITER);
        headline.append("Poäng totalt");
        headline.append(DELIMITER);

        return headline.toString();
    }

    public String generateRowFor(int position, Patrol patrol) {
        StringBuilder row = new StringBuilder();
        row.append(position);
        row.append(DELIMITER);
        row.append(escapeCsv(patrol.getPatrolName()));
        row.append(DELIMITER);
        row.append(escapeCsv(patrol.getTroop()));
        row.append(DELIMITER);

        for (Station station : stations) {
            boolean noScoreOnStation = true;
            for (Score score : patrol.getScores()) {
                if (score.getStation().getStationNumber() == station.getStationNumber()) {
                    row.append(score.getScorePoint());
                    row.append(DELIMITER);
                    row.append(score.getStylePoint());
                    row.append(DELIMITER);
                    noScoreOnStation = false;
                }
            }
            if (noScoreOnStation) {
                row.append(DELIMITER);
                row.append(DELIMITER);
            }
        }

        //add totalScores
        row.append(patrol.getTotalScorePoint());
        row.append(DELIMITER);
        row.append(patrol.getTotalStylePoint());
        row.append(DELIMITER);
        row.append(patrol.getTotalScore());
        row.append(DELIMITER);


        return row.toString();
    }

    String escapeCsv(String text){
        return text.replaceAll(";"," ");
    }
}
