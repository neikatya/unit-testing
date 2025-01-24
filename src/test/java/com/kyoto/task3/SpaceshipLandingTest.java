package com.kyoto.task3;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Condition;
import com.kyoto.task3.infra.impl.SpaceJourneyImpl;
import com.kyoto.task3.infra.impl.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SpaceshipLandingTest {

    private SpaceJourneyImpl spaceJourney;
    private Spaceship spaceship;

    @BeforeEach
    void setUp() {
        spaceJourney = new SpaceJourneyImpl();
        spaceship = Generator.generateDefaultSpaceship();
        spaceJourney.getJourneys().put(spaceship, Generator.generateRandomPassengers(3)); // Добавляем корабль с пассажирами в путешествие
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.PARKING); // Помечаем корабль как находящийся в стадии взлета
    }

    @Test
    void landing_ShouldSetCheeredUpConditionForPassengers() {
        List<Person> passengers = spaceJourney.getJourneys().get(spaceship);
        spaceJourney.landing(spaceship); // Корабль приземляется
        for (Person person : passengers) {
            assertEquals(Condition.CHEERED_UP, person.getCondition()); // Проверяем, что состояние пассажиров изменено на CHEERED_UP
        }
    }

    @Test
    void landing_ShouldRemoveSpaceshipFromJourney() {
        spaceJourney.landing(spaceship); // Корабль приземляется
        assertFalse(spaceJourney.getJourneys().containsKey(spaceship)); // Проверяем, что корабль больше не находится в путешествии
    }

    @Test
    void landing_ShouldThrowExceptionWhenSpaceshipIsNotPartOfJourney() {
        spaceJourney.getJourneys().remove(spaceship); // Убираем корабль из путешествия
        assertThrows(IllegalArgumentException.class, () -> spaceJourney.landing(spaceship)); // Попытка выполнения посадки корабля, который не участвует в путешествии
    }
}
