package com.example.invocationscountry.repository;

import com.example.invocationscountry.model.Invocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvocationsRepository extends JpaRepository<Invocations, Integer> {

    @Query("SELECT s.invocations FROM Invocations s WHERE s.country = :country")
    Integer getInvocationsByCountry(String country);

    @Query("SELECT s.distance FROM Invocations s WHERE s.country = :country")
    String getDistanceByCountry(String country);

    Invocations findInvocationsByCountry(String countryName);
    @Query(value = "SELECT MIN(CONVERT(SUBSTRING_INDEX(distance, ' ', 1), DECIMAL(10,2))) FROM invocations", nativeQuery = true)
    public String min();
    @Query(value = "SELECT MAX(CONVERT(SUBSTRING_INDEX(distance, ' ', 1), DECIMAL(10,2))) FROM invocations", nativeQuery = true)
    public String max();

    @Query(value = "SELECT SUM(CONVERT(SUBSTRING_INDEX(invocations, ' ', 1), DECIMAL(10,2))) FROM invocations", nativeQuery = true)
    public String getTotalSum();
    Invocations findByDistance(String distance);
}
