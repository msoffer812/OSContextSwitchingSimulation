package ContextSwitchingSim;
import java.util.*;

public class SimProcessor {
	private SimProcess currentProcess;
	private double register1Value;
	private double register2Value;
	private double register3Value;
	private double register4Value;
	private int currInstruction;
	
	//currentProcess Setter
	public void setCurrentProcess(SimProcess p)
	{
		this.currentProcess = p;
	}
	//Register Value Setters
	public void setRegisterValues(double a, double b, double c, double d)
	{
		register1Value = a;
		register2Value = b;
		register3Value = c;
		register4Value = d;
	}
	public void setRegisterValues(double[] vals)
	{
		register1Value=vals[0];
		register2Value=vals[1];
		register3Value=vals[2];
		register4Value = vals[3];
	}
	public void setRegister1Value(double a)
	{
		register1Value = a;
	}
	public void setRegister2Value(double a)
	{
		register2Value = a;
	}
	public void setRegister3Value(double a)
	{
		register3Value = a;
	}
	public void setRegister4Value(double a)
	{
		register4Value = a;
	}
	//currInstruction Setter
	public void setCurrInstruction(int a)
	{
		currInstruction = a;
	}
	
	//Current Process Getter
	public SimProcess getProcess()
	{
		return currentProcess;
	}
	//Register Value Getters
	public double[] getRegisterValues()
	{
		double[] values = {register1Value, register2Value, register3Value, register4Value};
		return values;
	}
	public double getRegister1Value()
	{
		return this.register1Value;
	}
	public double getRegister2Value()
	{
		return this.register2Value;
	}
	public double getRegister3Value()
	{
		return this.register3Value;
	}
	public double getRegister4Value()
	{
		return this.register4Value;
	}
	//CurrInstruction Getter
	public int getCurrInstruction()
	{
		return this.currInstruction;
	}
	
	//calls the execute method of the current process and returns result
	public ProcessState executeNextInstruction()
	{
		Random rand = new Random();
		ProcessState p = currentProcess.execute(currInstruction);
		currInstruction++;
		this.register1Value = (rand.nextDouble()*100);
		this.register2Value = (rand.nextDouble()*100);
		this.register3Value = (rand.nextDouble()*100);
		this.register4Value = (rand.nextDouble()*100);
		return p;
	}
}
