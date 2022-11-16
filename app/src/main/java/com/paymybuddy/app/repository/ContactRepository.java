package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.security.Principal;
import java.util.List;

@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {

    Contact findContactByUserIdAndContactId(@Param("UserId") long userId, @Param("ContactId") Long contactId);

    List<Contact> findContactsByUserId(@Param("UserId") long userId);

}
