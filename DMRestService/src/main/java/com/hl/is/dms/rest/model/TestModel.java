package com.hl.is.dms.rest.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;

import com.hl.is.dms.rest.model.util.HibernateUtil;

public class TestModel {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		/*Session session = HibernateUtil.getSessionfactory().openSession();
		session.beginTransaction();
		
		DocumentEntity rec = new DocumentEntity();
		rec.setName("new name");
		rec.setDocFormType("BSA form type");
		rec.setCreatedBy("sathishma");
		rec.setCreatedOn(new Date());
		
		//session.save(rec);
		
		
		WorkflowEntity workflow = new WorkflowEntity();
		workflow.setName("new orkflow");
		workflow.addDocument(rec);
		session.persist(workflow);
		session.getTransaction().commit();
		
		session.beginTransaction();*/
		
		EntityManagerFactory factory =  Persistence.createEntityManagerFactory("DMS-JPA");
		EntityManager em = factory.createEntityManager();
		
		/*em.getTransaction().begin();
		WorkflowEntity we = em.find(WorkflowEntity.class, 21L);
		System.out.println("em.name:"+ we.getName());
		System.out.println("em.document:"+ we.getWfDocuments().get(0).getName());
		we.setName("updated workflow");
		
		
		em.getTransaction().commit();*/
		
		//////////////////////// template and approvers//////////////
		
		/*WorkflowTemplateEntity wte  = em.find(WorkflowTemplateEntity.class, 1L);
		em.remove(wte);
		em.getTransaction().commit();*/
		
		///template approvers notifications on updating///
		
		setupWorkflows(em);
		setupWorkflowTemplates(em);
		/*em.getTransaction().begin();

		Query qe = em.createNamedQuery("workflow.byName");
		qe.setParameter("workflowName", "nesdsdw");
		List<WorkflowEntity> listwe = qe.getResultList();
		for(WorkflowEntity we : listwe){
			System.out.println("Name:"+we.getName());
		}
		
		em.getTransaction().commit();
		*/
		em.close();
	
	}
	
	private static void setupWorkflows(EntityManager em){
		em.getTransaction().begin();
		
		DocumentEntity rec = new DocumentEntity();
		rec.setName("new name");
		rec.setDocFormType("BSA form type");
		rec.setOwner("sathishma");
		rec.setCreatedBy("sathishma");
		rec.setCreatedOn(new Date());
		
		WorkflowEntity workflow = new WorkflowEntity();
		workflow.setName("new orkflow");
		workflow.setStatus("NEW_DOCUMENT");
		workflow.setOwner("sathishma");
		workflow.addDocument(rec);
		
		em.persist(workflow);
		em.getTransaction().commit();
	}
	
	private static NotificationOnEntity findNotificationOn(EntityManager em, String notificationType){
		Query qe = em.createNamedQuery("template.byNotification");
		qe.setParameter("notificationType", notificationType);
		List<NotificationOnEntity> list = qe.getResultList();
		if(list.size() > 0)
			return list.get(0);
		
		return null;
	}
	private static void setupWorkflowTemplates(EntityManager em){
		em.getTransaction().begin();
		WorkflowTemplateEntity wte = new WorkflowTemplateEntity();
		wte.setName("2nd wf tempalte name");
		wte.setOwner("sathishma");
		wte.setType("STATIC");
		
		
		NotificationOnEntity noeALL = findNotificationOn(em, "ALL");
		if(noeALL == null){
			noeALL = new NotificationOnEntity();
			noeALL.setNotificationType("ALL");			
		}
		
		
		NotificationOnEntity noeINITIATED = findNotificationOn(em, "INITIATED");
		if(noeINITIATED == null){
			noeINITIATED = new NotificationOnEntity();
			noeINITIATED.setNotificationType("INITIATED");			
		}
		
		NotificationOnEntity noeWORKFLOW_CHANGES = findNotificationOn(em, "WORKFLOW_CHANGES");
		if(noeWORKFLOW_CHANGES == null){
			noeWORKFLOW_CHANGES = new NotificationOnEntity();
			noeWORKFLOW_CHANGES.setNotificationType("WORKFLOW_CHANGES");			
		}
		
		
		TemplateApproversEntity tae = new TemplateApproversEntity();
		tae.setApproverSequence(1);
		tae.setApproverUserId("siddharths");		
		tae.addNotificationOn(noeINITIATED);
		tae.addNotificationOn(noeWORKFLOW_CHANGES);
		
		TemplateApproversEntity tae1 = new TemplateApproversEntity();
		tae1.setApproverSequence(2);
		tae1.setApproverUserId("lalitk");		
		tae1.addNotificationOn(noeALL);
		tae1.addNotificationOn(noeINITIATED);
		
		TemplateApproversEntity tae2 = new TemplateApproversEntity();
		tae2.setApproverSequence(3);
		tae2.setApproverUserId("ajayg");		
		tae2.addNotificationOn(noeALL);
		tae2.addNotificationOn(noeINITIATED);
		tae2.addNotificationOn(noeWORKFLOW_CHANGES);
		
		wte.addApprover(tae);
		wte.addApprover(tae1);
		wte.addApprover(tae2);
		
		em.persist(wte);
		em.persist(noeWORKFLOW_CHANGES);
		em.getTransaction().commit();
		
	}

}
