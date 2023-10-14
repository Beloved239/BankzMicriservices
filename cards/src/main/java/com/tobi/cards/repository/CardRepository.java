package com.tobi.cards.repository;

import com.tobi.cards.entity.Cards;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CardRepository extends JpaRepository<Cards, Long> {

    Optional<Cards> findByPhoneNumber(String mobileNumber);

    Optional<Cards> findByCardNumber(String cardNumber);
}
