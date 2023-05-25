import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.util.Pair;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class App extends JFrame {

    public static ChartPanel CountChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График зависимости размерности данных от количества вычислений", // заголовок графика
                "Размерность данных", // подпись оси X
                "Количество вычислений", // подпись оси Y
                dataset, // набор данных
                PlotOrientation.VERTICAL, // ориентация графика
                true, // показывать ли легенду
                true, // использовать ли tooltips
                false // использовать ли urls
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        // chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;
    }

    public static ChartPanel TimeChart(XYSeriesCollection dataset) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График зависимости размерности данных от времени(мс)", // заголовок графика
                "Размерность данных", // подпись оси X
                "Время(мс)", // подпись оси Y
                dataset, // набор данных
                PlotOrientation.VERTICAL, // ориентация графика
                true, // показывать ли легенду
                true, // использовать ли tooltips
                false // использовать ли urls
        );

        XYPlot plot = chart.getXYPlot();
        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE);
        renderer.setSeriesStroke(0, new BasicStroke(2.0f));
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        // chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;

    }

    public static ChartPanel CountChartMany(XYSeriesCollection datasetS, XYSeriesCollection datasetQ, XYSeriesCollection datasetB) {

        JFreeChart chart = ChartFactory.createXYLineChart(
                "График зависимости размерности данных от количества вычислений", // заголовок графика
                "Размерность данных",    // подпись оси X
                "Количество вычислений", // подпись оси Y
                null, // набор данных
                PlotOrientation.VERTICAL, // ориентация графика
                true, // показывать ли легенду
                true, // использовать ли tooltips
                false // использовать ли urls
        );

        chart.getXYPlot().setDataset(0, datasetS);
        chart.getXYPlot().setRenderer(0, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        //chart.getXYPlot().getRenderer(0).setSeriesShapesVisible(0, false);


        chart.getXYPlot().setDataset(1, datasetQ);
        chart.getXYPlot().setRenderer(1, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(1).setSeriesPaint(1, Color.MAGENTA);
        //chart.getXYPlot().getRenderer(1).setSeriesShapesVisible(1, false);

        chart.getXYPlot().setDataset(2, datasetB);
        chart.getXYPlot().setRenderer(2, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(2).setSeriesPaint(2, Color.BLACK);
        //chart.getXYPlot().getRenderer(2).setSeriesShapesVisible(2, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        // chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;
    }

    public static ChartPanel TimeChartMany(XYSeriesCollection datasetS, XYSeriesCollection datasetQ, XYSeriesCollection datasetB) {
        JFreeChart chart = ChartFactory.createXYLineChart(
                "График зависимости размерности данных от времени(мс)", // заголовок графика
                "Размерность данных",    // подпись оси X
                "Время(мс)", // подпись оси Y
                null, // набор данных
                PlotOrientation.VERTICAL, // ориентация графика
                true, // показывать ли легенду
                true, // использовать ли tooltips
                false // использовать ли urls
        );

        chart.getXYPlot().setDataset(0, datasetS);
        chart.getXYPlot().setRenderer(0, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(0).setSeriesPaint(0, Color.YELLOW);
        ///chart.getXYPlot().getRenderer(0).setSeriesShapesVisible(0, false);


        chart.getXYPlot().setDataset(1, datasetQ);
        chart.getXYPlot().setRenderer(1, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(1).setSeriesPaint(1, Color.MAGENTA);
        //chart.getXYPlot().getRenderer(1).setSeriesShapesVisible(1, false);

        chart.getXYPlot().setDataset(2, datasetB);
        chart.getXYPlot().setRenderer(2, new org.jfree.chart.renderer.xy.XYLineAndShapeRenderer());
        chart.getXYPlot().getRenderer(2).setSeriesPaint(2, Color.BLACK);
        //chart.getXYPlot().getRenderer(2).setSeriesShapesVisible(2, false);

        ChartPanel chartPanel = new ChartPanel(chart);
        // chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;

    }

    public static XYSeriesCollection createCountDataset(ArrayList<Long> sizeArray, ArrayList<Long> countArray) {
        XYSeries series = new XYSeries("Count Dataset");
        for (int i = 0; i < sizeArray.size(); i++) {
            series.add(sizeArray.get(i), countArray.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static XYSeriesCollection createTimeDataset(ArrayList<Long> sizeArray, ArrayList<Long> timeArray) {
        XYSeries series = new XYSeries("Time Dataset");
        for (int i = 0; i < sizeArray.size(); i++) {
            series.add(sizeArray.get(i), timeArray.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static void reverse(int[] array)
    {
 
        // Length of the array
        int n = array.length;
 
        // Swapping the first half elements with last half
        // elements
        for (int i = 0; i < n / 2; i++) {
 
            // Storing the first half elements temporarily
            int temp = array[i];
 
            // Assigning the first half to the last half
            array[i] = array[n - i - 1];
 
            // Assigning the last half to the first half
            array[n - i - 1] = temp;
        }
    }

    /*
     * public static void quicksortRandom(int[] arr) {
     * quicksortRandom(arr, 0, arr.length - 1);
     * }
     */
    private static Pair<int[], Long> quicksortRandom(int[] arr, int left, int right, long c) {
        if (left < right) {
            c++;
            // Выбор случайного опорного элемента из подмассива arr[left...right]
            int pivotIndex = new Random().nextInt(right - left + 1) + left;
            int pivotValue = arr[pivotIndex];
            c += 4;
            // Перемещение опорного элемента в конец подмассива
            swap(arr, pivotIndex, right);
            c += 3;
            // Разделение на подмассивы
            int storeIndex = left;
            c++;
            for (int i = left; i < right; i++) {
                c += 3;
                if (arr[i] < pivotValue) {
                    c++;
                    // Перемещение элементов меньших опорного в начало подмассива
                    swap(arr, i, storeIndex);
                    c += 3;
                    storeIndex++;
                    c++;
                }
            }
            // Перемещение опорного элемента на место, где закончилась последняя итерация
            // цикла
            swap(arr, storeIndex, right);
            c += 3;
            // Рекурсивный вызов сортировки для левой и правой частей подмассива
            quicksortRandom(arr, left, storeIndex - 1, c);
            quicksortRandom(arr, storeIndex + 1, right, c);
        }
        return new Pair<int[], Long>(arr, c);
    }

    private static Pair<int[], Long> quicksortMedian(int[] arr, int left, int right, long c) {
        if (left < right) {
            c++;
            // Выбор опорного элемента как медианы arr[left], arr[(left+right)/2],
            // arr[right]
            int pivotIndex = (left+right)/2;
            int pivotValue = arr[pivotIndex];
            c += 4;
            // Перемещение опорного элемента в конец подмассива
            swap(arr, pivotIndex, right);
            c += 3;
            // Разделение на подмассивы
            int storeIndex = left;
            c++;
            for (int i = left; i < right; i++) {
                c += 3;
                if (arr[i] < pivotValue) {
                    c++;
                    // Перемещение элементов меньших опорного в начало подмассива
                    swap(arr, i, storeIndex);
                    c += 3;
                    storeIndex++;
                    c++;
                }
            }
            // Перемещение опорного элемента на место, где закончилась последняя итерация
            // цикла
            swap(arr, storeIndex, right);
            c += 3;
            // Рекурсивный вызов сортировки для левой и правой частей подмассива
            quicksortMedian(arr, left, storeIndex - 1, c);
            quicksortMedian(arr, storeIndex + 1, right, c);
        }
        return new Pair<int[], Long>(arr, c);
    }

    private static Pair<int[], Long> quicksortMin(int[] arr, int left, int right, long c) {
        if (left < right) {
            c++;
            // Выбор опорного элемента как медианы arr[left], arr[(left+right)/2],
            // arr[right]
            int pivotIndex = left;
            int pivotValue = arr[pivotIndex];
            c += 4;
            // Перемещение опорного элемента в конец подмассива
            swap(arr, pivotIndex, right);
            c += 3;
            // Разделение на подмассивы
            int storeIndex = left;
            c++;
            for (int i = left; i < right; i++) {
                c += 3;
                if (arr[i] < pivotValue) {
                    c++;
                    // Перемещение элементов меньших опорного в начало подмассива
                    swap(arr, i, storeIndex);
                    c += 3;
                    storeIndex++;
                    c++;
                }
            }
            // Перемещение опорного элемента на место, где закончилась последняя итерация
            // цикла
            swap(arr, storeIndex, right);
            c += 3;
            // Рекурсивный вызов сортировки для левой и правой частей подмассива
            quicksortMedian(arr, left, storeIndex - 1, c);
            quicksortMedian(arr, storeIndex + 1, right, c);
        }
        return new Pair<int[], Long>(arr, c);
    }


    private static void swap(int[] arr, int i, int j) {
        // Метод для обмена элементов в массиве
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) throws Exception {

        int WIDTH = 1800;
        int HEIGHT = 900;

        JFrame frame = new JFrame("Lab 3");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());

        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.setLayout(new GridLayout(4, 4, 100, 10));

        JPanel panelLeft = new JPanel();

        JPanel panelCenter = new JPanel(new BorderLayout());

        JPanel panelRight = new JPanel(new BorderLayout());

        JTextArea infoArea = new JTextArea(40, 30);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(infoArea);


        JLabel firstTaskLabel = new JLabel("Shell sort");
        JLabel secondTaskLabel = new JLabel("Quick sort");
        JLabel thirdTaskLabel = new JLabel("Bubble sort");
        JLabel forthTaskLabel = new JLabel("All");

        JButton firstTaskAButton = new JButton("random");
        firstTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(10000000);
                    }

                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        count += 2;
                        interval = interval * 3 + 1;
                        count += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        count++;
                        for (int i = interval; i < n; i++) {
                            count += 4;
                            int temp = arr[i];
                            int j = i;
                            while (j >= interval && arr[j - interval] > temp) {
                                count += 5;
                                arr[j] = arr[j - interval];
                                j -= interval;
                                count += 5;
                            }
                            arr[j] = temp;
                            count++;
                        }
                        interval = (interval - 1) / 3;
                        count += 4;
                    }

                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();

            }
        });

        panelCenter.removeAll();
        panelRight.removeAll();

        JButton firstTaskBButton = new JButton("minimization");
        firstTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(10000000);
                    }
                    Arrays.sort(arr);

                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        count += 2;
                        interval = interval * 3 + 1;
                        count += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        count++;
                        for (int i = interval; i < n; i++) {
                            count += 4;
                            int temp = arr[i];
                            int j = i;
                            while (j >= interval && arr[j - interval] > temp) {
                                count += 5;
                                arr[j] = arr[j - interval];
                                j -= interval;
                                count += 5;
                            }
                            arr[j] = temp;
                            count++;
                        }
                        interval = (interval - 1) / 3;
                        count += 4;
                    }

                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });

        JButton firstTaskCButton = new JButton("maximization");
        firstTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    Integer arr[] = new Integer[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(10000000);
                    }
                    Arrays.sort(arr, Comparator.reverseOrder());

                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        count += 2;
                        interval = interval * 3 + 1;
                        count += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        count++;
                        for (int i = interval; i < n; i++) {
                            count += 4;
                            int temp = arr[i];
                            int j = i;
                            while (j >= interval && arr[j - interval] > temp) {
                                count += 5;
                                arr[j] = arr[j - interval];
                                j -= interval;
                                count += 5;
                            }
                            arr[j] = temp;
                            count++;
                        }
                        interval = (interval - 1) / 3;
                        count += 4;
                    }

                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });

        JButton secondTaskAButton = new JButton("random");
        secondTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(1000);
                    }
                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    arr = quicksortRandom(arr, 0, n - 1, count).getKey();
                    count = quicksortRandom(arr, 0, n - 1, count).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });

        JButton secondTaskBButton = new JButton("minimization");
        secondTaskBButton.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(1000);
                    }
                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    arr = quicksortMedian(arr, 0, n - 1, count).getKey();
                    count = quicksortMedian(arr, 0, n - 1, count).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });

        JButton secondTaskCButton = new JButton("maximization");
        secondTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 100000; n <= 1000000; n += 100000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(1000);
                    }
                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    arr = quicksortMin(arr, 0, n - 1, count).getKey();
                    count = quicksortMin(arr, 0, n - 1, count).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }

        });
        
        JButton thirdTaskAButton = new JButton("random");
        thirdTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 1000; n <= 10000; n += 1000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(100);
                    }
                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    int lastSwappedIndex = n - 1;
                    while (lastSwappedIndex > 0) {
                        count++;
                        int newLastSwappedIndex = 0;
                        for (int i = 0; i < lastSwappedIndex; i++) {
                            count += 2;
                            if (arr[i] > arr[i + 1]) {
                                count++;
                                // обменять элементы
                                int temp = arr[i];
                                arr[i] = arr[i + 1];
                                arr[i + 1] = temp;
                                newLastSwappedIndex = i;
                                count+=4;
                            }
                        }
                        lastSwappedIndex = newLastSwappedIndex;
                    }
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });


        JButton thirdTaskBButton = new JButton("minimization");
        thirdTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 1000; n <= 10000; n += 1000) {
                    int arr[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(100);
                    }
                    Arrays.sort(arr);

                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    for (int i = 0; i < n - 1; i++) {
                        count += 3;
                        for (int j = 0; j < n - i - 1; j++) {
                            count += 3;
                            if (arr[j] > arr[j + 1]) {
                                count++;
                                // Обмен элементов
                                int temp = arr[j];
                                arr[j] = arr[j + 1];
                                arr[j + 1] = temp;
                                count += 3;
                            }
                        }
                    }
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });
        
        JButton thirdTaskCButton = new JButton("maximization");
        thirdTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();

                for (int n = 1000; n <= 10000; n += 1000) {
                    Integer arr[] = new Integer[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arr[i] = random.nextInt(100);
                    }
                    Arrays.sort(arr, Comparator.reverseOrder());

                    long timeBegin = System.currentTimeMillis();
                    long count = 2;
                    int lastSwappedIndex = n - 1;
                    while (lastSwappedIndex > 0) {
                        count++;
                        int newLastSwappedIndex = 0;
                        for (int i = 0; i < lastSwappedIndex; i++) {
                            count += 2;
                            if (arr[i] > arr[i + 1]) {
                                count++;
                                // обменять элементы
                                int temp = arr[i];
                                arr[i] = arr[i + 1];
                                arr[i + 1] = temp;
                                newLastSwappedIndex = i;
                                count+=4;
                            }
                        }
                        lastSwappedIndex = newLastSwappedIndex;
                    }
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = n;

                    timeArray.add(timeEnd);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n], где n = " + n + ". Количество операций = " + count
                            + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEnd + '\n');
                }

                XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                ChartPanel countPanel = CountChart(countDataset);
                panelCenter.add(countPanel, BorderLayout.CENTER);

                XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                ChartPanel timePanel = TimeChart(timeDataset);
                panelRight.add(timePanel, BorderLayout.EAST);

                frame.revalidate();
            }
        });

        JButton forthTaskAButton = new JButton("random");
        forthTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArrayShell = new ArrayList<>();
                ArrayList<Long> countArrayShell = new ArrayList<>();
                ArrayList<Long> sizeArrayShell = new ArrayList<>();

                ArrayList<Long> timeArrayQuick = new ArrayList<>();
                ArrayList<Long> countArrayQuick = new ArrayList<>();
                ArrayList<Long> sizeArrayQuick = new ArrayList<>();

                ArrayList<Long> timeArrayBubble = new ArrayList<>();
                ArrayList<Long> countArrayBubble = new ArrayList<>();
                ArrayList<Long> sizeArrayBubble = new ArrayList<>();

                for (int n = 10000; n <= 100000; n += 10000) {
                    int arrS[] = new int[n];
                    int arrQ[] = new int[n];
                    int arrB[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arrS[i] = random.nextInt(100);
                        arrQ[i] = random.nextInt(100);
                        arrB[i] = random.nextInt(100);
                    }

                    long timeBeginShell = System.currentTimeMillis();
                    long countS = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        countS += 2;
                        interval = interval * 3 + 1;
                        countS += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        countS++;
                        for (int i = interval; i < n; i++) {
                            countS += 4;
                            int temp = arrS[i];
                            int j = i;
                            while (j >= interval && arrS[j - interval] > temp) {
                                countS += 5;
                                arrS[j] = arrS[j - interval];
                                j -= interval;
                                countS += 5;
                            }
                            arrS[j] = temp;
                            countS++;
                        }
                        interval = (interval - 1) / 3;
                        countS += 4;
                    }
                    long timeEndShell = System.currentTimeMillis() - timeBeginShell;

                    long size = n;

                    timeArrayShell.add(timeEndShell);
                    countArrayShell.add(countS);
                    sizeArrayShell.add(size);

                    long timeBegin = System.currentTimeMillis();
                    long countQ = 2;
                    arrQ = quicksortRandom(arrQ, 0, n - 1, countQ).getKey();
                    countQ = quicksortRandom(arrQ, 0, n - 1, countQ).getValue();
                    long timeEndQuick = System.currentTimeMillis() - timeBegin;

                    timeArrayQuick.add(timeEndQuick);
                    countArrayQuick.add(countQ);
                    sizeArrayQuick.add(size);


                    long timeBeginBubble = System.currentTimeMillis();
                    long countB = 2;
                    int lastSwappedIndex = n - 1;
                    while (lastSwappedIndex > 0) {
                        countB++;
                        int newLastSwappedIndex = 0;
                        for (int i = 0; i < lastSwappedIndex; i++) {
                            countB += 2;
                            if (arrB[i] > arrB[i + 1]) {
                                countB++;
                                // обменять элементы
                                int temp = arrB[i];
                                arrB[i] = arrB[i + 1];
                                arrB[i + 1] = temp;
                                newLastSwappedIndex = i;
                                countB+=4;
                            }
                        }
                        lastSwappedIndex = newLastSwappedIndex;
                    }
                    long timeEndBubble = System.currentTimeMillis() - timeBeginBubble;

                    timeArrayBubble.add(timeEndBubble);
                    countArrayBubble.add(countB);
                    sizeArrayBubble.add(size);

                    infoArea.append("Shell: Матрица A[n], где n = " + n + ". Количество операций = " + countS
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndShell + '\n');
                    infoArea.append("Quick: Матрица A[n], где n = " + n + ". Количество операций = " + countQ
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndQuick + '\n');
                    infoArea.append("Bubble: Матрица A[n], где n = " + n + ". Количество операций = " + countB
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndBubble + '\n');
                }


                XYSeriesCollection countDatasetS = createCountDataset(sizeArrayShell, countArrayShell);
                XYSeriesCollection countDatasetQ = createCountDataset(sizeArrayQuick, countArrayQuick);
                XYSeriesCollection countDatasetB = createCountDataset(sizeArrayBubble, countArrayBubble);

                XYSeriesCollection timeDatasetS = createTimeDataset(sizeArrayShell, timeArrayShell);
                XYSeriesCollection timeDatasetQ = createTimeDataset(sizeArrayQuick, timeArrayQuick);
                XYSeriesCollection timeDatasetB = createTimeDataset(sizeArrayBubble, timeArrayBubble);


                ChartPanel countPanel = CountChartMany(countDatasetS, countDatasetQ, countDatasetB);
                ChartPanel timePanel = TimeChartMany(timeDatasetS, timeDatasetQ, timeDatasetB);

                panelCenter.add(countPanel, BorderLayout.CENTER);
                panelRight.add(timePanel, BorderLayout.EAST);
                frame.revalidate();
            }
        });


        JButton forthTaskBButton = new JButton("minimization");
        forthTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArrayShell = new ArrayList<>();
                ArrayList<Long> countArrayShell = new ArrayList<>();
                ArrayList<Long> sizeArrayShell = new ArrayList<>();

                ArrayList<Long> timeArrayQuick = new ArrayList<>();
                ArrayList<Long> countArrayQuick = new ArrayList<>();
                ArrayList<Long> sizeArrayQuick = new ArrayList<>();

                ArrayList<Long> timeArrayBubble = new ArrayList<>();
                ArrayList<Long> countArrayBubble = new ArrayList<>();
                ArrayList<Long> sizeArrayBubble = new ArrayList<>();

                for (int n = 1000; n <= 10000; n += 1000) {
                    int arrS[] = new int[n];
                    int arrQ[] = new int[n];
                    int arrB[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arrS[i] = random.nextInt(100);
                        arrQ[i] = random.nextInt(100);
                        arrB[i] = random.nextInt(100);
                    }
                    Arrays.sort(arrS);
                    Arrays.sort(arrB);

                    // SHELL

                    long timeBeginShell = System.currentTimeMillis();
                    long countS = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        countS += 2;
                        interval = interval * 3 + 1;
                        countS += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        countS++;
                        for (int i = interval; i < n; i++) {
                            countS += 4;
                            int temp = arrS[i];
                            int j = i;
                            while (j >= interval && arrS[j - interval] > temp) {
                                countS += 5;
                                arrS[j] = arrS[j - interval];
                                j -= interval;
                                countS += 5;
                            }
                            arrS[j] = temp;
                            countS++;
                        }
                        interval = (interval - 1) / 3;
                        countS += 4;
                    }
                    long timeEndShell = System.currentTimeMillis() - timeBeginShell;

                    long size = n;

                    timeArrayShell.add(timeEndShell);
                    countArrayShell.add(countS);
                    sizeArrayShell.add(size);

                    // QUICK

                    long timeBegin = System.currentTimeMillis();
                    long countQ = 2;
                    arrQ = quicksortRandom(arrQ, 0, n - 1, countQ).getKey();
                    countQ = quicksortRandom(arrQ, 0, n - 1, countQ).getValue();
                    long timeEndQuick = System.currentTimeMillis() - timeBegin;

                    timeArrayQuick.add(timeEndQuick);
                    countArrayQuick.add(countQ);
                    sizeArrayQuick.add(size);

                    // BUBBLE

                    long timeBeginBubble = System.currentTimeMillis();
                    long countB = 2;
                    int lastSwappedIndex = n - 1;
                    while (lastSwappedIndex > 0) {
                        countB++;
                        int newLastSwappedIndex = 0;
                        for (int i = 0; i < lastSwappedIndex; i++) {
                            countB += 2;
                            if (arrB[i] > arrB[i + 1]) {
                                countB++;
                                // обменять элементы
                                int temp = arrB[i];
                                arrB[i] = arrB[i + 1];
                                arrB[i + 1] = temp;
                                newLastSwappedIndex = i;
                                countB+=4;
                            }
                        }
                        lastSwappedIndex = newLastSwappedIndex;
                    }
                    long timeEndBubble = System.currentTimeMillis() - timeBeginBubble;

                    timeArrayBubble.add(timeEndBubble);
                    countArrayBubble.add(countB);
                    sizeArrayBubble.add(size);

                    infoArea.append("Shell: Матрица A[n], где n = " + n + ". Количество операций = " + countS
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndShell + '\n');
                    infoArea.append("Quick: Матрица A[n], где n = " + n + ". Количество операций = " + countQ
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndQuick + '\n');
                    infoArea.append("Bubble: Матрица A[n], где n = " + n + ". Количество операций = " + countB
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndBubble + '\n');
                }


                XYSeriesCollection countDatasetS = createCountDataset(sizeArrayShell, countArrayShell);
                XYSeriesCollection countDatasetQ = createCountDataset(sizeArrayQuick, countArrayQuick);
                XYSeriesCollection countDatasetB = createCountDataset(sizeArrayBubble, countArrayBubble);

                XYSeriesCollection timeDatasetS = createTimeDataset(sizeArrayShell, timeArrayShell);
                XYSeriesCollection timeDatasetQ = createTimeDataset(sizeArrayQuick, timeArrayQuick);
                XYSeriesCollection timeDatasetB = createTimeDataset(sizeArrayBubble, timeArrayBubble);


                ChartPanel countPanel = CountChartMany(countDatasetS, countDatasetQ, countDatasetB);
                ChartPanel timePanel = TimeChartMany(timeDatasetS, timeDatasetQ, timeDatasetB);

                panelCenter.add(countPanel, BorderLayout.CENTER);
                panelRight.add(timePanel, BorderLayout.EAST);
                frame.revalidate();
            }
        });

   
        
        JButton forthTaskCButton = new JButton("maximization");
        forthTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArrayShell = new ArrayList<>();
                ArrayList<Long> countArrayShell = new ArrayList<>();
                ArrayList<Long> sizeArrayShell = new ArrayList<>();

                ArrayList<Long> timeArrayQuick = new ArrayList<>();
                ArrayList<Long> countArrayQuick = new ArrayList<>();
                ArrayList<Long> sizeArrayQuick = new ArrayList<>();

                ArrayList<Long> timeArrayBubble = new ArrayList<>();
                ArrayList<Long> countArrayBubble = new ArrayList<>();
                ArrayList<Long> sizeArrayBubble = new ArrayList<>();

                for (int n = 1000; n <= 10000; n += 1000) {
                    int arrS[] = new int[n];
                    int arrQ[] = new int[n];
                    int arrB[] = new int[n];

                    Random random = new Random();
                    for (int i = 0; i < n; ++i) {
                        arrS[i] = random.nextInt(100);
                        arrQ[i] = random.nextInt(100);
                        arrB[i] = random.nextInt(100);
                    }

                    Arrays.sort(arrS);
                    reverse(arrS);

                    Arrays.sort(arrB);
                    reverse(arrB);



                    // SHELL

                    long timeBeginShell = System.currentTimeMillis();
                    long countS = 2;
                    // Выбираем начальный интервал
                    int interval = 1;
                    while (interval < n / 3) {
                        countS += 2;
                        interval = interval * 3 + 1;
                        countS += 3;
                    }

                    // Уменьшаем интервал до 1
                    while (interval > 0) {
                        countS++;
                        for (int i = interval; i < n; i++) {
                            countS += 4;
                            int temp = arrS[i];
                            int j = i;
                            while (j >= interval && arrS[j - interval] > temp) {
                                countS += 5;
                                arrS[j] = arrS[j - interval];
                                j -= interval;
                                countS += 5;
                            }
                            arrS[j] = temp;
                            countS++;
                        }
                        interval = (interval - 1) / 3;
                        countS += 4;
                    }
                    long timeEndShell = System.currentTimeMillis() - timeBeginShell;

                    long size = n;

                    timeArrayShell.add(timeEndShell);
                    countArrayShell.add(countS);
                    sizeArrayShell.add(size);

                    // QUICK

                    long timeBegin = System.currentTimeMillis();
                    long countQ = 2;
                    arrQ = quicksortRandom(arrQ, 0, n - 1, countQ).getKey();
                    countQ = quicksortRandom(arrQ, 0, n - 1, countQ).getValue();
                    long timeEndQuick = System.currentTimeMillis() - timeBegin;

                    timeArrayQuick.add(timeEndQuick);
                    countArrayQuick.add(countQ);
                    sizeArrayQuick.add(size);

                    // BUBBLE

                    long timeBeginBubble = System.currentTimeMillis();
                    long countB = 2;
                    int lastSwappedIndex = n - 1;
                    while (lastSwappedIndex > 0) {
                        countB++;
                        int newLastSwappedIndex = 0;
                        for (int i = 0; i < lastSwappedIndex; i++) {
                            countB += 2;
                            if (arrB[i] > arrB[i + 1]) {
                                countB++;
                                // обменять элементы
                                int temp = arrB[i];
                                arrB[i] = arrB[i + 1];
                                arrB[i + 1] = temp;
                                newLastSwappedIndex = i;
                                countB+=4;
                            }
                        }
                        lastSwappedIndex = newLastSwappedIndex;
                    }
                    long timeEndBubble = System.currentTimeMillis() - timeBeginBubble;

                    timeArrayBubble.add(timeEndBubble);
                    countArrayBubble.add(countB);
                    sizeArrayBubble.add(size);

                    infoArea.append("Shell: Матрица A[n], где n = " + n + ". Количество операций = " + countS
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndShell + '\n');
                    infoArea.append("Quick: Матрица A[n], где n = " + n + ". Количество операций = " + countQ
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndQuick + '\n');
                    infoArea.append("Bubble: Матрица A[n], где n = " + n + ". Количество операций = " + countB
                        + ". Размерность исходных данных = " + n + ". Время(мс) = " + timeEndBubble + '\n');
                }


                XYSeriesCollection countDatasetS = createCountDataset(sizeArrayShell, countArrayShell);
                XYSeriesCollection countDatasetQ = createCountDataset(sizeArrayQuick, countArrayQuick);
                XYSeriesCollection countDatasetB = createCountDataset(sizeArrayBubble, countArrayBubble);

                XYSeriesCollection timeDatasetS = createTimeDataset(sizeArrayShell, timeArrayShell);
                XYSeriesCollection timeDatasetQ = createTimeDataset(sizeArrayQuick, timeArrayQuick);
                XYSeriesCollection timeDatasetB = createTimeDataset(sizeArrayBubble, timeArrayBubble);


                ChartPanel countPanel = CountChartMany(countDatasetS, countDatasetQ, countDatasetB);
                ChartPanel timePanel = TimeChartMany(timeDatasetS, timeDatasetQ, timeDatasetB);

                panelCenter.add(countPanel, BorderLayout.CENTER);
                panelRight.add(timePanel, BorderLayout.EAST);
                frame.revalidate();
            }
        });
        
        panelLeft.add(scroll);

        panelInput.add(firstTaskLabel);
        panelInput.add(secondTaskLabel);
        panelInput.add(thirdTaskLabel);
        panelInput.add(forthTaskLabel);

        panelInput.add(firstTaskAButton);
        panelInput.add(secondTaskAButton);
        panelInput.add(thirdTaskAButton);
        panelInput.add(forthTaskAButton);

        panelInput.add(firstTaskBButton);
        panelInput.add(secondTaskBButton);
        panelInput.add(thirdTaskBButton);
        panelInput.add(forthTaskBButton);

        panelInput.add(firstTaskCButton);
        panelInput.add(secondTaskCButton);
        panelInput.add(thirdTaskCButton);
        panelInput.add(forthTaskCButton);

        mainPanel.add(panelInput, BorderLayout.NORTH);
        mainPanel.add(panelLeft, BorderLayout.WEST);
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        mainPanel.add(panelRight, BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

}
