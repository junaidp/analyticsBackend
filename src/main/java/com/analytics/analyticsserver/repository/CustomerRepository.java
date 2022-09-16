package com.analytics.analyticsserver.repository;

import com.analytics.analyticsserver.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface CustomerRepository extends MongoRepository<Customer, String> {

    @Query("{ 'jcCode' : ?0'}")
    List<Customer> findByAmtIsNull(String jcCode);


}
