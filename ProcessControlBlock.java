package ContextSwitchingSim;

public class ProcessControlBlock {
	private SimProcess process;
	private int currInstruction;
	private double register1Value;
	private double register2Value;
	private double register3Value;
	private double register4Value;
	
	//Constructor
	public ProcessControlBlock (SimProcess p)
	{
		this.process = p;
	}
	//Setters
		//Register Value Setters
		public void setRegisterValues(double a, double b, double c, double d)
		{
			register1Value = a;
			register2Value = b;
			register3Value = c;
			register4Value = d;
		}
		public void setRegisterValues(double[]a)
		{
			register1Value = a[0];
			register2Value = a[1];
			register3Value = a[2];
			register4Value = a[3];
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
			return process;
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
}
