package com.tobi.cards.service;

import com.tobi.cards.dto.CardDto;

public interface ICardService {
    /**
     *
     * @param phoneNumber - Mobile Number of the Customer
     */
    void createCard(String phoneNumber);

    /**
     *
     * @param phoneNumber - Input mobile Number
     *  @return Card Details based on a given phoneNumber
     */
    CardDto fetchCard(String phoneNumber);

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    boolean updateCard(CardDto cardsDto);

    /**
     *
     * @param phoneNumber - Input Mobile Number
     * @return boolean indicating if the delete of card details is successful or not
     */
    boolean deleteCard(String phoneNumber);
}
