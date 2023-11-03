package com.magicpost.repo;

import com.magicpost.model.ConsolidationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IConsolidationPointRepo extends JpaRepository<ConsolidationPoint , Long> {
    ConsolidationPoint findByLeader_Id(long id);
    List<ConsolidationPoint> findAllByStatus(int status);

}
