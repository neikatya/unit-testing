package com.kyoto.task3;

import com.kyoto.task3.domain.Person;
import com.kyoto.task3.domain.Spaceship;
import com.kyoto.task3.domain.enums.Color;
import com.kyoto.task3.domain.enums.Gender;
import com.kyoto.task3.domain.spaceship_parts.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

final class Generator {
    static Spaceship generateDefaultSpaceship() {
        List<AirCushion> airCushions = new ArrayList<>();
        airCushions.add(new AirCushion(1.0));
        airCushions.add(new AirCushion(1.0));
        airCushions.add(new AirCushion(2.5));
        airCushions.add(new AirCushion(2.5));
        airCushions.add(new AirCushion(3.2));
        airCushions.add(new AirCushion(3.2));

        Engine engine = new Engine(100);

        NavigationPanel navigationPanel = new NavigationPanel(10, 30);

        List<Seat> seats = new ArrayList<>();
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.RED));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.BLUE));
        seats.add(new Seat(Color.GREEN));
        seats.add(new Seat(Color.GREEN));
        seats.add(new Seat(Color.GREEN));

        List<Wall> walls = new ArrayList<>();
        for (int i=0; i<20; i++) {
            walls.add(new Wall(Color.GRAY));
        }

        List<Window> windows = new ArrayList<>();
        for (int i=0; i<20; i++) {
            windows.add(new Window());
        }

        return Spaceship.builder().navigationPanel(navigationPanel).engine(engine).seats(seats).airCushions(airCushions).windows(windows).walls(walls).build();
    }

    static List<Person> generateRandomPassengers(int count) {
        List<Person> personList = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            personList.add(generateRandomPerson());
        }

        return personList;
    }

    private static final List<String> NAMES = Arrays.asList("Alice", "Bob", "Charlie", "David", "Emma", "Frank", "Grace", "Henry", "Ivy", "Jack", "Katherine", "Liam", "Mia", "Noah", "Olivia", "Peter", "Quinn", "Ryan", "Sophia", "Thomas", "Uma", "Victor", "Willow", "Xander", "Yara", "Zach");

    static Person generateRandomPerson() {
        Random random = new Random();

        String name = NAMES.get(random.nextInt(NAMES.size()));
        int age = random.nextInt(101); // возраст от 0 до 100
        Gender gender = random.nextBoolean() ? Gender.MALE : Gender.FEMALE; // случайный выбор пола
        List<String> wears = generateRandomWears(); // генерируем случайный список одежды

        return new Person(name, age, gender, wears);
    }

    private static List<String> generateRandomWears() {
        List<String> wears = new ArrayList<>();
        Random random = new Random();

        int wearCount = random.nextInt(6); // генерируем от 0 до 5 предметов одежды
        for (int i = 0; i < wearCount; i++) {
            wears.add("Wear " + (i + 1)); // добавляем случайный предмет одежды
        }

        return wears;
    }
}
