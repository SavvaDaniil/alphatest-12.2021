package com.alphatest.Clients;

import com.alphatest.Models.GifRandomResponse;

import feign.Param;
import feign.RequestLine;

public interface GifClient {
	
    @RequestLine("GET /random?api_key={api_key}&tag={tag}")
    GifRandomResponse getRandom(@Param("api_key") String api_key, @Param("tag") String tag);
    
}
