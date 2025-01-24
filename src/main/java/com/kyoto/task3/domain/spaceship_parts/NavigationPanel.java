package com.kyoto.task3.domain.spaceship_parts;

import com.kyoto.task3.domain.enums.Color;
import com.kyoto.task3.domain.enums.State;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.kyoto.task3.domain.enums.State.OFF;
import static com.kyoto.task3.domain.enums.State.ON;

@Getter
public class NavigationPanel {
    private final List<ToggleSwitch> toggleSwitches;
    private final List<Lamp> lamps;

    public NavigationPanel(int switchesCount, int lampsCount){
        List<ToggleSwitch> switches = new ArrayList<>();
        for (int i=0; i<switchesCount; i++) {
            switches.add(new ToggleSwitch());
        }
        List<Lamp> lamps = new ArrayList<>();
        for (int i=0; i<lampsCount; i++) {
            lamps.add(new Lamp());
        }
        this.lamps = lamps;
        this.toggleSwitches=switches;
    }

    public State getToggleSwitchState(int index) {
        if (index >= 0 && index < toggleSwitches.size()) {
            return toggleSwitches.get(index).getState();
        } else {
            throw new IndexOutOfBoundsException("Invalid toggle switch index");
        }
    }

    public Optional<Color> getLampColor(int index) {
        if (index >= 0 && index < lamps.size()) {
            return Optional.of(lamps.get(index).getColor());
        } else {
            throw new IndexOutOfBoundsException("Invalid lamp index");
        }
    }

    @Getter
    @Setter
    public
    class ToggleSwitch {
        private State state = OFF;
    }

    @Getter
    public
    class Lamp {
        private State state = OFF;
        private Color color;

        public void setColor(Color color) {
            this.state = ON;
            this.color = color;
        }

        public void offLamp() {
            state = OFF;
            color = null;
        }

    }
}
