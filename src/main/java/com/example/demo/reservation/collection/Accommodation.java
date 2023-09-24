package com.example.demo.reservation.collection;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Accommodation {

    private String description;
    private boolean available;

    public boolean isAvailable() {
        return available;
    }

}
