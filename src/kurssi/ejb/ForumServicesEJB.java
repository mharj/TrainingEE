/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.ejb;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import kurssi.servlet.MessageAreaEntity;
import kurssi.servlet.MessageAreaEntity_;
import kurssi.servlet.MessageEntity;
import kurssi.servlet.MessageEntity_;
import kurssi.servlet.PersonEntity;
import kurssi.servlet.PersonEntity_;

/**
 *
 * @author Oppi
 */
@Stateless
public class ForumServicesEJB {
	@PersistenceContext private EntityManager em;
	
	public void createMessage(MessageEntity m,Long messageAreaId) {
		m.setArea(getArea(messageAreaId));
		em.persist(m);
	}
	
	public void createArea(MessageAreaEntity mae) {
		em.persist(mae);
	}
	
	public MessageAreaEntity getArea(Long messageAreaId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessageAreaEntity> mec = cb.createQuery(MessageAreaEntity.class);
		Root<MessageAreaEntity> p = mec.from(MessageAreaEntity.class);
		Predicate condition = cb.equal( p.get(MessageAreaEntity_.id), messageAreaId);
		mec.where(condition);
		TypedQuery<MessageAreaEntity> mecq = em.createQuery(mec);
		return mecq.getResultList().get(0);
	}
	
	public PersonEntity getPerson(Long personId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> mec = cb.createQuery(PersonEntity.class);
		Root<PersonEntity> p = mec.from(PersonEntity.class);
		Predicate condition = cb.equal( p.get(PersonEntity_.personId), personId);
		mec.where(condition);
		TypedQuery<PersonEntity> mecq = em.createQuery(mec);
		return mecq.getResultList().get(0);
	}	
	
	
	public List<MessageAreaEntity> findAllAreas() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessageAreaEntity> mec = cb.createQuery(MessageAreaEntity.class);
		TypedQuery<MessageAreaEntity> mecq = em.createQuery(mec);
		return mecq.getResultList();
	}

	public List<PersonEntity> findAllUsers() {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<PersonEntity> mec = cb.createQuery(PersonEntity.class);
		TypedQuery<PersonEntity> mecq = em.createQuery(mec);
		return mecq.getResultList();
	}
	
	public List<MessageEntity> findMessageForArea(Long messageAreaId) {
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<MessageEntity> mec = cb.createQuery(MessageEntity.class);
		Root<MessageEntity> p = mec.from(MessageEntity.class);
		// build join
		Join<MessageEntity, MessageAreaEntity> area = p.join(MessageEntity_.area);
		// filter with join
		Predicate condition = cb.equal(area.get(MessageAreaEntity_.id), messageAreaId);
		mec.where(condition);
		TypedQuery<MessageEntity> mecq = em.createQuery(mec);
		return mecq.getResultList();
	}

}
