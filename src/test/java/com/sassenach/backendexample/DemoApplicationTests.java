package com.sassenach.backendexample;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.assertj.core.api.Assertions.assertThat;
import com.sassenach.backendexample.controller.QueryHandler;
import com.sassenach.backendexample.entities.Reading;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private QueryHandler controller;

	@Test
	void contextLoads() throws Exception{
		assertThat(controller).isNotNull();
	}



}

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class HttpRequestTest {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;

	@Test
	void databasesLoaded() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sensors",
				String.class)).contains("Jeremiah","Sacred");
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/readings",
				String.class)).contains("200");
	}


	@Test
	void invalidSensorRequest() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/sensors/nonsense/latest",
				String.class)).contains("does not exist!");
	}

	@Test
	void rejectNonsense() throws Exception {
		assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/nonsense",
				String.class)).contains("404");
	}

	@Test
	void postNewReading() throws Exception {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		Reading testReading = new Reading(1700944131300L,12,5,11,"Jeremiah");
		HttpEntity<Reading> httpEntity = new HttpEntity<>(testReading,headers);
		assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/Readings",httpEntity,String.class)).contains("Readings");
	}
}
