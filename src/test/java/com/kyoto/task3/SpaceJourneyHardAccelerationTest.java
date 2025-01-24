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

public class SpaceJourneyHardAccelerationTest {

    private SpaceJourneyImpl spaceJourney;
    private Spaceship spaceship;

    @BeforeEach
    void setUp() {
        spaceJourney = new SpaceJourneyImpl();
        spaceship = generateDefaultSpaceship();
        spaceJourney.getJourneys().put(spaceship, generateRandomPassengers(3)); // Добавляем корабль с пассажирами в путешествие
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.FLYING); // Помечаем корабль как находящийся в стадии полета
    }

    @Test
    void hardAcceleration_ShouldSetPanicConditionForPassengers() {
        spaceJourney.hardAcceleration(spaceship); // Корабль выполняет жесткое ускорение
        List<Person> passengers = spaceJourney.getJourneys().get(spaceship);
        for (Person person : passengers) {
            assertEquals(Condition.PANIC, person.getCondition()); // Проверяем, что состояние пассажиров изменено на PANIC
        }
    }

    @Test
    void hardAcceleration_ShouldThrowExceptionWhenSpaceshipIsNotPartOfJourney() {
        spaceJourney.getJourneys().remove(spaceship); // Убираем корабль из путешествия
        assertThrows(IllegalArgumentException.class, () -> spaceJourney.hardAcceleration(spaceship)); // Попытка выполнения жесткого ускорения корабля, который не участвует в путешествии
    }

    @Test
    void hardAcceleration_ShouldThrowExceptionWhenSpaceshipIsNotFlying() {
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.PARKING); // Помечаем корабль как припаркованный
        assertThrows(IllegalArgumentException.class, () -> spaceJourney.hardAcceleration(spaceship)); // Попытка выполнения жесткого ускорения корабля, который не находится в стадии полета
    }
}
