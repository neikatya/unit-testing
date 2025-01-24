package com.kyoto.task3.domain;

import com.kyoto.task3.domain.spaceship_parts.*;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Spaceship {
    private NavigationPanel navigationPanel;
    private Engine engine;
    private List<AirCushion> airCushions;
    private List<Wall> walls;
    private List<Window> windows;
    private List<Seat> seats;

}
