package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.data.User;
import org.saad.tradehub_be.repository.UserRepository;

import java.util.ArrayList;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BuyerServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BuyerService buyerService;

    @Test
    public void testAddPurchaseOrder_withValidUserAndNewItem() {
        User mockUser = new User();
        mockUser.setPurchasedItemIds(new ArrayList<>());

        when(userRepository.findById("user123")).thenReturn(Optional.of(mockUser));

        buyerService.addPurchaseOrder("user123", "item123");

        assertTrue(mockUser.getPurchasedItemIds().contains("item123"));
        verify(userRepository, times(1)).save(mockUser);
    }

    @Test
    public void testAddPurchaseOrder_withExistingItem_throwsException() {
        User mockUser = new User();
        ArrayList<String> purchasedItems = new ArrayList<>();
        purchasedItems.add("item123");
        mockUser.setPurchasedItemIds(purchasedItems);

        when(userRepository.findById("user123")).thenReturn(Optional.of(mockUser));

        assertThrows(RuntimeException.class, () -> buyerService.addPurchaseOrder("user123", "item123"));
    }

    @Test
    public void testAddPurchaseOrder_withInvalidUser_throwsException() {
        when(userRepository.findById("user123")).thenReturn(Optional.empty());

        assertThrows(RuntimeException.class, () -> buyerService.addPurchaseOrder("user123", "item123"));
    }
}
