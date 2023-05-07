package com.example.invocationscountry.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class IpRequest implements Serializable {
    private String ip;
    private String region;
    private String country_code;
    private String country_name;
    private String languages;
    private Double latitude;
    private Double longitude;
    private String currency;
    private String timezone;
}
