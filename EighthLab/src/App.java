import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Класс для описания вершины бинарного дерева
class BinaryTreeNode {
    String data;
    BinaryTreeNode left;
    BinaryTreeNode right;

    public BinaryTreeNode(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

// Класс для построения бинарного дерева поиска
class BinarySearchTree {
    private BinaryTreeNode root;

    public BinarySearchTree() {
        this.root = null;
    }

    public void insert(String data) {
        this.root = insertRecursive(this.root, data);
    }

    private BinaryTreeNode insertRecursive(BinaryTreeNode root, String data) {
        if (root == null) {
            return new BinaryTreeNode(data);
        }

        if (data.compareTo(root.data) < 0) {
            root.left = insertRecursive(root.left, data);
        } else if (data.compareTo(root.data) > 0) {
            root.right = insertRecursive(root.right, data);
        }

        return root;
    }

    public void printInOrder() {
        printInOrderRecursive(this.root);
    }

    private void printInOrderRecursive(BinaryTreeNode root) {
        if (root != null) {
            printInOrderRecursive(root.left);
            System.out.print(root.data + " ");
            printInOrderRecursive(root.right);
        }
    }

    public BinaryTreeNode getRoot() {
        return root;
    }
}

// Класс для создания линейного списка на основе файла
class LinkedList {
    private Node head;

    class Node {
        String data;
        Node next;

        public Node(String data) {
            this.data = data;
            this.next = null;
        }
    }

    public LinkedList() {
        this.head = null;
    }

    public void insert(String data) {
        Node newNode = new Node(data);
        if (this.head == null) {
            this.head = newNode;
        } else {
            Node temp = this.head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
        }
    }

    public void printList() {
        Node temp = this.head;
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.next;
        }
        System.out.println();
    }
    
    public Node getHead() {
        return head;
    }
}

// Класс для визуализации бинарного дерева с помощью JTree
class TreeVisualizer extends JFrame {
    private JPanel contentPane;

    public TreeVisualizer(BinarySearchTree binarySearchTree) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(400, 300));
        contentPane = new JPanel();
        setContentPane(contentPane);

        DefaultMutableTreeNode rootNode = createTree(binarySearchTree.getRoot());
        JTree tree = new JTree(rootNode);
        JScrollPane scrollPane = new JScrollPane(tree);
        contentPane.add(scrollPane);
    }

    private DefaultMutableTreeNode createTree(BinaryTreeNode node) {
        if (node == null) {
            return null;
        }

        DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node.data);

        if (node.left != null) {
            DefaultMutableTreeNode leftNode = createTree(node.left);
            treeNode.add(leftNode);
        }

        if (node.right != null) {
            DefaultMutableTreeNode rightNode = createTree(node.right);
            treeNode.add(rightNode);
        }

        return treeNode;
    }
}

public class App {
    public static void main(String[] args) {
        // Создание бинарного дерева поиска из текстового файла
        BinarySearchTree binarySearchTree = createBinarySearchTreeFromFile("input.txt");

        // Построение линейного списка на основе файла
        LinkedList linkedList = createLinkedListFromFile("input.txt");

        // Вывод содержимого линейного списка
        System.out.println("Содержимое линейного списка:");
        linkedList.printList();

        // Построение бинарного дерева по словам из линейного списка
        BinarySearchTree listToTree = createBinarySearchTreeFromList(linkedList);

        // Вывод содержимого полученного бинарного дерева
        System.out.println("Содержимое бинарного дерева:");
        listToTree.printInOrder();

        // Визуализация полученного дерева с помощью JTree
        TreeVisualizer visualizer = new TreeVisualizer(listToTree);
        visualizer.setVisible(true);
    }

    private static BinarySearchTree createBinarySearchTreeFromFile(String filename) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    binarySearchTree.insert(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return binarySearchTree;
    }

    private static LinkedList createLinkedListFromFile(String filename) {
        LinkedList linkedList = new LinkedList();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split(" ");
                for (String word : words) {
                    linkedList.insert(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return linkedList;
    }

    private static BinarySearchTree createBinarySearchTreeFromList(LinkedList linkedList) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();
        LinkedList.Node current = linkedList.getHead();
        while (current != null) {
            binarySearchTree.insert(current.data);
            current = current.next;
        }
        return binarySearchTree;
    }
}