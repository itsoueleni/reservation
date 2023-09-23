package com.example.demo.collection;

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
