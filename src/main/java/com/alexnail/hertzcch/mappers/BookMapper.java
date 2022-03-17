package com.alexnail.hertzcch.mappers;

import java.util.Collection;

import org.mapstruct.Mapper;

import com.alexnail.hertzcch.entities.Book;
import com.alexnail.hertzcch.entities.BookId;
import com.alexnail.hertzcch.entities.Category;
import com.alexnail.hertzcch.models.BookModel;

@Mapper(componentModel = "spring")
public interface BookMapper {

    Book toEntity(BookModel model);

    BookModel toModel(Book model);

    default String categoryToString(Category category){
        return category.getCategory();
    }

    Category stringToCategory(String category);

    Collection<BookModel> entitiesToModels(Collection<Book> all);

    BookId toBookId(BookModel book);

    Collection<Book> modelsToEntities(Collection<BookModel> books);
}
