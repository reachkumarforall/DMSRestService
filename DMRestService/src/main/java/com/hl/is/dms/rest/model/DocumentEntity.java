package com.hl.is.dms.rest.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name="DM_DOCUMENT_TL")
@NamedQuery(name="document.byName",query="from DocumentEntity where name like CONCAT(:docName,'%')")
public class DocumentEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="DOCUMENT_ID")
	private long id;
	
	@Column(name="DOCUMENT_NAME",nullable=false)
	private String name;
	
	@Column(name="DOCUMENT_FORM_TYPE",nullable=false)
	private String docFormType;
	
	@Column(name="DOCUMENT_FORM_CLASSIFICATION")
	private String docFormClassification;
	
	@Column(name="DOCUMENT_MIME_TYPE")
	private String attachmentMimeType;
	
	@Column(name="DOCUMENT")
	private Blob document;
	
	@Column(name="DOCUMENT_FILE_TYPE")
	private String fileType;
	
	@Column(name="LAST_COMMENTS")
	private String lastCommnets;
	
	@Column(name="OWNER",nullable=false)
	private String owner; 
	
	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="MODIFIED_ON")
	private Date modifiedOn;

	public String getOwner() {
		return owner;
	}
	public void setOwner(String owner) {
		this.owner = owner;
	}
	@JsonBackReference //this is the backward part of the bi-directional relationship, JSON ignores to serialize this object
	@ManyToOne(fetch=FetchType.LAZY,optional=false)
	@JoinColumn(
			name="WORKFLOW_ID_FK",
			foreignKey=@ForeignKey(name="FK_WORKFLOW_DOCUMENT_01"), //if no tprovided, there would be conflicts on the constraint name when running spring boot vs jpa example
			referencedColumnName="WORKFLOW_ID",
			nullable=true
			)
	private WorkflowEntity workflow;
	
	public Blob getDocument() {
		return document;
	}
	public void setDocument(Blob document) {
		this.document = document;
	}
	public String getLastCommnets() {
		return lastCommnets;
	}
	public void setLastCommnets(String lastCommnets) {
		this.lastCommnets = lastCommnets;
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
	public String getDocFormType() {
		return docFormType;
	}
	public void setDocFormType(String docFormType) {
		this.docFormType = docFormType;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
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
	public WorkflowEntity getWorkflow() {
		return workflow;
	}
	public void setWorkflow(WorkflowEntity workflow) {
		this.workflow = workflow;
	}
	
	public String getDocFormClassification() {
		return docFormClassification;
	}
	public void setDocFormClassification(String docFormClassification) {
		this.docFormClassification = docFormClassification;
	}
	public String getAttachmentMimeType() {
		return attachmentMimeType;
	}
	public void setAttachmentMimeType(String attachmentMimeType) {
		this.attachmentMimeType = attachmentMimeType;
	}
	
}
