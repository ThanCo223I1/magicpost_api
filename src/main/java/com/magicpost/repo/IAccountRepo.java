package com.magicpost.repo;

import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAccountRepo extends JpaRepository<Account , Long> {
    Account getAccountByUsername(String username);
    Account getAccountByUsernameAndPassword(String username , String password);

}
