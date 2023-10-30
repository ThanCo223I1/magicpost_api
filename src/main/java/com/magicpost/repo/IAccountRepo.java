package com.magicpost.repo;

import com.magicpost.model.Account;
import com.magicpost.model.dto.AccountDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IAccountRepo extends JpaRepository<Account , Long> {
    Account getAccountByUsername(String username);
//    @Query("SELECT AccountDTO(a.id, a.username, a.role) FROM Account a")
//    List<AccountDTO> findAllAccountDTOs();
}
