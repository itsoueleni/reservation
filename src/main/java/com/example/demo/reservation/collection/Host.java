package com.example.demo.reservation.collection;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Host {
    private String name;
    private String email;
}
