package com.hl.is.dms.rest.model;

import java.sql.Blob;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="DM_USER_DETAILS_TL")
public class UserDetailsEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="USER_ID",nullable=false)
	private String userId;
	
	@Column(name="FIRST_NAME",nullable=false)
	private String firstName;
	
	@Column(name="LAST_NAME",nullable=false)
	private String lastName;
	
	@Column(name="MIDDLE_NAME")
	private String middleName;
	
	@Column(name="DISPLAY_NAME",nullable=false)
	private String displayName;
	
	@Column(name="EMAIL",nullable=false)
	private String email;
	
	@Column(name="COUNTRY_CODE")	
	private String countryCode;
	
	@Column(name="KEY_STORE")
	private Blob keyStore;
	
	@Column(name="CREATED_BY")
	private String createdBy;

	@Column(name="MODIFIED_BY")
	private String modifiedBy;

	@Column(name="CREATED_ON")
	private Date createdOn;

	@Column(name="MODIFIED_ON")
	private Date modifiedOn;
}
