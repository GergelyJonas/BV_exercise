package org.practice.actionmonitor.web.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MessageRequest {

    private String text;

    public String getText() {
        return text;
    }

}
