package libs;

import ast.POSITIONServices;
import ast.STATEServices;
import ast.TRANSITIONServices;
import ui.Main;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InputBox extends JFrame {

    JButton inputButton;

    public InputBox() {

        super();
        setSize(1000, 800);
        setLayout(new BorderLayout());

        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel inputWindow = new JPanel();

         inputWindow.setSize(1000, 800);

        inputWindow.add(new JLabel("Enter input for finite automata"));
        JTextArea inputDSL = new JTextArea();
        inputDSL.setPreferredSize(new Dimension(1000,600));
        inputDSL.setAutoscrolls(true);
        DefaultCaret caret = (DefaultCaret)inputDSL.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        inputWindow.add(inputDSL);

        inputButton  = new JButton("Input");
        inputButton.setSize(1000, 200);
        inputButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println(inputDSL.getText());
                Tokenizer.makeTokenizer(Main.literals,inputDSL.getText());
                Latex latex = new Latex();
                STATEServices state = new STATEServices();
                TRANSITIONServices transition = new TRANSITIONServices();
                POSITIONServices position = new POSITIONServices();
                state.parse();
                transition.parse();
                position.parse();
                state.evaluate();
                latex.addSB(position.evaluate());
                latex.addSB(transition.evaluate());
                latex.endSB();
                StringBuilder sb = latex.getSb();
                System.out.println(sb);
                /* Output final Latex code to Window */
                JFrame output = new JFrame();
                output.setSize(800,600);
                output.setLayout(new BorderLayout());
                output.setDefaultCloseOperation(EXIT_ON_CLOSE);
                JPanel out = new JPanel();
                out.setPreferredSize(new Dimension(800,600));
                out.setLayout(new GridLayout(2,1));
                out.add(new JLabel("Output in Latex!"));
                JTextArea outputcode = new JTextArea();
                outputcode.setPreferredSize(new Dimension(800,500));
                outputcode.setText(sb.toString());
                out.add(outputcode);
                output.add(out, BorderLayout.CENTER);
                output.setVisible(true);

            }
        });
        inputWindow.add(inputButton);

        this.add(inputWindow, BorderLayout.CENTER);
        setVisible(true);
    }
}
