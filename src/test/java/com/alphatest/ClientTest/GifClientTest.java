package com.alphatest.ClientTest;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alphatest.Clients.GifClient;
import com.alphatest.Models.GifRandomData;
import com.alphatest.Models.GifRandomResponse;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class GifClientTest {


	@MockBean
	private GifClient gifClient;
	
	private GifRandomResponse gifRandomResponse;

	@Before
	public void init() {
		gifRandomResponse = new GifRandomResponse();
		GifRandomData gifRandomData = new GifRandomData();
		gifRandomData.setId("1");
		gifRandomData.setUrl("https");
		gifRandomData.setEmbed_url("https://embed");
		gifRandomResponse.setData(gifRandomData);
	}
	
    @Test
    public void whenPositiveChanges() {
        Mockito.when(gifClient.getRandom(Mockito.anyString(), Mockito.anyString())).thenReturn(this.gifRandomResponse);
    }
}
