package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {


    Contact findContactByUserUserIDAndContactID(Long userId, Long contactId);

    List<Contact> findAllByUserUserID(Long userId);

}
