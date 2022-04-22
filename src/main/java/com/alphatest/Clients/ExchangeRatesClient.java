package com.alphatest.Clients;

import com.alphatest.Models.ExchangeRates;

import feign.Param;
import feign.RequestLine;

public interface ExchangeRatesClient {
	
    @RequestLine("GET /latest.json?app_id={appId}&base=USD")
	ExchangeRates getLatest(@Param("appId") String appId);
    
    @RequestLine("GET /historical/{date}.json?app_id={appId}&base=USD")
	ExchangeRates getHistorical(@Param("appId") String appId, @Param("date")  String date);
}
