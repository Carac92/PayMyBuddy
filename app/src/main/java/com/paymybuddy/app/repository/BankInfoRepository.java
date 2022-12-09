package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo,Long> {
    BankInfo getBankInfoByUserId(Long userID);
    Long deleteBankInfoByUserId(Long userID);

}
