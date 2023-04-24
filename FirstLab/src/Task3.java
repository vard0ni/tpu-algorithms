import java.util.*;
import javafx.util.Pair;

public class Task3 {

    public int n;
    public int[] seq = new int[n];

    public Task3(int n) {
        this.n = n;
    }

    public int getSizeSeq(int n) {
        return n;
    }

    // Стратегия формирования исходных данных а) случайным образом
    public static Pair<int[], Long> generateRandomSeq(int n) {
        long count = 0;
        int[] seq = new int[n];
        Random random = new Random();
        count+=2;
        
        for (int i = 0; i < n; i++) {
            count+=2;
            seq[i] = random.nextInt(9) + 1; // случайное число от 1 до 9
            count+=2;
        }
        return new Pair<int[], Long>(seq, count);
    }

    // можно использовать заранее подготовленные значения и сохранить их в массив
    public static Pair<int[], Long> generateOptimizedSeq(int n) {
        long count = 0;
        int[] seq = new int[n];
        count++;

        for (int i = 0; i < n; i++) {
            count+=2;
            seq[i] = i * i + 1;
            count+=2;
        }
        return new Pair<int[], Long>(seq, count);
    }

    // можно использовать выражения, которые будут приводить к повторному
    // вычислению значений в цикле. Этот подход увеличит число вычислений в алгоритме,
    // но может привести к увеличению времени выполнения.
    public static Pair<int[], Long> generateMaximizedSeq(int n) {
        long count = 0;
        int[] seq = new int[n];
        count++;

        for (int i = 0; i < n; i++) {
            count+=2;
            seq[i] = (i + 1) * (i + 2) / 2;
            count+=2;
        }
        return new Pair<int[], Long>(seq, count);
    }

    public static ArrayList<Long> getData(int[] seq, int n) {
        ArrayList<Long> result = new ArrayList<>();
        long timeBegin = System.currentTimeMillis();
        long count = 0;

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            count+=2;
            if (seq[i] > seq[maxIndex]) {
                maxIndex = i;
                count+=3;
            }
        }

        int sum1 = 0;
        for (int i = 0; i < maxIndex; i++) {
            count+=2;
            sum1 += seq[i];
            count+=2;
        }

        int sum2 = 0;
        for (int i = maxIndex + 1; i < n; i++) {
            count+=2;
            sum2 += seq[i];
            count+=2;
        }
        long timeEnd = System.currentTimeMillis() - timeBegin; 
        count+=2;   

        result.add(count);
        result.add(timeEnd);

        return result;
    }

    public static void main(String[] args) {

        long timeBegin = System.currentTimeMillis();
        int count = 0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите количество чисел в последовательности: ");
        int n = scanner.nextInt();

        // размерность исходных данных
        long size = n;

        int[] sequence = new int[n];
        System.out.print("Введите последовательность чисел: ");
        for (int i = 0; i < n; i++) {
            sequence[i] = scanner.nextInt();
            count++;
        }

        int maxIndex = 0;
        for (int i = 1; i < n; i++) {
            if (sequence[i] > sequence[maxIndex]) {
                maxIndex = i;
                count++;
            }
        }

        int sum1 = 0;
        for (int i = 0; i < maxIndex; i++) {
            sum1 += sequence[i];
            count++;
        }

        int sum2 = 0;
        for (int i = maxIndex + 1; i < n; i++) {
            sum2 += sequence[i];
            count++;
        }

        System.out.println("Сумма элементов до максимального элемента: " + sum1);
        System.out.println("Сумма элементов после максимального элемента: " + sum2);
        System.out.println("Num of operations: " + count);
        long timeEnd = System.currentTimeMillis() - timeBegin;
        System.out.println("Time: " + timeEnd + " ms");
    }
}