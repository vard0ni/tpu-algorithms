import org.jfree.chart.*;
import org.jfree.chart.plot.*;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.List;
import java.util.Arrays;

public class App extends JFrame {
    private static final int NUM_STATIONS = 10;
    private static final int TRAIN_CAPACITY = 30;
    private static final int MAX_PASSENGERS_PER_STATION = 20;
    private static final int MAX_TRAIN_INTERVAL = 5;

    private JTextArea logTextArea;
    private Timer trainTimer;
    private Station[] stations;
    private Train train;
    private Random random;

    private DefaultCategoryDataset dataset;
    private JFreeChart chart;
    private ChartPanel chartPanel;

    public App() {
        setTitle("Симуляция метро");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 800);
        setLocationRelativeTo(null);

        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.EAST);  

        logTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Начать моделирование");
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startSimulation();
            }
        });
        add(startButton, BorderLayout.SOUTH);
    }

    private DefaultCategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        for (int i = 0; i < NUM_STATIONS; i++) {
            dataset.addValue(0, "Очередь пассажиров", "Станция " + (i + 1));
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Размер очереди пассажиров", "Станция", "Размер очереди",
                dataset, PlotOrientation.VERTICAL, false, true, false);
        CategoryPlot plot = (CategoryPlot) chart.getPlot();
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        renderer.setBarPainter(new StandardBarPainter());
        renderer.setSeriesPaint(0, new Color(79, 129, 189));
        return chart;
    }

    private void updateChart(List<Station> stations) {
        for (int i = 0; i < NUM_STATIONS; i++) {
            Station station = stations.get(i);
            dataset.setValue(station.getQueueSize(), "Очередь пассажиров", "Станция " + (i + 1));
        }
    }

    public void startSimulation() {
        random = new Random();
        stations = new Station[NUM_STATIONS];
        train = new Train("A", TRAIN_CAPACITY);

        for (int i = 0; i < NUM_STATIONS; i++) {
            stations[i] = new Station("Станция " + (i + 1));
            int numPassengers = random.nextInt(MAX_PASSENGERS_PER_STATION);
            for (int j = 0; j < numPassengers; j++) {
                Passenger passenger = new Passenger("П" + (j + 1));
                stations[i].addPassenger(passenger);
            }
        }

        logTextArea.setText("");
        trainTimer = new Timer(1000, new ActionListener() {
            int currentStationIndex = 0;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentStationIndex < NUM_STATIONS) {
                    Station currentStation = stations[currentStationIndex];
                    logTextArea.append("Поезд " + train.getName() + " прибыл на " + currentStation.getName() + "\n");

                    // Часть пассажиров выходит из поезда
                    int numPassengersToExit = random.nextInt(currentStation.getPassengerQueue().size() + 1);
                    train.removePassengers(numPassengersToExit);

                    int numPassengersToBoard = Math.min(currentStation.getPassengerQueue().size(), train.getAvailableSeats());
                    for (int i = 0; i < numPassengersToBoard; i++) {
                        Passenger passenger = currentStation.getPassengerQueue().dequeue();
                        train.addPassenger(passenger);
                    }
                    System.out.println(numPassengersToExit + " пассажиров вышло из поезда.");
                    //train.removePassengers();

                    currentStationIndex++;
                    if (currentStationIndex < NUM_STATIONS) {
                        int trainInterval = random.nextInt(MAX_TRAIN_INTERVAL) + 1;
                        logTextArea.append("Поезд " + train.getName() + " отправился из " + currentStation.getName() + "\n");
                        logTextArea.append("Следующий поезд прибудет через " + trainInterval + " минут.\n\n");
                        try {
                            Thread.sleep(trainInterval * 1000); // Convert minutes to milliseconds
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    logTextArea.append("Поезд " + train.getName() + " дошел до конца очереди.\n");
                    trainTimer.stop();
                }
            }
        });

        trainTimer.start();

        // Обновление графика
        SwingUtilities.invokeLater(() -> updateChart(Arrays.asList(stations)));
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            App subwaySimulation = new App();
            subwaySimulation.setVisible(true);
        });
    }
}