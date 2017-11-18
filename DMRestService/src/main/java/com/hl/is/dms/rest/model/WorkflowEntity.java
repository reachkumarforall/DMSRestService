package com.hl.is.dms.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@NamedQueries({
		@NamedQuery(name="workflow.mydocs.byName",query="from WorkflowEntity where name like CONCAT(:workflowName,'%')"),
		@NamedQuery(name="workflow.mydocs.byStatus",query="from WorkflowEntity where name like :status"),
		@NamedQuery(name="workflow.mydocs.byForm",query="from WorkflowEntity where name like :workflowName")})
@Table(name="DM_WORKFLOW_TL")
public class WorkflowEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="WORKFLOW_ID")
	private long id;
	
	@Column(name="WORKFLOW_NAME",nullable=false)
	private String name;
	
	@Column(name="STATUS",nullable=false)
	private String status;
	
	@Column(name="OWNER",nullable=false)
	private String owner; 
	
	@Column(name="LAST_COMMENTS")
	private String lastCommnets;
	
	@Column(name="COUNTRY_CODE")
	private String countryCode;
	
	@JsonManagedReference //this is the forward part of the bi-directional relationship, JSON does serialize this object
	@OneToMany(fetch=FetchType.EAGER,cascade={CascadeType.ALL}, mappedBy="workflow")	
	private List<DocumentEntity> wfDocuments = new ArrayList<DocumentEntity>();
	
	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="MODIFIED_ON")
	private Date modifiedOn;

	public List<DocumentEntity> addDocument(DocumentEntity de){
		de.setWorkflow(this);
		this.getWfDocuments().add(de);
		return this.getWfDocuments();
	}
	

	public List<DocumentEntity> removeDocument(DocumentEntity de){		
		this.getWfDocuments().remove(de);
		return this.getWfDocuments();
	}
	
	public List<DocumentEntity> getWfDocuments() {
		return wfDocuments;
	}

	public void setWfDocuments(List<DocumentEntity> wfDocuments) {
		this.wfDocuments = wfDocuments;
		for(DocumentEntity de : wfDocuments){
			de.setWorkflow(this);
		}
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	public String getLastCommnets() {
		return lastCommnets;
	}

	public void setLastCommnets(String lastCommnets) {
		this.lastCommnets = lastCommnets;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	public Date getModifiedOn() {
		return modifiedOn;
	}

	public void setModifiedOn(Date modifiedOn) {
		this.modifiedOn = modifiedOn;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}
	
}
