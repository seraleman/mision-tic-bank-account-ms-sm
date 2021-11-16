package com.seraleman.account_ms.repositories;

import com.seraleman.account_ms.models.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

}
