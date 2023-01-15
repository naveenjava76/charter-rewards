package com.charter.rewards.exceptions;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

import static com.charter.rewards.utils.RewardsConstants.CODE;
import static com.charter.rewards.utils.RewardsConstants.MESSAGE;

@ControllerAdvice
@Slf4j
public class RewardsControllerAdvice {

    @ExceptionHandler(RewardsException.class)
    public ResponseEntity handleRewardsExceptions(
            RewardsException e
    ) {
        Map<String, Object> data = new HashMap<>();

        data.put(
                CODE,
                e.getHttpCode()
        );
        data.put(
                MESSAGE,
                e.getMessage()
        );

        log.error("Exception response {}", data);
        return new ResponseEntity(data, HttpStatusCode.valueOf(e.httpCode));
    }

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public ResponseEntity handleExceptions() {
        Map<String, Object> data = new HashMap<>();

        data.put(
                CODE,
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        data.put(
                MESSAGE,
                HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()
        );

        log.error("Exception response {}", data);
        return new ResponseEntity(data, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
