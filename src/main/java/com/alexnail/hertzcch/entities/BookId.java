package com.alexnail.hertzcch.entities;

import java.io.Serializable;

import lombok.Data;

@Data
public class BookId implements Serializable {

    private String title;

    private String author;
}
