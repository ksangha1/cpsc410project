package ast;

import models.POSITION;
import models.STATE;
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
                    if(node.isEmpty()) node = current.replace(" ", "");
                    else if(dir.isEmpty()) dir = current.replace(" ", "");
                    else if(base.isEmpty()) base = current.replace(" ", "");
                }
            }
        }
        System.out.println("Reached end of POSITION Statements!");

    }

    @Override
    public StringBuilder evaluate()  {
        StringBuilder sb_pos = new StringBuilder();
        for (POSITION p : positions) {
            System.out.println("POSITION EVALUATION: base: "+p.getBase()+"\n  dir:"+p.getDir()+"\n  node"+p.getNode());

            //check status exist
            if (getState(p.getNode())==null) throw new java.lang.Error("POSITION:"+p.getNode()+" does not exist");
            if (getState(p.getBase())==null) throw new java.lang.Error("POSITION:"+p.getBase()+" does not exist");

            if(!getState(p.getBase()).isCreated()){
                // if base has not created, create it with random position
                STATE start = getState(p.getBase());
                String status="";
                if(start.getStatus()!=null)
                    status += translateStatus(start);

                sb_pos.append("\t\\node[state"+status+"] (" + start.getName() + ") {" + start.getName() + "};\n");
                start.setCreated();
            }

            if(!POSITION.checkDir(p.getDir()))
                throw new java.lang.Error("ERROR: invalid dir");

            //position node
            String latexDir = POSITION.mapDir(p.getDir());
            STATE dest = getState(p.getNode());
            String status="";
            if(dest.getStatus()!=null)
                status += translateStatus(dest);
            sb_pos.append("\t\\node[state"+status+", "+latexDir+" of="+p.getBase()+"] ("+p.getNode()+") {"+p.getNode()+"};\n");
            dest.setCreated();
        }
        return sb_pos;
    }

    // return the STATE object corresponding to given status name
    public STATE getState(String statename){
        for (STATE s : STATEServices.nodes){
            if(s.getName().equals(statename))
                return s;
        }
        return null;
    }

    // return the STATE object corresponding to given status name
    public String translateStatus(STATE s){
        String subsb="";
        if (s.getStatus().equals("start"))
            subsb += ", initial";
        else if (s.getStatus().equals("accept"))
            subsb += ", accepting";
        else if(s.getStatus().equals("intial accepting")) {
            //TODO: when both start and accept took place
        }
        return subsb;
    }
}
