package com.kyoto.task3;

import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Color;
import com.kyoto.task3.domain.spaceship_parts.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpaceshipBuilderTest {

    List<AirCushion> airCushions;
    Engine engine;
    NavigationPanel navigationPanel;
    List<Seat> seats;
    List<Wall> walls;
    List<Window> windows;

    @BeforeEach
    void init(){
        airCushions = new ArrayList<>();
        airCushions.add(new AirCushion(1.0));
        airCushions.add(new AirCushion(1.0));
        airCushions.add(new AirCushion(2.5));
        airCushions.add(new AirCushion(2.5));
        airCushions.add(new AirCushion(3.2));
        airCushions.add(new AirCushion(3.2));

        engine = new Engine(100);

        navigationPanel = new NavigationPanel(10, 30);

        seats = new ArrayList<>();
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.GREEN));
        seats.add(new Seat(Color.GREEN));
        seats.add(new Seat(Color.GREEN));

        walls = new ArrayList<>();
        for (int i=0; i<20; i++) {
            walls.add(new Wall(Color.GRAY));
        }

        windows = new ArrayList<>();
        for (int i=0; i<20; i++) {
            windows.add(new Window());
        }
    }

    @Test
    void builderTest(){
        Spaceship expectedShip = new Spaceship(navigationPanel,engine,airCushions,walls,windows,seats);

        Spaceship testingShip = Spaceship.builder().airCushions(airCushions).navigationPanel(navigationPanel).engine(engine).walls(walls).windows(windows).seats(seats).build();

        assertEquals(testingShip, expectedShip);
    }
}
