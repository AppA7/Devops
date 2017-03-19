package com.ynov.service;

import com.ynov.model.Account;
import com.ynov.model.Error;
import com.ynov.model.Payment;
import com.ynov.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by camoroso on 09/03/2017.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Error verifPayment(Payment payment) {
        if(!verifCardNumber(payment)){
            return new Error(1, "card number");
        }

        if(!verifCvv(payment)){
            return new Error(2, "cvv");
        }

        if(!verifDateValidity(payment)){
            return new Error(3, "validity date");
        }

        if(!verifAmount(payment)){
            return new Error(4, "amount");
        }

        return null;
    }

    public boolean verifAmount(Payment payment) {
        return payment.getAmount() > 0;
    }

    public boolean verifCvv(Payment payment){
        if(payment.getCvv().matches("^([0-9]{3})$")){
            if(accountRepository.findOne(payment.getCardNumber()).getCvv().equals(payment.getCvv())){
                return true;
            }
        }
        return false;
    }

    public boolean verifCardNumber(Payment payment) {
        if(payment.getCardNumber().matches("^([0-9]{12})$")){
            if(accountRepository.exists(payment.getCardNumber())){
                return true;
            }
        }
        return false;
    }

    public boolean verifDateValidity(Payment payment){
        return accountRepository.findOne(payment.getCardNumber()).getValidityDate() == payment.getValidityDate()
                && System.currentTimeMillis() < payment.getValidityDate();
    }

    public boolean payment(Payment payment){
        Account account = accountRepository.findOne(payment.getCardNumber());

        if(account.getSum() >= payment.getAmount() * 5)  {
            account.setSum(account.getSum() - payment.getAmount());
            accountRepository.save(account);
            return true;
        }

        return false;
    }

    public void forcePayment(Payment payment){
        Account account = accountRepository.findOne(payment.getCardNumber());
        account.setSum(account.getSum() - payment.getAmount());
        accountRepository.save(account);
    }

}
