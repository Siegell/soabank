package by.siegell.soabank.controller;

import by.siegell.soabank.domain.Transaction;
import by.siegell.soabank.domain.exception.BadTransactionException;
import by.siegell.soabank.domain.exception.EntityNotFoundException;
import by.siegell.soabank.service.TransactionService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/transaction")
@AllArgsConstructor
public class TransactionController {

    private TransactionService transactionService;

    @PostMapping("/")
    public Transaction createTransaction(@RequestBody UUID from, @RequestBody UUID to, @RequestBody Double value) {

        //TODO check jwt

        return transactionService.save(new Transaction(from, to, value));
    }

    @GetMapping("/")
    public List<Transaction> readAllTransactions() {

        //TODO check jwt

        return transactionService.getAll();
    }

    @GetMapping("/{id}")
    public Transaction readTransaction(@PathVariable UUID id) throws EntityNotFoundException {

        //TODO check jwt

        return transactionService.getById(id);
    }

    @GetMapping("/from/{id}")
    public List<Transaction> readAllTransactionsByFrom(@PathVariable UUID id) {

        //TODO check jwt

        return transactionService.getAllByFrom(id);
    }

    @GetMapping("/to/{id}")
    public List<Transaction> readAllTransactionsByTo(@PathVariable UUID id) {

        //TODO check jwt

        return transactionService.getAllByTo(id);
    }

    @GetMapping("/not_payed/from/{id}")
    public List<Transaction> readAllTransactionsNotPayedByFrom(@PathVariable UUID id) {

        //TODO check jwt

        return transactionService.getAllNotPayedByFrom(id);
    }

    @GetMapping("/not_payed/to/{id}")
    public List<Transaction> readAllTransactionsNotPayedByTo(@PathVariable UUID id) {

        //TODO check jwt

        return transactionService.getAllNotPayedByTo(id);
    }

    @PutMapping("/")
    public Transaction updateTransaction(Transaction transaction) {

        //TODO check jwt

        return transactionService.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable UUID id) {

        //TODO check jwt

        transactionService.deleteById(id);
    }

    @GetMapping("/pay/{id}")
    public Transaction pay(@PathVariable UUID id) throws BadTransactionException, EntityNotFoundException {

        //TODO check jwt

        return transactionService.pay(id);
    }
}
