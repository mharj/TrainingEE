/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kurssi.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import kurssi.ejb.ForumServicesEJB;
import kurssi.ejb.TestBean;
import kurssi.ejb.TestUsedBean;

/**
 *
 * @author Oppi
 */
@WebServlet(name = "HelloServlet", urlPatterns = {"/HelloServlet"})
public class HelloServlet extends HttpServlet {

	@PersistenceUnit
	EntityManagerFactory emf;

	@Resource
	UserTransaction utx;

	@EJB ForumServicesEJB forumService;
	
	@EJB TestBean test;
	
	@EJB TestUsedBean icept;
	
	/**
	 * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		try (PrintWriter out = response.getWriter()) {
			/* TODO output your page here. You may use following sample code. */
			out.println("<!DOCTYPE html>");
			out.println("<html>");
			out.println("<head>");
			out.println("<title>Servlet HelloServlet</title>");
			out.println("</head>");
			out.println("<body>");
			out.println("<h1>Servlet HelloServlet at " + request.getContextPath() + "</h1>");
/*
			PersonEntity person = new PersonEntity();
			person.setEmail("test@test.te");
			person.setFirstName("Kalle");
			person.setLastName("Kukko");
			person.setRegistered(new Date());

			MessageAreaEntity mae = new MessageAreaEntity();
			mae.setName("General Stuff");

			MessageEntity me = new MessageEntity();
			me.setTopic("Testing topic");
			me.setWritten(new Date());
			me.setMessageText("Testing message text !!!");
			me.setPerson(person);
			try {
				utx.begin();
				EntityManager em = emf.createEntityManager();
	
				 em.persist(person);
				 em.persist(mae);
				 me.setArea(mae); // we have Id!
				 em.persist(me);
	
				//
				Query q = em.createQuery("SELECT mae FROM MessageAreaEntity mae ORDER BY mae.name");
				List<MessageAreaEntity> areaList = q.getResultList();
				for (MessageAreaEntity messageAreaEntity : areaList) {
					out.println("Message Area:" + messageAreaEntity.getName() + "<br/>");
				}
				out.println("<hr>");
				// crit
				CriteriaBuilder cb = em.getCriteriaBuilder();
				CriteriaQuery<MessageEntity> mec = cb.createQuery(MessageEntity.class);
				TypedQuery<MessageEntity> mecq = em.createQuery(mec);
				for (MessageEntity c : mecq.getResultList()) {
					out.println("Topic: <a href='?area_id=" + c.getArea().getId() + "'>" + c.getTopic() + "</a><br/>");
				}
				out.println("<hr>");
				// crit 2
				Calendar cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				cb = em.getCriteriaBuilder();
				mec = cb.createQuery(MessageEntity.class);
				Root<MessageEntity> p = mec.from(MessageEntity.class);
				Predicate condition = cb.greaterThan(p.get(MessageEntity_.written), cal.getTime());
				mec.where(condition);
				mec.orderBy(cb.desc(p.get(MessageEntity_.written)));
				mecq = em.createQuery(mec);
				for (MessageEntity c : mecq.getResultList()) {
					out.println("Topic: <a href='?area_id=" + c.getArea().getId() + "'>" + c.getTopic() + "</a><br/>");
				}
				out.println("<hr>");
				// crit 3
				cal = Calendar.getInstance();
				cal.add(Calendar.DATE, -1);
				cb = em.getCriteriaBuilder();
				mec = cb.createQuery(MessageEntity.class);
				mecq = em.createQuery(mec);
				mecq.setMaxResults(3);
				for (MessageEntity c : mecq.getResultList()) {
					out.println("Topic: <a href='?area_id=" + c.getArea().getId() + "'>" + c.getTopic() + "</a><br/>");
				}
				// crit 3
				String areaId = request.getParameter("area_id");
				if (areaId != null) {
					Long areaLongId = Long.parseLong(areaId);
					out.println("<hr>");

					cb = em.getCriteriaBuilder();
					mec = cb.createQuery(MessageEntity.class);
					p = mec.from(MessageEntity.class);
					// build join
					Join<MessageEntity, MessageAreaEntity> area = p.join(MessageEntity_.area);
					// filter with join
					condition = cb.equal(area.get(MessageAreaEntity_.id), areaLongId);

					mec.where(condition);
					mecq = em.createQuery(mec);
					Boolean first = true;
					for (MessageEntity c : mecq.getResultList()) {
						if (first) {
							out.println("<h1>Area: " + c.getArea().getName() + "</h1>");
							first = false;
						}
						out.println("Topic:" + c.getTopic() + "<br/>");
					}
//				mecq.unwrap(org.eclipse.persistence.jpa.JpaQuery.class).getDatabaseQuery().getSQLString();
				}
				em.flush();
				em.close();
				utx.commit();
				out.println("Test ok!<br/>");
			} catch (NotSupportedException | SystemException | RollbackException | HeuristicMixedException | HeuristicRollbackException | SecurityException | IllegalStateException ex) {
				Logger.getLogger(HelloServlet.class.getName()).log(Level.SEVERE, null, ex);
			}
*/			
			
			Long areaId = (request.getParameter("area_id")==null?null:Long.parseLong(request.getParameter("area_id")));
			for (MessageAreaEntity c : forumService.findAllAreas() ) {
				out.println("<a href='?area_id="+c.getId()+"'>" + c.getName()+"</a><br/>" );
				if ( areaId != null && areaId.equals( c.getId() ) ) {
					for ( MessageEntity msg : forumService.findMessageForArea(c.getId()) ) {
						PersonEntity user = msg.getPerson() ;
						out.println("--- "+msg.getTopic()+" "+(user==null?"":" by "+user.getFirstName()+" "+user.getLastName()) +"<br/>");
					}
				}
				out.println("<hr/>");
			}
			out.println("<hr/><form method=post>Person:<select name='person_id'>");
			for (PersonEntity c : forumService.findAllUsers() ) {
				out.println("<option value='"+c.getPersonId()+"'>"+c.getFirstName()+" "+c.getLastName()+"</option>");
			}
			
			out.println("</select><br/>Topic:<input type=text name='topic'><br/>Msg:<input type=text name='msg'><br/><input type=submit value='Save'><br/></form>");
			out.println("<hr/><form method=post>Area :<input type=text name='area_name'><br/><input type=submit value='Create'><br/></form>");
			
			
			test.testJDBCAccess();
			
			icept.doRun();
			
			out.println("</body>");
			out.println("</html>");
		}
	}

	// <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
	/**
	 * Handles the HTTP <code>GET</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	/**
	 * Handles the HTTP <code>POST</code> method.
	 *
	 * @param request servlet request
	 * @param response servlet response
	 * @throws ServletException if a servlet-specific error occurs
	 * @throws IOException if an I/O error occurs
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Long areaId = (request.getParameter("area_id")==null?null:Long.parseLong(request.getParameter("area_id")));
		Long person_id = (request.getParameter("person_id")==null?null:Long.parseLong(request.getParameter("person_id")));
		String topic = (request.getParameter("topic")==null?null:request.getParameter("topic"));
		String msg = (request.getParameter("msg")==null?null:request.getParameter("msg"));
		String area_name = (request.getParameter("area_name")==null?null:request.getParameter("area_name"));
		if ( areaId != null && topic != null && msg != null  ) {
			MessageEntity me = new MessageEntity();
			me.setTopic(topic);
			me.setMessageText(msg);
			me.setWritten(new Date());
			me.setPerson(forumService.getPerson(person_id));
			forumService.createMessage(me,areaId);
		}
		if ( area_name != null ) {
			MessageAreaEntity mae = new MessageAreaEntity();
			mae.setName(area_name);
			forumService.createArea(mae);
		}
		processRequest(request, response);
	}

	/**
	 * Returns a short description of the servlet.
	 *
	 * @return a String containing servlet description
	 */
	@Override
	public String getServletInfo() {
		return "Short description";
	}// </editor-fold>

}
