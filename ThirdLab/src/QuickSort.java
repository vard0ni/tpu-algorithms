public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {25, 3, 61, 31, 9, 45, 12, 76, 1, 21, 9, 6, 13, 41, 11}; // Исходный массив

        int n = arr.length;
        System.out.println();
        System.out.println("Исходный массив:");
        for (int i = 0; i < n; i++)
        {
            System.out.print(arr[i] + " ");
        }
        System.out.println();

        // Быстрая сортировка
        quickSort(arr, 0, arr.length - 1);

        System.out.println("Полученный массив:");
        // Вывод отсортированного массива
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }        
    }

    public static void quickSort(int[] arr, int low, int high) {
        if(low < high) {
            int pivotIndex = partition(arr, low, high); // Индекс опорного элемента
            quickSort(arr, low, pivotIndex - 1); // Рекурсивный вызов для левой части массива
            quickSort(arr, pivotIndex + 1, high); // Рекурсивный вызов для правой части массива
        }
    }

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[high]; // Опорный элемент
        int i = low - 1; // Индекс меньшего элемента

        for(int j = low; j < high; j++) {
            if(arr[j] < pivot) {
                i++;

                // swap элементов
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }

        // swap элементов
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;

        return i + 1; // Возвращает индекс опорного элемента
    }
}