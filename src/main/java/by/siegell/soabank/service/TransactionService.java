package by.siegell.soabank.service;

import by.siegell.soabank.domain.Transaction;
import by.siegell.soabank.domain.exception.BadTransactionException;
import by.siegell.soabank.domain.exception.EntityNotFoundException;
import by.siegell.soabank.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TransactionService extends EntityCRUDService<Transaction, UUID, TransactionRepository> {

    private TransactionRepository repository;
    private AccountService accountService;

    @Override
    TransactionRepository getRepository() {
        return repository;
    }

    public List<Transaction> getAllByFrom(UUID from) {
        return repository.getAllByFromAccount(from);
    }

    public List<Transaction> getAllByTo(UUID to) {
        return repository.getAllByFromAccount(to);
    }

    public List<Transaction> getAllNotPayedByFrom(UUID from) {
        return repository.getAllByFromAccountAndTransactionDateIsNull(from);
    }

    public List<Transaction> getAllNotPayedByTo(UUID to) {
        return repository.getAllByFromAccountAndTransactionDateIsNull(to);
    }


    public Transaction pay(UUID id) throws EntityNotFoundException, BadTransactionException {
        Transaction transaction = getById(id);
        UUID from = transaction.getFromAccount();
        UUID to = transaction.getToAccount();
        Double value = transaction.getValue();
        if (transaction.getTransactionDate() == null
                && accountService.isExists(from)
                && accountService.isExists(to)
                && accountService.haveMoney(from, value)) {
            accountService.minusMoney(from, value);
            accountService.plusMoney(to, value);
            transaction.setTransactionDate(LocalDateTime.now());
            return save(transaction);
        } else {
            throw new BadTransactionException();
        }
    }
}
