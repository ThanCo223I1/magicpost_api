package com.magicpost.repo;

import com.magicpost.model.ConsolidationPoint;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConsolidationPointRepo extends JpaRepository<ConsolidationPoint , Long> {
    ConsolidationPoint findByLeader_Id(long id);
}
