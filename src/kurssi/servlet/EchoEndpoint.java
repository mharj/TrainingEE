/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.servlet;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.websocket.OnMessage;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import kurssi.ejb.ForumServicesEJB;


/**
 *
 * @author Oppi
 */
@ServerEndpoint("/echo")
public class EchoEndpoint {
//	@EJB ForumServicesEJB forumService;
	@OnMessage
	public void onMessage(Session session,String message) {
		try {
			String out="";
			ForumServicesEJB forumService = (ForumServicesEJB) new InitialContext().lookup("java:global/Forum/ForumServicesEJB");
			for ( PersonEntity c : forumService.findAllUsers() ) {
				Logger.getLogger(EchoEndpoint.class.getName()).log(Level.INFO, c.getFirstName());
				out += (out.length()==0?"":",")+"{\"name\":\""+c.getFirstName()+"\"}";
				message += c.getFirstName();
			}
			session.getBasicRemote().sendText("["+out+"]");
		} catch (IOException ex) {
			Logger.getLogger(EchoEndpoint.class.getName()).log(Level.SEVERE, null, ex);
		} catch (NamingException ex) {
			Logger.getLogger(EchoEndpoint.class.getName()).log(Level.SEVERE, null, ex);
		}
	}
	
}
