import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.*;

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

    public static XYSeriesCollection createCountDataset(ArrayList<Integer> sizeArray, ArrayList<Integer> countArray)
    {
        XYSeries series = new XYSeries("Count Dataset");
        for (int i = 0; i < sizeArray.size(); i++) {
            series.add(sizeArray.get(i), countArray.get(i));
        }
        XYSeriesCollection dataset = new XYSeriesCollection(series);
        return dataset;
    }

    public static XYSeriesCollection createTimeDataset(ArrayList<Integer> sizeArray, ArrayList<Integer> timeArray)
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

        JFrame frame = new JFrame("Lab 2");
        frame.setSize(WIDTH, HEIGHT);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout());

        // TEXT AREA ////////////////////////////////////////////////////////////

        JTextArea infoArea = new JTextArea(20, 20);
        infoArea.setLineWrap(true);
        infoArea.setWrapStyleWord(true);
        JScrollPane scroll = new JScrollPane(infoArea);


        container.add(scroll);


        // DATA SIZE ////////////////////////////////////////////////////////////

        JPanel dataSizePanel = new JPanel(new GridLayout(0, 1, 5, 5));
        dataSizePanel.setBorder(BorderFactory.createTitledBorder("data size"));
        ButtonGroup dataSizeGroup = new ButtonGroup();

        JRadioButton smallDataSize = new JRadioButton("small");
        JRadioButton mediumDataSize = new JRadioButton("medium");
        JRadioButton largeDataSize = new JRadioButton("large");

        dataSizePanel.add(smallDataSize);
        dataSizePanel.add(mediumDataSize);
        dataSizePanel.add(largeDataSize);
        dataSizeGroup.add(smallDataSize);
        dataSizeGroup.add(mediumDataSize);
        dataSizeGroup.add(largeDataSize);

        container.add(dataSizePanel);

        // DATA GENERATION ////////////////////////////////////////////////////////////

        JPanel dataGenerationPanel = new JPanel(new GridLayout(0, 1, 5, 5));
        dataGenerationPanel.setBorder(BorderFactory.createTitledBorder("generation"));
        ButtonGroup dataGenerationGroup = new ButtonGroup();

        JRadioButton randomDataGeneration = new JRadioButton("random");
        JRadioButton worseDataGeneration = new JRadioButton("worse case");
        JRadioButton bestDataGeneration = new JRadioButton("best case");

        dataGenerationPanel.add(randomDataGeneration);
        dataGenerationPanel.add(worseDataGeneration);
        dataGenerationPanel.add(bestDataGeneration);
        dataGenerationGroup.add(randomDataGeneration);
        dataGenerationGroup.add(worseDataGeneration);
        dataGenerationGroup.add(bestDataGeneration);

        container.add(dataGenerationPanel);


        int n;

        // UNORDERED ARRAY ////////////////////////////////////////////////////////////

        JPanel unorderArrayPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        unorderArrayPanel.setBorder(BorderFactory.createTitledBorder("Unordered array"));

        JButton lineUnorderBtn = new JButton("linear");
        lineUnorderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                infoArea.selectAll();
                infoArea.replaceSelection("");
                if (smallDataSize.isSelected())
                {
                    int n = 100;
                    if (randomDataGeneration.isSelected())
                    {
                        ArrayList<Integer> timeArray = new ArrayList<>();
                        ArrayList<Integer> countArray = new ArrayList<>();
                        ArrayList<Integer> sizeArray = new ArrayList<>();

                        //int n = 1000;

                        LineUnorder lineUnorder = new LineUnorder(n);

                        int timeBegin = (int)System.currentTimeMillis();
                        int[] arr = lineUnorder.generateRandomArray(n).getKey();
                        for (int i = 0; i < arr.length; i++) 
                        {
                            System.out.print(arr[i] + " ");
                        }
                        System.out.println();
                        System.out.println(arr.length);
                        int countInit = lineUnorder.generateRandomArray(n).getValue();
                        int timeEnd = (int)System.currentTimeMillis() - timeBegin;

                        for (int i = 0; i <= 500; i++)
                        {
                            Random rand = new Random();
                            int x = rand.nextInt(n) + 1;

                            int element = lineUnorder.linearSearch(arr, n, x).get(0);
                            int index = lineUnorder.linearSearch(arr, n, x).get(1);

                            int count = lineUnorder.linearSearch(arr, n, x).get(2);
                            int time = lineUnorder.linearSearch(arr, n, x).get(3);

                            int timeRes = time + timeEnd;
                            int countRes = count + countInit;

                            timeArray.add(timeRes);
                            countArray.add(countRes);
                            sizeArray.add(n);

                            // infoArea.append("Массив A[n], где n = " + n + ". Количество операций = " +
                            // countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " +
                            // timeRes + '\n');

                            infoArea.append("Element " + element + ", position index " + index + '\n');
                        }
                        XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                        ChartPanel countPanel = CountChart(countDataset);
                        container.add(countPanel, BorderLayout.CENTER);

                        XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                        ChartPanel timePanel = TimeChart(timeDataset);
                        container.add(timePanel, BorderLayout.EAST);


                        frame.revalidate();
                    }
                    if (worseDataGeneration.isSelected())
                    {
                        ArrayList<Integer> timeArray = new ArrayList<>();
                        ArrayList<Integer> countArray = new ArrayList<>();
                        ArrayList<Integer> sizeArray = new ArrayList<>();

                        LineUnorder lineUnorder = new LineUnorder(n);

                        int timeBegin = (int)System.currentTimeMillis();
                        int[] arr = lineUnorder.generateRandomArray(n).getKey();
                        int countInit = lineUnorder.generateRandomArray(n).getValue();
                        for (int i = 0; i < arr.length; i++) 
                        {
                            System.out.print(arr[i] + " ");
                        }
                        System.out.println();
                        System.out.println(arr.length);
                        int timeEnd = (int)System.currentTimeMillis() - timeBegin;

                        for (int i = 10; i <= 10; i++)
                        {
                            Random rand = new Random();
                            int x = rand.nextInt(n) + 1;

                            int element = lineUnorder.linearSearch(arr, n, x).get(0);
                            int index = lineUnorder.linearSearch(arr, n, x).get(1);

                            int count = lineUnorder.linearSearch(arr, n, x).get(2);
                            int time = lineUnorder.linearSearch(arr, n, x).get(3);

                            int timeRes = time + timeEnd;
                            int countRes = count + countInit;

                            timeArray.add(timeRes);
                            countArray.add(countRes);
                            sizeArray.add(n);

                            // infoArea.append("Массив A[n], где n = " + n + ". Количество операций = " +
                            // countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " +
                            // timeRes + '\n');

                            infoArea.append("Element " + element + ", position index " + index + '\n');
                        }
                        XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                        ChartPanel countPanel = CountChart(countDataset);
                        container.add(countPanel, BorderLayout.CENTER);

                        XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                        ChartPanel timePanel = TimeChart(timeDataset);
                        container.add(timePanel, BorderLayout.EAST);


                        frame.revalidate();
                    }

                    if (bestDataGeneration.isSelected())
                    {
                        ArrayList<Integer> timeArray = new ArrayList<>();
                        ArrayList<Integer> countArray = new ArrayList<>();
                        ArrayList<Integer> sizeArray = new ArrayList<>();

                        LineUnorder lineUnorder = new LineUnorder(n);

                        int timeBegin = (int)System.currentTimeMillis();
                        int[] arr = lineUnorder.generateRandomArray(n).getKey();
                        int countInit = lineUnorder.generateRandomArray(n).getValue();
                        int timeEnd = (int)System.currentTimeMillis() - timeBegin;

                        for (int i = 10; i <= 500; i++)
                        {
                            Random rand = new Random();
                            int x = rand.nextInt(n) + 1;

                            int element = lineUnorder.linearSearch(arr, n, x).get(0);
                            int index = lineUnorder.linearSearch(arr, n, x).get(1);

                            int count = lineUnorder.linearSearch(arr, n, x).get(2);
                            int time = lineUnorder.linearSearch(arr, n, x).get(3);

                            int timeRes = time + timeEnd;
                            int countRes = count + countInit;

                            timeArray.add(timeRes);
                            countArray.add(countRes);
                            sizeArray.add(n);

                            // infoArea.append("Массив A[n], где n = " + n + ". Количество операций = " +
                            // countRes + ". Размерность исходных данных = " + size + ". Время(мс) = " +
                            // timeRes + '\n');

                            infoArea.append("Element " + element + ", position index " + index + '\n');
                        }
                        XYSeriesCollection countDataset = createCountDataset(sizeArray, countArray);
                        ChartPanel countPanel = CountChart(countDataset);
                        container.add(countPanel, BorderLayout.CENTER);

                        XYSeriesCollection timeDataset = createTimeDataset(sizeArray, timeArray);
                        ChartPanel timePanel = TimeChart(timeDataset);
                        container.add(timePanel, BorderLayout.EAST);


                        frame.revalidate();
                    }
                }

                if (mediumDataSize.isSelected())
                {
                    int n = 10000;
                    if (randomDataGeneration.isSelected())
                    {
                        
                    }

                    if (worseDataGeneration.isSelected())
                    {

                    }

                    if (bestDataGeneration.isSelected())
                    {

                    }
                }

                if (largeDataSize.isSelected())
                {
                    int n = 1000000;    
                    if (randomDataGeneration.isSelected())
                    {

                    }

                    if (worseDataGeneration.isSelected())
                    {

                    }

                    if (bestDataGeneration.isSelected())
                    {

                    }
                }

            }
        });

        JButton quickUnorderBtn = new JButton("quick");
        quickUnorderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        unorderArrayPanel.add(lineUnorderBtn);
        unorderArrayPanel.add(quickUnorderBtn);

        container.add(unorderArrayPanel);

        // ORDERED ARRAY ////////////////////////////////////////////////////////////

        JPanel orderArrayPanel = new JPanel(new GridLayout(1, 0, 5, 5));
        orderArrayPanel.setBorder(BorderFactory.createTitledBorder("Ordered array"));

        JButton quickOrderBtn = new JButton("quick");
        quickOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

            }
        });

        JButton binaryOrderBtn = new JButton("binary");
        binaryOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        JButton blockOrderBtn = new JButton("block");
        blockOrderBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                
            }
        });

        JLabel numOfBlocksLabel = new JLabel("  count of blocks: ");

        JTextField numOfBlocksField = new JTextField(10);

        orderArrayPanel.add(quickOrderBtn);
        orderArrayPanel.add(binaryOrderBtn);
        orderArrayPanel.add(blockOrderBtn);
        orderArrayPanel.add(numOfBlocksLabel);
        orderArrayPanel.add(numOfBlocksField);

        container.add(orderArrayPanel);




        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
