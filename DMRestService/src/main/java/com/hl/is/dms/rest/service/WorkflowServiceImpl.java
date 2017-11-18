package com.hl.is.dms.rest.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hl.is.dms.rest.model.WorkflowEntity;
import com.hl.is.dms.rest.repository.WorkflowRespository;

@Service("workflowService")
public class WorkflowServiceImpl implements WorkflowService {
	@Autowired
	private WorkflowRespository repository;

	/**
	 * Method to return all workflows
	 */
	public List<WorkflowEntity> getAllWorkflows() {
		List<WorkflowEntity> wList = new ArrayList<WorkflowEntity>();
		repository.findAll().forEach(wList::add);
		return wList;
	}

	/**
	 * delete workflow by id
	 */
	public void deleteWorkflow(Long workflowId) {
		if (workflowId != null)
			repository.delete(workflowId);
		return;
	}

	public boolean isWorkflowExists(WorkflowEntity we) {
		return repository.exists(we.getId());
	}

	@Override
	public WorkflowEntity getWorkflowById(Long workflowId) {
		return repository.findOne(workflowId);

	}

	/*
	 * @Override public List<WorkflowEntity> getWorkflowsByName(String name) {
	 * if (!org.springframework.util.StringUtils.isEmpty(name)) { return
	 * repository.findByNameIgnoreCase(name); } return null; }
	 */
	@Override
	public List<WorkflowEntity> getWorkflowsOfUser(String userId) {
		if (!org.springframework.util.StringUtils.isEmpty(userId)) {
			return repository.findByOwnerIgnoreCase(userId);
		}
		return null;
	}

	@Override
	public List<WorkflowEntity> getWorkflowsWaitingForUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorkflowEntity> getWorkflowsActionedByUser(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long createWorkflow(WorkflowEntity we) {

		if (repository.exists(we.getId())) {
			System.err.println("is already existing");
			return -1L; // return error
		}
		we = repository.save(we);
		return we.getId();
	}

	@Override

	public void updateWorkflow(WorkflowEntity we, Long workflowId) {

		WorkflowEntity findExisting = repository.findOne(workflowId);

		if (findExisting == null) {
			return; // should return error.
		}
		findExisting.setName(we.getName());
		findExisting.setLastCommnets(we.getLastCommnets());
		findExisting.setStatus(we.getStatus());
		findExisting.setOwner(we.getOwner());

		repository.save(findExisting);
		return;
	}

	/*
	 * @Override public List<WorkflowEntity> getWorkflowsByStatus(String status)
	 * { if (!org.springframework.util.StringUtils.isEmpty(status)) { return
	 * repository.findByStatusIgnoreCase(status); } return null; }
	 */

	@Override
	public List<WorkflowEntity> queryWorkflowsByName(String name, String category, String userId) {

		// query from all tabs
		if (StringUtils.isEmpty(userId))
			return repository.findByNameIgnoreCase(name);
		else // fetch only from my docs tab.
			return repository.findByNameAndOwnerAllIgnoreCase(name, userId);
		
		//to do: need to implement other tabs
	}

	@Override
	public List<WorkflowEntity> queryWorkflowsByStatus(String status, String category, String userId) {
		
			//query from all tabs
			if(StringUtils.isEmpty(userId))
				return repository.findByStatusIgnoreCase(status);
			else
				return repository.findByStatusAndOwnerAllIgnoreCase(status, userId);
		
	}

}
