package com.alexnail.hertzcch.mappers;

import org.mapstruct.Mapper;

import com.alexnail.hertzcch.entities.Book;
import com.alexnail.hertzcch.entities.BookCategory;
import com.alexnail.hertzcch.models.BookModel;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookModel model);

    BookModel toModel(Book model);

    default String categoryToString(BookCategory category){
        return category.getCategory();
    }

    BookCategory stringToCategory(String category);
}
