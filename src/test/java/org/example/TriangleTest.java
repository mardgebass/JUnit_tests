package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Площади треугольников")
public class TriangleTest {

    private static final Logger logger = LoggerFactory.getLogger(TriangleTest.class);

//    @Test
//    @DisplayName("Обычный тест")
//    public void areaTriangleTest() {
//        Triangle triangle = new Triangle(8,8,8);
//        double area = triangle.areaTriangle();
//        assertEquals(27.712812921102035,area);
//    }

    public static Stream<Arguments> triangles(){
        return Stream.of(Arguments.of(new Triangle(8,8,8), 27.712812921102035),
                Arguments.of(new Triangle(9,5,6), 14.142135623730951),
                Arguments.of(new Triangle(10,8,8), 31.22498999199199)
        );
    }

    public static Stream<Arguments> wrongTriangles(){
        return Stream.of(Arguments.of(new Triangle(1,66666661,10)),
                Arguments.of(new Triangle(1,1000,100)),
                Arguments.of(new Triangle(3,9000,1))
        );
    }

    public static Stream<Arguments> negativeTriangles(){
        return Stream.of(Arguments.of(new Triangle(-1,1,10)),
                Arguments.of(new Triangle(1,-1000,100)),
                Arguments.of(new Triangle(3,-9000,1))
        );
    }

    @DisplayName("Позитивные проверки")
    @ParameterizedTest(name = "{0} area = {1}")
    @MethodSource("triangles")
    public void areaTriangleParamTest(Triangle triangle, double expectedArea) {
        logger.info("Позитивные проверки");
        double area = triangle.areaTriangle();
        assertEquals(expectedArea, area);
    }

    @DisplayName("Неправильные треугольники")
    @ParameterizedTest(name = "{0}")
    @MethodSource("wrongTriangles")
    public void areaWrongTriangleTest(Triangle triangle) {
        logger.info("Неправильные треугольники");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::areaTriangle);
        assertEquals("Invalid triangle", illegalArgumentException.getMessage());
    }

    @DisplayName("Отрицательные стороны")
    @ParameterizedTest(name = "{0}")
    @MethodSource("negativeTriangles")
    public void areaNegativeTriangleTest(Triangle triangle) {
        logger.info("Отрицательные стороны");
        IllegalArgumentException illegalArgumentException = assertThrows(IllegalArgumentException.class, triangle::areaTriangle);
        assertEquals("Side(s) is(are) negative", illegalArgumentException.getMessage());
    }

}
