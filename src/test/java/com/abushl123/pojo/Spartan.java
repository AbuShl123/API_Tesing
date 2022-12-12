package com.abushl123.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;


@JsonIgnoreProperties(value = "id", allowSetters = true, allowGetters = true)
@Data
public class Spartan {
    private int id;
    private String name;
    private String gender;
    private long phone;
}
