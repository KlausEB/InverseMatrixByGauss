package Gauss;

import java.util.Arrays;

public class InverseMatrix {

    private static double[][] matrix;
    private static double[][] resultMatrix;



    static double[][] getInverseMatrix(double[][] inputMatrix){
        matrixCheck(inputMatrix);
        matrix = inputMatrix;
        resultMatrix = buildIdentityMatrix(matrix.length);

        convertToIdentityMatrix();

        printMatrices();
        return resultMatrix;
    }


    private static void matrixCheck(double[][] matrix){
        int columnLength = matrix.length;
        for (double[] lineLength : matrix) {
            if(columnLength != lineLength.length){
                throw new IllegalArgumentException();
            }
        }
    }


    private static double[][] buildIdentityMatrix(int length){
        double[][] matrix = new double[length][length];
        for (int i = 0; i < length; i++) {
            matrix[i][i] = 1;
        }
        return matrix;
    }


    private static void convertToIdentityMatrix(){
        convertToDiagonalMatrix();

        for (int i = 0; i < matrix.length; i++) {
            double bufferMatrixElem = matrix[i][i];
            matrix[i][i] /= matrix[i][i];
            for (int j = 0; j < matrix.length; j++) {
                resultMatrix[i][j] /= bufferMatrixElem;
            }
        }
    }


    private static void convertToDiagonalMatrix(){
        convertToTriangleMatrix();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = i+1; j < matrix.length; j++) {
                matrixTransformationWithScalingFactor(matrix.length - j - 1, matrix.length - i - 1);
            }
        }
    }


    private static void convertToTriangleMatrix(){
        for (int i = 0; i < matrix.length; i++) {
            int lineWithMaxElement = maxElementInColumn(i);
            swapLines(i, lineWithMaxElement);
            for (int j = i+1; j < matrix.length; j++) {
                matrixTransformationWithScalingFactor(j, i);
            }
        }
    }


    private static int maxElementInColumn(int step){
        double maxElement = 0;
        int lineWithMaxElement = 0;
        for (int i = step; i < matrix.length; i++) {
            if(Math.abs(matrix[i][step]) > maxElement){
                maxElement = Math.abs(matrix[i][step]);
                lineWithMaxElement = i;
            }
        }
        return lineWithMaxElement;
    }

    private static void swapLines(int firstLine, int secondLine){
        double[] bufferForMatrix = matrix[firstLine];
        double[] bufferForResultMatrix = resultMatrix[firstLine];

        matrix[firstLine] = matrix[secondLine];
        resultMatrix[firstLine] = resultMatrix[secondLine];
        matrix[secondLine] = bufferForMatrix;
        resultMatrix[secondLine] = bufferForResultMatrix;
    }

    private static void matrixTransformationWithScalingFactor(int numberOfMinuendLine, int numberOfSubtrahendLine){
        double scalingFactor = matrix[numberOfMinuendLine][numberOfSubtrahendLine] / matrix[numberOfSubtrahendLine][numberOfSubtrahendLine];
        for (int i = 0; i < matrix.length; i++) {
            matrix[numberOfMinuendLine][i] = matrix[numberOfMinuendLine][i] - matrix[numberOfSubtrahendLine][i]*scalingFactor;
            resultMatrix[numberOfMinuendLine][i] = resultMatrix[numberOfMinuendLine][i] - resultMatrix[numberOfSubtrahendLine][i]*scalingFactor;
        }
    }

    private static void printMatrices(){
        for (double[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
        for (double[] line : resultMatrix) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println();
    }
}
