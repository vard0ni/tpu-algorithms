import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class App extends JFrame {
    private JTextField inputTextField;
    private JTextArea outputTextArea;
    private JTextArea countOfBytesTextArea;

    public App() {
        setTitle("Lab 4 - Data Types");
        setSize(800, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel contentPane = new JPanel();
        contentPane.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Enter a number or a list of numbers separated by commas: "));
        inputTextField = new JTextField(10);
        inputPanel.add(inputTextField);
        JButton convertButton = new JButton("Convert");
        inputPanel.add(convertButton);

        outputTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(outputTextArea);
        
        countOfBytesTextArea = new JTextArea();
        JScrollPane countOfBytesscrollPane = new JScrollPane(countOfBytesTextArea);

        contentPane.add(inputPanel, BorderLayout.NORTH);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        contentPane.add(countOfBytesscrollPane, BorderLayout.SOUTH);

        setContentPane(contentPane);

        convertButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    String input = inputTextField.getText();
                    String[] parts = input.split(",");
                    String result = "";
                    for (String part : parts) {
                        part = part.trim();
                        if (part.contains(".")) {
                            float num = Float.parseFloat(part);
                            //System.out.println(num);
                            result += printFloatRepresentation(num);
                            countOfBytesTextArea.setText(printCountOfBytesFloat(num));
                        } else {
                            long num = Long.parseLong(part);
                            //System.out.println(num);
                            result += printLongRepresentation(num);
                            countOfBytesTextArea.setText(printCountOfBytesLong(num));
                        }
                    }
                    outputTextArea.setText(result);
                    if (parts.length > 1)
                    {
                        long size = 0;
                        for (int i = 0; i < parts.length; i++) {
                            String str = parts[i];
                            if (str != null) {
                                size += 2 * str.length(); // 2 байта на символ для Unicode-строк
                            }
                        }
                        size += 12 + 4 * parts.length;
                        countOfBytesTextArea.setText("Count of bytes in array of strings: " + size);
                    }
                    
                } catch (NumberFormatException ex) {
                    outputTextArea.setText("Error: Invalid input");
                }
            }
        });
    }

    public String printFloatRepresentation(float num) {
        
        int binary = Float.floatToIntBits(num);
        System.out.println(binary);
        int sign = binary >>> 31;
        int exponent = (binary >>> 23) & 0xff;
        int mantissa = binary & 0x7fffff;
        
        String binary2 = Integer.toBinaryString(binary);
        String tmp = binary2;
        System.out.println(binary2);
        String sign2 = Integer.toBinaryString(sign);
        String exponent2 = Integer.toBinaryString(exponent);
        String mantissa2 = Integer.toBinaryString(mantissa);

        // определить знак числа
        int sign3 = binary2.charAt(0) == '0' ? 1 : -1;
        // Определить экспоненту по следующим 8 битам (в формате IEEE 754 для типа float).
        int exponent3 = Integer.parseInt(binary2.substring(1, 9), 2) - 127;
        // Определить мантиссу по следующим 23 битам.
        float mantissa3 = 1f;

        //float convert = binaryToFloat(binary2);
        if (sign == -1) {
            // Если число отрицательное, инвертируем все биты, кроме первого (знака)
            StringBuilder sb = new StringBuilder(binary2);
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == '0') {
                    sb.setCharAt(i, '1');
                } else {
                    sb.setCharAt(i, '0');
                }
            }
            binary2 = sb.toString();
        }
        
        
        for (int i = 9; i < binary2.length(); i++) { // определить мантиссу
            if (binary2.charAt(i) == '1') {
                mantissa3 += Math.pow(2, -1 * (i - 8));
                //mantissa3 += x / Math.pow(2, exponent);
            }

        }
    
        
        float convert = (float) (sign3 * mantissa3 * Math.pow(2, exponent3));

        String result = "Float Representation:\n" +
                        "Value: " + num + "\n" +
                        "Binary: " + tmp + "\n" +
                        "Sign: " + sign2 + "\n" +
                        "Exponent: " + exponent2 + "\n" +
                        "Mantissa: " + mantissa2 + "\n" +
                        "Convert from binary to float: " + binaryToFloat(tmp) + "\n";

        float reconstructed = Float.intBitsToFloat(binary);
        if (reconstructed != num) {
            result += "Error: Loss of precision in binary representation\n";
            result += "Recovered number: " + reconstructed;
        }
        result += "\n";
        return result;
    }
   
        public static float binaryToFloat(String binary) {
        int sign = 1;
        if (binary.charAt(0) == '1') {
            sign = -1;
            binary = twosComplement(binary);
        }

        int exponent = binaryToDecimal(binary.substring(1, 9)) - 127;

        float fraction = 1.0f;
        for (int i = 9; i < binary.length(); i++) {
            fraction += (binary.charAt(i) - '0') * Math.pow(2, -(i - 8));
        }

        return sign * fraction * (float) Math.pow(2, exponent);
    }

    private static String twosComplement(String binary) {
        StringBuilder sb = new StringBuilder();
        boolean carry = true;
        for (int i = binary.length() - 1; i >= 0; i--) {
            char c = binary.charAt(i);
            if (carry) {
                if (c == '1') {
                    sb.insert(0, '0');
                } else {
                    sb.insert(0, '1');
                    carry = false;
                }
            } else {
                sb.insert(0, c);
            }
        }
        return sb.toString();
    }

    private static int binaryToDecimal(String binary) {
        int decimal = 0;
        for (int i = binary.length() - 1, j = 0; i >= 0; i--, j++) {
            decimal += (binary.charAt(i) - '0') * Math.pow(2, j);
        }
        return decimal;
    }

    public String printLongRepresentation(long num) {
        String binary = String.format("%64s", Long.toBinaryString(num)).replace(' ', '0');
        String tmp = binary;
        System.out.println(binary);


        int sign = binary.charAt(0) == '0' ? 1 : -1;
        if (sign == -1) {
            StringBuilder sb = new StringBuilder(binary);
            for (int i = 1; i < sb.length(); i++) {
                if (sb.charAt(i) == '0') {
                    sb.setCharAt(i, '1');
                } else {
                    sb.setCharAt(i, '0');
                }
            }
            binary = sb.toString();
        }

        long convert = 0;
        for (int i = binary.length() - 1; i > 0; i--) {
            if (binary.charAt(i) == '1') {
                convert += Math.pow(2, binary.length() - i - 1);
            }
        }

        if (sign == -1) {
            convert *= -1;
            convert -= 1;
        }

        String result = "Long Representation:\n" +
                        "Value: " + num + "\n" +
                        "Binary: " + tmp + "\n" +
                        "Convert from binary to long: " + convert + "\n";

        result += "\n";
        return result;
    }

    public String printCountOfBytesFloat(float num) {
        int floatSize = Float.SIZE/8;
        String result = "Count of bytes in float: " + floatSize;
        return result;
    }
    
    public String printCountOfBytesLong(float num) {
        int longSize = Long.SIZE/8;
        String result = "Count of bytes in long: " + longSize;
        return result;
    }

    public static void main(String[] args) {
        App frame = new App();
        frame.setVisible(true);
    }
}
