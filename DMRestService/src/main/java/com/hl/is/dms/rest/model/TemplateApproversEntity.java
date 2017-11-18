package com.hl.is.dms.rest.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;


@Entity
@Table(name = "DM_TEMPLATE_APPROVERS_TL")
@NamedQuery(name="template.bySequence",query="from TemplateApproversEntity where approverSequence = :sequence")
public class TemplateApproversEntity implements Comparable<TemplateApproversEntity> {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "TEMPLATE_APPR_ID")
	private long id;

	@Column(name = "SEQUENCE",nullable=false)
	private Integer approverSequence;

	@Column(name = "APPROVER_USERID",nullable=false)
	private String approverUserId;

	@ManyToOne(fetch = FetchType.LAZY, optional = false)
	@JoinColumn(
			name = "TEMPLATE_ID_FK",
			foreignKey = @ForeignKey(name="FK_WORKTEMPL_TEMPLAPPRS_01"),
			referencedColumnName="TEMPLATE_ID",
			nullable = false
			)
	private WorkflowTemplateEntity template;
	
	@Column(name="APPR_PERCENT_WEIGHT",scale=3,precision=2)
	private Double approverPercentWight;
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name="TEMPLATE_APPR_NOTIFS_ON_TL",
		joinColumns={@JoinColumn(name="TEMPLATE_APPR_ID",nullable=false, foreignKey=@ForeignKey(name="FK_TEMPLAPPRS_TEMPAPPNOTIF_01"))},
		inverseJoinColumns={@JoinColumn(name="NOTIFON_ID",nullable=false, foreignKey=@ForeignKey(name="FK_NOTIFON_TEMPAPPNOTIF_02"))})
	private List<NotificationOnEntity> notificationsOn = new ArrayList<NotificationOnEntity>();
	
	public List<NotificationOnEntity> addNotificationOn(NotificationOnEntity noe){
		//TO DO null check
		this.notificationsOn.add(noe);
		return this.notificationsOn;
	}
	
	public List<NotificationOnEntity> removeNotificationOn(NotificationOnEntity noe){
		this.notificationsOn.remove(noe);
		
		return this.notificationsOn;
	}
	@Column(name = "CREATED_BY")
	private String createdBy;

	@Column(name = "MODIFIED_BY")
	private String modifiedBy;

	@Column(name = "CREATED_ON")
	private Date createdOn;

	@Column(name = "MODIFIED_ON")
	private Date modifiedOn;


	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Double getApproverPercentWight() {
		return approverPercentWight;
	}

	public void setApproverPercentWight(Double approverPercentWight) {
		this.approverPercentWight = approverPercentWight;
	}

	public Integer getApproverSequence() {
		return approverSequence;
	}

	public void setApproverSequence(Integer approverSequence) {
		this.approverSequence = approverSequence;
	}

	public String getApproverUserId() {
		return approverUserId;
	}

	public void setApproverUserId(String approverUserId) {
		this.approverUserId = approverUserId;
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

	public WorkflowTemplateEntity getTemplate() {
		return template;
	}

	public void setTemplate(WorkflowTemplateEntity template) {
		this.template = template;
	}

	@Override
	public int compareTo(TemplateApproversEntity o) {
		if(this.equals(o))
			return 0;
		else 
			return ObjectUtils.compare(this.approverSequence, o.getApproverSequence());		
	}
	
		
	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if(obj == this)
			return true;
		if (!(obj instanceof TemplateApproversEntity))
			return false;

		final TemplateApproversEntity otherObj = (TemplateApproversEntity) obj;
		
		return new EqualsBuilder()
				.appendSuper(super.equals(otherObj))
				.append(this.getApproverSequence(), otherObj.getApproverSequence())
				.append(this.getApproverUserId(), otherObj.getApproverUserId())
				.isEquals();
		
	}
	
	@Override
	public int hashCode(){
		return Objects.hash(this.approverSequence, this.approverUserId);
	}

	public List<NotificationOnEntity> getNotificationsOn() {
		return notificationsOn;
	}

	public void setNotificationsOn(List<NotificationOnEntity> notificationsOn) {
		this.notificationsOn = notificationsOn;
	}

}
