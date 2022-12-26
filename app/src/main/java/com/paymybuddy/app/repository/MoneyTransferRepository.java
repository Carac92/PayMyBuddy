package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.MoneyTransfer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for Money transfer extends JpaRepository
 */
@Repository
public interface MoneyTransferRepository extends JpaRepository <MoneyTransfer,Long> {
    List<MoneyTransfer> getMoneyTransferByUserId(Long userID);
}
