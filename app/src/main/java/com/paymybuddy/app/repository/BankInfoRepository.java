package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.BankInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BankInfoRepository extends JpaRepository<BankInfo,Long> {
    List<BankInfo> getBankInfoByUserId(Long userID);
    BankInfo getBankInfoById(long id);
    void deleteById(Long id);

}
