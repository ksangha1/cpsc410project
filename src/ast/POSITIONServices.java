package ast;

import models.POSITION;
import ui.Main;

import java.util.ArrayList;
import java.util.Stack;
public class POSITIONServices extends STATEServices {

    String dir;
    String base;
    String node;
    private Stack<String> stack = new Stack<>();
    private ArrayList<POSITION> positions = new ArrayList<>();

    @Override
    public void parse()  {
        tokenizer.getAndCheckNext("POSITION");
        dir=base=node= "";
        while(tokenizer.moreTokens()) {
            String current = tokenizer.getNext();
            if(current.equals("(")) stack.push(")");
            else {
                if (current.equals(")")) stack.pop();
                if(current.equals(")") && stack.size() == 0) {
                    POSITION pos = new POSITION();
                    pos.setBase(this.base);
                    pos.setDir(this.dir);
                    pos.setNode(this.node);
                    positions.add(pos);
                    dir = base = node = "";
                }
                else if(!Main.literals.contains(current) && !current.equals(" ")) {
                    if(node.isEmpty()) node = current;
                    else if(dir.isEmpty()) dir = current;
                    else if(base.isEmpty()) base = current;
                }
            }
        }
        System.out.println("Reached end of POSITION Statements!");

    }

    @Override
    public void evaluate()  {
    }
}
