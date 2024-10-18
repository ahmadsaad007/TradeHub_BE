package org.saad.tradehub_be.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.saad.tradehub_be.data.Category;
import org.saad.tradehub_be.data.ItemListing;
import org.saad.tradehub_be.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class ItemListingRepositoryTest {

    @Autowired
    private ItemListingRepository itemListingRepository;

    // We add a UserRepository to save the User entity (mockSeller) before saving the ItemListing.
    // This ensures that the User entity exists in the database when Hibernate tries to fetch it lazily. Same for Category
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    private ItemListing mockItemListing;
    private User mockSeller;
    private Category mockCategory;

    @BeforeEach
    public void setUp() {
        mockSeller = new User();
        mockSeller.setUserId("user123");
        mockSeller.setUsername("testSeller");
        mockSeller.setPassword("password");
        mockSeller.setEmail("seller@example.com");
        userRepository.save(mockSeller);  // Save the seller in the database

        mockCategory = new Category();
        mockCategory.setCategoryId("1");
        mockCategory.setName("Electronics");
        categoryRepository.save(mockCategory);  // Save the category in the database

        mockItemListing = ItemListing.builder()
                .itemId("item123")
                .name("Test Item")
                .description("Test description")
                .price(99.99)
                .sellerInfo(mockSeller)
                .imageUrls(List.of("http://example.com/image1.png"))
                .listingAddress("123 Main St")
                .zip("12345")
                .category(mockCategory)
                .isAvailable(true)
                .createdAt(new Date())
                .build();

        // Save the item listing to the repository
        itemListingRepository.save(mockItemListing);
    }

    @Test
    public void testFindById_withValidId_returnsItemListing() {
        Optional<ItemListing> foundItemListing = itemListingRepository.findById("item123");

        assertTrue(foundItemListing.isPresent());
        assertEquals("Test Item", foundItemListing.get().getName());
        assertEquals("testSeller", foundItemListing.get().getSellerInfo().getUsername());
    }

    @Test
    public void testFindById_withInvalidId_returnsEmpty() {
        Optional<ItemListing> foundItemListing = itemListingRepository.findById("invalidId");

        assertFalse(foundItemListing.isPresent());
    }

    @Test
    public void testFindByNameContaining_withMatchingName_returnsItemListings() {
        List<ItemListing> foundItemListings = itemListingRepository.findByNameContaining("Test");

        assertFalse(foundItemListings.isEmpty());
        assertEquals(1, foundItemListings.size());
        assertEquals("Test Item", foundItemListings.get(0).getName());
    }

    @Test
    public void testFindByNameContaining_withNoMatchingName_returnsEmptyList() {
        List<ItemListing> foundItemListings = itemListingRepository.findByNameContaining("Nonexistent");

        assertTrue(foundItemListings.isEmpty());
    }

    @Test
    public void testFindBySellerInfoUsername_withValidUsername_returnsItemListings() {
        List<ItemListing> foundItemListings = itemListingRepository.findBySellerInfoUsername("testSeller");

        assertFalse(foundItemListings.isEmpty());
        assertEquals(1, foundItemListings.size());
        assertEquals("testSeller", foundItemListings.get(0).getSellerInfo().getUsername());
    }

    @Test
    public void testFindBySellerInfoUsername_withInvalidUsername_returnsEmptyList() {
        List<ItemListing> foundItemListings = itemListingRepository.findBySellerInfoUsername("invalidSeller");

        assertTrue(foundItemListings.isEmpty());
    }

    @Test
    public void testFindAll_returnsAllItemListings() {
        List<ItemListing> allListings = itemListingRepository.findAll();

        assertFalse(allListings.isEmpty());
        assertEquals(1, allListings.size());
    }
}
