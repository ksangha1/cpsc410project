package ast;

import libs.Node;
import models.STATE;
import models.TRANSITION;
import ui.Main;

import java.util.ArrayList;
import java.util.Stack;

public class TRANSITIONServices extends Node {

    private Stack<String> stack = new Stack<>();
    private ArrayList<TRANSITION> nodes = new ArrayList<>();
    private String start, value, dest;

    @Override
    public void parse() {
        //TODO: seems like not able to parse TRANSITION(q1,(0,1),q2) where multiple transition values took place
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
              if(!Main.literals.contains(current) && !current.equals(" ")) {
                  if (start.isEmpty()) {
                      start = current;
                  } else if (stack.size() == 2) {
                      value += current +",";
                  } else if (dest.isEmpty()) {
                      dest = current;
                  }
              }
          }
        }
        System.out.println("Reached end of TRANSITION Statements!");
    }

    @Override
    public StringBuilder evaluate(){
        StringBuilder sb_trans = new StringBuilder();
        for (TRANSITION t : nodes) {
            System.out.println("TRANSITION EVALUATION: start status: " + t.getStart() + "\n  destination status: " + t.getEnd() + "\n  value: " + t.getValue());

            //check status exist
            if (getState(t.getStart())==null) throw new java.lang.Error("INPUT ERROR: (POSITION)"+t.getStart()+" does not exist");
            if (getState(t.getEnd())==null) throw new java.lang.Error("INPUT ERROR: (POSITION):"+t.getEnd()+" does not exist");

            if(t.getStart().equals(t.getEnd()))
                //For edges that start and end in the same node (self-loops)
                sb_trans.append(sb_trans.append("\t\\draw ("+t.getStart()+") edge [->,loop above] node {\\tt "+t.getValue()+"} ("+t.getEnd()+");\n"));
            else
                sb_trans.append("\t\\draw ("+t.getStart()+") edge [->,bend left] node {\\tt "+t.getValue()+"} ("+t.getEnd()+");\n");
        }
        return sb_trans;
    }

    // return the STATE object corresponding to given status name
    public STATE getState(String statename){
        for (STATE s : STATEServices.nodes){
            if(s.getName().equals(statename))
                return s;
        }
        return null;
    }
}
