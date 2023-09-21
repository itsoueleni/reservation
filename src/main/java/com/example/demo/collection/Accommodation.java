package com.example.demo.collection;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Accommodation {
    private String description;
    private boolean isAvailable;
}
