package com.ynov.rest;

import com.ynov.model.Error;
import com.ynov.model.Payment;
import com.ynov.model.ReportedPayment;
import com.ynov.repository.ReportedPaymentRepository;
import com.ynov.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * Created by camoroso on 09/03/2017.
 */
@RestController
@RequestMapping("/payment")
public class AccountEndpoint {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ReportedPaymentRepository reportedPaymentRepository;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity postPayment(@RequestBody Payment payment) {
        Error error = accountService.verifPayment(payment);

        if(error != null){
            return ResponseEntity.badRequest().body(error);
        }

        if(!accountService.payment(payment)){
            reportedPaymentRepository.save(new ReportedPayment(System.currentTimeMillis(), payment));
        }

        return ResponseEntity.ok().build();
    }

}
