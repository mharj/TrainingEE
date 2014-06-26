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

	public List<MessageAreaEntity> getAreaList() {
		areaList = new LinkedList<MessageAreaEntity>();
		areaList =  forumService.findAllAreas();
		return areaList;
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
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		String idString = ec.getRequestParameterMap().get("id");
		return forumService.findMessageForArea(Long.parseLong(idString));
	}
}
