package com.charter.rewards.facades;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.beans.Transaction;

import java.util.List;

public interface RewardsFacade {
    List<CustomerReward> aggregatePointsByCustomerAndMonth(List<Transaction> transactions);
}
