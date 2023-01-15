package com.charter.rewards.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Transaction {

    private String customerId;
    private BigDecimal transactionAmount;
    private LocalDate transactionDate;
}
