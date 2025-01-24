package com.kyoto.task1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static com.kyoto.task1.ArcsinExpander.arccosTaylorSeries;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class Task1Test {

    @ParameterizedTest
    @ValueSource(doubles = {-1.0, -0.9, -0.5, 0, 0.5, 0.707, 1.0})
    void testArccosTaylorSeries(double d) {
        assertEquals(arccosTaylorSeries(d, 50), Math.acos(d), 0.1);
    }

    @Test
    void testArccosTaylorSeriesInvalidInput() {
        assertThrows(IllegalArgumentException.class, () -> {
            arccosTaylorSeries(1.5, 20);
        });
        assertThrows(IllegalArgumentException.class, () -> {
            arccosTaylorSeries(-1.5, 20);
        });
    }
}