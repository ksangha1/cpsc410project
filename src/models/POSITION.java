package models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class POSITION {

    String base;
    String dir;
    String node;
    static ArrayList<String> validDirs = new ArrayList<>();

    public POSITION(){
        validDirs.add("N");
        validDirs.add("S");
        validDirs.add("E");
        validDirs.add("W");
        validDirs.add("SW");
        validDirs.add("SE");
        validDirs.add("NE");
        validDirs.add("NW");
    }

    public void setBase(String base)
    {
        this.base = base;
    }
    public void setDir(String dir)
    {
        this.dir = dir;
    }
    public void setNode(String node)
    {
        this.node = node;
    }

    public String getBase(){return this.base;}
    public String getDir(){return this.dir;}
    public String getNode(){return this.node;}

    public static  boolean checkDir(String dir)
    {
        return validDirs.contains(dir);
    }

}
