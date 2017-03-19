package com.ynov.repository;

import com.ynov.model.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by camoroso on 09/03/2017.
 */
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
}
