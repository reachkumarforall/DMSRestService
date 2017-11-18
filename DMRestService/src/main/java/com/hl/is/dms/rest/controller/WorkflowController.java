package com.hl.is.dms.rest.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.hl.is.dms.rest.model.WorkflowEntity;
import com.hl.is.dms.rest.service.WorkflowService;


@RestController
public class WorkflowController {

	@Autowired
	private WorkflowService workflowService;

	@RequestMapping("/workflows")
	public @ResponseBody List<WorkflowEntity> getWorkflows() {
		return workflowService.getAllWorkflows();
	}

	@RequestMapping("/workflows/{id}")
	public @ResponseBody WorkflowEntity getWorkflowById(@PathVariable long id) {
		return workflowService.getWorkflowById(id);
	}

	@RequestMapping(value = "/workflows/{id}", method = RequestMethod.PUT)
	public void updateWorkflow(@RequestBody WorkflowEntity we, @PathVariable long id) {
		workflowService.updateWorkflow(we, id);
		return;
	}

	@RequestMapping(value = "/workflows", method = RequestMethod.POST)
	public Long createWorkflow(@RequestBody WorkflowEntity we) {

		return workflowService.createWorkflow(we);

	}

	@RequestMapping(value = "/workflows/{id}", method = RequestMethod.DELETE)
	public void deleteWorkflow(@PathVariable long id) {
		workflowService.deleteWorkflow(id);
		return;
	}

	@RequestMapping(value = "/search/workflow/{user}", method = RequestMethod.GET)
	public List<WorkflowEntity> getWorkflowsByParam(
			@PathVariable(name="user",required=false) Optional<String> user,  // temporary setting to pass the user
			@RequestParam(name="category",required=false) Optional<String> category,
			@RequestParam(name="name",required=false) Optional<String> name, 
			@RequestParam(name="status",required=false) Optional<String> status
			)
															
	{
		if (name.isPresent()) {
			return workflowService.queryWorkflowsByName(name.get(), category.isPresent() ? category.get() : null, 
					user.isPresent() ?user.get() : null);
		} else if (status.isPresent()) {
			return workflowService.queryWorkflowsByStatus(status.get(), category.isPresent() ? category.get() : null, 
					user.isPresent() ?user.get() : null);
		}

		return null;

	}
}
