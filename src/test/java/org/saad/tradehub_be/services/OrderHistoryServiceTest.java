package org.saad.tradehub_be.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.User;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.saad.tradehub_be.repository.UserRepository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderHistoryServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ItemListingRepository itemListingRepository;

    @InjectMocks
    private OrderHistoryService orderHistoryService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetOrderHistory_withValidBuyerId_returnsOrderHistory() {
        User buyer = new User();
        buyer.setUserId("buyer123");
        buyer.setPurchasedItemIds(List.of("item1", "item2", "item3"));

        ItemListing item1 = new ItemListing();
        item1.setItemId("item1");
        ItemListing item2 = new ItemListing();
        item2.setItemId("item2");
        ItemListing item3 = new ItemListing();
        item3.setItemId("item3");

        when(userRepository.findById("buyer123")).thenReturn(Optional.of(buyer));
        when(itemListingRepository.findById("item1")).thenReturn(Optional.of(item1));
        when(itemListingRepository.findById("item2")).thenReturn(Optional.of(item2));
        when(itemListingRepository.findById("item3")).thenReturn(Optional.of(item3));

        List<ItemListing> orderHistory = orderHistoryService.getOrderHistory("buyer123");

        assertEquals(3, orderHistory.size());
        assertTrue(orderHistory.contains(item1));
        assertTrue(orderHistory.contains(item2));
        assertTrue(orderHistory.contains(item3));
    }

    @Test
    public void testGetOrderHistory_withEmptyPurchaseIds_returnsEmptyList() {
        User buyer = new User();
        buyer.setUserId("buyer123");
        buyer.setPurchasedItemIds(List.of());

        when(userRepository.findById("buyer123")).thenReturn(Optional.of(buyer));

        List<ItemListing> orderHistory = orderHistoryService.getOrderHistory("buyer123");

        assertTrue(orderHistory.isEmpty());
    }

    @Test
    public void testGetOrderHistory_withInvalidBuyerId_throwsException() {
        when(userRepository.findById("invalidBuyer")).thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            orderHistoryService.getOrderHistory("invalidBuyer");
        });

        assertEquals("Buyer not found", exception.getMessage());
    }

    @Test
    public void testGetOrderHistory_withMissingItemInRepository_returnsPartialOrderHistory() {
        User buyer = new User();
        buyer.setUserId("buyer123");
        buyer.setPurchasedItemIds(List.of("item1", "item2", "item3"));

        ItemListing item1 = new ItemListing();
        item1.setItemId("item1");
        ItemListing item2 = new ItemListing();
        item2.setItemId("item2");

        when(userRepository.findById("buyer123")).thenReturn(Optional.of(buyer));
        when(itemListingRepository.findById("item1")).thenReturn(Optional.of(item1));
        when(itemListingRepository.findById("item2")).thenReturn(Optional.of(item2));
        when(itemListingRepository.findById("item3")).thenReturn(Optional.empty()); // Missing item

        List<ItemListing> orderHistory = orderHistoryService.getOrderHistory("buyer123");

        assertEquals(2, orderHistory.size()); // Only two items should be returned
        assertTrue(orderHistory.contains(item1));
        assertTrue(orderHistory.contains(item2));
    }
}
