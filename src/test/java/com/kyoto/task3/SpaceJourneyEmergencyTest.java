package com.kyoto.task3;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Condition;
import com.kyoto.task3.domain.spaceship_parts.AirCushion;
import com.kyoto.task3.domain.spaceship_parts.NavigationPanel;
import com.kyoto.task3.infra.impl.SpaceJourneyImpl;
import com.kyoto.task3.infra.impl.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpaceJourneyEmergencyTest {

    private SpaceJourneyImpl spaceJourney;
    private Spaceship spaceship;

    @BeforeEach
    void setUp() {
        spaceJourney = new SpaceJourneyImpl();
        spaceship = Generator.generateDefaultSpaceship();
        spaceJourney.getJourneys().put(spaceship, Generator.generateRandomPassengers(3)); // Добавляем корабль с пассажирами в путешествие
        spaceJourney.getStagesOfSpaceships().put(spaceship, Stage.FLYING); // Помечаем корабль как находящийся в стадии полета
    }

    @Test
    void emergencySituation_ShouldSetPanicConditionForPassengers() {
        spaceJourney.emergencySituation(spaceship); // Возникла аварийная ситуация
        List<Person> passengers = spaceJourney.getJourneys().get(spaceship);
        for (Person person : passengers) {
            assertEquals(Condition.PANIC, person.getCondition()); // Проверяем, что состояние пассажиров изменено на PANIC
        }
    }

    @Test
    void emergencySituation_ShouldChangeSpaceshipStageToAccelerating() {
        spaceJourney.emergencySituation(spaceship); // Возникла аварийная ситуация
        assertEquals(Stage.ACCELERATING, spaceJourney.getStagesOfSpaceships().get(spaceship)); // Проверяем, что стадия корабля изменена на ACCELERATING
    }

    @Test
    void emergencySituation_ShouldActivateAirCushions() {
        spaceJourney.emergencySituation(spaceship); // Возникла аварийная ситуация
        List<AirCushion> airCushions = spaceship.getAirCushions();
        for (AirCushion airCushion : airCushions) {
            assertTrue(airCushion.isActive()); // Проверяем, что все воздушные подушки активированы
        }
    }

    @Test
    void emergencySituation_ShouldSetRandomColorForLamps() {
        List<NavigationPanel.Lamp> lamps = spaceship.getNavigationPanel().getLamps();
        for (NavigationPanel.Lamp lamp:
             lamps) {
            lamp.offLamp();
        }       // Предварительно выключаем все лампочки на приборной панели

        spaceJourney.emergencySituation(spaceship); // Возникла аварийная ситуация
        boolean hasColor = false;
        for (NavigationPanel.Lamp lamp : lamps) {
            if (lamp.getColor() != null) {
                hasColor = true;
                break;
            }
        }
        assertTrue(hasColor); // Проверяем, что хотя бы одна лампа имеет цвет (так как цвет выбирается случайным образом)
    }
}
