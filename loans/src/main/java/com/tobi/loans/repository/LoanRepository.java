package com.tobi.loans.repository;

import com.tobi.loans.entity.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LoanRepository extends JpaRepository<Loan, Long> {
    Optional<Loan> findByMobileNumber(String mobileNumber);
    Optional<Loan> findByLoanNumber(String loanNumber);
}
