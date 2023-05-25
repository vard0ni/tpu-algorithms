import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;


public class App extends JFrame{

    
    public static ChartPanel CountChart(XYSeriesCollection dataset)
    {
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
        //chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;
    }

    public static ChartPanel TimeChart(XYSeriesCollection dataset)
    {
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
        //chartPanel.setPreferredSize(new Dimension(10, 10));

        return chartPanel;

        
    }

    public static XYSeriesCollection createCountDataset(ArrayList<Long> sizeArray, ArrayList<Long> countArray)
    {
        XYSeries series = new XYSeries("Count Dataset");
        for (int i = 0; i < sizeArray.size(); i++) {
            series.add(sizeArray.get(i), countArray.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static XYSeriesCollection createTimeDataset(ArrayList<Long> sizeArray, ArrayList<Long> timeArray)
    {
        XYSeries series = new XYSeries("Time Dataset");
        for (int i = 0; i < sizeArray.size(); i++) {
            series.add(sizeArray.get(i), timeArray.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }
    
    
    public static void main(String[] args) throws Exception {  

        int WIDTH = 1800;
        int HEIGHT = 900;

        JFrame frame = new JFrame("Lab 1");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JPanel mainPanel = new JPanel();
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        mainPanel.setLayout(new BorderLayout());


        JPanel panelInput = new JPanel();
        panelInput.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panelInput.setLayout(new GridLayout(4, 3, 100, 10));

        JPanel panelLeft = new JPanel();

        JPanel panelCenter = new JPanel(new BorderLayout());

        JPanel panelRight = new JPanel(new BorderLayout());

        JTextArea infoArea = new JTextArea(40, 30);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(infoArea);

        JLabel firstTaskLabel = new JLabel("Task 1");
        JLabel secondTaskLabel = new JLabel("Task 2");
        JLabel thirdTaskLabel = new JLabel("Task 3");
        
        JButton firstTaskAButton = new JButton("A");
        firstTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    Task1 task1 = new Task1(n);

                    long timeBegin = System.currentTimeMillis();
                    int[][] matrix = task1.generateRandomMatrix(n).getKey();
                    long countInit = task1.generateRandomMatrix(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = task1.getSize(n);
                    long count = task1.getData(matrix, n).get(0);
                    long time = task1.getData(matrix, n).get(1);

                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(countRes);
                    sizeArray.add(size);
                    
                    infoArea.append("Матрица A[n][n], где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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

        JButton firstTaskBButton = new JButton("B");
        firstTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 
                
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    Task1 task1 = new Task1(n);

                    long timeBegin = System.currentTimeMillis();
                    int[][] matrix = task1.generateOptimizedMatrix(n).getKey();
                    long countInit = task1.generateOptimizedMatrix(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;

                    long size = task1.getSize(n);
                    long count = task1.getData(matrix, n).get(0);
                    long time = task1.getData(matrix, n).get(1);

                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n][n], где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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


        JButton firstTaskCButton = new JButton("C");
        firstTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    Task1 task1 = new Task1(n);
                    
                    long timeBegin = System.currentTimeMillis();
                    int[][] matrix = task1.generateMaximizedMatrix(n).getKey();
                    long countInit = task1.generateMaximizedMatrix(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;
                    
                    long size = task1.getSize(n);
                    long count = task1.getData(matrix, n).get(0);
                    long time = task1.getData(matrix, n).get(1);
                    
                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Матрица A[n][n], где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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

        JButton secondTaskAButton = new JButton("A");
        secondTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    for (int m = 1000; m <= 10000; m += 1000)
                    {
                        Task2 task2 = new Task2(n, m);
                     
                        long timeBegin = System.currentTimeMillis();
                        int[][] A = task2.generateRandomMatrix(n, m).getKey();
                        long countMatrix = task2.generateRandomMatrix(n, m).getValue();
                        int[] E = task2.generateRandomVector(n).getKey();
                        long countVector = task2.generateRandomVector(n).getValue();
                        long timeEnd = System.currentTimeMillis() - timeBegin;

                        long sizeMatrix = task2.getSizeMatrix(n, m);
                        long sizeVector = task2.getSizeVector(n);
                        long count = task2.getData(A, E, n, m).get(0);
                        long time = task2.getData(A, E, n, m).get(1);

                        long timeRes = time + timeEnd;
                        long countRes = count + countMatrix + countVector;

                        infoArea.append("Матрица A[n][m], где n = " + n + ", m = " + m + ". Количество операций = " + countRes + ". Размерность матрицы = " + sizeMatrix + ", Размерность вектора = " + sizeVector + ". Время(мс) = " + timeRes + '\n');
                    }
                    XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                    ChartPanel countPanel = CountChart(countDataset);
                    panelCenter.add(countPanel, BorderLayout.CENTER);

                    XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                    ChartPanel timePanel = TimeChart(timeDataset);
                    panelRight.add(timePanel, BorderLayout.EAST);

                    frame.revalidate();
                }
            }
        });


        JButton secondTaskBButton = new JButton("B");
        secondTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    for (int m = 1000; m <= 10000; m += 1000)
                    {
                        Task2 task2 = new Task2(n, m);
                     
                        long timeBegin = System.currentTimeMillis();
                        int[][] A = task2.generateOptimizedMatrix(n, m).getKey();
                        long countMatrix = task2.generateOptimizedMatrix(n, m).getValue();
                        int[] E = task2.generateOptimizedVector(n).getKey();
                        long countVector = task2.generateOptimizedVector(n).getValue();
                        long timeEnd = System.currentTimeMillis() - timeBegin;

                        long sizeMatrix = task2.getSizeMatrix(n, m);
                        long sizeVector = task2.getSizeVector(n);
                        long count = task2.getData(A, E, n, m).get(0);
                        long time = task2.getData(A, E, n, m).get(1);

                        long timeRes = time + timeEnd;
                        long countRes = count + countMatrix + countVector;

                        infoArea.append("Матрица A[n][m], где n = " + n + ", m = " + m + ". Количество операций = " + countRes + ". Размерность матрицы = " + sizeMatrix + ", Размерность вектора = " + sizeVector + ". Время(мс) = " + timeRes + '\n');
                    }
                    XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                    ChartPanel countPanel = CountChart(countDataset);
                    panelCenter.add(countPanel, BorderLayout.CENTER);

                    XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                    ChartPanel timePanel = TimeChart(timeDataset);
                    panelRight.add(timePanel, BorderLayout.EAST);

                    frame.revalidate();
                }
            }
        });

        JButton secondTaskCButton = new JButton("C");
        secondTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {    
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000; n <= 10000; n += 1000)
                {
                    for (int m = 1000; m <= 10000; m += 1000)
                    {
                        Task2 task2 = new Task2(n, m);
                     
                        long timeBegin = System.currentTimeMillis();
                        int[][] A = task2.generateMaximizedMatrix(n, m).getKey();
                        long countMatrix = task2.generateMaximizedMatrix(n, m).getValue();
                        int[] E = task2.generateMaximizedVector(n).getKey();
                        long countVector = task2.generateMaximizedVector(n).getValue();
                        long timeEnd = System.currentTimeMillis() - timeBegin;

                        long sizeMatrix = task2.getSizeMatrix(n, m);
                        long sizeVector = task2.getSizeVector(n);
                        long count = task2.getData(A, E, n, m).get(0);
                        long time = task2.getData(A, E, n, m).get(1);

                        long timeRes = time + timeEnd;
                        long countRes = count + countMatrix + countVector;

                        infoArea.append("Матрица A[n][m], где n = " + n + ", m = " + m + ". Количество операций = " + countRes + ". Размерность матрицы = " + sizeMatrix + ", Размерность вектора = " + sizeVector + ". Время(мс) = " + timeRes + '\n');
                    }
                    XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                    ChartPanel countPanel = CountChart(countDataset);
                    panelCenter.add(countPanel, BorderLayout.CENTER);

                    XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                    ChartPanel timePanel = TimeChart(timeDataset);
                    panelRight.add(timePanel, BorderLayout.EAST);

                    frame.revalidate();
                }
            }
        });
        
        JButton thirdTaskAButton = new JButton("A");
        thirdTaskAButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000000; n <= 10000000; n += 1000000) {
                    Task3 task3 = new Task3(n);
                    
                    long timeBegin = System.currentTimeMillis();
                    int[] seq = task3.generateRandomSeq(n).getKey();
                    long countInit = task3.generateRandomSeq(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;
                    
                    long size = task3.getSizeSeq(n);
                    long count = task3.getData(seq, n).get(0);
                    long time = task3.getData(seq, n).get(1);
                    
                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Последовательность B1,B2..Bn, где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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


        JButton thirdTaskBButton = new JButton("B");
        thirdTaskBButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000000; n <= 10000000; n += 1000000) {
                    Task3 task3 = new Task3(n);
                    
                    long timeBegin = System.currentTimeMillis();
                    int[] seq = task3.generateOptimizedSeq(n).getKey();
                    long countInit = task3.generateOptimizedSeq(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;
                    
                    long size = task3.getSizeSeq(n);
                    long count = task3.getData(seq, n).get(0);
                    long time = task3.getData(seq, n).get(1);
                    
                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Последовательность B1,B2..Bn, где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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
        
        JButton thirdTaskCButton = new JButton("C");
        thirdTaskCButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();   
                infoArea.replaceSelection("");
                ArrayList<Long> timeArray = new ArrayList<>();
                ArrayList<Long> countArray = new ArrayList<>();
                ArrayList<Long> sizeArray = new ArrayList<>();
                for (int n = 1000000; n <= 10000000; n += 1000000) {
                    Task3 task3 = new Task3(n);
                    
                    long timeBegin = System.currentTimeMillis();
                    int[] seq = task3.generateMaximizedSeq(n).getKey();
                    long countInit = task3.generateMaximizedSeq(n).getValue();
                    long timeEnd = System.currentTimeMillis() - timeBegin;
                    
                    long size = task3.getSizeSeq(n);
                    long count = task3.getData(seq, n).get(0);
                    long time = task3.getData(seq, n).get(1);
                    
                    long timeRes = time + timeEnd;
                    long countRes = count + countInit;

                    timeArray.add(timeRes);
                    countArray.add(count);
                    sizeArray.add(size);

                    infoArea.append("Последовательность B1,B2..Bn, где n = " + n + ". Количество операций = " + countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " + timeRes + '\n');
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
        
        panelLeft.add(scroll);

        panelInput.add(firstTaskLabel);
        panelInput.add(secondTaskLabel);
        panelInput.add(thirdTaskLabel);

        panelInput.add(firstTaskAButton);
        panelInput.add(secondTaskAButton);
        panelInput.add(thirdTaskAButton);

        panelInput.add(firstTaskBButton);
        panelInput.add(secondTaskBButton);
        panelInput.add(thirdTaskBButton);

        panelInput.add(firstTaskCButton);
        panelInput.add(secondTaskCButton);
        panelInput.add(thirdTaskCButton);

        mainPanel.add(panelInput, BorderLayout.NORTH);
        mainPanel.add(panelLeft, BorderLayout.WEST);
        mainPanel.add(panelCenter, BorderLayout.CENTER);
        mainPanel.add(panelRight, BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
    

}
