package com.example.invocationscountry.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvocationDistance {
    private String country;
    private String distance;
    private Integer invocations;
}
