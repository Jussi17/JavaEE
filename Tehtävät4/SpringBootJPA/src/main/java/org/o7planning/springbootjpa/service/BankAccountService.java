package org.o7planning.springbootjpa.service;

import java.util.List;
import org.o7planning.springbootjpa.dao.BankAccountDAO;
import org.o7planning.springbootjpa.exception.BankTransactionException;
import org.o7planning.springbootjpa.model.BankAccountInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BankAccountService {

    @Autowired
    private BankAccountDAO bankAccountDAO;

    public List<BankAccountInfo> listBankAccountInfo() {
        return bankAccountDAO.listBankAccountInfo();
    }

    @Transactional(rollbackFor = BankTransactionException.class)
    public void sendMoney(Long fromAccountId, Long toAccountId, double amount)
            throws BankTransactionException {
        bankAccountDAO.addAmount(fromAccountId, -amount);
        bankAccountDAO.addAmount(toAccountId, amount);
    }
}