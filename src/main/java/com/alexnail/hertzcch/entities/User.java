package com.alexnail.hertzcch.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class User {

    @Id
    private Long id;

    private String name;

    @OneToMany
    private List<Book> books;
}
