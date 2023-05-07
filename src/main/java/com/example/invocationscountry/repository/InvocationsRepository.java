package com.example.invocationscountry.repository;

import com.example.invocationscountry.model.Invocations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvocationsRepository extends JpaRepository<Invocations, Integer> {

    @Query("SELECT s.invocations FROM Invocations s WHERE s.country = :country")
    Integer getCountInvocationsByCountry(String country);
}
