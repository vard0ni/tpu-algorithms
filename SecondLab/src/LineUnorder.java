import java.util.*;
import javafx.util.Pair;

public class LineUnorder {
    
    public int n;
    public int[] arr = new int[n];

    public LineUnorder(int n) {
        this.n = n;
    }

    public int getSize(int n) {
        return n;
    }

    public static Pair<int[], Integer> generateRandomArray(int n) {
        int count = 0;
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            count += 2;
            arr[i] = rand.nextInt(n) + 1;
            count += 2;
        }
        return new Pair<int[], Integer>(arr, count);
    }
    
    public static Pair<int[], Integer> generateWorstCaseArray(int n) {
        int count = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            count += 2;
            arr[i] = n - i;
            count += 2;
        }
        return new Pair<int[], Integer>(arr, count);
    }

    public static Pair<int[], Integer> generateBestCaseArray(int n) {
        int count = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            count += 2;
            arr[i] = i;
            count += 1;
        }
        return new Pair<int[], Integer>(arr, count);
    }
    

    // Работа алгоритма заключается в том, что элементы  массива, начиная с первого, последовательно сравнивается
    // с искомым элементом. Сравнение элементов продолжается до тех пор, пока не будут просмотрены все элементы
    // или очередной элемент массива не равен искомому.
    public static ArrayList<Integer> linearSearch(int[] arr, int n, int x) {
        int timeBegin = (int) System.currentTimeMillis();
        ArrayList<Integer> res = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < n; i++) {
            count+=2;
            if (arr[i] == x) {
                count+=2;
                res.add(arr[i]);
                res.add(i); // элемент найден, возвращаем его индекс
                break;
            }
        }
        int timeEnd = (int) System.currentTimeMillis() - timeBegin;

        res.add(count);
        res.add(timeEnd);
        return res;
    }

    public static void main(String[] args) {
        int n = 10;
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(9) + 1;
        }

        for (int i = 0; i < arr.length; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        int x = 7; // ищем число 7 в массиве
        System.out.println("Найти число: " + x);

        int index = 0;  // ищем индекс элемента
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == x) {
                index = i;
                break; 
            }
            else index = -1;
        }
        if (index == -1) {
            System.out.println("Элемент не найден в массиве");
        } else {
        System.out.println("Элемент найден в массиве на позиции: " + index);
        }
    }
}