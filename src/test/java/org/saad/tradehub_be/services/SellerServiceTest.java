package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.boundary.request.NewListingForm;
import org.saad.tradehub_be.boundary.request.UpdateListingForm;
import org.saad.tradehub_be.entity.data.Category;
import org.saad.tradehub_be.entity.data.ItemListing;
import org.saad.tradehub_be.entity.data.User;
import org.saad.tradehub_be.repository.CategoryRepository;
import org.saad.tradehub_be.repository.ItemListingRepository;
import org.saad.tradehub_be.repository.UserRepository;
import org.saad.tradehub_be.util.ItemListingUtil;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SellerServiceTest {

    @Mock
    private ItemListingRepository itemListingRepository;

    @Mock
    private CategoryRepository categoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private SellerService sellerService;

    @Test
    public void testCreateListing_withValidData_returnsSuccess() {
        Category mockCategory = mock(Category.class);
        User mockSellerUser = mock(User.class);

        NewListingForm newListingForm = NewListingForm.builder()
                .name("Sample Item")
                .description("Sample item for testing")
                .price(100.00)
                .sellerUsername("seller")
                .imageUrls(List.of("http://example.com/image1.png"))
                .listingAddress("123 Main St")
                .zip("12345")
                .categoryName("Electronics")
                .build();

        when(userRepository.findByUsername("seller")).thenReturn(Optional.ofNullable(mockSellerUser));
        when(categoryRepository.findByName("Electronics")).thenReturn(Optional.ofNullable(mockCategory));

        sellerService.createListing(newListingForm);

        verify(itemListingRepository, times(1)).save(any(ItemListing.class));
    }

    @Test
    public void testCreateListing_withMissingFields_throwsException() {
        NewListingForm newListingForm = NewListingForm.builder()
                .description("This item has no name")
                .price(100.00)
                .sellerUsername("seller")
                .imageUrls(List.of("http://example.com/image1.png"))
                .listingAddress("123 Main St")
                .zip("12345")
                .categoryName("Electronics")
                .build();

        assertThrows(RuntimeException.class, () -> sellerService.createListing(newListingForm));

        verify(itemListingRepository, never()).save(any(ItemListing.class));
    }

    @Test
    public void testUpdateListing_withValidData_updatesSuccessfully() {
        UpdateListingForm updateListingForm = UpdateListingForm.builder()
                .name("Updated Item Name")
                .description("Updated description")
                .price(150.00)
                .imageUrls(List.of("http://example.com/image2.png"))
                .listingAddress("789 Oak St")
                .zip("54321")
                .categoryName("Home Appliances")
                .build();

        ItemListing mockListing = new ItemListing();
        mockListing.setName("Old Item Name");

        Category mockCategory = new Category();
        when(itemListingRepository.findById("item123")).thenReturn(Optional.of(mockListing));
        when(categoryRepository.findByName("Home Appliances")).thenReturn(Optional.of(mockCategory));

        mockStatic(ItemListingUtil.class);
        when(ItemListingUtil.getUpdatedItemListing(mockListing, updateListingForm, mockCategory))
                .thenAnswer(invocation -> {
                    ItemListing updatedListing = mockListing;
                    updatedListing.setName(updateListingForm.getName());
                    updatedListing.setDescription(updateListingForm.getDescription());
                    updatedListing.setPrice(updateListingForm.getPrice());
                    updatedListing.setImageUrls(updateListingForm.getImageUrls());
                    updatedListing.setListingAddress(updateListingForm.getListingAddress());
                    updatedListing.setZip(updateListingForm.getZip());
                    updatedListing.setCategory(mockCategory);
                    return updatedListing;
                });

        sellerService.updateExistingListing("item123", updateListingForm);

        verify(itemListingRepository, times(1)).save(mockListing);
        assertEquals("Updated Item Name", mockListing.getName());
        assertEquals(150.00, mockListing.getPrice());
    }

    @Test
    public void testDeleteListing_withValidId_deletesSuccessfully() {
        ItemListing mockListing = new ItemListing();
        when(itemListingRepository.findById("item123")).thenReturn(Optional.of(mockListing));

        sellerService.deleteListing("item123");

        verify(itemListingRepository, times(1)).delete(mockListing);
    }

    @Test
    public void testDeleteListing_withInvalidId_throwsNotFoundException() {
        when(itemListingRepository.findById("nonexistentId")).thenReturn(Optional.empty());
        assertThrows(RuntimeException.class, () -> sellerService.deleteListing("nonexistentId"));
    }
}
