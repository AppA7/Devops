package com.ynov;

import com.ynov.model.Account;
import com.ynov.model.Payment;
import com.ynov.repository.AccountRepository;
import com.ynov.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.mockito.BDDMockito.given;

/**
 * Created by hdelon on 09/03/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceTests {

    @MockBean
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void testVerifAmount(){
        assertFalse(accountService.verifAmount(new Payment("", "", 0, -1)));
        assertFalse(accountService.verifAmount(new Payment("", "", 0, 0)));
        assertTrue(accountService.verifAmount(new Payment("", "", 0, 1)));
    }

    @Test
    public void testVerifCcv(){
        assertFalse(accountService.verifCvv(new Payment("", "6666", 0, 0)));
        assertFalse(accountService.verifCvv(new Payment("", "66", 0, 0)));
        assertFalse(accountService.verifCvv(new Payment("", "AAA", 0, 0)));

        String cardNumber = "123456789012";
        given(accountRepository.findOne(cardNumber)).willReturn(new Account(cardNumber, "666", 1L));
        assertTrue(accountService.verifCvv(new Payment(cardNumber, "666", 0, 0)));
    }

    @Test
    public void testVerifCardNumber(){
        assertFalse(accountService.verifCardNumber(new Payment("3456", "", 0, 0)));
        assertFalse(accountService.verifCardNumber(new Payment("AAAAZZZZEEEE", "", 0, 0)));

        String cardNumber = "123456789012";
        given(accountRepository.exists(cardNumber)).willReturn(true);
        assertFalse(accountService.verifCardNumber(new Payment("345612341234", "", 0, 0)));
        assertTrue(accountService.verifCardNumber(new Payment(cardNumber, "", 0, 0)));
    }

    @Test
    public void testVerifDateValidity(){
        String cardNumber = "123456789012";
        long date = System.currentTimeMillis() + 100;
        given(accountRepository.findOne(cardNumber)).willReturn(new Account(cardNumber, "666", date));
        assertFalse(accountService.verifDateValidity(new Payment(cardNumber, "", 1, 0)));
        //assertTrue(accountService.verifDateValidity(new Payment(cardNumber, "", date, 0)));
    }

}
