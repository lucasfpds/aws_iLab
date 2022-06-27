package br.com.sessender.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import br.com.sessender.aws.Credentials;
import br.com.sessender.util.StringEmail;
import software.amazon.awssdk.core.SdkBytes;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.ses.SesClient;
import software.amazon.awssdk.services.ses.model.RawMessage;
import software.amazon.awssdk.services.ses.model.SendRawEmailRequest;
import software.amazon.awssdk.services.ses.model.SesException;

public class SESService implements iSESService {
    @Override
    public void sendMessage() {

        Region region = Region.US_EAST_1;
        SesClient client = SesClient.builder()
                .credentialsProvider(Credentials.getCredentials())
                .region(region)
                .build();

        try {
            send(client, System.getenv("EMAIL_SENDER"), System.getenv("EMAIL_RECIPIENT"), "Email de teste",
                    StringEmail.getBodyText(), StringEmail.getBodyHTML());
            client.close();

            System.out.println("Email envido.");

        } catch (IOException | MessagingException e) {
            e.getStackTrace();
        }
    }

    @Override
    public void send(SesClient client,
            String sender,
            String recipient,
            String subject,
            String bodyText,
            String bodyHTML) throws AddressException, MessagingException, IOException {

        Session session = Session.getDefaultInstance(new Properties());
        MimeMessage message = new MimeMessage(session);

        message.setSubject(subject, "UTF-8");
        message.setFrom(new InternetAddress(sender));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipient));

        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent(bodyText, "text/plain; charset=UTF-8");

        MimeBodyPart htmlPart = new MimeBodyPart();
        htmlPart.setContent(bodyHTML, "text/html; charset=UTF-8");

        MimeMultipart msgBody = new MimeMultipart();
        msgBody.addBodyPart(textPart);
        msgBody.addBodyPart(htmlPart);

        message.setContent(msgBody);

        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            message.writeTo(outputStream);
            ByteBuffer buf = ByteBuffer.wrap(outputStream.toByteArray());

            byte[] arr = new byte[buf.remaining()];
            buf.get(arr);

            SdkBytes data = SdkBytes.fromByteArray(arr);
            RawMessage rawMessage = RawMessage.builder()
                    .data(data)
                    .build();

            SendRawEmailRequest rawEmailRequest = SendRawEmailRequest.builder()
                    .rawMessage(rawMessage)
                    .build();

            client.sendRawEmail(rawEmailRequest);

        } catch (SesException e) {
            System.err.println(e.awsErrorDetails().errorMessage());
        }
    }
}