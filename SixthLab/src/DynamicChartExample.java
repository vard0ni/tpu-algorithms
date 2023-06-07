import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

public class DynamicChartExample extends JFrame {

    private static final int MAX_DATA_POINTS = 10;
    private static final int MAX_RANDOM_VALUE = 100;

    private DefaultCategoryDataset dataset;

    public DynamicChartExample() {
        super("Dynamic Bar Chart Example");

        // Создаем пустой набор данных для столбчатой диаграммы
        dataset = new DefaultCategoryDataset();

        // Создаем объект графика
        JFreeChart chart = ChartFactory.createBarChart(
                "Dynamic Data", "Category", "Value", dataset,
                PlotOrientation.VERTICAL, true, true, false);

        // Настраиваем оси графика
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        CategoryAxis xAxis = (CategoryAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        yAxis.setAutoRange(true);

        // Создаем панель для отображения графика
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(800, 600));
        setContentPane(chartPanel);

        // Запускаем фоновый поток, который будет обновлять данные графика
        Thread dataThread = new Thread(this::updateData);
        dataThread.setDaemon(true);
        dataThread.start();
    }

    private void updateData() {
        Random random = new Random();

        while (true) {
            try {
                Thread.sleep(1000); // Приостанавливаем поток на 1 секунду

                // Генерируем случайные данные для каждой категории
                for (int i = 0; i < MAX_DATA_POINTS; i++) {
                    String category = "Category " + i;
                    int randomValue = random.nextInt(MAX_RANDOM_VALUE);
                    dataset.setValue(randomValue, "Series", category);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            DynamicChartExample example = new DynamicChartExample();
            example.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            example.pack();
            example.setVisible(true);
        });
    }
}