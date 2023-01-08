package com.paymybuddy.app.repository;

import com.paymybuddy.app.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.plaf.synth.ColorType;
import java.util.List;

/**
 * Repository interface for the contact extends JpaRepository
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact,Long> {


    Contact getContactById(Long id);
    List<Contact> findAllByUserId(Long userId);
    void deleteById(Long id);

}
