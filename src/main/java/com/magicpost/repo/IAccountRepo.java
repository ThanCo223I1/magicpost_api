package com.magicpost.repo;

import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IAccountRepo extends JpaRepository<Account , Long> {
    Account getAccountByUsername(String username);
}
