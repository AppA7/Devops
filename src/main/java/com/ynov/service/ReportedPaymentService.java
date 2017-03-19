package com.ynov.service;

import com.ynov.model.ReportedPayment;
import com.ynov.repository.ReportedPaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by camoroso on 09/03/2017.
 */
@Service
public class ReportedPaymentService {

    @Autowired
    private ReportedPaymentRepository reportedPaymentRepository;

    @Autowired
    private AccountService accountService;

    /**
     * Executed each hour.
     */
    @Scheduled(cron = "0 0 * * * *")
    public void processReportedPayments(){
        for(ReportedPayment reportedPayment: getPaymentsOneWeekBefore()){
            accountService.forcePayment(reportedPayment.getPayment());
            reportedPaymentRepository.delete(reportedPayment);
        }
    }

    private List<ReportedPayment> getPaymentsOneWeekBefore(){
        long current = System.currentTimeMillis();
        long oneWeek = 1000 * 60 * 60 * 24 * 7;

        return reportedPaymentRepository.findAllByTimestampLessThan(current - oneWeek);
    }
}
