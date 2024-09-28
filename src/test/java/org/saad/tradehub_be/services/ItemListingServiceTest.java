package org.saad.tradehub_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ItemListingServiceTest {

    @Mock
    private ItemListingRepository itemListingRepository;

    @InjectMocks
    private ItemListingService itemListingService;

    private ItemListing mockItemListing;

    @BeforeEach
    public void setUp() {
        mockItemListing = new ItemListing();
        mockItemListing.setItemId("item123");
        mockItemListing.setName("Test Item");
        mockItemListing.setDescription("Test description");
        mockItemListing.setPrice(100.00);
    }

    @Test
    public void testFindById_withValidId_returnsItemListing() {
        when(itemListingRepository.findById("item123")).thenReturn(Optional.of(mockItemListing));

        List<ItemListing> result = itemListingService.findById("item123");

        assertFalse(result.isEmpty());
        assertEquals("Test Item", result.get(0).getName());
        verify(itemListingRepository, times(1)).findById("item123");
    }

    @Test
    public void testFindById_withInvalidId_returnsEmptyList() {
        when(itemListingRepository.findById("invalidId")).thenReturn(Optional.empty());

        List<ItemListing> result = itemListingService.findById("invalidId");

        assertTrue(result.isEmpty());
        verify(itemListingRepository, times(1)).findById("invalidId");
    }

    @Test
    public void testSearchItemListings_withMatchingQuery_returnsItemListings() {
        when(itemListingRepository.findByNameContaining("Test")).thenReturn(List.of(mockItemListing));

        List<ItemListing> result = itemListingService.searchItemListings("Test");

        assertFalse(result.isEmpty());
        assertEquals("Test Item", result.get(0).getName());
        verify(itemListingRepository, times(1)).findByNameContaining("Test");
    }

    @Test
    public void testSearchItemListings_withNoMatchingQuery_returnsEmptyList() {
        when(itemListingRepository.findByNameContaining("Nonexistent")).thenReturn(List.of());

        List<ItemListing> result = itemListingService.searchItemListings("Nonexistent");

        assertTrue(result.isEmpty());
        verify(itemListingRepository, times(1)).findByNameContaining("Nonexistent");
    }
}
