/* 
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class Data {
    private String value;

    public Data(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}

class StackNode {
    private Data data;
    private StackNode next;

    public StackNode(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public StackNode getNext() {
        return next;
    }

    public void setNext(StackNode next) {
        this.next = next;
    }
}

class Stack {
    private StackNode top;

    public void push(Data data) {
        StackNode newNode = new StackNode(data);
        newNode.setNext(top);
        top = newNode;
    }

    public Data pop() {
        if (isEmpty()) {
            return null;
        }
        Data data = top.getData();
        top = top.getNext();
        return data;
    }

    public boolean isEmpty() {
        return top == null;
    }
}

class QueueNode {
    private Data data;
    private QueueNode next;

    public QueueNode(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public QueueNode getNext() {
        return next;
    }

    public void setNext(QueueNode next) {
        this.next = next;
    }
}

class Queue {
    private QueueNode front;
    private QueueNode rear;

    public void enqueue(Data data) {
        QueueNode newNode = new QueueNode(data);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else {
            rear.setNext(newNode);
            rear = newNode;
        }
    }

    public Data dequeue() {
        if (isEmpty()) {
            return null;
        }
        Data data = front.getData();
        front = front.getNext();
        if (front == null) {
            rear = null;
        }
        return data;
    }

    public boolean isEmpty() {
        return front == null;
    }
}

class DoublyLinkedListNode {
    private Data data;
    private DoublyLinkedListNode prev;
    private DoublyLinkedListNode next;

    public DoublyLinkedListNode(Data data) {
        this.data = data;
    }

    public Data getData() {
        return data;
    }

    public DoublyLinkedListNode getPrev() {
        return prev;
    }

    public void setPrev(DoublyLinkedListNode prev) {
        this.prev = prev;
    }

    public DoublyLinkedListNode getNext() {
        return next;
    }

    public void setNext(DoublyLinkedListNode next) {
        this.next = next;
    }
}

class DoublyLinkedList {
    private DoublyLinkedListNode head;
    private DoublyLinkedListNode tail;

    public void insertAtHead(Data data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setNext(head);
            head.setPrev(newNode);
            head = newNode;
        }
    }

    public void insertAtTail(Data data) {
        DoublyLinkedListNode newNode = new DoublyLinkedListNode(data);
        if (isEmpty()) {
            head = newNode;
            tail = newNode;
        } else {
            newNode.setPrev(tail);
            tail.setNext(newNode);
            tail = newNode;
        }
    }

    public Data deleteAtHead() {
        if (isEmpty()) {
            return null;
        }
        Data data = head.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            head = head.getNext();
            head.setPrev(null);
        }
        return data;
    }

    public Data deleteAtTail() {
        if (isEmpty()) {
            return null;
        }
        Data data = tail.getData();
        if (head == tail) {
            head = null;
            tail = null;
        } else {
            tail = tail.getPrev();
            tail.setNext(null);
        }
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }
}

class GUI {
    private JFrame frame;
    private JTextArea outputTextArea;
    private JButton stackPushButton;
    private JButton stackPopButton;
    private JButton queuePushButton;
    private JButton queuePopButton;
    private JButton doublyLinkedListInsertButton;
    private JButton doublyLinkedListDeleteButton;
    private JPanel panel;

    private Stack stack;
    private Queue queue;
    private DoublyLinkedList doublyLinkedList;

    public GUI() {
        frame = new JFrame("Data Structures");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        outputTextArea = new JTextArea(20, 40);
        outputTextArea.setEditable(false);

        stackPushButton = new JButton("Stack: Push");
        stackPushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                pushToStack();
            }
        });

        stackPopButton = new JButton("Stack: Pop");
        stackPopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                popFromStack();
            }
        });

        queuePushButton = new JButton("Queue: Push");
        queuePushButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enqueueToQueue();
            }
        });

        queuePopButton = new JButton("Queue: Pop");
        queuePopButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dequeueFromQueue();
            }
        });

        doublyLinkedListInsertButton = new JButton("Doubly Linked List: Insert");
        doublyLinkedListInsertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                insertToDoublyLinkedList();
            }
        });

        doublyLinkedListDeleteButton = new JButton("Doubly Linked List: Delete");
        doublyLinkedListDeleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                deleteFromDoublyLinkedList();
            }
        });

        panel = new JPanel();
        panel.add(stackPushButton);
        panel.add(stackPopButton);
        panel.add(queuePushButton);
        panel.add(queuePopButton);
        panel.add(doublyLinkedListInsertButton);
        panel.add(doublyLinkedListDeleteButton);

        frame.getContentPane().add(outputTextArea, BorderLayout.CENTER);
        frame.getContentPane().add(panel, BorderLayout.SOUTH);
        frame.pack();
        frame.setVisible(true);

        stack = new Stack();
        queue = new Queue();
        doublyLinkedList = new DoublyLinkedList();
    }

    private void pushToStack() {
        stack.push(new Data("Stack: Element"));
        updateOutput();
    }

    private void popFromStack() {
        Data data = stack.pop();
        if (data != null) {
            outputTextArea.append("Popped from Stack: " + data.getValue() + "\n");
        } else {
            outputTextArea.append("Stack is empty\n");
        }
    }
    
    private void enqueueToQueue() {
        queue.enqueue(new Data("Queue: Element"));
        updateOutput();
    }

    private void dequeueFromQueue() {
        Data data = queue.dequeue();
        if (data != null) {
            outputTextArea.append("Dequeued from Queue: " + data.getValue() + "\n");
        } else {
            outputTextArea.append("Queue is empty\n");
        }
    }

    private void insertToDoublyLinkedList() {
        doublyLinkedList.insertAtHead(new Data("Doubly Linked List: Element"));
        updateOutput();
    }

    private void deleteFromDoublyLinkedList() {
        Data data = doublyLinkedList.deleteAtHead();
        if (data != null) {
            outputTextArea.append("Deleted from Doubly Linked List: " + data.getValue() + "\n");
        } else {
            outputTextArea.append("Doubly Linked List is empty\n");
        }
    }
    
    private void updateOutput() {
        outputTextArea.setText("");
        outputTextArea.append("Stack:\n");
        StackNode currentStackNode = stack.getTop();
        while (currentStackNode != null) {
            outputTextArea.append(currentStackNode.getData().getValue() + "\n");
            currentStackNode = currentStackNode.getNext();
        }
        outputTextArea.append("\nQueue:\n");
        QueueNode currentQueueNode = queue.getFront();
        while (currentQueueNode != null) {
            outputTextArea.append(currentQueueNode.getData().getValue() + "\n");
            currentQueueNode = currentQueueNode.getNext();
        }
        outputTextArea.append("\nDoubly Linked List:\n");
        DoublyLinkedListNode currentDoublyLinkedListNode = doublyLinkedList.getHead();
        while (currentDoublyLinkedListNode != null) {
            outputTextArea.append(currentDoublyLinkedListNode.getData().getValue() + "\n");
            currentDoublyLinkedListNode = currentDoublyLinkedListNode.getNext();
        }
    }
    
}

public class App {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new GUI();
            }
        });
    }
}
*/

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class App {
    public static void main(String[] args) {
        // Создаем экземпляр стека
        Stack stack = new Stack();

        // Измеряем начальный размер памяти
        long initialMemory = getUsedMemory();

        // Добавляем незначительные объекты в стек
        for (int i = 0; i < 100; i++) {
            stack.push(i);
        }

        // Измеряем размер памяти после добавления незначительных объектов
        long memoryAfterAddingSmallObjects = getUsedMemory();

        // Добавляем элемент, содержащий массив из ста тысяч целочисленных элементов
        int[] largeArray = new int[100000];
        stack.push(largeArray);

        // Измеряем размер памяти после добавления элемента с большим массивом
        long memoryAfterAddingLargeObject = getUsedMemory();

        // Выводим результаты
        System.out.println("Размер памяти перед добавлением: " + initialMemory + " байт");
        System.out.println("Размер памяти после добавления незначительных объектов: " + memoryAfterAddingSmallObjects + " байт");
        System.out.println("Размер памяти после добавления объекта с большим массивом: " + memoryAfterAddingLargeObject + " байт");

        // Сравниваем с использованием диспетчера задач
        long taskManagerMemory = getTaskManagerMemory();
        System.out.println("Использование памяти в диспетчере задач: " + taskManagerMemory + " байт");
    }

    // Метод для измерения размера выделенной памяти
    private static long getUsedMemory() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    // Метод для измерения использования памяти в диспетчере задач (для Linux)
    private static long getTaskManagerMemory() {
        try {
            Process process = Runtime.getRuntime().exec("ps -p $$ -o rss=");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = reader.readLine();
            if (line != null) {
                long memoryInKb = Long.parseLong(line.trim());
                return memoryInKb * 1024; // Конвертируем в байты
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }
}