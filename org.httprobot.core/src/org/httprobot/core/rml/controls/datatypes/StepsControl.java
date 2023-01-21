package org.httprobot.core.rml.controls.datatypes;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlRootElement;

import org.httprobot.common.definitions.Enums.Command;
import org.httprobot.common.events.CliEventArgs;
import org.httprobot.common.events.RmlEventArgs;
import org.httprobot.common.exceptions.InconsistenMessageException;
import org.httprobot.common.exceptions.NotImplementedException;
import org.httprobot.common.interfaces.IRmlListener;
import org.httprobot.common.rml.datatypes.Step;
import org.httprobot.common.rml.datatypes.Steps;
import org.httprobot.common.tools.CommandLineInterface;
import org.httprobot.core.rml.controls.RmlDataTypeControl;
import org.httprobot.core.rml.controls.datatypes.StepControl;

/**
 * @author Joan
 * Steps Control Class. Inherits RmlDataTypeControl.
 */
@XmlRootElement
public class StepsControl extends RmlDataTypeControl
{
	/**
	 * -5878517083143765613L
	 */
	private static final long serialVersionUID = -5878517083143765613L;
	private Steps steps;
	private ArrayList<StepControl> steps_controls;
	private int steps_count;
	private ArrayList<Step> steps_list;	
	/**
	 * Steps control default class constructor.
	 */
	public StepsControl()
	{
		super();
		
		//Initialize using data
		this.steps_controls = new ArrayList<StepControl>();
		this.steps_list = new ArrayList<Step>();
		this.steps = new Steps();
	}
	/**
	 * Steps control class constructor.
	 * @param parent {@link IRmlListener} the parent control
	 * @param steps {@link Steps} the steps
	 */
	public StepsControl(IRmlListener parent, Steps steps) 
	{
		super(parent, steps);
	}
	/**
	 * Gets the steps
	 * @return steps {@link Steps} the steps
	 */
	public Steps getSteps() 
	{
		return steps;
	}
	/**
	 * Gets the total number of steps.
	 * @return steps_count {@link Integer} the steps count
	 */
	public Integer getSteps_count() 
	{
		return steps_count;
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlChanged(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlChanged(Object sender, RmlEventArgs e) 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlInit(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlInit(Object sender, RmlEventArgs e) throws InconsistenMessageException 
	{
		if(e.getMessage() != null)
		{
			//Initialize using data
			this.steps_controls = new ArrayList<StepControl>();
			this.steps_list = new ArrayList<Step>();
			this.steps = new Steps();
			
			//Set inherited data
			this.setUuid(e.getMessage().getUuid());
			this.setInherited(e.getMessage().getInherited());
			this.setRuntimeOptions(e.getMessage().getRuntimeOptions());
			
			this.steps.setUuid(getUuid());
			this.steps.setInherited(getInherited());
			this.steps.setRuntimeOptions(getRuntimeOptions());
			
			//Associate message with control.
			this.addCommandOutputListener(this.steps);
			
			try
			{
				Steps steps = Steps.class.cast(e.getMessage());
				
				this.steps_count = steps.getStep().size();
					
				if(steps.getStep() != null)
				{
					//Sets the count of steps for post back checks
					for(Step step : steps.getStep())
					{
						//This instance listens for it's CliCommandInput
						StepControl step_control = new StepControl(this, step);
						
						//Associate child controls to parent
						step_control.addStepListener(this);
						this.addCommandOutputListener(step_control);
						
						//Store the controller
						this.steps_controls.add(step_control);
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "\nStepsControl.OnControlInit: Step RML message array expected");
				}
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "\nStepsControl.OnControlInit: Steps RML message expected");
			}
		}		
	}
	
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlLoaded(Object sender, RmlEventArgs e)  throws NotImplementedException, InconsistenMessageException
	{
		if(e.getMessage() != null)
		{
			try
			{
				Steps steps = Steps.class.cast(e.getMessage());
				
				if(steps.getStep() != null)
				{
					for(StepControl stepControl : this.steps_controls)
					{
						for(Step step : steps.getStep())
						{
							if(step.getUuid() == stepControl.getUuid())
							{
								stepControl.setMessage(step);
								break;
							}
						}
					}
				}
				else
				{
					CommandLineInterface.ThrowInconsistentMessageException(this, "StepsControl.OnControlLoaded: Steps RML message array expected");
				}				
			}
			catch(ClassCastException ex)
			{
				CommandLineInterface.ThrowInconsistentMessageException(this, "StepsControl.OnControlLoaded: Steps RML message expected");
			}
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlRendered(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnControlRendered(Object sender, RmlEventArgs e)  throws NotImplementedException, InconsistenMessageException
	{
		
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnControlWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnControlWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepLoaded(java.lang.Object, org.httprobot.common.events.RmlControlEventArgs)
	 */
	@Override
	public void OnStepLoaded(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{
		try
		{
			Step step = Step.class.cast(e.getMessage());
			
			//Set loaded data
			this.steps_list.add(step);
			
			if (this.steps_list.size() == this.steps_count - 1) 
			{
				this.steps.setStep(this.steps_list);
			}
			
			CliCommandInputEvent(new CliEventArgs(this, Command.STEP_CONTROL, e.getMessage()));
		}
		catch(ClassCastException ex1)
		{
			ex1.printStackTrace();
		}
	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsChanged(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsChanged(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsInit(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsInit(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsLoaded(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsLoaded(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsRead(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRead(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsRendered(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsRendered(Object sender, RmlEventArgs e) throws InconsistenMessageException, NotImplementedException 
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepsWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepsWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException
	{

	}
	/* (non-Javadoc)
	 * @see org.httprobot.core.rml.controls.RmlDataTypeControl#OnStepWrite(java.lang.Object, org.httprobot.common.events.RmlEventArgs)
	 */
	@Override
	public void OnStepWrite(Object sender, RmlEventArgs e) throws NotImplementedException, InconsistenMessageException 
	{

	}
}