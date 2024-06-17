package at.czlabinger;

import java.util.List;
import java.util.Random;

/**
 * Class that Simulates a runner
 * @author  Zlabinger Christof
 * @version 1.1
 */
public class Runner implements Runnable {
 

  private final String name;
  private final List<Integer> calculations;
  private final Communication c;

  /**
   * Constructor for a Runner
   * @param name the name of the Runner
   * @param calculations the calculation the Runner should do every round
   * @param c the Communication object used for synchronisation
   */
  public Runner(String name, List<Integer> calculations, Communication c) {
    this.name = name;
    this.calculations = calculations;
    this.c = c;
  }


  /**
   * Starts to run for three rounds. Every round either the fibonachi or a matrix multiplication is executed to simulate the lap times
   * The time of each lap is reported back to the main thread via the Communication object
   */
  @Override
  public void run() {
    System.out.println(name + " started at:" + System.currentTimeMillis());
    try {
        c.readyUp();
        for(int i = 0; i < 2; i++) {
          long t0 = System.currentTimeMillis();
          calculate(calculations.get(i));
          long t1 = System.currentTimeMillis();
          c.put(this.name + " finished lap " + (i + 1) + " in: " + (t1-t0) + "ms");
        }
        long t0 = System.currentTimeMillis();
        calculate(calculations.get(2));
        long t1 = System.currentTimeMillis();
        c.put(this.name + " finished lap 3 in: " + (t1-t0) + "ms. In position: ");
    } catch (InterruptedException e) {
      System.err.println("Thread: " + this.name + " was interrupted!");
    }
  }

  /**
   * Executes the specific calculation provided by the param with a random value
   * @param calculation the calculation that should be executed
   */
  private void calculate(int calculation) {
    if(calculation == 0) {
      calculateFibonatchi(new Random().nextInt((45 - 30) + 1) + 30);
    } else if(calculation == 1) {
      int[][] matrix1 = generateRandomMatrix(100, 100);
      int[][] matrix2 = generateRandomMatrix(100, 100);
      calculateMatrixMultiply(matrix1, matrix2);
    }
  }

  /**
   * Calculates the nth fibonachi number recursively
   * @param n the number to calculate the fibonachi number
   * @return the nth fibonachi number
   */
  private double calculateFibonatchi(int n) {
    if (n==1||n==2) return 1;
    else return calculateFibonatchi(n-1)+calculateFibonatchi(n-2);
  }


  /**
   * Multiplies two matrices
   *
   * @param matrix1 the first matrix to multiply
   * @param matrix2 the second matrix to multiply
   */
  private void calculateMatrixMultiply(int[][] matrix1, int[][] matrix2) {
    int rows1 = matrix1.length;
    int cols1 = matrix1[0].length;
    int rows2 = matrix2.length;
    int cols2 = matrix2[0].length;

    // Check if the matrices can be multiplied
    if (cols1 != rows2) {
      throw new IllegalArgumentException("Number of columns in the first matrix must equal the number of rows in the second matrix");
    }

    int[][] result = new int[rows1][cols2];

    for (int i = 0; i < rows1; i++) { // Loop through each row of the first matrix
      for (int j = 0; j < cols2; j++) { // Loop through each column of the second matrix
        for (int k = 0; k < cols1; k++) { // Loop through each element of the common dimension
          result[i][j] += matrix1[i][k] * matrix2[k][j];
        }
      }
    }
  }

  /**
   * Helper method to generate a random matrix
   * @param rows the number of rows the matrix should have
   * @param cols the number of columns the matrix should have
   * @return the matrix with rows x cols with random numbers between 0 and 99
   */
  int[][] generateRandomMatrix(int rows, int cols) {
    Random random = new Random();
    int[][] matrix = new int[rows][cols];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        matrix[i][j] = random.nextInt(100);
      }
    }

    return matrix;
  }
}
