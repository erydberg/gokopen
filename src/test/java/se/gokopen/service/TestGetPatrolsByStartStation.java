package se.gokopen.service;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import se.gokopen.model.Patrol;
import se.gokopen.model.Station;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/mvc-dispatcher-servlet.xml"})

public class TestGetPatrolsByStartStation {
	
	@Autowired
	private PatrolService patrolService;

	@Autowired
	private StationService stationService;

	@Ignore
	@Test
	public void shouldReturnPatrolsByStartStation() {
		System.out.println("start");
		List<Station> stations = stationService.getAllStations();
		System.out.println("start");
		for(Station station:stations) {
			System.out.println("Kontroll: " + station.getStationName());
			List<Patrol> patrols = patrolService.getAllPatrolsByStartStation(station);
			System.out.println("antal patruller på kontrollen: " + patrols.size());
		}
	}
}
