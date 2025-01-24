package com.kyoto.task3;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.infra.impl.SpaceJourneyImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kyoto.task3.Generator.generateDefaultSpaceship;
import static com.kyoto.task3.Generator.generateRandomPassengers;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceshipBoardigTest {
    private Spaceship spaceship;
    private SpaceJourneyImpl journey;

    @BeforeEach
    void setUp() {
        journey = new SpaceJourneyImpl();
        spaceship = generateDefaultSpaceship();
    }

    @Test
    void boarding_ShouldAddSpaceshipToJourney() {
        journey.boarding(spaceship, generateRandomPassengers(3));
        assertTrue(journey.getJourneys().containsKey(spaceship));
    }

    @Test
    void boarding_ShouldThrowExceptionWhenSpaceshipIsAlreadyInvolved() {
        journey.boarding(spaceship, generateRandomPassengers(3)); // Добавляем корабль в путешествие
        assertTrue(journey.getJourneys().containsKey(spaceship));
        assertThrows(IllegalArgumentException.class, () -> journey.boarding(spaceship, generateRandomPassengers(5))); // Попытка добавить тот же корабль в путешествие
    }

    @Test
    void boarding_ShouldThrowExceptionWhenTooManyPassengers() {
        assertThrows(IllegalArgumentException.class, () -> journey.boarding(spaceship, generateRandomPassengers(30))); // Попытка посадить слишком много пассажиров
    }

    @Test
    void boarding_ShouldThrowExceptionWhenSpaceshipHasNoSeats() {
        spaceship.setSeats(null); // Предполагаем, что у корабля нет мест
        assertThrows(IllegalArgumentException.class, () -> journey.boarding(spaceship, generateRandomPassengers(3))); // Попытка посадить пассажиров, когда у корабля нет мест
    }
}
