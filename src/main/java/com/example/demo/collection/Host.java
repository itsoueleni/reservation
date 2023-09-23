package com.example.demo.collection;

import lombok.Builder;
import lombok.Data;

import java.util.List;
@Data
@Builder
public class Host {
    private String name;
    private String email;
}
