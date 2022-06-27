package br.com.sqs_producer;

import java.time.LocalDate;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sqs_producer.services.Producer;
import br.com.sqs_producer.util.Message;

@SpringBootApplication
public class SqsProducerApplication {

	public static void main(String[] args) {
		Producer.sendMessage(new Message("Uma mensagem - ", LocalDate.now()).toString());
	}

}
