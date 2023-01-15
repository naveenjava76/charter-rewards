package com.charter.rewards.exceptions;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RewardsException  extends RuntimeException{
    Integer httpCode;
    String message;

    public RewardsException(Integer httpCode, String message) {
        super();
        this.httpCode = httpCode;
        this.message = message;
    }
}
