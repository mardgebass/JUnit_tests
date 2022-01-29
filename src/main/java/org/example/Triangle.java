package org.example;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Triangle {
    private int a;
    private int b;
    private int c;

    public boolean isValid(){
        int maxSide = Math.max(a, Math.max(b,c));
        return maxSide < (a + b + c - maxSide);
    }

    public boolean overZero(){
        return a > 0 && b > 0 && c > 0;
    }

    public double areaTriangle(){
        if (!overZero()){
            throw new IllegalArgumentException("Side(s) is(are) negative");
        }
        if (!isValid()){
            throw new IllegalArgumentException("Invalid triangle");
        }
        return Math.sqrt (((a + b + c) / 2) * (((a + b + c) / 2) - a) * (((a + b + c) / 2) - b) * (((a + b + c) / 2) - c));
    }

}
