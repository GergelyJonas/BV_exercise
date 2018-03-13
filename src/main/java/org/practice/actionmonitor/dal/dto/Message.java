package org.practice.actionmonitor.dal.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;
    private String text;

    public Message(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

}
