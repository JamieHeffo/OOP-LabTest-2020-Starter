package ie.tudublin;

import processing.data.TableRow;

//Make a class called Task that encapsulates the columns from a single row from the tasks.csv file
public class Task {
    
    //Make private fields and public accessor methods.
    private String name;
    private int start;
    private int end;

    //create public accessor methods
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getStart() {
        return start;
    }
    public void setStart(int start) {
        this.start = start;
    }
    public int getEnd() {
        return end;
    }
    public void setEnd(int end) {
        this.end = end;
    }

    //Make a constructor that takes initial values and assigns them to the fields.
    public Task(String name, int start, int end) {
        this.name = name;
        this.start = start;
        this.end = end;
    }
    
    //Make a constructor that takes a Processing TableRow field as a parameter and make an appropriate toString method.
    public String toString() {
        return "\nTask : " + end + "\nName : " + name + "\nStart : " + start + "\n";
    }
    
    public Task(TableRow tr)
    {
        this(tr.getString("Task"), tr.getInt("Start"), tr.getInt("End"));
    }

    

    


    
}
