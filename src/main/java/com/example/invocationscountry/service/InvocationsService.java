package com.example.invocationscountry.service;

import com.example.invocationscountry.dto.AverageDistance;
import com.example.invocationscountry.dto.InvocationDistance;
import com.example.invocationscountry.model.Invocations;

import java.util.List;

public interface InvocationsService {
    Invocations saveInvocation(Invocations transactionModel);

    List<Invocations> getAll();

    Integer getInvocationByCountry(String country);

    String getDistanceByCountry(String country);

    Invocations findInvocationsByCountry(String country);

    InvocationDistance getCloseDistance();

    InvocationDistance getFarDistance();

    AverageDistance getAverageDistance();
}
