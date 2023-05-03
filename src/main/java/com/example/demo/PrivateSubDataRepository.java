package com.example.demo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PrivateSubDataRepository extends JpaRepository<PrivateSubData, UUID> {

    @Modifying
    @Query("DELETE FROM PrivateSubData psd where psd.data.person.id = :personId")
    void deleteByPerson(@Param("personId") UUID personId);


    @Modifying
    @Query("UPDATE PrivateSubData psd set psd.dataName = '...' where psd.data.person.id = :personId")
    void anonymize(@Param("personId") UUID personId);

}
