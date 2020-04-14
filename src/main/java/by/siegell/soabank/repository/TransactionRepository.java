package by.siegell.soabank.repository;

import by.siegell.soabank.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, UUID> {
    List<Transaction> getAllByFromAccount(UUID fromAccount);

    List<Transaction> getAllByToAccount(UUID toAccount);

    List<Transaction> getAllByFromAccountAndTransactionDateIsNull(UUID fromAccount);

    List<Transaction> getAllByToAccountAndTransactionDateIsNull(UUID toAccount);
}
