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

		loadTasks();
		printTasks();
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
	
	/*
	Write code that allows a user to alter the start day and end day of a task by clicking and dragging on the start or end of a task with the mouse
	(20 pixels after the start or 20 pixels before the end of the rectangle.
	The user should not be able to set the start or end of the task beyond the range 1-30 and also
	should not be able to set the duration to be less than 1.
	*/
	public void mousePressed()
	{
		for(int i = 0; i < tasks.size(); i++)
		{
			float y1 = (border + border + rowHeight * i) - 15;
			float y2 = (border + border + rowHeight * i) + 15;

			float x1 = map(tasks.get(i).getStart(), 1, maxMonths, namesPart, width - border);
			float x2 = map(tasks.get(i).getEnd(), 1, maxMonths, namesPart, width - border);

			if(mouseX >= x1 && mouseX <= x1 + 20 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = true;
				return;
			}

			if(mouseX <= x2 && mouseX >= x2 - 20 && mouseY >= y1 && mouseY <= y2)
			{
				whichTask = i;
				isEnd = true;
				return;
			}

		}
		//default value for whichtask
		whichTask = -1;
	}

	public void mouseDragged()
	{
		if (whichTask != -1)
		{
			int month = (int)map(mouseX, namesPart, width - border, 1, maxMonths);

			if (month >= 1 && month <= maxMonths)
			{
				Task task = tasks.get(whichTask);
				if(isEnd){
					if(month - task.getStart() > 0){
						task.setEnd(month);
					}
				}
				else{
					if(task.getEnd() - month > 0){
						task.setStart(month);
					}
				}
			}
		}
	}

	//Write a method called displayTasks() that displays the tasks as in the video.
	void displayTasks()
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
		colorMode(HSB);
	}
	
	public void draw()
	{			
		background(0);
		displayTasks();
	}
}
