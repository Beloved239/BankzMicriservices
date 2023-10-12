package com.tobi.accounts.service.impl;

import com.tobi.accounts.constant.AccountConstants;
import com.tobi.accounts.dto.AccountsDto;
import com.tobi.accounts.dto.CustomerDto;
import com.tobi.accounts.entity.Accounts;
import com.tobi.accounts.entity.Customer;
import com.tobi.accounts.exception.CustomerAlreadyExistException;
import com.tobi.accounts.exception.ResourceNotFoundException;
import com.tobi.accounts.mapper.AccountMapper;
import com.tobi.accounts.mapper.CustomerMapper;
import com.tobi.accounts.repository.AccountRepository;
import com.tobi.accounts.repository.CustomerRepository;
import com.tobi.accounts.service.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private AccountRepository accountRepository;
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerDto-CustomerDto Object
     */

    @Override
    public void createAccount(CustomerDto customerDto) {
        Customer customer = CustomerMapper.mapToCustomer(customerDto, new Customer());
        Optional<Customer> customer1 = customerRepository.findByEmail(customer.getEmail());
        if (customer1.isPresent()){
            throw new CustomerAlreadyExistException("Customer already registered with the given email: " +
                    " "+customerDto.getEmail());
        }
        Customer savedCustomer = customerRepository.save(customer);
        accountRepository.save(createNewAccount(savedCustomer));

    }

    @Override
    public CustomerDto fetchAccount(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "email", email)
        );
        Accounts accounts = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                ()-> new ResourceNotFoundException("Customer", "email", email)
        );

        CustomerDto customerDto = CustomerMapper.mapToCustomerDto(customer, new CustomerDto());
        customerDto.setAccountsDto(AccountMapper.mapToAccountsDto(accounts, new AccountsDto()));

        return customerDto;
    }

    /**
     *
     * @param customerDto - CustomerDto Object
     * @return boolean indicating if the update of Account details is successful or not
     */
    @Override
    public boolean updateAccount(CustomerDto customerDto) {
        boolean isUpdated = false;
        AccountsDto accountsDto = customerDto.getAccountsDto();
        if(accountsDto !=null ){
            Accounts accounts = accountRepository.findById(accountsDto.getAccountNumber()).orElseThrow(
                    () -> new ResourceNotFoundException("Account", "AccountNumber", accountsDto.getAccountNumber().toString())
            );
            AccountMapper.mapToAccounts(accountsDto, accounts);
            accounts = accountRepository.save(accounts);

            Long customerId = accounts.getCustomerId();
            Customer customer = customerRepository.findById(customerId).orElseThrow(
                    () -> new ResourceNotFoundException("Customer", "CustomerID", customerId.toString())
            );
            CustomerMapper.mapToCustomer(customerDto,customer);
            customerRepository.save(customer);
            isUpdated = true;
        }
        return  isUpdated;
    }

    /**
     *
     * @param email- Input email
     * @return boolean indicating if the delete of Account details is successful or not
     */
    @Override
    public boolean deleteAccount(String email) {
        Customer customer = customerRepository.findByEmail(email).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "email", email)
        );
        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
        return true;

    }


    /**
     *
     * @param customer - Customer Object
     * @return new account details
     */
    private Accounts createNewAccount(Customer customer){
        Accounts newAccount = new Accounts();
        newAccount.setCustomerId(customer.getCustomerId());
        long accountNumber = 1000000000L + new Random().nextInt(900000000);

        newAccount.setAccountNumber(accountNumber);
        newAccount.setAccountType(AccountConstants.SAVINGS);
        newAccount.setBranchAddress(AccountConstants.ADDRESS);

        return newAccount;
    }


}
