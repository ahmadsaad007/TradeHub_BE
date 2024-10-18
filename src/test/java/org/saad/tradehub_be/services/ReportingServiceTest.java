package org.saad.tradehub_be.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.saad.tradehub_be.data.ItemListingReport;
import org.saad.tradehub_be.repository.ItemListingReportRespository;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReportingServiceTest {

    @Mock
    private ItemListingReportRespository itemListingReportRespository;

    @InjectMocks
    private ReportingService reportingService;

    @Test
    public void testReportListing_withValidData() {
        ItemListingReport mockReport = mock(ItemListingReport.class);

        reportingService.reportListing(mockReport);

        verify(itemListingReportRespository, times(1)).save(any());
    }

    @Test
    public void testReportListing_withNullData_throwsException() {
        assertThrows(RuntimeException.class, () -> reportingService.reportListing(null));
    }
}
