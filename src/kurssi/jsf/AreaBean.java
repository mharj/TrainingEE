/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.jsf;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import kurssi.ejb.ForumServicesEJB;
import kurssi.servlet.MessageAreaEntity;
import kurssi.servlet.MessageEntity;
import kurssi.servlet.PersonEntity;

/**
 *
 * @author Oppi
 */
@ManagedBean
@RequestScoped
public class AreaBean {
	@EJB ForumServicesEJB forumService;
	private MessageAreaEntity messageAreaEntity = new MessageAreaEntity();
	private List<MessageAreaEntity> areaList = new LinkedList<MessageAreaEntity>();
	private PersonEntity person = new PersonEntity();
	private MessageEntity message = new MessageEntity();
	private Long personId = null;
	private Long lastAreaId = null;

	public Long getLastAreaId() {
		return lastAreaId;
	}

	public void setLastAreaId(Long lastAreaId) {
		this.lastAreaId = lastAreaId;
	}

	public Long getPersonId() {
		return personId;
	}

	public void setPersonId(Long personId) {
		this.personId = personId;
	}

	public MessageEntity getMessage() {
		return message;
	}

	public void setMessage(MessageEntity message) {
		this.message = message;
	}
	public void saveMessage() {
		message.setPerson(forumService.getPerson(personId));
		forumService.createMessage(message,lastAreaId);
		message = new MessageEntity();
		personId = null;
		FacesMessage fm = new FacesMessage("New message was added");
		FacesContext.getCurrentInstance().addMessage(null, fm);			
	}

	public PersonEntity getPerson() {
		return person;
	}

	public void setPerson(PersonEntity person) {
		this.person = person;
	}
	
	public void savePerson() {
		
		forumService.createUser(person);
		person = new PersonEntity();
		FacesMessage fm = new FacesMessage("New user was added");
		FacesContext.getCurrentInstance().addMessage(null, fm);		
	}
	
	public List<MessageAreaEntity> getAreaList() {
		areaList = new LinkedList<MessageAreaEntity>();
		areaList =  forumService.findAllAreas();
		return areaList;
	}
	
	public List<PersonEntity> getUserList() {
		return forumService.findAllUsers();
	}

	public void setAreaList(List<MessageAreaEntity> areaList) {
		this.areaList = areaList;
	}
	/**
	 * Creates a new instance of AreaBean
	 */
	public AreaBean() {
	}
	public MessageAreaEntity getMessageAreaEntity() {
		return messageAreaEntity;
	}

	public void setMessageAreaEntity(MessageAreaEntity messageAreaEntity) {
		this.messageAreaEntity = messageAreaEntity;
	}

	public String actionCreateArea() {
		forumService.createArea(messageAreaEntity);
		messageAreaEntity = new MessageAreaEntity();
		FacesMessage fm = new FacesMessage("New area was added");
		FacesContext.getCurrentInstance().addMessage(null, fm);
		return null;
	}
	
	public List<MessageEntity> getMessages() {
		List<MessageEntity> ret = new LinkedList<MessageEntity>();
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String idString = ec.getRequestParameterMap().get("id");
		if ( idString != null && ! idString.isEmpty() ) {
			ret = forumService.findMessageForArea(Long.parseLong(idString));
		}
		return ret;
	}
	
}
