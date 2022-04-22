package com.alphatest.ClientTest;


import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alphatest.Clients.ExchangeRatesClient;
import com.alphatest.Models.ExchangeRates;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ExchangeRatesClientTest {

	private String openexchangerates_rate_base = "USD";
	
	@MockBean
	private ExchangeRatesClient exchangeRatesClient;

    private ExchangeRates currentRates;
    
	@Before
	public void init() {
        this.currentRates = new ExchangeRates();
        this.currentRates.setTimestamp(1609459199);
        this.currentRates.setBase("T_BASE");
        Map<String, Double> currentRatesMap = new HashMap<>();
        currentRatesMap.put("RE", 0.1);
        currentRatesMap.put(this.openexchangerates_rate_base, 1.0);
        currentRatesMap.put("RE2", 1.0);
        this.currentRates.setRates(currentRatesMap);
	}
	
	@Test
	public void isCurrentRatesWorking() {
        Mockito.when(exchangeRatesClient.getLatest(Mockito.anyString())).thenReturn(this.currentRates);
	}
}
