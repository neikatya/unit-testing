package com.kyoto.task3;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Condition;
import com.kyoto.task3.infra.impl.SpaceJourneyImpl;
import com.kyoto.task3.infra.impl.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.kyoto.task3.Generator.generateDefaultSpaceship;
import static com.kyoto.task3.Generator.generateRandomPassengers;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SpaceshipTakingOffTest {

    private Spaceship spaceship;
    private SpaceJourneyImpl journey;

    @BeforeEach
    void setUp() {
        journey = new SpaceJourneyImpl();
        spaceship = generateDefaultSpaceship();
        journey.boarding(spaceship, generateRandomPassengers(3));
    }

    @Test
    void takingOff_ShouldSetStunnedConditionForPassengers() {
        journey.takingOff(spaceship); // Корабль взлетает
        List<Person> passengers = journey.getJourneys().get(spaceship);
        for (Person person : passengers) {
            assertEquals(Condition.STUNNED, person.getCondition()); // Проверяем, что состояние пассажиров изменено на STUNNED
        }
    }

    @Test
    void takingOff_ShouldChangeStageToTakingOff() {
        journey.takingOff(spaceship); // Корабль взлетает
        assertEquals(Stage.TAKING_OFF, journey.getStagesOfSpaceships().get(spaceship)); // Проверяем, что стадия корабля изменена на TAKING_OFF
    }

    @Test
    void takingOff_ShouldThrowExceptionWhenSpaceshipIsNotPartOfJourney() {
        journey.getJourneys().remove(spaceship); // Убираем корабль из путешествия
        assertThrows(IllegalArgumentException.class, () -> journey.takingOff(spaceship)); // Попытка взлета корабля, который не участвует в путешествии
    }

    @Test
    void takingOff_ShouldThrowExceptionWhenSpaceshipIsNotParked() {
        journey.getStagesOfSpaceships().put(spaceship, Stage.TAKING_OFF); // Помечаем корабль как находящийся в стадии взлета
        assertThrows(IllegalArgumentException.class, () -> journey.takingOff(spaceship)); // Попытка взлета корабля, который не припаркован
    }
}
