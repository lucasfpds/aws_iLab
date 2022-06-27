package br.com.sessender.services;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import software.amazon.awssdk.services.ses.SesClient;

public interface iSESService {
    public void sendMessage();

    public void send(SesClient client,
            String sender,
            String recipient,
            String subject,
            String bodyText,
            String bodyHTML) throws AddressException, MessagingException, IOException;
}
