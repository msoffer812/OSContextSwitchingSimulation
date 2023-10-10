package ContextSwitchingSim;
import java.util.*;

public class file2 {
	public static void main(String[] args)
	{
		SimProcessor processor = new SimProcessor();
		final int QUANTUM = 5;
		ArrayList<ProcessControlBlock> readyPcbs = new ArrayList<ProcessControlBlock>();
		ArrayList<SimProcess> processes = new ArrayList<SimProcess>();
		ArrayList<ProcessControlBlock> blockedPcbs = new ArrayList<ProcessControlBlock>();
		String[] names = {"Hey", "Diddle", "The", "Cat", "And", "Fiddle", "Cow", "Jumped", "Over", "Moon"};
		int[] instructions = {101, 200, 300, 355, 266, 345, 129, 250, 399, 145};
		createProcesses(names, instructions, readyPcbs, processes);
		executePrograms(processor, QUANTUM, processes, readyPcbs, blockedPcbs);
		System.exit(0);
	}
	//create processes and pcbs
	public static void createProcesses(String[]names, int[] instructions, ArrayList<ProcessControlBlock> readyPcbs, ArrayList<SimProcess> processes)
	{	
		SimProcess process;
		ProcessControlBlock pcb;
		int i;
		for(i=0;i<10;i++)
		{
			process = new SimProcess((i+1), names[i], instructions[i]);
			pcb = new ProcessControlBlock(process);
			processes.add(process);
			readyPcbs.add(pcb);
		}
	}

	public static void executePrograms(SimProcessor processor, final int QUANTUM, ArrayList<SimProcess> processes, ArrayList<ProcessControlBlock> readyPcbs, ArrayList<ProcessControlBlock>blockedPcbs)
	{
		int i;
		ProcessState processState = ProcessState.READY;
		ProcessControlBlock pcb = readyPcbs.get(0);
		readyPcbs.remove(pcb);//removing the process from the ready list because it's not ready, it's running.
		//set processor with first process
		processor.setCurrentProcess(pcb.getProcess());
		int quanta = 0;
	
		for(i=0;i<3000; i++)
		{
			
			if(quanta < 5)
			{
				switch(processState)
				{
				case FINISHED:
					System.out.println("***Process completed***");
					System.out.print("Step " + (i+1) + " ");
					pcb = contextSwitch(processor, pcb, i, processState, processes, readyPcbs, blockedPcbs);
					processState = ProcessState.READY;
					quanta = 0;
					break;
				case BLOCKED:
					System.out.println("***Process blocked***");
					System.out.println("Step " + (i+1) + " ");
					pcb = contextSwitch(processor, pcb, i, processState, processes, readyPcbs, blockedPcbs);
					processState = ProcessState.READY;
					quanta = 0;
					break;
				default:
					System.out.println("Step " + (i+1) + " ");
					processState = processor.executeNextInstruction();
					quanta++; 
					break;
				}
			}
			else
			{
				System.out.println("***Quantum Expired***");
				System.out.println("Step " + (i+1) + " ");
				pcb = contextSwitch(processor, pcb, i, processState, processes, readyPcbs, blockedPcbs);
				processState = ProcessState.READY;
				quanta = 0;
			}
			
			//looping through blocked processes and maybe one will wake up!
			unblock(readyPcbs, blockedPcbs);
		}
	}
	
	//looping through blocked processes and maybe one will wake up!
	public static void unblock(ArrayList<ProcessControlBlock>readyPcbs, ArrayList<ProcessControlBlock>blockedPcbs)
	{
		int i;
		double wakeup;
		Random rand = new Random();
		for(i=0;i<blockedPcbs.size(); i++)
		{
			wakeup = rand.nextDouble();
			if(wakeup <= .3)
			{
				readyPcbs.add(blockedPcbs.get(i));
				blockedPcbs.remove(i);
			}
		}
			
	}
	//performs a context switch
	public static ProcessControlBlock contextSwitch(SimProcessor processor, ProcessControlBlock pcb,  int i, ProcessState processState, ArrayList<SimProcess>processes, ArrayList<ProcessControlBlock>readyPcbs, ArrayList<ProcessControlBlock>blockedPcbs)
	{
		double[] registerValues = processor.getRegisterValues();
		int currInstruction = processor.getCurrInstruction();
		
		System.out.println("Context Switch: Saving process:  " + processor.getProcess().getName());
		System.out.println("R1:" + registerValues[0] + ", R2:" + registerValues[1] + ", R3: " + registerValues[2] + ", R4: " + registerValues[3] +  "    INSTRUCTION: " + currInstruction);
		
		//Saving the register values and current instruction number
		pcb.setRegisterValues(registerValues);
		pcb.setCurrInstruction(currInstruction);
		
		//What to do with PCB? If finished, can discard. Else, it either goes onto blocked or ready depending on state
		if(processState.equals(ProcessState.BLOCKED))
		{
			blockedPcbs.add(pcb);
		}else if(processState.equals(ProcessState.READY))
		{
			readyPcbs.add(pcb);
		}
		
		//getting the next pcb from the ready list. performing this loop in case there is no more stuff in the ready list - if there aren't does the unblocking thing until something gets unblocked.
		while(readyPcbs.isEmpty())
		{
			unblock(readyPcbs, blockedPcbs);
		}
		pcb = readyPcbs.get(0);
		readyPcbs.remove(pcb);//removing the process from the ready list because it's not ready, it's running.
		//set processor with current process
		processor.setCurrentProcess(pcb.getProcess());
		processor.setCurrInstruction(pcb.getCurrInstruction());
		processor.setRegisterValues(pcb.getRegisterValues());
		
		registerValues = processor.getRegisterValues();
		currInstruction = processor.getCurrInstruction();
		System.out.println(" Restoring process: " + processor.getProcess().getName());
		System.out.println("R1:" + registerValues[0] + ", R2:" + registerValues[1] + ", R3: " + registerValues[2] + ", R4: "+registerValues[3] +  "    INSTRUCTION: " + currInstruction);
		return pcb;
	}
}
