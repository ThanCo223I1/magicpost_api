package com.magicpost.service;

import com.magicpost.model.Account;
import com.magicpost.model.Leader;

import java.util.List;

public interface ILeader {
    Leader create (Account account , Leader leader);
}
