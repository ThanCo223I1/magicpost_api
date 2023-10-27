package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.repo.IAccountRepo;
import com.magicpost.service.IAccount;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountImpl implements IAccount {
    IAccountRepo iAccountRepo;

    public AccountImpl(IAccountRepo iAccountRepo) {
        this.iAccountRepo=iAccountRepo;
    }

    @Override
    public Account create(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public List<Account> getAll() {
        return iAccountRepo.findAll();
    }

    @Override
    public Account edit(Account account) {
        return iAccountRepo.save(account);
    }

    @Override
    public void delete(Account account) {
         iAccountRepo.delete(account);
    }

    @Override
    public void delete(long idAccount) {
        iAccountRepo.deleteById(idAccount);
    }

    @Override
    public Optional<Account> findById(long idAccount) {
        return iAccountRepo.findById(idAccount);
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = iAccountRepo.getAccountByUsername(username);
        List<GrantedAuthority> roles = new ArrayList<>();
        roles.add((GrantedAuthority) account.getRole());
        return new User(account.getUsername(), account.getPassword(), roles);
    }
}
