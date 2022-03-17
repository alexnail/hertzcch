package com.alexnail.hertzcch.mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import com.alexnail.hertzcch.entities.Category;

class BookMapperTest {

    private final BookMapper mapper = Mappers.getMapper(BookMapper.class);

    @Test
    void testCategoryToString() {
        Category category = new Category();
        category.setCategory("XYZ");

        assertEquals("XYZ", mapper.categoryToString(category));
    }
}