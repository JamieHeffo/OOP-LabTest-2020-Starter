package ie.tudublin;

import processing.core.PApplet;
import java.util.ArrayList;
import processing.data.*;

public class Gantt extends PApplet
{
	//Global Variables
	private boolean isEnd = false;
	private int whichTask = -1;
	private int maxMonths = 30;

	private float border = 40;
	private float rowHeight = 40;
	float namesPart = 150;

	//Declare an ArrayList to hold instances of the Task class
	ArrayList <Task> tasks = new ArrayList <Task> ();
	
	public void settings()
	{
		size(800, 600);
	}

	//Write a method called loadTasks that populates the ArrayList from the file tasks.csv
	public void loadTasks()
	{
		Table table = loadTable("tasks.csv", "header");

		for(TableRow row:table.rows())
		{
			Task task = new Task(row);
			tasks.add(task);
		}
	}

	public void printTasks()
	{
		for(Task t:tasks)
		{	
			println(t);
		}
	}
	
	public void mousePressed()
	{
		println("Mouse pressed");	
	}

	public void mouseDragged()
	{
		println("Mouse dragged");
	}

	//Write a method called displayTasks() that displays the tasks as in the video.
	public void displayTasks()
	{
		textSize(14);
		textAlign(LEFT, CENTER);

		textAlign(CENTER, CENTER);
		stroke(128);

		//Draw Vertical Lines and Numbers
		for(int i = 1; i <= maxMonths; i++)
		{
			float x = map(i, 1, maxMonths, namesPart, width - border);
			line(x, border, x, height - border);
			fill(255);
			text(i, x, border * 0.5f);
		}

		textAlign(LEFT, CENTER);

		for(int i = 0; i < tasks.size(); i++)
		{
			//print the name of the task
			float y = border + border + rowHeight * i;
			fill(255);
			text(tasks.get(i).getName(), border, y);

			//Print the task iteself
			noStroke();
			fill(map(i, 0, tasks.size(), 0, 255), 255, 255);
			float x1 = map(tasks.get(i).getStart(), 1, maxMonths, namesPart, width - border);
			float x2 = map(tasks.get(i).getEnd(), 1, maxMonths, namesPart, width - border);
			rect(x1, y - 15, x2 - x1, rowHeight - 5, 5.0f);
		}
	}
	
	
	public void setup() 
	{
		//Call these methods from setup()
		loadTasks();
		printTasks();
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
