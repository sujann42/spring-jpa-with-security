package com.rest.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;


@Data
@NoArgsConstructor
@ToString
@ApiModel("Details about the User")
public class User {

    private Integer id;
    @Size(min=2, max = 23, message = "Name should have at least 2 characters.")
    @ApiModelProperty("Name should be at least 2 characters.")
    private String name;
    @Past
    @ApiModelProperty("Birth date should not be in the past.")
    private Date birthDate;

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
