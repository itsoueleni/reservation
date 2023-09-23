package com.example.demo.collection;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;


@Data
@Builder
public class Accommodation {

   @Id
    private Long accomodationId;
    private String description;
    private boolean isAvailable;
}
