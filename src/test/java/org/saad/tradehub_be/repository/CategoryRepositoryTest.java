package org.saad.tradehub_be.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.saad.tradehub_be.entity.data.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    private Category mockCategory;

    @BeforeEach
    public void setUp() {
        mockCategory = new Category();
        mockCategory.setCategoryId("category123");
        mockCategory.setName("Electronics");
        mockCategory.setDescription("Category for electronics items");

        categoryRepository.save(mockCategory);
    }

    @Test
    public void testFindByName_withValidCategoryName_returnsCategory() {
        Optional<Category> foundCategory = categoryRepository.findByName("Electronics");

        assertTrue(foundCategory.isPresent());
        assertEquals("Electronics", foundCategory.get().getName());
    }

    @Test
    public void testFindByName_withInvalidCategoryName_returnsEmpty() {
        Optional<Category> foundCategory = categoryRepository.findByName("InvalidCategory");

        assertFalse(foundCategory.isPresent());
    }

    @Test
    public void testSaveCategory_andRetrieveById() {
        Optional<Category> foundCategory = categoryRepository.findById("category123");

        assertTrue(foundCategory.isPresent());
        assertEquals("Electronics", foundCategory.get().getName());
        assertEquals("Category for electronics items", foundCategory.get().getDescription());
    }
}
