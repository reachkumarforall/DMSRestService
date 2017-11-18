package com.hl.is.dms.rest.model;

import java.util.Date;
import java.util.Set;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "DM_WORKFLOW_TEMPLATE_TL")
@NamedQuery(name="template.byName",query="from WorkflowTemplateEntity where name like CONCAT(:templateName,'%')")
public class WorkflowTemplateEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEMPLATE_ID")
	private long id;

	@Column(name = "NAME",nullable=false)
	private String name;

	@Column(name = "TYPE",nullable=false)
	private String Type;

	@Column(name = "DESCRIPTION")
	private String description;

	@Column(name = "OWNER",nullable=false)
	private String owner;

	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "MODIFIED_ON")
	private Date modifiedOn;

	@Column(name = "VISIBILITY_LEVEL")
	private String visibilityLevel;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true,mappedBy="template")	
	private Set<TemplateApproversEntity> approvers = new TreeSet<TemplateApproversEntity>();

	public Set<TemplateApproversEntity> getApprovers() {
		return approvers;
	}

	public void setApprovers(Set<TemplateApproversEntity> approvers) {
		this.approvers = approvers;
	}

	public Set<TemplateApproversEntity> addApprover(TemplateApproversEntity ae) {
		// TO DO: null check		
		ae.setTemplate(this);
		this.getApprovers().add(ae);
		return this.getApprovers();
	}

	public Set<TemplateApproversEntity> removeApprover(TemplateApproversEntity ae) {
		// to do null check
		this.getApprovers().remove(ae);
		return this.getApprovers();
	}

	public String getVisibilityLevel() {
		return visibilityLevel;
	}

	public void setVisibilityLevel(String visibilityLevel) {
		this.visibilityLevel = visibilityLevel;
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

	public String getType() {
		return Type;
	}

	public void setType(String type) {
		Type = type;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
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

}
