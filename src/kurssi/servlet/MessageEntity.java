/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.servlet;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Oppi
 */
@Entity
@Table(name="messages")
public class MessageEntity implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Column(name="topic") private String topic;

    @Column(name="message") private String messageText;
    
    @Column(name="written") 
    @Temporal(TemporalType.TIMESTAMP)
    private Date written;
    
    @ManyToOne
    private MessageAreaEntity area;
    
    @OneToOne
    @JoinColumn(name="personId")
    private PersonEntity person;



    public Long getId() {
        return id;
    }
    public void setId(Long id ) {
        this.id = id;
    }
    
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }

    public Date getWritten() {
        return written;
    }

    public void setWritten(Date written) {
        this.written = written;
    }

    public MessageAreaEntity getArea() {
        return area;
    }

    public void setArea(MessageAreaEntity area) {
        this.area = area;
    }    
    public PersonEntity getPerson() {
        return person;
    }

    public void setPerson(PersonEntity person) {
        this.person = person;
    }    
}
