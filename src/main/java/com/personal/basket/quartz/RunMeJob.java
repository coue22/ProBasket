package com.personal.basket.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;
 
public class RunMeJob extends QuartzJobBean {
	
	private RunMeTask runMeTask;
 
	public void setRunMeTask(RunMeTask runMeTask) {
		this.runMeTask = runMeTask;
	}
 
	protected void executeInternal(JobExecutionContext context)
		throws JobExecutionException {
 
		//String message = (String) context.getJobDetail().getJobDataMap().get("message");  
        //System.out.println("[JOB] " + message);  
		
		runMeTask.printMe();
 
	}
}