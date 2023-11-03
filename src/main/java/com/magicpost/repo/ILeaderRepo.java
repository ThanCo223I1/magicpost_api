package com.magicpost.repo;

import com.magicpost.model.Leader;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ILeaderRepo extends JpaRepository<Leader , Long> {
    Leader findAllByAccount_Id(long id);
}
