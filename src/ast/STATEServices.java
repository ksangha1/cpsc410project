package ast;

import libs.Node;
import models.STATE;
import ui.Main;

import java.util.ArrayList;
import java.util.Stack;

public class STATEServices extends Node {
    String name;
    String status;
    private Stack<String> stack = new Stack<>();
    public static ArrayList<STATE> nodes = new ArrayList<>();


    @Override
    public void parse()  {
        name = status = "";
        tokenizer.getAndCheckNext("STATE");
        while(!tokenizer.checkToken("TRANSITION")) {
            String current = tokenizer.getNext();
            if (current.equals("(")) stack.push(current);
            else {
                if (current.equals(")")) stack.pop();
                if (current.equals(")") && stack.size() == 1) {
                    STATE temp = new STATE();
                    if(!status.isEmpty()) {
                        int remove = status.length();
                        this.status = status.substring(0, remove - 1);
                    }
                    temp.setName(this.name);
                    temp.setStatus(this.status);
                    this.name = "";
                    this.status = "";
                    nodes.add(temp);
                } else {
                    if (current.contains("accept") || current.equals("start")) {
                        this.status += current + ",";
                    } else if(!Main.literals.contains(current) && !current.equals(" ")) {
                        name = current;
                    }

                }
            }
        }
        System.out.println("Reached end of STATE Parsing!");
    }

    @Override
    public StringBuilder evaluate()  {
        System.out.println("in STATE evaluation ");
        for (STATE s: nodes) {
            System.out.println("STATE EVALUATION: state name:"+s.getName() +"\n  status: "+s.getStatus());
            //check status
            if(s.getStatus()!="") {
                if (!s.getStatus().contains("start") && !s.getStatus().contains("accept"))
                    throw new java.lang.Error("INPUT ERROR: invalid status");
            }
        }
        return new StringBuilder();
    }
}
