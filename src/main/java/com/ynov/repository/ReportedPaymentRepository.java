package com.ynov.repository;

import com.ynov.model.ReportedPayment;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by camoroso on 09/03/2017.
 */
@Repository
public interface ReportedPaymentRepository extends MongoRepository<ReportedPayment, String> {

    List<ReportedPayment> findAllByTimestampLessThan(long timestamp);

}
