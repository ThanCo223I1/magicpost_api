package com.magicpost.repo;

import com.magicpost.model.TransactionPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ITransactionPointRepo extends JpaRepository<TransactionPoint , Long> {
    TransactionPoint findByLeader_Id(long id);
    List<TransactionPoint> findAllByStatus(int status);
}
