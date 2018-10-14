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
        this.value = value;
    }

    public void setEnd(String end)
    {
        this.end = end;
    }

    public String getStart(){return start;}
    public String getValue(){return value;}
    public String getEnd(){return end;}
}
