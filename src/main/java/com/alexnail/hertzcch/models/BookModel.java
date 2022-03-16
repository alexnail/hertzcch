package com.alexnail.hertzcch.models;

import java.util.Collection;

import lombok.Data;

@Data
public class BookModel {

    private String title;

    private String author;

    private Collection<String> categories;
}
