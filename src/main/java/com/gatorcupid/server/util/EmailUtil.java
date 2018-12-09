package com.gatorcupid.server.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;


@Component
public class EmailUtil {

	@Autowired
	private Environment environment;

	public JsonNode sendEmail(String to, String from, String subject, String content) throws UnirestException {

		HttpResponse<JsonNode> request = Unirest.post("https://api.mailgun.net/v3/"+environment.getRequiredProperty("email.mailgun.domain")+"/messages")
				.basicAuth("api", environment.getRequiredProperty("email.mailgun.apikey"))
				.queryString("from", from)
				.queryString("to", to)
				.queryString("subject", subject)
				.queryString("text", content)
				.asJson();
		if(request.getStatus() == HttpStatus.ACCEPTED.ordinal()) {
			request.getBody();
		}

		return null;
	}
}