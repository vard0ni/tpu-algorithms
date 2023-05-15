public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {15, 76, 2, 11, 32, 4, 1, 6, 44, 22, 34, 5, 17, 10, 1}; // Исходный массив

        int n = arr.length;
        System.out.println();
        System.out.println("Исходный массив:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Сортировка методом Шелла
        shellSort(arr);

        System.out.println("Полученный массив:");
        // Вывод отсортированного массива
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }

    public static void shellSort(int[] arr) {
        int gap = arr.length / 2; // Начальное значение шага

        while(gap > 0) {
            for(int i = gap; i < arr.length; i++) {
                int j = i;
                int temp = arr[i];

                // Сдвиг элементов на шаг gap
                while(j >= gap && arr[j - gap] > temp) {
                    arr[j] = arr[j - gap];
                    j -= gap;
                }
                arr[j] = temp;
            }
            gap /= 2; // Уменьшение шага
        }
    }
}
