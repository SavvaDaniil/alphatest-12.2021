package com.alphatest.Facades;

import com.alphatest.Clients.GifClient;
import com.alphatest.Models.GifRandomResponse;

import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;

public class GifFacade {

	private String giphy_url;
	private String giphy_api_key;
	private String giphy_tag_rich;
	private String giphy_tag_broke;
	private GifClient gifClient;
	
	public GifFacade(String giphy_url, String giphy_api_key, String giphy_tag_rich, String giphy_tag_broke) {
		super();
		this.giphy_url = giphy_url;
		this.giphy_api_key = giphy_api_key;
		this.giphy_tag_rich = giphy_tag_rich;
		this.giphy_tag_broke = giphy_tag_broke;
		

		this.gifClient = (GifClient) Feign.builder()
				  .encoder(new GsonEncoder())
				  .decoder(new GsonDecoder())
				  .target(GifClient.class, this.giphy_url);
	}

	public String getGifRich() {
		GifRandomResponse gifRandomResponseRich = gifClient.getRandom(this.giphy_api_key, this.giphy_tag_rich);
		System.out.println("gifRandomResponseRich : " + gifRandomResponseRich.getData().getEmbed_url());
		return gifRandomResponseRich.getData().getEmbed_url();
	}

	public String getGifBroke() {
		GifRandomResponse gifRandomResponseRich = gifClient.getRandom(this.giphy_api_key, this.giphy_tag_broke);
		return gifRandomResponseRich.getData().getEmbed_url();
	}
	
}
