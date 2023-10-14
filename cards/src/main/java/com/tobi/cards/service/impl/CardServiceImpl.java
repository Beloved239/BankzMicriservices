package com.tobi.cards.service.impl;

import com.tobi.cards.constant.CardConstants;
import com.tobi.cards.dto.CardDto;
import com.tobi.cards.entity.Cards;
import com.tobi.cards.exception.CardAlreadyExistException;
import com.tobi.cards.exception.ResourceNotFoundException;
import com.tobi.cards.mapper.CardMapper;
import com.tobi.cards.repository.CardRepository;
import com.tobi.cards.service.ICardService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;
@Service
@AllArgsConstructor
public class CardServiceImpl implements ICardService {
    private CardRepository cardsRepository;

    /**
     * @param phoneNumber - Mobile Number of the Customer
     */
    @Override
    public void createCard(String phoneNumber) {
        Optional<Cards> optionalCards= cardsRepository.findByPhoneNumber(phoneNumber);
        if(optionalCards.isPresent()){
            throw new CardAlreadyExistException("Card already registered with given phoneNumber "+phoneNumber);
        }
        cardsRepository.save(createNewCard(phoneNumber));
    }

    /**
     * @param phoneNumber - Mobile Number of the Customer
     * @return the new card details
     */
    private Cards createNewCard(String phoneNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 1000000000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setPhoneNumber(phoneNumber);
        newCard.setCardType(CardConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardConstants.NEW_CARD_LIMIT);
        return newCard;
    }

    /**
     *
     * @param mobileNumber - Input mobile Number
     * @return Card Details based on a given mobileNumber
     */
    @Override
    public CardDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByPhoneNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        return CardMapper.mapToCardsDto(cards, new CardDto());
    }

    /**
     *
     * @param cardsDto - CardsDto Object
     * @return boolean indicating if the update of card details is successful or not
     */
    @Override
    public boolean updateCard(CardDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(
                () -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return  true;
    }

    /**
     * @param mobileNumber - Input MobileNumber
     * @return boolean indicating if the delete of card details is successful or not
     */
    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByPhoneNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber)
        );
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }

}
