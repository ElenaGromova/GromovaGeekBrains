package lesson6;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TestCalculator extends Component {
    public static String last;
    public static ActionListener math = new MathOperation();
    public static ActionListener result = new Result();
    static JTextField chislo1 = new JTextField ();
    static JTextField chislo2 = new JTextField ();
    static JLabel text1 = new JLabel("Введите первое число: ");
    static JLabel text2 = new JLabel("Введите второе число: ");
    static JLabel res = new JLabel("Результат вычисления: ");


    public static void createPanelUI(Container container)
    {
        container.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
        container.setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        // По умолчанию натуральная высота, максимальная ширина
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = 200;
        constraints.gridy   = 0  ;  // нулевая ячейка таблицы по вертикали
///////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 0;      // нулевая ячейка таблицы по горизонтали
        container.add(text1, constraints);

        //JTextField chislo1 = new JTextField ();
        chislo1.setPreferredSize(new Dimension(200, 30));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx = 1;      // первая ячейка таблицы по горизонтали
        container.add(chislo1, constraints);

////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 0;    // первая ячейка по горизонтали
        constraints.gridy     = 1;     // нулевая ячейка таблицы по горизонтали
        container.add(text2, constraints);

        //JTextField chislo2 = new JTextField ();
        chislo2.setPreferredSize(new Dimension(200, 30));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 1;    // нулевая ячейка по горизонтали
        constraints.gridy     = 1;      // первая ячейка таблицы по горизонтали
        container.add(chislo2, constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 0;    // первая ячейка по горизонтали
        constraints.gridy     = 2;     // нулевая ячейка таблицы по горизонтали
        container.add(res, constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 0;    // первая ячейка по горизонтали
        constraints.gridy     = 3;     // нулевая ячейка таблицы по горизонтали
        container.add(res, constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 0;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("+", math), constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 1;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("-", math), constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 2;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("/", math), constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 3;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("*", math), constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 4;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("x2", math), constraints);
////////////////////////////////////////////////
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.gridx     = 5;    // первая ячейка по горизонтали
        constraints.gridy     = 4;     // нулевая ячейка таблицы по горизонтали
        container.add(Button("Ok", result), constraints);

    }

    private static JButton Button(String name, ActionListener listener){
        JButton button = new JButton(name);
        button.addActionListener(listener);
        return button;
    }

    static class MathOperation implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            last = event.getActionCommand();
            System.out.println(last);
            double x = Double.valueOf(chislo1.getText());
            double y = Double.valueOf(chislo2.getText());
            switch (last) {
                case "+": res.setText("Результат вычисления: " + (x + y)); res.getText(); break;
                case "-": res.setText("Результат вычисления: " + (x - y)); res.getText(); break;
                case "*": res.setText("Результат вычисления: " + (x * y)); res.getText(); break;
                case "/": res.setText("Результат вычисления: " + (x / y)); res.getText(); break;
                case "x2": res.setText("Результат вычисления: " + Math.pow(x, y)); res.getText(); break;
            }
        }
    }

    public static class Result implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            res.setText("Результат вычисления: ");
            res.getText();
            chislo1.setText("0");
            chislo1.getText();
            chislo2.setText("0");
            chislo2.getText();
        }
    }

        public static void main (String [] args) {
        JFrame calculator = new JFrame("Calculator");
        calculator.setSize(400, 400);
        calculator.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Определить панель содержания
        createPanelUI(calculator.getContentPane());

        // Показать окно

         calculator.pack();
         calculator.setVisible(true);
        //frame.getContentPane().add(new CalculatorPanel());
    }

}
