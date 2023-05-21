import java.util.*;
import javafx.util.Pair;

public class QuickLineUnorder {
    
    public int n;
    public long[] arr = new long[n];

    public QuickLineUnorder(int n) {
        this.n = n;
    }

    public long getSize(int n) {
        return n;
    }

    public static Pair<int[], Long> generateRandomArray(int n) {
        long count = 0;
        int[] arr = new int[n];
        Random rand = new Random();
        for (int i = 0; i < n; i++) {
            arr[i] = rand.nextInt(9) + 1;
        }
        return new Pair<int[], Long>(arr, count);
    }

    // Любой алгоритм поиска содержит блок проверки на окончание массива. В алгоритме линейного поиска
    // эта проверка осуществляется каждый раз перед обращением очередному элементу. Однако проверка на окончание
    // массива может осуществляться не при каждом сравнении. Для этого в конец массива включается (N+1)-й элемент, равный искомому. 
    // Тогда проверка на окончание массива осуществляется лишь при совпадении очередного элемента с искомым. Если этот элемент находится
    // внутри массива, то поиск заканчивается удачно и элемент считается найденным. Если же этот элемент оказался (N+1)-ым, то искомого элемента в массиве нет.
    public static int quickLinearSearch(int[] arr, int x) {
        int n = arr.length;
        int last = arr[n - 1];
        arr[n - 1] = x;
        int i = 0;
        while (arr[i] != x) {
            i++;
        }
        arr[n - 1] = last;
        if (i < n - 1 || arr[n - 1] == x) {
            return i;
        } else {
            return -1;
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {2, 5, 1, 8, 3, 7, 4, 6};
        int x = 8;
        int index = quickLinearSearch(arr, x);
        if (index == -1) {
            System.out.println("Элемент " + x + " не найден в массиве.");
        } else {
            System.out.println("Элемент " + x + " найден в массиве на позиции " + index + ".");
        }
    }
}