package com.alphatest.Controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alphatest.Facades.ExchangeRatesFacade;
import com.alphatest.Facades.GifFacade;

@Controller
public class GifController {
	
	@Value("${openexchangerates.url}")
	private String openexchangerates_url;
	
	@Value("${openexchangerates.app_id}")
	private String openexchangerates_app_id;
	
	@Value("${openexchangerates.rate.currency}")
	private String openexchangerates_rate_currency;
	
	@Value("${openexchangerates.rate.base}")
	private String openexchangerates_rate_base;

	@Value("${giphy.url}")
	private String giphy_url;

	@Value("${giphy.api.key}")
	private String giphy_api_key;

	@Value("${giphy.tag.rich}")
	private String giphy_tag_rich;

	@Value("${giphy.tag.broke}")
	private String giphy_tag_broke;
	
	
	@GetMapping("/gif/get")
	//@RequestMapping(value = "/gif/get?code={code}", method = RequestMethod.GET)
	public String get(@RequestParam(name="code", required=false, defaultValue="RUB") String code, Model model) {
		
		GifFacade gifFacade = new GifFacade(
			this.giphy_url,
			this.giphy_api_key,
			this.giphy_tag_rich,
			this.giphy_tag_broke
		);
		
		ExchangeRatesFacade exchangeRatesFacade = new ExchangeRatesFacade(
			this.openexchangerates_url,
			this.openexchangerates_app_id,
			code,
			this.openexchangerates_rate_base
		);
		
		String gifEmberUrl = exchangeRatesFacade.getRichOrBrokeGifEmberUrl(gifFacade);
		
		model.addAttribute("gifEmberUrl", gifEmberUrl);
		
		return "gif";
	}
}
