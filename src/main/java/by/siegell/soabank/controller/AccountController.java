package by.siegell.soabank.controller;

import by.siegell.soabank.domain.Account;
import by.siegell.soabank.domain.exception.EntityNotFoundException;
import by.siegell.soabank.service.AccountService;
import lombok.AllArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/bank_account")
@AllArgsConstructor
public class AccountController {

    private AccountService accountService;

    @PostMapping("/")
    public Account createAccount(@RequestBody(required = false) String detail, @RequestBody(required = false) Double value) {

        //TODO check jwt

        return accountService.save(Account.builder()
                .id(null)
                .detail(StringUtils.isEmpty(detail) ? "" : detail)
                .value(value == null ? .0 : value)
                .build());
    }

    @GetMapping("/{id}")
    public Account readAccount(@PathVariable UUID id) throws EntityNotFoundException {

        //TODO check jwt

        return accountService.getById(id);
    }

    @GetMapping("/")
    public List<Account> readAllAccounts() {

        //TODO check jwt

        return accountService.getAll();
    }

    @PutMapping("/")
    public Account updateAccount(Account account) {

        //TODO check jwt

        return accountService.save(account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        accountService.deleteById(id);
    }

    @GetMapping("/exist/{id}")
    public boolean existAccount(@PathVariable UUID id) {
        return accountService.isExists(id);
    }
}
