package com.example.invocationscountry.message;

import com.example.invocationscountry.dto.IpRequest;
import com.example.invocationscountry.model.Invocations;
import com.example.invocationscountry.service.InvocationsService;
import com.example.invocationscountry.utils.DistanceUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.listener.AcknowledgingMessageListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class InvocationsConsumerListenerManual implements AcknowledgingMessageListener<Integer, String> {
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private InvocationsService invocationsService;

    @KafkaListener(topics = "${spring.kafka.template.default-topic}")
    @Override
    public void onMessage(ConsumerRecord<Integer, String> data, Acknowledgment acknowledgment) {
        // TODO Auto-generated method stub

        System.out.println("Datos : " + data.value());

        try {
            IpRequest request = objectMapper.readValue(data.value(), IpRequest.class);
            Invocations invocations = new Invocations();

            //Set Country Name
            String countryName = request.getCountry_name();
            invocations.setCountry(countryName);

            Double latitude = request.getLatitude();
            Double longitude = request.getLongitude();

            //Validate if the table is empty
            if(invocationsService.getAll().isEmpty()){
                invocations.setInvocations(1);

                //Calculating distance between the country that sends the request and Argentina.
                Double totalDistanceInvocation = DistanceUtil.calculateDistanceFromArgentina(latitude, longitude);
                totalDistanceInvocation = Math.round(totalDistanceInvocation * 100) / 100d;

                //Converting the Distance attribute to a String
                String CountDistanceString = totalDistanceInvocation + " km";
                invocations.setDistance(countryName);

                //Save
                invocationsService.saveInvocation(invocations);
                log.info("Post: Country {} - Distance {} - Invocaciones {}", countryName, CountDistanceString, 1);
            }else {
                //Consulting the total number of invocations per country.
                Integer totalInvocationsByCountry = (invocationsService.getInvocationByCountry(countryName));

                // Validate if totalInvocationsByCountry is null
                if(totalInvocationsByCountry == null){
                    totalInvocationsByCountry = 1;
                    invocations.setInvocations(totalInvocationsByCountry);

                    //Calculating distance between the country that sends the request and Argentina.
                    Double totalDistanceInvocation = DistanceUtil.calculateDistanceFromArgentina(latitude, longitude);
                    totalDistanceInvocation = Math.round(totalDistanceInvocation * 100) / 100d;

                    //Converting the Distance attribute to a String
                    String CountDistanceString = totalDistanceInvocation + " km";
                    invocations.setDistance(countryName);

                    //Save
                    invocationsService.saveInvocation(invocations);
                    log.info("Post: Country {} - Distance {} - Invocaciones {}", countryName, CountDistanceString, totalInvocationsByCountry);
                }
            }
            acknowledgment.acknowledge();

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
