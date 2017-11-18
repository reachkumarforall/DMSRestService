package com.hl.is.dms.rest.repository;
import java.util.List;



import org.springframework.data.repository.CrudRepository;


import com.hl.is.dms.rest.model.WorkflowEntity;


public interface WorkflowRespository extends CrudRepository<WorkflowEntity, Long>{
	

	public List<WorkflowEntity> findByNameIgnoreCase(String name);
	
	public List<WorkflowEntity> findByNameAndOwnerAllIgnoreCase(String name,String owner); //find by name in my docs
	
	public List<WorkflowEntity> findByStatusIgnoreCase(String status);
	
	public List<WorkflowEntity> findByStatusAndOwnerAllIgnoreCase(String status,String owner); //find by status in my docs
	
	public List<WorkflowEntity> findByOwnerIgnoreCase(String owner);  //get all my documents
	
	
}
