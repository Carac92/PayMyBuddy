package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {


    Contact findContactByUserIdAndContactUserId(Long userId, Long contactUserId);

    List<Contact> findAllByUserId(Long userId);
    Long deleteContactByUserIdAndContactUserId(Long userId, Long contactUserId);

}
