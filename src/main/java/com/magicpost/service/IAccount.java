package com.magicpost.service;

import com.magicpost.model.Account;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IAccount extends UserDetailsService {
    Account create(Account account);
    List<Account> getAll();
    Account edit(Account account);
    void delete(Account account);
    void delete(long idAccount);
    Optional<Account> findById(long idAccount);
    Account findAccountById(long id);
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;
}
