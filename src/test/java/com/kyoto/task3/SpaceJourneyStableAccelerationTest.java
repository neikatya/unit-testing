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

public class SpaceJourneyStableAccelerationTest {

    private SpaceJourneyImpl spaceJourney;
    private Spaceship spaceship;

    @BeforeEach
    void setUp() {
        spaceJourney = new SpaceJourneyImpl();
        spaceship = generateDefaultSpaceship();
        spaceJourney.getJourneys().put(spaceship, generateRandomPassengers(3)); // Добавляем корабль с пассажирами в путешествие
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.TAKING_OFF); // Помечаем корабль как находящийся в стадии взлета
    }

    @Test
    void stableAcceleration_ShouldSetNormalConditionForPassengers() {
        spaceJourney.stableAcceleration(spaceship); // Корабль выполняет устойчивое ускорение
        List<Person> passengers = spaceJourney.getJourneys().get(spaceship);
        for (Person person : passengers) {
            assertEquals(Condition.NORMAL, person.getCondition()); // Проверяем, что состояние пассажиров изменено на NORMAL
        }
    }

    @Test
    void stableAcceleration_ShouldChangeStageToFlying() {
        spaceJourney.stableAcceleration(spaceship); // Корабль выполняет устойчивое ускорение
        assertEquals(Stage.FLYING, spaceJourney.getStagesOfSpaceships().get(spaceship)); // Проверяем, что стадия корабля изменена на FLYING
    }

    @Test
    void stableAcceleration_ShouldThrowExceptionWhenSpaceshipIsNotPartOfJourney() {
        spaceJourney.getJourneys().remove(spaceship); // Убираем корабль из путешествия
        assertThrows(IllegalArgumentException.class, () -> spaceJourney.stableAcceleration(spaceship)); // Попытка выполнения устойчивого ускорения корабля, который не участвует в путешествии
    }

    @Test
    void stableAcceleration_ShouldThrowExceptionWhenSpaceshipAccelerationIsNotUnstable() {
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.FLYING); // Помечаем корабль как находящийся в стадии полета
        assertThrows(IllegalArgumentException.class, () -> spaceJourney.stableAcceleration(spaceship)); // Попытка выполнения устойчивого ускорения корабля, который не находится в стадии взлета или ускорения
    }

}
