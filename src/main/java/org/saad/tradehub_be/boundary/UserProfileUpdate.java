package org.saad.tradehub_be.boundary;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserProfileUpdate {
    private String userId; //can't be modified
    private String email;
    private String phoneNumber;
    private String address;
    private String zipCode;
}
