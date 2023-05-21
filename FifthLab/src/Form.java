import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryUsage;

public class Form extends JFrame {
    private static int o;
    private static int g;
    public static JTextArea infoArea = new JTextArea(40, 40);

    private static void showMemory() {
        //MemoryUsage memoryUsage = ManagementFactory.getMemoryMXBean().getHeapMemoryUsage();
        //long memorySize = memoryUsage.getUsed() / 1024;

        Runtime runtime = Runtime.getRuntime();
        long totalMemory = runtime.totalMemory();
        long freeMemory = runtime.freeMemory();
        long usedMemory = totalMemory - freeMemory;

        infoArea.append("usedMemory: " + usedMemory + " KB\n\n");
    }

    public static void main(String[] args) throws Exception {

    int WIDTH = 1300;
    int HEIGHT = 900;

    Random rand = new Random();

    JFrame frame = new JFrame("Lab 5");
    frame.setSize(WIDTH, HEIGHT);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel mainPanel = new JPanel();
    mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    mainPanel.setLayout(new BorderLayout());

    JPanel panelLeft = new JPanel();
    JPanel panelRight = new JPanel();

    String[] Names = {"Волшебная гора", "Театр", "Портрет Дориана Грея", "Мария Стюарт", "Исповедь маски", "Простаки за границей", "Скверный анекдот",
                      "Великий Гэтсби", "Былое и думы", "Путешествие по Италии"};
    String[] Authors = {"Томас Манн", "Сомерсет Моэм", "Оскар Уайльд", "Шилле Фридрих", "Юкио Мисима", "Марк Твен", "Фёдор Достоевский",
                        "Фрэнсис Скотт Фицджеральд", "Александр Герцен", "Ипполит Тэн"};

    

    Stack stack = new Stack();
    Queue queue = new Queue();
    Deque deque = new Deque();
    
    showMemory();
    
    //====================================================================

    infoArea = new JTextArea(40, 40);
    infoArea.setLineWrap(true);
    infoArea.setWrapStyleWord(true);
    JScrollPane scrollPane = new JScrollPane(infoArea);

    panelRight.add(scrollPane);

    //====================================================================

    JPanel inputDataPanel = new JPanel(new GridLayout(3, 2, 0, 5));

    JLabel bookNameLabel = new JLabel("Book");
    JTextField bookNameField = new JTextField(15);
    
    JLabel bookAuthorLabel = new JLabel("Author");
    JTextField bookAuthorField = new JTextField(15);

    JLabel bookCountOfPagesLabel = new JLabel("Pages");
    JTextField bookCountOfPagesField = new JTextField(15);

    inputDataPanel.add(bookNameLabel);
    inputDataPanel.add(bookNameField);
    inputDataPanel.add(bookAuthorLabel);
    inputDataPanel.add(bookAuthorField);
    inputDataPanel.add(bookCountOfPagesLabel);
    inputDataPanel.add(bookCountOfPagesField);

    panelLeft.add(inputDataPanel, BorderLayout.NORTH);

    //====================================================================

    
    JPanel objectSizePanel = new JPanel(new FlowLayout());
    objectSizePanel.setBorder(BorderFactory.createTitledBorder("Object size"));
    ButtonGroup objectSizeGroup = new ButtonGroup();

    JRadioButton smallObjectSize = new JRadioButton("small");
    JRadioButton bigObjectSize = new JRadioButton("big");

    objectSizePanel.add(smallObjectSize);
    objectSizePanel.add(bigObjectSize);
    objectSizeGroup.add(smallObjectSize);
    objectSizeGroup.add(bigObjectSize);

    panelLeft.add(objectSizePanel, BorderLayout.CENTER);

    //==========

    JPanel garbageCollectorPanel = new JPanel(new FlowLayout());
    garbageCollectorPanel.setBorder(BorderFactory.createTitledBorder("Garbage collector"));
    ButtonGroup garbageCollectorGroup = new ButtonGroup();

    JRadioButton autoGarbageCollector = new JRadioButton("auto");
    JRadioButton forcedGarbageCollector = new JRadioButton("forced");

    garbageCollectorPanel.add(autoGarbageCollector);
    garbageCollectorPanel.add(forcedGarbageCollector);
    garbageCollectorGroup.add(autoGarbageCollector);
    garbageCollectorGroup.add(forcedGarbageCollector);

    panelLeft.add(garbageCollectorPanel, BorderLayout.SOUTH);

    //====================================================================

    ActionListener buttonActionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (smallObjectSize.isSelected()) {
                o = 0;
            } else if (bigObjectSize.isSelected()) {
                o = 1;
            } else if (autoGarbageCollector.isSelected()) {
                g = 0;
            } else if (forcedGarbageCollector.isSelected()) {
                g = 1;
            }
        }
    };

    //====================================================================

    JPanel structuresPanel = new JPanel();

    //====================================================================
    JButton clearButton = new JButton("clear");
    clearButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            infoArea.selectAll();
            infoArea.replaceSelection("");
        }
    });
    structuresPanel.add(clearButton);

    //====================================================================

    

    JButton random = new JButton("random");
    random.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            bookNameField.setText(Names[rand.nextInt(0, 10)]);
            bookAuthorField.setText(Authors[rand.nextInt(0, 10)]);
            bookCountOfPagesField.setText(Integer.toString(rand.nextInt(200,3000)));
        }
    });
    structuresPanel.add(random);

    JLabel stackLabel = new JLabel("Stack");
    structuresPanel.add(stackLabel);

    JButton pushStack = new JButton("push");
    pushStack.addActionListener(buttonActionListener);
    pushStack.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String name = bookNameField.getText();
            String author = bookAuthorField.getText();
            int pages = Integer.parseInt(bookCountOfPagesField.getText());
            Book book = new Book(name, author, pages, 0);
            stack.push(book);
            infoArea.append("Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n");
            showMemory();
        }
    });
    structuresPanel.add(pushStack);

    JButton popStack = new JButton("pop");
    popStack.addActionListener(buttonActionListener);
    popStack.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Book book = (Book)stack.pop();
            if (book == null)
            {
                infoArea.append("List of books is empty\n");
            }
            else
            {
                infoArea.append("The book was extracted\n" + "Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n");
            }
            if (g == 1) System.gc();
            showMemory();
        }
    });
    structuresPanel.add(popStack);

    //==============================================================================================

    JLabel queueLabel = new JLabel("Queue");
    structuresPanel.add(queueLabel);

    JButton pushQueue = new JButton("push");
    pushQueue.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String name = bookNameField.getText();
            String author = bookAuthorField.getText();
            int pages = Integer.parseInt(bookCountOfPagesField.getText());
            Book book = new Book(name, author, pages, 0);
            queue.push(book); 
            infoArea.append("Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
        }
    });
    pushQueue.addActionListener(buttonActionListener);
    structuresPanel.add(pushQueue);

    JButton popQueue = new JButton("pop");
    popQueue.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Book book = (Book)queue.pop();
            if (book == null)
            {
                infoArea.append("List of books is empty\n\n");
            }
            else
            {
                infoArea.append("The book was extracted\n" + "Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
            }
        }
    });
    popQueue.addActionListener(buttonActionListener);
    structuresPanel.add(popQueue);

    //==============================================================================================

    JLabel dequeLabel = new JLabel("Deque");
    structuresPanel.add(dequeLabel);

    JButton pushBackDeque = new JButton("push_back");
    pushBackDeque.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String name = bookNameField.getText();
            String author = bookAuthorField.getText();
            int pages = Integer.parseInt(bookCountOfPagesField.getText());
            Book book = new Book(name, author, pages, 0);
            deque.push_back(book); 
            infoArea.append("Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
        }
    });
    structuresPanel.add(pushBackDeque);

    JButton pushFrontDeque = new JButton("push_front");
    pushFrontDeque.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            String name = bookNameField.getText();
            String author = bookAuthorField.getText();
            int pages = Integer.parseInt(bookCountOfPagesField.getText());
            Book book = new Book(name, author, pages, 0);
            deque.push_front(book); 
            infoArea.append("Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
        }
    });
    structuresPanel.add(pushFrontDeque);

    JButton popFrontDeque = new JButton("pop_front");
    popFrontDeque.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Book book = (Book)deque.pop_front();
            if (book == null)
            {
                infoArea.append("List of books is empty\n\n");
            }
            else
            {
                infoArea.append("The book was extracted\n" + "Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
            }
        }
    });
    structuresPanel.add(popFrontDeque);

    JButton popBackQueue = new JButton("pop_back");
    popBackQueue.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            Book book = (Book)deque.pop_back();
            if (book == null)
            {
                infoArea.append("List of books is empty\n\n");
            }
            else
            {
                infoArea.append("The book was extracted\n" + "Name: " + book.Name + '\n' + "Author: " + book.Author + '\n' + "Pages: " + book.Pages + "\n\n");
            }
        }
    });
    structuresPanel.add(popBackQueue);
    
    mainPanel.add(panelLeft, BorderLayout.WEST);
    mainPanel.add(panelRight, BorderLayout.EAST);
    mainPanel.add(structuresPanel, BorderLayout.SOUTH);

    frame.add(mainPanel);

    frame.setLocationRelativeTo(null);
    frame.setVisible(true);
}
}
