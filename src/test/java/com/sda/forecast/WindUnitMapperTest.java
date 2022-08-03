package com.sda.forecast;

import net.bytebuddy.implementation.bytecode.Throw;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class WindUnitMapperTest {
    WindUnitMapper windUnitMapper = new WindUnitMapper();

    @Test
    void convertWindDegreeToDirection() {
        //given
        //when
        //then

        assertThat(windUnitMapper.mapWindDegreeToDirection(0)).isEqualTo("N");
        assertThat(windUnitMapper.mapWindDegreeToDirection(11)).isEqualTo("N");
        assertThat(windUnitMapper.mapWindDegreeToDirection(349)).isEqualTo("N");
        assertThat(windUnitMapper.mapWindDegreeToDirection(360)).isEqualTo("N");

        assertThat(windUnitMapper.mapWindDegreeToDirection(12)).isEqualTo("NNE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(33)).isEqualTo("NNE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(34)).isEqualTo("NE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(56)).isEqualTo("NE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(57)).isEqualTo("ENE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(78)).isEqualTo("ENE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(79)).isEqualTo("E");
        assertThat(windUnitMapper.mapWindDegreeToDirection(101)).isEqualTo("E");

        assertThat(windUnitMapper.mapWindDegreeToDirection(102)).isEqualTo("ESE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(123)).isEqualTo("ESE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(124)).isEqualTo("SE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(146)).isEqualTo("SE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(147)).isEqualTo("SSE");
        assertThat(windUnitMapper.mapWindDegreeToDirection(168)).isEqualTo("SSE");

        assertThat(windUnitMapper.mapWindDegreeToDirection(169)).isEqualTo("S");
        assertThat(windUnitMapper.mapWindDegreeToDirection(191)).isEqualTo("S");

        assertThat(windUnitMapper.mapWindDegreeToDirection(192)).isEqualTo("SSW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(213)).isEqualTo("SSW");

        assertThat(windUnitMapper.mapWindDegreeToDirection(214)).isEqualTo("SW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(236)).isEqualTo("SW");

        assertThat(windUnitMapper.mapWindDegreeToDirection(237)).isEqualTo("WSW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(258)).isEqualTo("WSW");

        assertThat(windUnitMapper.mapWindDegreeToDirection(259)).isEqualTo("W");
        assertThat(windUnitMapper.mapWindDegreeToDirection(281)).isEqualTo("W");

        assertThat(windUnitMapper.mapWindDegreeToDirection(282)).isEqualTo("WNW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(303)).isEqualTo("WNW");

        assertThat(windUnitMapper.mapWindDegreeToDirection(304)).isEqualTo("NW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(326)).isEqualTo("NW");

        assertThat(windUnitMapper.mapWindDegreeToDirection(327)).isEqualTo("NNW");
        assertThat(windUnitMapper.mapWindDegreeToDirection(348)).isEqualTo("NNW");

    }

    @Test
    void mapWindDegreeToDirection_whenDegreeIsOutOfRange_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> windUnitMapper.mapWindDegreeToDirection(-1));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Kierunek wiatru musi znajdować się w przedziale <0, 360>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

    void mapWindDegreeToDirection_whenDegreeIsUnderOfRange_throwsException() {
        //given
        Throwable throwable;
        //when
        throwable = catchThrowable(() -> windUnitMapper.mapWindDegreeToDirection(361));
        //then
        assertThat(throwable).isNotNull();
        assertThat(throwable.getMessage()).isEqualTo("Kierunek wiatru musi znajdować się w przedziale <0, 360>");
        assertThat(throwable).isExactlyInstanceOf(IllegalArgumentException.class);
    }

}