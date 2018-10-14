package ast;

import libs.Node;

import java.util.*;

import libs.Tokenizer;
import models.STATE;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;

public class STATEServices extends Node {
    String name;
    String status;
    private Stack<String> stack = new Stack<>();
    private ArrayList<STATE> nodes = new ArrayList<>();


    @Override
    public void parse()  {
        tokenizer.getAndCheckNext("STATE");
        boolean end = false;
        while(!tokenizer.checkToken("TRANSITION")) {
            String current = tokenizer.getNext();
            if (current.equals("(")) stack.push(current);
            else {
                if (current.equals(")")) stack.pop();
                if (current.equals(")") && stack.size() == 1) {
                    STATE temp = new STATE();
                    temp.setName(this.name);
                    temp.setStatus(this.status);
                    this.name = "";
                    this.status = "";
                    nodes.add(temp);
                } else {
                    if (current.equals("accept") || current.equals("start")) {
                        status = current;
                    } else if(!current.equals("(") && !current.equals(")")) {
                        name = current;
                    }
                }
            }
        }
        System.out.println("Reached end of STATE Parsing!");
    }

    @Override
    public void evaluate()  {
        for (STATE s: nodes) {

        }
    }
}
