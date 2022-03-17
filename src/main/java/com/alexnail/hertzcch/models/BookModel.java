package com.alexnail.hertzcch.models;

import java.util.Collection;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookModel {

    private String title;

    private String author;

    private Collection<String> categories;
}
