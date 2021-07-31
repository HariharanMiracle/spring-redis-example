package com.darkdevildev.springredisexample.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Data
@Entity
public class Test implements Serializable {
    @Id
    private String id;
    private String firstName;
}
