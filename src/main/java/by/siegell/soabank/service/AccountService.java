package by.siegell.soabank.service;

import by.siegell.soabank.domain.Account;
import by.siegell.soabank.domain.exception.EntityNotFoundException;
import by.siegell.soabank.repository.AccountRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AccountService extends EntityCRUDService<Account, UUID, AccountRepository> {

    private AccountRepository repository;

    @Override
    AccountRepository getRepository() {
        return repository;
    }

    public boolean haveMoney(UUID id, Double value) throws EntityNotFoundException {
        return getById(id).getValue() >= value;
    }

    public void plusMoney(UUID id, Double value) throws EntityNotFoundException {
        Account account = getById(id);
        account.setValue(account.getValue() + value);
        save(account);
    }

    public void minusMoney(UUID id, Double value) throws EntityNotFoundException {
        Account account = getById(id);
        account.setValue(account.getValue() - value);
        save(account);
    }

}
