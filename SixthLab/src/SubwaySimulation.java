import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.Queue;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.chart.renderer.xy.XYLineAndShapeRenderer;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
import org.jfree.ui.RectangleInsets;

public class SubwaySimulation extends JFrame {

    private static final int TICK_DURATION = 1000; // Длительность одного тика (в миллисекундах)
    private static final int MAX_CAPACITY = 5; // Максимальная вместимость вагона

    private Timer timer;
    private Queue<Integer> platformQueue;
    private int passengersExited;
    private int passengersEntered;

    private XYSeries passengersSeries;

    public SubwaySimulation() {
        setTitle("Модель движения вагонов в метро");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        passengersSeries = new XYSeries("Пассажиры");
        XYSeriesCollection dataset = new XYSeriesCollection(passengersSeries);
        JFreeChart chart = ChartFactory.createXYLineChart(
                "Количество пассажиров на станции",
                "Время (тики)",
                "Количество пассажиров",
                dataset
        );
        XYPlot plot = chart.getXYPlot();
        plot.setAxisOffset(new RectangleInsets(10, 10, 10, 10));

        NumberAxis xAxis = (NumberAxis) plot.getDomainAxis();
        NumberAxis yAxis = (NumberAxis) plot.getRangeAxis();
        xAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
        yAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());

        XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
        renderer.setSeriesPaint(0, Color.BLUE); // Установка цвета линии на синий
        plot.setRenderer(renderer);

        ChartPanel chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.CENTER);

        platformQueue = new LinkedList<>();
        passengersExited = 0;
        passengersEntered = 0;

        timer = new Timer(TICK_DURATION, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
                updateChart();
            }
        });
    }

    private void tick() {
        // Вагон прибыл на станцию
        int passengersToExit = (int) (Math.random() * (passengersEntered + 1));
        passengersExited += passengersToExit;
        passengersEntered -= passengersToExit;

        // Новые пассажиры пришли на станцию
        int passengersArriving = (int) (Math.random() * 5);
        passengersEntered += passengersArriving;
        platformQueue.offer(passengersArriving);

        // Проверяем, есть ли свободные места в вагоне
        if (passengersEntered < MAX_CAPACITY) {
            int passengersToEnter = Math.min(platformQueue.peek(), MAX_CAPACITY - passengersEntered);
            passengersEntered += passengersToEnter;
            platformQueue.poll();
        }
    }

    private void updateChart() {
        passengersSeries.add(passengersSeries.getItemCount(), passengersExited);
    }

    public void startSimulation() {
        timer.start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                SubwaySimulation simulation = new SubwaySimulation();
                simulation.pack();
                simulation.setVisible(true);
                simulation.startSimulation();
            }
        });
    }
}