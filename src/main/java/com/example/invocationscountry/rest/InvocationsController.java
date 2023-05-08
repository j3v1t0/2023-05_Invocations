package com.example.invocationscountry.rest;

import com.example.invocationscountry.dto.AverageDistance;
import com.example.invocationscountry.dto.InvocationDistance;
import com.example.invocationscountry.service.InvocationsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("invocations/")
public class InvocationsController {

    @Autowired
    private InvocationsService invocationsService;
    @GetMapping("/close-distance")
    public ResponseEntity<?> getMinDistanceInvocation() {
        try{
            InvocationDistance closest = invocationsService.getCloseDistance();
            if(closest.getDistance() == null) {
                throw new NullPointerException("Distance is null");
            } else {
                return new ResponseEntity<>(closest, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/far-distance")
    public ResponseEntity<?> getMaxDistanceInvocation() {
        try{
            InvocationDistance far = invocationsService.getFarDistance();
            if(far.getDistance() == null) {
                throw new NullPointerException("Distance is null");
            } else {
                return new ResponseEntity<>(far, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("average-distance")
    public ResponseEntity<?> getAverageDistance() throws JsonProcessingException {
        try{
            AverageDistance avg = invocationsService.getAverageDistance();

            if(avg.getAverageDistance() == null) {
                throw new NullPointerException("Distance is null");
            } else {
                return new ResponseEntity<>(avg, HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }
}
