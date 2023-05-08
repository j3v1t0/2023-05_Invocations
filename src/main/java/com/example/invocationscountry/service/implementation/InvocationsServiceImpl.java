package com.example.invocationscountry.service.implementation;

import com.example.invocationscountry.dto.InvocationDistance;
import com.example.invocationscountry.model.Invocations;
import com.example.invocationscountry.repository.InvocationsRepository;
import com.example.invocationscountry.service.InvocationsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class InvocationsServiceImpl implements InvocationsService {

    @Autowired
    private InvocationsRepository invocationsRepository;

    @Override
    public Invocations saveInvocation(Invocations transactionModel) {
        return invocationsRepository.save(transactionModel);
    }

    @Override
    public List<Invocations> getAll(){
        return invocationsRepository.findAll();
    }

    @Override
    public Integer getInvocationByCountry(String country) {
        Integer TotalInvocationByCountry = invocationsRepository.getInvocationsByCountry(country);
        return TotalInvocationByCountry;
    }
    @Override
    public String getDistanceByCountry(String country){
        String TotalDistanceByCountry = invocationsRepository.getDistanceByCountry(country);
        return TotalDistanceByCountry;
    }
    @Override
    public Invocations findInvocationsByCountry(String country){
        Invocations findInvocationByCountry = invocationsRepository.findInvocationsByCountry(country);
        return findInvocationByCountry;
    }
    @Override
    public InvocationDistance getCloseDistance(){
        String closeDistance = invocationsRepository.min();
        if (closeDistance == null){
            log.error("Distance is null");
            throw new RuntimeException("Distance is null");
        }
        Invocations invocations = invocationsRepository.findByDistance(closeDistance + " km");
        InvocationDistance invocationDistance = new InvocationDistance();
        invocationDistance.setDistance(closeDistance);
        invocationDistance.setInvocations(invocations.getInvocations());
        invocationDistance.setCountry(invocations.getCountry());
        return invocationDistance;
    }
    @Override
    public InvocationDistance getFarDistance(){
        String farDistance = invocationsRepository.max();
        if (farDistance == null){
            log.error("Distance is null");
            throw new RuntimeException("Distance is null");
        }
        Invocations invocations = invocationsRepository.findByDistance(farDistance  + " km");
        InvocationDistance invocationDistance = new InvocationDistance();
        invocationDistance.setDistance(farDistance);
        invocationDistance.setInvocations(invocations.getInvocations());
        invocationDistance.setCountry(invocations.getCountry());
        return invocationDistance;
    }
}
