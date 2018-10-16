package models;

public class STATE {

    public STATE(){
        iscreated=false;
    };
    Boolean iscreated;
    String name;
    String status;

    public void setName(String name)
    {
        this.name = name;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getName()
    { return this.name;}

    public String getStatus()
    {return this.status;}

    public Boolean isCreated()
    {return this.iscreated;}

    public void create()
    { this.iscreated=true;}
}
