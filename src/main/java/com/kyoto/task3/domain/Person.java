package com.kyoto.task3.domain;

import com.kyoto.task3.domain.enums.Condition;
import com.kyoto.task3.domain.enums.Gender;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

import static com.kyoto.task3.domain.enums.Condition.NORMAL;

@RequiredArgsConstructor
@Getter
@Setter
@ToString
public class Person {
    private final String name;
    private final int age;
    private final Gender gender;
    private final List<String> wears;
    private Condition condition = NORMAL;
}
