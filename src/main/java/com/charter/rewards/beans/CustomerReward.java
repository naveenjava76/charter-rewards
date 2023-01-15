package com.charter.rewards.beans;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomerReward {

    private String customerId;
    private List<Reward> pointsByMonth;
    private Double totalPoints;
}
