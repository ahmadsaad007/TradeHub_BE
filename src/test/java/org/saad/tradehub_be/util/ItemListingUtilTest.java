package org.saad.tradehub_be.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.ReportListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.Category;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.ItemListingReport;
import org.saad.tradehub_be.entity.data.User;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ItemListingUtilTest {

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testBuildItemListing_withValidData_returnsItemListing() {
        // Arrange
        NewListingForm newListingForm = NewListingForm.builder()
                .listingId("item123")
                .name("Test Item")
                .description("A test description")
                .price(100.0)
                .imageUrls(List.of("http://image1.com"))
                .listingAddress("123 Test St")
                .zip("12345")
                .categoryName("Electronics")
                .createdAt(new Date())
                .build();

        User mockUser = new User();
        mockUser.setUsername("testUser");

        Category mockCategory = new Category();
        mockCategory.setName("Electronics");

        // Act
        ItemListing itemListing = ItemListingUtil.buildItemListing(newListingForm, mockUser, mockCategory);

        // Assert
        assertEquals("item123", itemListing.getItemId());
        assertEquals("Test Item", itemListing.getName());
        assertEquals(100.0, itemListing.getPrice());
        assertEquals("testUser", itemListing.getSellerInfo().getUsername());
        assertEquals("Electronics", itemListing.getCategory().getName());
        assertEquals("123 Test St", itemListing.getListingAddress());
        assertEquals("12345", itemListing.getZip());
        assertTrue(itemListing.isAvailable());
        assertNotNull(itemListing.getCreatedAt());
        assertEquals(List.of("http://image1.com"), itemListing.getImageUrls());
    }

    @Test
    public void testGetUpdatedItemListing_withValidData_updatesListing() {
        // Arrange
        ItemListing existingListing = ItemListing.builder()
                .itemId("item123")
                .name("Old Name")
                .description("Old description")
                .price(50.0)
                .listingAddress("Old Address")
                .zip("00000")
                .imageUrls(List.of("http://oldimage.com"))
                .createdAt(new Date())
                .build();

        UpdateListingForm updateListingForm = UpdateListingForm.builder()
                .name("Updated Name")
                .description("Updated description")
                .price(200.0)
                .listingAddress("Updated Address")
                .zip("54321")
                .imageUrls(List.of("http://newimage.com"))
                .createdAt(new Date())
                .categoryName("Updated Category")
                .build();

        Category newCategory = new Category();
        newCategory.setName("Updated Category");

        // Act
        ItemListing updatedListing = ItemListingUtil.getUpdatedItemListing(existingListing, updateListingForm, newCategory);

        // Assert
        assertEquals("Updated Name", updatedListing.getName());
        assertEquals("Updated description", updatedListing.getDescription());
        assertEquals(200.0, updatedListing.getPrice());
        assertEquals("Updated Address", updatedListing.getListingAddress());
        assertEquals("54321", updatedListing.getZip());
        assertEquals(List.of("http://newimage.com"), updatedListing.getImageUrls());
        assertEquals("Updated Category", updatedListing.getCategory().getName());
        assertNotNull(updatedListing.getCreatedAt());
    }

    @Test
    public void testGetReport_withValidData_returnsReportListingForm() {
        // Arrange
        ItemListingReport itemListingReport = ItemListingReport.builder()
                .itemId("item123")
                .sellerId("seller123")
                .reportedBy("buyer123")
                .reason("SPAM")
                .reportedAt(new Date())
                .build();

        // Act
        ReportListingForm reportListingForm = ItemListingUtil.getReport(itemListingReport);

        // Assert
        assertEquals("item123", reportListingForm.getItemId());
        assertEquals("seller123", reportListingForm.getSellerId());
        assertEquals("buyer123", reportListingForm.getBuyerReportedBy());
        assertEquals("SPAM", reportListingForm.getReportType());
        assertNotNull(reportListingForm.getTimeReported());
        assertFalse(reportListingForm.isReviewed());
    }

    @Test
    public void testGetUpdatedItemListing_withEmptyFields_doesNotChangeListing() {
        // Arrange
        ItemListing existingListing = ItemListing.builder()
                .itemId("item123")
                .name("Old Name")
                .description("Old description")
                .price(50.0)
                .listingAddress("Old Address")
                .zip("00000")
                .imageUrls(List.of("http://oldimage.com"))
                .createdAt(new Date())
                .build();

        UpdateListingForm updateListingForm = UpdateListingForm.builder().build(); // No changes

        Category category = new Category();
        category.setName("Old Category");

        // Act
        ItemListing updatedListing = ItemListingUtil.getUpdatedItemListing(existingListing, updateListingForm, category);

        // Assert
        assertEquals("Old Name", updatedListing.getName());
        assertEquals("Old description", updatedListing.getDescription());
        assertEquals(50.0, updatedListing.getPrice());
        assertEquals("Old Address", updatedListing.getListingAddress());
        assertEquals("00000", updatedListing.getZip());
        assertEquals(List.of("http://oldimage.com"), updatedListing.getImageUrls());
        assertEquals("Old Category", updatedListing.getCategory().getName());
    }
}
