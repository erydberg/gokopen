package se.gokopen.service;

import org.junit.Test;
import se.gokopen.model.Patrol;
import se.gokopen.model.Score;
import se.gokopen.model.Station;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class TestExport {

    public static final String CORRECT_HEADLINE = "Plats;Patrullnamn;Scoutkår;K1-poäng;K1-stil;K2-poäng;K2-stil;K3-poäng;K3-stil;Poäng;Stilpoäng;Poäng totalt;";
    public static final String CORRECT_PATROL_ROW = "1;Patrull 3;Scoutkåren 3;1;1;2;1;3;1;6;3;9;";
    public static final String CORRECT_PATROL_WITH_COMMA = "1;De glada laxarna;Scoutkåren;;;;;;;0;0;0;";


    @Test
    public void shouldCreateHeadlineRow() {
        List<Station> stations = generateStations();
        ExportService exportService = new ExportService(stations);
        System.out.println(exportService.generateHeadlineFromStations());
        assertThat(exportService.generateHeadlineFromStations()).isEqualTo(CORRECT_HEADLINE);
    }

    @Test
    public void shouldHandleSemisInText() {
        List<Station> stations = generateStations();
        Patrol patrol = new Patrol();
        patrol.setPatrolName("De;glada;laxarna");
        patrol.setTroop("Scoutkåren");
        ExportService exportService = new ExportService(stations);
        assertThat(exportService.generateRowFor(1, patrol)).isEqualTo(CORRECT_PATROL_WITH_COMMA);

    }

    @Test
    public void shouldReturnCorrectRow() {
        List<Station> stations = generateStations();
        List<Patrol> patrols = generatePatrols();
        addScoresTo(stations, patrols);

        ExportService exportService = new ExportService(stations);
        assertThat(exportService.generateRowFor(1, patrols.get(0))).isEqualTo(CORRECT_PATROL_ROW);
    }

    private void addScoresTo(List<Station> stations, List<Patrol> patrols) {
        for (Patrol patrol : patrols) {
            int i = 1;
            Set<Score> scores = new HashSet<>();
            for (Station station : stations) {
                Score score = new Score();
                score.setScorePoint(i);
                score.setStylePoint(1);
                score.setStation(station);
                score.setPatrol(patrol);
                scores.add(score);
                i++;
            }
            patrol.setScores(scores);
        }
    }

    private List<Patrol> generatePatrols() {
        Patrol patrol1 = new Patrol();
        Patrol patrol2 = new Patrol();
        Patrol patrol3 = new Patrol();

        patrol1.setPatrolName("Patrull 1");
        patrol2.setPatrolName("Patrull 2");
        patrol3.setPatrolName("Patrull 3");

        patrol1.setTroop("Scoutkåren 1");
        patrol2.setTroop("Scoutkåren 2");
        patrol3.setTroop("Scoutkåren 3");

        List<Patrol> patrols = new ArrayList<>();
        patrols.add(patrol3);
        patrols.add(patrol2);
        patrols.add(patrol1);
        return patrols;
    }

    private List<Station> generateStations() {
        Station station1 = new Station();
        Station station2 = new Station();
        Station station3 = new Station();

        station1.setStationNumber(1);
        station1.setStationName("Den första kontrollen");
        station2.setStationNumber(2);
        station2.setStationName("Den andra kontrollen");

        station3.setStationNumber(3);
        station3.setStationName("Den tredje kontrollen");
        List<Station> stations = new ArrayList<>();
        stations.add(station1);
        stations.add(station2);
        stations.add(station3);
        return stations;
    }
}
