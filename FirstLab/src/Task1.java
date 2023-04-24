import java.io.*;
import java.util.*;
import javafx.util.Pair;

public class Task1 {
   public int n;
   public int[][] matrix = new int[n][n];

   public Task1(int n) {
      this.n = n;
   }

   public long getSize(int n) {
      return n * n;
   }

   // Стратегия формирования исходных данных а) случайным образом
   public static Pair<int[][], Long> generateRandomMatrix(int n) {
      long count = 0;
      int[][] matrix = new int[n][n];
      Random random = new Random();   

      for (int i = 0; i < n; i++) {
         count+=2;
         for (int j = 0; j < n; j++) {
            count+=2;
            matrix[i][j] = random.nextInt(9) + 1; // случайное число от 1 до 9
            count+=2;
         }
      }
      return new Pair<int[][], Long>(matrix, count);
   }

   // можно использовать подход с заполнением матрицы заранее вычисленными
   // значениями. Этот подход уменьшит число вычислений в алгоритме, но может
   // привести к увеличению затрат на память.
   public static Pair<int[][], Long> generateOptimizedMatrix(int n) {
      long count = 0;
      int[][] matrix = new int[n][n];


      for (int i = 0; i < n; i++) {
         count+=2;
         for (int j = 0; j < n; j++) {
            count+=2;
            matrix[i][j] = i * n + j + 1;
            count+=2;
         }
      }
      return new Pair<int[][], Long>(matrix, count);
   }

   // можно использовать подход с генерацией случайных значений. 
   // Этот подход позволяет получить матрицу, в которой значения элементов
   // будут различными и случайными, что максимизирует число вычислений в алгоритме.
   public static Pair<int[][], Long> generateMaximizedMatrix(int n) {
      long count = 0;
      int[][] matrix = new int[n][n];

      for (int i = 0; i < n; i++) {
         count+=2;
         for (int j = 0; j < n; j++) {
            count+=2;
            matrix[i][j] = i + j;
            count+=4;
         }
      }
      return new Pair<int[][], Long>(matrix, count);
   }

   public static ArrayList<Long> getData(int[][] matrix, int n) {
      long count = 0;
      ArrayList<Long> result = new ArrayList<>();
      long timeBegin = System.currentTimeMillis();
      count += 2;

      // Находим максимальный отрицательный и минимальный положительный элементы на
      // побочной диагонали
      int maxNeg = Integer.MIN_VALUE;
      int minPos = Integer.MAX_VALUE;
      count += 2;
      for (int i = 0; i < n; i++) {
         count+=2;
         int j = n - 1 - i; // индекс элемента на побочной диагонали
         count += 2;
         if (matrix[i][j] < 0 && matrix[i][j] > maxNeg) {
            maxNeg = matrix[i][j];
            count+=4;
         }
         if (matrix[i][j] > 0 && matrix[i][j] < minPos) {
            minPos = matrix[i][j];
            count+=4;
         }
      }

      // Находим среднее произведение и среднеарифметическую сумму положительных
      // элементов на главной диагонали
      int sum = 0;
      int prodCount = 0;
      count += 2;
      for (int i = 0; i < n; i++) {
         count += 2;
         if (matrix[i][i] > 0) {
            sum += matrix[i][i];
            prodCount++;
            count += 3;
         }
      }
      double meanSum = (double) sum / prodCount;
      count += 2;

      int prod = 1;
      int posCount = 0;
      count += 2;
      for (int i = 0; i < n; i++) {
         count+=2;
         if (matrix[i][i] > 0) {
            prod *= matrix[i][i];
            posCount++;
            count+=3;
         }
      }
      double meanProd = (double) prod / posCount;
      count += 2;

      long timeEnd = System.currentTimeMillis() - timeBegin;
      count += 2;
      
      result.add(count);
      result.add(timeEnd);
      return result;
   }

   public static void main(String[] args) {

      long timeBegin = System.currentTimeMillis();
      int count = 0;

      int n = 1000; // размерность матрицы

      // размерность исходных данных
      long size = n * n;

      // Создаем матрицу и заполняем случайными числами
      int[][] matrix = new int[n][n];

      Random rand = new Random();
      for (int i = 0; i < n; i++) {
         for (int j = 0; j < n; j++) {
            matrix[i][j] = rand.nextInt(9) + 1; // случайное число от 1 до 9
            count++;
         }
      }
       
      // Находим максимальный отрицательный и минимальный положительный элементы на
      // побочной диагонали
      int maxNeg = Integer.MIN_VALUE;
      int minPos = Integer.MAX_VALUE;
      for (int i = 0; i < n; i++) {
         int j = n - 1 - i; // индекс элемента на побочной диагонали
         if (matrix[i][j] < 0 && matrix[i][j] > maxNeg) {
            maxNeg = matrix[i][j];
            count++;
         }
         if (matrix[i][j] > 0 && matrix[i][j] < minPos) {
            minPos = matrix[i][j];
            count++;
         }
      }

      // Находим среднее произведение и среднеарифметическую сумму положительных
      // элементов на главной диагонали
      int sum = 0;
      int prodCount = 0;
      for (int i = 0; i < n; i++) {
         if (matrix[i][i] > 0) {
            sum += matrix[i][i];
            prodCount++;
            count++;
         }
      }
      double meanSum = (double) sum / prodCount;
      count += 2;

      int prod = 1;
      int posCount = 0;
      for (int i = 0; i < n; i++) {
         if (matrix[i][i] > 0) {
            prod *= matrix[i][i];
            count++;
            posCount++;
         }
      }
      double meanProd = (double) prod / posCount;
      count += 2;

      
      // print matrix
      for (int i = 0; i < matrix.length; i++) {
         for (int j = 0; j < matrix[i].length; j++) {
            System.out.print(matrix[i][j] + " ");
            count++;
         }
         System.out.println();
      }

      System.out.println("maxNeg: " + maxNeg);
      System.out.println("minPos: " + minPos);
      System.out.println("meanSum: " + meanSum);
      System.out.println("meanProd: " + meanProd);
      
      System.out.println("Num of operations: " + count);
      long timeEnd = System.currentTimeMillis() - timeBegin;
      System.out.println("Time: " + timeEnd + " ms");
   }
}