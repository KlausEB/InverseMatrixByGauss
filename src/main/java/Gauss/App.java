package Gauss;

import java.util.Arrays;

public class App 
{
    public static void main( String[] args )
    {
        double[][] matrix = {{  3,   4, -9,   5},
                             {-15, -12, 50, -16},
                             {-27, -36, 73,   8},
                             {  9,  12,-10, -16}};

        InverseMatrix.getInverseMatrix(matrix);
    }
}
