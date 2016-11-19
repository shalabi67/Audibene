package com.shalabi.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

@Repository
public interface ICustomerRepository extends CrudRepository<Customer, Long> {
}
