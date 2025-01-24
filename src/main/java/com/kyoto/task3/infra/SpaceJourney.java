package com.kyoto.task3.infra;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;

import java.util.List;

public interface SpaceJourney {

    void boarding(Spaceship spaceship, List<Person> passengers);
    void takingOff(Spaceship spaceship);
    void hardAcceleration(Spaceship spaceship);
    void stableAcceleration(Spaceship spaceship);
    void planting(Spaceship spaceship);
    void landing(Spaceship spaceship);
    void emergencySituation(Spaceship spaceship);
}
