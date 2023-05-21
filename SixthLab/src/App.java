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
    private static final int NUM_STATIONS = 5;
    private static final int TRAIN_CAPACITY = 100;
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
        setTitle("Subway Simulation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 800);
        setLocationRelativeTo(null);

        dataset = createDataset();
        chart = createChart(dataset);
        chartPanel = new ChartPanel(chart);
        add(chartPanel, BorderLayout.EAST);  

        logTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(logTextArea);
        add(scrollPane, BorderLayout.CENTER);

        JButton startButton = new JButton("Start Simulation");
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
            dataset.addValue(0, "Passenger Queue", "Station " + (i + 1));
        }
        return dataset;
    }

    private JFreeChart createChart(DefaultCategoryDataset dataset) {
        JFreeChart chart = ChartFactory.createBarChart(
                "Passenger Queue Size", "Station", "Queue Size",
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
            dataset.setValue(station.getQueueSize(), "Passenger Queue", "Station " + (i + 1));
        }
    }

    public void startSimulation() {
        random = new Random();
        stations = new Station[NUM_STATIONS];
        train = new Train("A", TRAIN_CAPACITY);

        for (int i = 0; i < NUM_STATIONS; i++) {
            stations[i] = new Station("Station " + (i + 1));
            int numPassengers = random.nextInt(MAX_PASSENGERS_PER_STATION);
            for (int j = 0; j < numPassengers; j++) {
                Passenger passenger = new Passenger("P" + (j + 1));
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
                    logTextArea.append("Train " + train.getName() + " arrived at " + currentStation.getName() + "\n");

                    int numPassengersToBoard = Math.min(currentStation.getPassengerQueue().size(), train.getAvailableSeats());
                    for (int i = 0; i < numPassengersToBoard; i++) {
                        Passenger passenger = currentStation.getPassengerQueue().dequeue();
                        train.addPassenger(passenger);
                    }

                    train.removePassengers();

                    currentStationIndex++;
                    if (currentStationIndex < NUM_STATIONS) {
                        int trainInterval = random.nextInt(MAX_TRAIN_INTERVAL) + 1;
                        logTextArea.append("Train " + train.getName() + " departed from " + currentStation.getName() + "\n");
                        logTextArea.append("Next train will arrive in " + trainInterval + " minutes.\n\n");
                        try {
                            Thread.sleep(trainInterval * 1000); // Convert minutes to milliseconds
                        } catch (InterruptedException ex) {
                            ex.printStackTrace();
                        }
                    }
                } else {
                    logTextArea.append("Train " + train.getName() + " reached the end of the line.\n");
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