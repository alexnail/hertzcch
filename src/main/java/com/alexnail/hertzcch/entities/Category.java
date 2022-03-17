package com.alexnail.hertzcch.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Category {

    @Id
    private String category;
}