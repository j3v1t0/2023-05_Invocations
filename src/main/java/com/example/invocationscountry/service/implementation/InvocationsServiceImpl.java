package com.example.invocationscountry.service.implementation;

import com.example.invocationscountry.model.Invocations;
import com.example.invocationscountry.repository.InvocationsRepository;
import com.example.invocationscountry.service.InvocationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        Integer TotalInvocationByCountry = invocationsRepository.getCountInvocationsByCountry(country);
        return TotalInvocationByCountry;
    }
}