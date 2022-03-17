package com.alexnail.hertzcch.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
@IdClass(BookId.class)
public class Book {

    @Id
    private String title;

    @Id
    private String author;

    @OneToMany(fetch = FetchType.EAGER)
    private List<Category> categories;

    @ManyToOne
    private User loanedBy;
}
