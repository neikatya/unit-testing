package com.kyoto.task3.infra.impl;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Color;
import com.kyoto.task3.infra.SpaceJourney;
import lombok.Getter;

import java.util.Hashtable;
import java.util.List;

import static com.kyoto.task3.domain.enums.Condition.*;

@Getter
public class SpaceJourneyImpl implements SpaceJourney {

    private Hashtable<Spaceship, List<Person>> journeys = new Hashtable<>();
    private Hashtable<Spaceship, Stage> stagesOfSpaceships = new Hashtable<>();

    @Override
    public void boarding(Spaceship spaceship, List<Person> passengers) {
        if (journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is already involved in the journey");
        if (spaceship.getSeats() != null && spaceship.getSeats().size() >= passengers.size()) {
            journeys.put(spaceship, passengers);
            stagesOfSpaceships.put(spaceship, Stage.PARKING);
        } else {
            throw new IllegalArgumentException("Too many passengers");
        }
    }

    @Override
    public void takingOff(Spaceship spaceship) {
        if (!journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is not part of the journey");
        if (stagesOfSpaceships.get(spaceship) != Stage.PARKING) throw new IllegalArgumentException("Spaceship can't taking off");
        journeys.get(spaceship).forEach(person -> person.setCondition(STUNNED));
        stagesOfSpaceships.put(spaceship,Stage.TAKING_OFF);
    }

    @Override
    public void hardAcceleration(Spaceship spaceship) {
        if (!journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is not part of the journey");
        if (stagesOfSpaceships.get(spaceship) != Stage.FLYING) throw new IllegalArgumentException("This spaceship can't acceleration");
        journeys.get(spaceship).forEach(person -> person.setCondition(PANIC));
        stagesOfSpaceships.put(spaceship,Stage.ACCELERATING);
    }

    @Override
    public void stableAcceleration(Spaceship spaceship) {
        if (!journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is not part of the journey");
        if (stagesOfSpaceships.get(spaceship) != Stage.TAKING_OFF && stagesOfSpaceships.get(spaceship) != Stage.ACCELERATING) throw new IllegalArgumentException("Spaceship acceleration is not unstable");
        journeys.get(spaceship).forEach(person -> person.setCondition(NORMAL));
        stagesOfSpaceships.put(spaceship,Stage.FLYING);
    }

    @Override
    public void planting(Spaceship spaceship) {
        if (!journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is not part of the journey");
        if (stagesOfSpaceships.get(spaceship) != Stage.FLYING) throw new IllegalArgumentException("This spaceship can't planting");
        journeys.get(spaceship).forEach(person -> person.setCondition(STUNNED));
        stagesOfSpaceships.put(spaceship,Stage.PARKING);
    }

    @Override
    public void landing(Spaceship spaceship) {
        if (!journeys.containsKey(spaceship)) throw new IllegalArgumentException("This spaceship is not part of the journey");
        if (stagesOfSpaceships.get(spaceship) != Stage.PARKING) throw new IllegalArgumentException("This spaceship can't landing");
        journeys.get(spaceship).forEach(person -> person.setCondition(CHEERED_UP));
        journeys.remove(spaceship);
    }

    @Override
    public void emergencySituation(Spaceship spaceship) {
        journeys.get(spaceship).forEach(person -> person.setCondition(PANIC));
        stagesOfSpaceships.put(spaceship,Stage.ACCELERATING);
        spaceship.getAirCushions().forEach(airCushion -> airCushion.setActive(true));
        spaceship.getNavigationPanel().getLamps().forEach(lamp -> {
            if (Math.random() < 0.5) lamp.setColor(Color.randomColor());
        });
        spaceship.getNavigationPanel().getLamps().forEach(lamp -> {
            if (Math.random() < 0.5) lamp.setColor(Color.randomColor());
        });
        spaceship.getNavigationPanel().getLamps().forEach(lamp -> {
            if (Math.random() < 0.5) lamp.setColor(Color.randomColor());
        });
    }
}
