package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for the bill extends JpaRepository.
 */
@Repository
public interface BillRepository extends JpaRepository<Bill,Long> {
    List<Bill> getBillsByUserId(Long UserID);

}
