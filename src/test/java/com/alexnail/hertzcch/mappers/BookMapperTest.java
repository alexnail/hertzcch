package com.alexnail.hertzcch.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.alexnail.hertzcch.entities.BookCategory;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void testCategoryToString() {
        BookCategory bookCategory = new BookCategory();
        bookCategory.setCategory("XYZ");

        assertEquals("XYZ", mapper.categoryToString(bookCategory));
    }
}