package com.example.demo.repositories;

import com.example.demo.model.PostalCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<PostalCode, Long> {

}
