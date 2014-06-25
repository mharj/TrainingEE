/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package kurssi.servlet;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Oppi
 */
@Entity
@TableGenerator(name="AREA_SEQ",table = "SEQUENCE",pkColumnName = "SEQ_NAME")  
@Table(name="message_area")
public class MessageAreaEntity implements Serializable {
    @Id 
    @GeneratedValue(strategy = GenerationType.TABLE, generator="AREA_SEQ")
    private Long id;
    private String name;
    @OneToMany(mappedBy="area")
    private List<MessageEntity> messages = Collections.EMPTY_LIST;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MessageEntity> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageEntity> messages) {
        this.messages = messages;
    }
}
