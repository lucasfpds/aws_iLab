package br.com.sessender;

import br.com.sessender.services.SESService;

public class SesSenderApplication {

	public static void main(String[] args) {
		SESService sesService = new SESService();
		sesService.sendMessage();
	}

}
