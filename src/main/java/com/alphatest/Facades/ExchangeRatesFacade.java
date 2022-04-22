package com.alphatest.Facades;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.alphatest.Clients.ExchangeRatesClient;
import com.alphatest.Models.ExchangeRates;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class ExchangeRatesFacade {

	private String openexchangerates_url;
	private String openexchangerates_app_id;
	private String openexchangerates_rate_currency;
	private String openexchangerates_rate_base;
	
	public ExchangeRatesFacade() {}
	
	public ExchangeRatesFacade(String openexchangerates_url, String openexchangerates_app_id,
			String openexchangerates_rate_currency, String openexchangerates_rate_base) {
		super();
		this.openexchangerates_url = openexchangerates_url;
		this.openexchangerates_app_id = openexchangerates_app_id;
		this.openexchangerates_rate_currency = openexchangerates_rate_currency;
		this.openexchangerates_rate_base = openexchangerates_rate_base;
	}


	public String getRichOrBrokeGifEmberUrl(GifFacade gifFacade) {

		ExchangeRatesClient exchangeRatesClient = (ExchangeRatesClient) Feign.builder()
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .target(ExchangeRatesClient.class, this.openexchangerates_url);

		ExchangeRates exchangeRatesCurrent = exchangeRatesClient.getLatest(this.openexchangerates_app_id);
		//System.out.println("exchangeRates : " + exchangeRatesCurrent.getDisclaimer());
		
		Calendar cal = Calendar.getInstance();
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		cal.add(Calendar.DATE, -1);
		//System.out.println("Yesterday's date was "+dateFormat.format(cal.getTime()));  

		ExchangeRates exchangeRatesYesterday = exchangeRatesClient.getHistorical(
				this.openexchangerates_app_id,
				dateFormat.format(cal.getTime())
		);
		
		//System.out.println("getRateValue(exchangeRatesCurrent) : " + getRateValue(exchangeRatesCurrent));
		//System.out.println("getRateValue(exchangeRatesYesterday) : " + getRateValue(exchangeRatesYesterday));
		
		if(getRateValue(exchangeRatesCurrent) > getRateValue(exchangeRatesYesterday)) {
			return gifFacade.getGifRich();
		} else {
			return gifFacade.getGifBroke();
		}
		
		
		//System.out.println("exchangeRatesCurrent.getRates().get(this.openexchangerates_currency_rate): " 
		//+ exchangeRatesCurrent.getRates().get(this.openexchangerates_currency_rate));
		
		//System.out.println("exchangeRatesYesterday.getRates().get(this.openexchangerates_currency_rate): " 
		//+ exchangeRatesYesterday.getRates().get(this.openexchangerates_currency_rate));
	}
	
	private Double getRateValue(ExchangeRates exchangeRates) {
		return exchangeRates.getRates().getOrDefault(this.openexchangerates_rate_currency, 0.0d);
	}
	
	
	public String testFunc() {
		return "text";
	}
}
