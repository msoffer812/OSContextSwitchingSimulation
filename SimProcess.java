package ContextSwitchingSim;
import java.util.*;

public class SimProcess {
	private int pid;
	private String procName;
	private int totalInstructions;

	public SimProcess(int p, String pr, int ti)
	{
		this.pid = p;
		this.procName = pr;
		this.totalInstructions = ti;
	}
	public ProcessState execute(int i)
	{
		String message = "PID: " + this.pid + " Process Name: " + this.procName + " Instruction: " + i; 
		System.out.println(message);
		if(i >= this.totalInstructions)
		{
			return ProcessState.FINISHED;
		}else
		{
			Random rand = new Random();
			double random = rand.nextDouble();
			if(random < .15)
			{
				return ProcessState.BLOCKED;
			}
		}
		return ProcessState.READY;
	}
	//I noticed that context switching steps also stated the name of the process - this is the only way I could think of doing it.
	public String getName()
	{
		return this.procName;
	}
}
