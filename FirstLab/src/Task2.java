import java.util.*;
import javafx.util.Pair;

public class Task2 {

    public int n, m;
    public int[][] matrix = new int[n][m];

    public Task2(int n, int m) {
        this.n = n;
        this.m = m;
    }

    public long getSizeMatrix(int n, int m) {
        return n * m;
    }

    public long getSizeVector(int n) {
        return n;
    }

    // Стратегия формирования исходных данных а) случайным образом
    public static Pair<int[][], Long> generateRandomMatrix(int n, int m) {
        long count = 0;
        int[][] A = new int[n][m];
        Random random = new Random();
        count+=2;
        
        for (int i = 0; i < n; i++) {
            count+=2;
            for (int j = 0; j < m; j++) {
                count+=2;
                A[i][j] = random.nextInt(9) + 1; // случайное число от 1 до 9
                count+=2;
            }
        }
        return new Pair<int[][], Long>(A, count);
    }

    public static Pair<int[], Long> generateRandomVector(int n) {
        long count = 0;
        int[] E = new int[n];
        Random random = new Random();
        count+=2;

        for (int i = 0; i < n; i++) {
            count+=2;
            E[i] = random.nextInt(9) + 1; // случайное число от 1 до 9
            count+=2;
        
        }
        return new Pair<int[], Long>(E, count);
    }

    // можно использовать подход с предварительным вычислением значений и
    // сохранением их во временных переменных или массивах. Этот подход уменьшит
    // число вычислений в алгоритме, но может привести к увеличению затрат на память.
    public static Pair<int[][], Long> generateOptimizedMatrix(int n, int m) {
        long count = 0;
        int[][] A = new int[n][m];
        // создаем временный массив, в котором будут сохранены предварительно вычисленные значения
        int[] temp = new int[n + m];
        count++;
        
        // заполняем временный массив предварительно вычисленными значениями
        for (int i = 0; i < n + m; i++) {
            temp[i] = (i + 1) * (i + 2) / 2;
        }

        // заполняем матрицу значениями из временного массива
        for (int i = 0; i < n; i++) {
            count+=2;
            for (int j = 0; j < m; j++) {
                count+=2;
                A[i][j] = temp[i + j];
                count+=2;
            }
        }
        return new Pair<int[][], Long>(A, count);
    }

    public static Pair<int[], Long> generateOptimizedVector(int n) {
        long count = 0;
        int[] E = new int[n];
        count++;

        for (int i = 0; i < n; i++) {
            count+=2;
            E[i] = i + 1;
            count+=2;
        }
        return new Pair<int[], Long>(E, count);
    }

    // можно использовать выражения, которые будут приводить к повторному
    // вычислению значений в цикле Этот подход увеличит число вычислений в
    // алгоритме, но может привести к увеличению времени выполнения
    public static Pair<int[][], Long> generateMaximizedMatrix(int n, int m) {
        long count = 0;
        int[][] A = new int[n][m];
        count++;

        for (int i = 0; i < n; i++) {
            count+=2;
            for (int j = 0; j < m; j++) {
                count+=2;
                A[i][j] = (i + j + 1) * (i + j + 2) / 2; 
                count+=3;
            }
        }
        return new Pair<int[][], Long>(A, count);
    }

    public static Pair<int[], Long> generateMaximizedVector(int n) {
        long count = 0;
        int[] E = new int[n];
        count++;

        for (int i = 0; i < n; i++) {
            count+=2;
            E[i] = Integer.MAX_VALUE - i;
            count+=2;
        }
        return new Pair<int[], Long>(E, count);
    }

    public static ArrayList<Long> getData(int[][] A, int[] E,  int n, int m) {
        ArrayList<Long> result = new ArrayList<>();
        long timeBegin = System.currentTimeMillis();
        long count = 0;

        int maxRow = 0;
        int maxCol = 0;
        int maxValue = A[0][0];

        // находим местоположение максимального элемента
        for (int i = 0; i < A.length; i++) {
            count+=2;
            for (int j = 0; j < A[i].length; j++) {
                count+=2;
                if (A[i][j] > maxValue) {
                    maxValue = A[i][j];
                    maxRow = i;
                    maxCol = j;
                    count+=4;
                }
            }
        }

        // меняем элементы строки с максимальным элементом
        int[] temp = A[maxRow];
        A[maxRow] = E;
        E = temp;
        count+=3;

        long timeEnd = System.currentTimeMillis() - timeBegin;
        count++;

        result.add(count);
        result.add(timeEnd);

        return result;
    }

    public static void main(String[] args) {

        long timeBegin = System.currentTimeMillis();
        int count = 0;

        int m = 10;
        int n = 10;

        // размерность исходных данных
        long size = n * m;

        int[] E = new int[n];
        int[][] A = new int[m][n];

        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                A[i][j] = rand.nextInt(9) + 1; // случайное число от 1 до 9
                count++;
            }
        }
        System.out.println("Исходная матрица A: ");
        for (int[] row : A) {
            System.out.println(Arrays.toString(row));
            count++;
        }

        for (int i = 0; i < n; i++) {
            E[i] = rand.nextInt(10);
            count++;
        }
        System.out.println("Новый массив E: " + Arrays.toString(E));

        int maxRow = 0;
        int maxCol = 0;
        int maxValue = A[0][0];

        // находим местоположение максимального элемента
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                if (A[i][j] > maxValue) {
                    maxValue = A[i][j];
                    maxRow = i;
                    maxCol = j;
                    count++;
                }
            }
        }

        // меняем элементы строки с максимальным элементом
        int[] temp = A[maxRow];
        A[maxRow] = E;
        E = temp;

        // выводим результаты
        System.out.println("Максимальный элемент: " + maxValue);
        System.out.println("Местоположение: строка " + (maxRow + 1) + ", столбец " + (maxCol + 1));
        System.out.println("Новая матрица A: ");
        for (int[] row : A) {
            System.out.println(Arrays.toString(row));
        }
        System.out.println("Num of operations: " + count);
        long timeEnd = System.currentTimeMillis() - timeBegin;
        System.out.println("Time: " + timeEnd + " ms");
    }
}