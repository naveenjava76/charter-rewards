package com.charter.rewards;

import com.charter.rewards.beans.CustomerReward;
import com.charter.rewards.beans.Transaction;
import com.charter.rewards.contollers.RewardsController;
import com.charter.rewards.exceptions.RewardsException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = RewardsApplication.class)
class RewardsApplicationTests {

	@Autowired
	private RewardsController controller;

	@Autowired
	ResourceLoader resourceLoader;

	static ObjectMapper objectMapper;

	@BeforeAll
	static void initAll() {
		objectMapper = new ObjectMapper().setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);
		objectMapper.registerModule(new JavaTimeModule());
	}

	@Test
	public void testForValidRequest() throws IOException {

		Resource requestResource = resourceLoader.getResource("classpath:requests/ValidRequest.json");
		List<Transaction> transactions = Arrays.asList(objectMapper.readValue(requestResource.getFile(), Transaction[].class));

		Resource responseResource = resourceLoader.getResource("classpath:responses/ValidResponse.json");
		List<CustomerReward> expectedPointSummary = Arrays.asList(objectMapper.readValue(responseResource.getFile(), CustomerReward[].class));

		List<CustomerReward> pointsSummary = controller.getPointsSummary(transactions);
		Assertions.assertEquals(objectMapper.writeValueAsString(expectedPointSummary), objectMapper.writeValueAsString(pointsSummary));
	}

	@Test
	public void testForInvalidCustomerIdInRequest() throws IOException {
		Resource requestResource = resourceLoader.getResource("classpath:requests/InvalidRequest_customerId.json");
		List<Transaction> transactions = Arrays.asList(objectMapper.readValue(requestResource.getFile(), Transaction[].class));
		try{
			controller.getPointsSummary(transactions);
		}catch (RewardsException ex){
			Assertions.assertEquals(ex.getHttpCode(), 400);
			Assertions.assertEquals(ex.getMessage(), "Input transactions has null or blank customer id");
		}
	}

	@Test
	public void testForInvalidTransactionAmountInRequest() throws IOException {
		Resource requestResource = resourceLoader.getResource("classpath:requests/InvalidRequest_transactionAmount.json");
		List<Transaction> transactions = Arrays.asList(objectMapper.readValue(requestResource.getFile(), Transaction[].class));
		try{
			controller.getPointsSummary(transactions);
		}catch (RewardsException ex){
			Assertions.assertEquals(ex.getHttpCode(), 400);
			Assertions.assertEquals(ex.getMessage(), "Input transactions has null as transaction amount");
		}
	}

	@Test
	public void testForInvalidTransactionDateInRequest() throws IOException {
		Resource requestResource = resourceLoader.getResource("classpath:requests/InvalidRequest_transactionDate.json");
		List<Transaction> transactions = Arrays.asList(objectMapper.readValue(requestResource.getFile(), Transaction[].class));
		try{
			controller.getPointsSummary(transactions);
		}catch (RewardsException ex){
			Assertions.assertEquals(ex.getHttpCode(), 400);
			Assertions.assertEquals(ex.getMessage(), "Input transactions has null as transaction date");
		}
	}
}
