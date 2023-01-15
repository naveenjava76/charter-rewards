package com.charter.rewards.contollers;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.beans.Transaction;
import com.charter.rewards.facades.RewardsFacade;
import com.charter.rewards.utils.TransactionsValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.charter.rewards.utils.RewardsConstants.REWARDS_API_RESOURCE;

@RestController
@ComponentScan(basePackages = "com.charter.rewards.*")
@Slf4j
public class RewardsController {

    @Autowired
    RewardsFacade rewardsFacade;

    @PostMapping(REWARDS_API_RESOURCE)
    public List<CustomerReward> getPointsSummary(@RequestBody List<Transaction> transactions){
        final String METHOD_NAME = "getPointsSummary";

        log.debug("Entered method {}",METHOD_NAME);
        List<CustomerReward> rewards;

        log.info("Request of input transactions : {}",transactions.toString());
        TransactionsValidator.validateTransactions(transactions);

        rewards = rewardsFacade.aggregatePointsByCustomerAndMonth(transactions);
        log.info("Response : {}",rewards.toString());

        log.debug("Exit method {}",METHOD_NAME);
        return rewards;
    }
}
