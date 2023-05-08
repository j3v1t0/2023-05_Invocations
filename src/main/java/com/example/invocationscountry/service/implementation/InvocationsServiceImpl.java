package com.example.invocationscountry.service.implementation;

import com.example.invocationscountry.dto.AverageDistance;
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

    @Override
    public AverageDistance getAverageDistance(){
        AverageDistance distanceResponse = new AverageDistance();

        // Obtener la suma de las invocaciones desde la base de datos
        Double totalInvocations = Double.valueOf(invocationsRepository.getTotalSum());

        // Obtener todos los registros de la tabla Invocations
        List<Invocations> invocationsList = invocationsRepository.findAll();

        // Calcular la distancia promedio ponderada
        double totalDistanceWeighted = 0.0;
        for (Invocations invocation : invocationsList) {
            double distance = Double.parseDouble(invocation.getDistance().replace(" km", ""));
            int invocations = invocation.getInvocations();
            totalDistanceWeighted += distance * invocations;
        }
        double averageDistance = totalDistanceWeighted / totalInvocations;

        // Configurar la respuesta
        distanceResponse.setAverageDistance(String.format("%.2f km", averageDistance));

        return distanceResponse;
    }
}
