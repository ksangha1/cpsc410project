package ast;

import libs.Node;
import models.TRANSITION;

import java.util.Stack;
import java.util.ArrayList;

public class TRANSITIONServices extends Node {

    private Stack<String> stack = new Stack<>();
    private ArrayList<TRANSITION> nodes = new ArrayList<>();
    private String start, value, dest;

    @Override
    public void parse() {
        start = value = dest = "";
        tokenizer.getAndCheckNext("TRANSITION");
        while(!tokenizer.checkToken("POSITION"))
        {
          String current = tokenizer.getNext();
          if(current.equals("(")) stack.push(current);
          else{
              if(current.equals(")")) stack.pop();
              if(current.equals(")") && stack.size() == 0) {
                  TRANSITION temp = new TRANSITION();
                  temp.setStart(start);
                  temp.setEnd(dest);
                  temp.setValue(value);
                  nodes.add(temp);
                  start = value = dest = "";
              }
              if(!current.equals("(") && !current.equals(")") && !current.equals("TRANSITION")) {
                  if (start.isEmpty()) {
                      start = current;
                  } else if (value.isEmpty()) {
                      value = current;
                  } else if (dest.isEmpty()) {
                      dest = current;
                  }
              }
          }
        }
        System.out.println("Reached end of TRANSITION Statements!");
    }

    @Override
    public void evaluate() {

    }
}
