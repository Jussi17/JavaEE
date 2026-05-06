package org.o7planning.springbootjpa.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import org.o7planning.springbootjpa.entity.BankAccount;
import org.o7planning.springbootjpa.exception.BankTransactionException;
import org.o7planning.springbootjpa.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class BankAccountDAO {

    @Autowired
    private EntityManager entityManager;

    public BankAccount findById(Long id) {
        return entityManager.find(BankAccount.class, id);
    }

    @Transactional(propagation = Propagation.MANDATORY)
    public void addAmount(Long id, double amount) throws BankTransactionException {
        BankAccount account = findById(id);
        if (account == null) {
            throw new BankTransactionException("Account not found: " + id);
        }
        double newBalance = account.getBalance() + amount;
        if (newBalance < 0) {
            throw new BankTransactionException("Insufficient funds for account: " + id);
        }
        account.setBalance(newBalance);
    }

    public List<BankAccountInfo> listBankAccountInfo() {
        String sql = "SELECT new org.o7planning.springbootjpa.model.BankAccountInfo"
                + "(e.id, e.fullName, e.balance) FROM BankAccount e ORDER BY e.id";
        Query query = entityManager.createQuery(sql);
        return query.getResultList();
    }
}