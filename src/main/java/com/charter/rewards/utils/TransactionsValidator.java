package com.charter.rewards.utils;

import com.charter.rewards.beans.Transaction;
import com.charter.rewards.exceptions.RewardsException;
import io.micrometer.common.util.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Slf4j
public class TransactionsValidator {

    public static void validateTransactions(List<Transaction> transactions){
        final String METHOD_NAME = "validateTransactions";
        log.debug("Entered method {}",METHOD_NAME);

        if(transactions == null){
            log.error("Input transactions are null");
            throw new RewardsException(BAD_REQUEST.value(),"Input transactions are null");
        }
        for (Transaction transaction: transactions) {
            if(StringUtils.isBlank(transaction.getCustomerId())){
                log.error("Input transactions has null or blank customer id");
                throw new RewardsException(BAD_REQUEST.value(),"Input transactions has null or blank customer id");
            } else if(transaction.getTransactionAmount() == null){
                log.error("Input transactions has null as transaction amount");
                throw new RewardsException(BAD_REQUEST.value(),"Input transactions has null as transaction amount");
            } else if(transaction.getTransactionDate() == null){
                log.error("Input transactions has null as transaction date");
                throw new RewardsException(BAD_REQUEST.value(),"Input transactions has null as transaction date");
            }
        }
    }
}
