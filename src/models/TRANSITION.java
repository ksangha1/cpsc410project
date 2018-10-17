package models;

public class TRANSITION {
    String start;
    String value;
    String end;

    public TRANSITION()
    {

    }

    public void setStart(String start)
    {
        this.start = start;
    }

    public void setValue(String value)
    {
        /* Removing extra "," char */
        int last = value.length();
        this.value = value.substring(0,last-1);
    }

    public void setEnd(String end)
    {
        this.end = end;
    }

    public String getStart(){return start;}
    public String getValue(){return value;}
    public String getEnd(){return end;}
}
