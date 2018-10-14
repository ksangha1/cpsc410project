package ast;

import models.POSITION;

import java.util.ArrayList;
import java.util.Stack;
public class POSITIONServices extends STATEServices {

    String dir;
    String base;
    private Stack<String> stack = new Stack<>();
    private ArrayList<POSITION> positions = new ArrayList<>();

    @Override
    public void parse()  {
        //tokenizer.getAndCheckNext("TRANSITION");

    }

    @Override
    public void evaluate()  {
    }
}
