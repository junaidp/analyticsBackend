package com.analytics.analyticsserver.paginationrepository;

import com.analytics.analyticsserver.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerPaginationRepository extends MongoRepository<Customer, String> {
//    Page<Customer> findByPublished(boolean published, Pageable pageable);
    Page<Customer> findByCustDescriptionContainingIgnoreCase(String custDescription, Pageable pageable);
}
