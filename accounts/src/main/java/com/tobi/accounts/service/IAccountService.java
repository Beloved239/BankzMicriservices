package com.tobi.accounts.service;

import com.tobi.accounts.dto.CustomerDto;

public interface IAccountService {

    /**
     *
     * @param customerDto - CustomerDto Object
     */
    void createAccount(CustomerDto customerDto);

    /**
     *
     * @param email - Input Mobile Number
     * @return Accounts Details based on a given mobileNumber
     */
    CustomerDto fetchAccount(String email);

    /**
     *
     * @param customerDto - CustomerDto Object
     *  @return boolean indicating if the update of Account details is successful or not
     */
    boolean updateAccount(CustomerDto customerDto);

    /**
     *
     * @param email- Input email
     * @return boolean indicating if the delete of Account details is successful or not
     */
    boolean deleteAccount(String email);

}
