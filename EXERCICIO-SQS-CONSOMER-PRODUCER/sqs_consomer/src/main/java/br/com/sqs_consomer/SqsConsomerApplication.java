package br.com.sqs_consomer;

import br.com.sqs_consomer.services.Consomer;

public class SqsConsomerApplication {
	public static void main(String[] args) {
		System.out.println("Lendo mensagens ...");
		while (true) {
			Consomer.messageReader();
		}
	}
}
