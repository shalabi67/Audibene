package com.shalabi.data;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
