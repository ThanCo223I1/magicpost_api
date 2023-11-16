package com.magicpost.service;

import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAccount extends UserDetailsService {
    List<Account> getAll();
    Account edit(Account account);
    void delete(Account account);
    void delete(long idAccount);
    Optional<Account> findById(long idAccount);
    Account findAccountById(long id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
    Account getAccountLogin(String username, String password);
    AccountDTO editStatus(long idAccount , long idStatus);

}
