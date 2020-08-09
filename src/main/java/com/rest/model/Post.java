package com.rest.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class Post {
    @Id
    @GeneratedValue
    private int id;
    private String description;

    //User has a many-to-one relation with Post
    //Unless you call it will not fetch
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Post{" +
                "id=" + id +
                ", description='" + description + '\'' +
                '}';
    }
}
