package com.kyoto.task3.domain.spaceship_parts;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
public class AirCushion {
    private final double size;
    private boolean isActive;
}
