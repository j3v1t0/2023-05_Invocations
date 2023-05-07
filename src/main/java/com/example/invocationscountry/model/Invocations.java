package com.example.invocationscountry.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Table(name = "invocations")
public class Invocations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idInvocation;
    @NonNull
    @Column(name = "country", nullable = false)
    private String country;
    @NonNull
    @Column(name = "distance", nullable = false)
    private String distance;
    @NonNull
    @Column(name = "invocations", nullable = false)
    private Integer invocations;
}
