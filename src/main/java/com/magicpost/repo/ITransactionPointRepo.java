package com.magicpost.repo;

import com.magicpost.model.TransactionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITransactionPointRepo extends JpaRepository<TransactionPoint , Long> {
}
