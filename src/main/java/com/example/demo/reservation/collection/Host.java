package com.example.demo.reservation.collection;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class Host {
    @ApiModelProperty(value = "The name of the host or accommodation provider")
    private String name;
    @ApiModelProperty(value = "The email of the host or accommodation provider")
    private String email;
}
