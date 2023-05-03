package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivateDataRepository extends JpaRepository<PrivateData, UUID> {

    @Modifying
    @Query("UPDATE PrivateData pd set pd.dataName = '...' where pd.person.id = :personId")
    void anonymize(@Param("personId") UUID personId);

}
