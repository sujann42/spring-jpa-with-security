package com.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import java.util.List;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;


@Data
@ToString
@ApiModel("Details about the User")
@Entity
public class User {

    @Id
    @GeneratedValue
    private Integer id;
    @Size(min=2, max = 23, message = "Name should have at least 2 characters.")
    @ApiModelProperty("Name should be at least 2 characters.")
    private String name;
    @Past
    @ApiModelProperty("Birth date should not be in the past.")
    private Date birthDate;

    //One User can have many posts
    @OneToMany(mappedBy = "user")
    private List<Post> posts;

    public List<Post> getPosts() {
        return posts;
    }

    public void setPosts(List<Post> posts) {
        this.posts = posts;
    }

    public User() {
    }

    public User(Integer id, String name, Date birthDate) {
        super();
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
