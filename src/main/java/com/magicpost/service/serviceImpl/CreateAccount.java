package com.magicpost.service.serviceImpl;

import com.magicpost.model.Account;
import com.magicpost.repo.IAccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class CreateAccount {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private IAccountRepo iAccountRepo;


    public Account create(Account account) {

        account.setPassword(passwordEncoder.encode(account.getPassword()));
        return iAccountRepo.save(account);
    }
}
