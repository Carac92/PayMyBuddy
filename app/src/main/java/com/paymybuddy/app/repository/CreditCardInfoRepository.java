package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.CreditCardInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardInfoRepository extends JpaRepository<CreditCardInfo, Long> {
    CreditCardInfo getCreditCardInfoByUserId(Long userID);
    Long deleteCreditCardInfoByUserId(Long userID);
}
