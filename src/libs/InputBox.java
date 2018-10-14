package libs;

import ast.POSITIONServices;
import ast.STATEServices;
import ast.TRANSITIONServices;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.*;
import java.util.Arrays;
import java.util.List;

public class InputBox extends JFrame {

    JButton inputButton;

    public InputBox(){

        super();
        setSize(800, 450);
        setLayout(new BorderLayout());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel inputWindow = new JPanel();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setPreferredSize(new Dimension(800,200));

        inputWindow.setSize(800, 200);
        inputWindow.setLayout(new GridLayout(3, 1));
        inputWindow.setAutoscrolls(true);

        inputWindow.add(new JLabel("Enter input for finite automata"));
        JTextArea inputDSL = new JTextArea();
        inputDSL.setAutoscrolls(true);
        scrollPane.setAutoscrolls(true);
        inputDSL.setSize(1000, 450);
        DefaultCaret caret = (DefaultCaret)inputDSL.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scrollPane.add(inputDSL);
        inputWindow.add(scrollPane);

        inputButton  = new JButton("Input");
        inputButton.setSize(800, 250);
        inputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                System.out.println(inputDSL.getText());
                List<String> literals = Arrays.asList("STATE", "TRANSITION", "POSITION", "(", ")");
                Tokenizer.makeTokenizer(literals,inputDSL.getText());
                Latex latex = new Latex();
                StringBuilder sb = latex.getSb();
                System.out.println(sb.toString());
                STATEServices state = new STATEServices();
                TRANSITIONServices transition = new TRANSITIONServices();
                POSITIONServices position = new POSITIONServices();
                state.parse();
                transition.parse();
                position.parse();
                state.evaluate();

            }
        });
        inputWindow.add(inputButton);

        this.add(inputWindow, BorderLayout.CENTER);
        setVisible(true);
    }
}
