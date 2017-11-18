package com.hl.is.dms.rest.service;


import java.util.List;

import com.hl.is.dms.rest.model.WorkflowEntity;

public interface WorkflowService {
	public List<WorkflowEntity> getAllWorkflows();
	
	public WorkflowEntity getWorkflowById(Long workflowId);
	
	/*public List<WorkflowEntity> getWorkflowsByName(String name);*/
	
	public List<WorkflowEntity> getWorkflowsOfUser(String userId);
	
	public List<WorkflowEntity> getWorkflowsWaitingForUser(String userId);
	
	public List<WorkflowEntity> getWorkflowsActionedByUser(String userId);
	
	/*public List<WorkflowEntity> getWorkflowsByStatus(String status);*/
	
	public Long createWorkflow(WorkflowEntity we);
	
	public void updateWorkflow(WorkflowEntity we ,Long workflowId);
	
	public void deleteWorkflow(Long workflowId);
	
	public boolean isWorkflowExists(WorkflowEntity we);
	
	public List<WorkflowEntity> queryWorkflowsByName(String name, String category, String userId);
	
	public List<WorkflowEntity> queryWorkflowsByStatus(String status, String category , String userId);
	
	
}
