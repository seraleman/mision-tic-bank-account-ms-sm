package com.seraleman.account_ms.models;

import org.springframework.data.annotation.Id;
import java.util.Date;

public class Transaction {

    @Id
    private String id;

    private String usernameOrigin;

    private String usernameDestinity;

    private Integer value;

    private Date date;

    public Transaction(String id, String usernameOrigin, String usernameDestinity, Integer value, Date date) {
        this.id = id;
        this.usernameOrigin = usernameOrigin;
        this.usernameDestinity = usernameDestinity;
        this.value = value;
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsernameOrigin() {
        return usernameOrigin;
    }

    public void setUsernameOrigin(String usernameOrigin) {
        this.usernameOrigin = usernameOrigin;
    }

    public String getUsernameDestinity() {
        return usernameDestinity;
    }

    public void setUsernameDestinity(String usernameDestinity) {
        this.usernameDestinity = usernameDestinity;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
