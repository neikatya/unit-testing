package com.kyoto.task3.domain.spaceship_parts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Setter
@Getter
public class Engine {
    private final int powerCapacity;
    private int currentPower;

}
