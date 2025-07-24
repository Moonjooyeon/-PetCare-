// HospitalDto.java
package com.petcare.petCarepp.Hospital.DTO;

import lombok.*;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HospitalDto {
    private String placeId;
    private String placeName;
    private String addressName;
    private String phone;
    private Double lat;
    private Double lng;

    public String getName() {
        return placeName;
    }

    public String getAddress() {
        return addressName;
    }
}
