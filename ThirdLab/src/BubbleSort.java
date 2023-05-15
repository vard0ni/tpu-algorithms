public class BubbleSort {
    public static void main(String[] args) {
        int[] arr = {5, 2, 82, 1, 9, 4, 11, 6, 44, 22, 3, 5, 17, 101, 16}; // Исходный массив
        int n = arr.length;
        int temp = 0;
        System.out.println();
        System.out.println("Исходный массив:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        // Пузырьковая сортировка
        for(int i = 0; i < n; i++) {
            for(int j = 1; j < (n - i); j++) {
                if(arr[j - 1] > arr[j]) {
                    // swap элементов
                    temp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        System.out.println("Полученный массив:");
        // Вывод отсортированного массива
        for(int i = 0; i < n; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}