package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.AccountTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for the account transfer extends JpaRepository.
 */
@Repository
public interface AccountTransferRepository extends JpaRepository<AccountTransfer,Long> {
    List<AccountTransfer> getAccountTransferByUserId(long userId);
}
