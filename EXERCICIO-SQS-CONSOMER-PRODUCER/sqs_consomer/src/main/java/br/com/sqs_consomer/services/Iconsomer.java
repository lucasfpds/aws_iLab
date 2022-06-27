package br.com.sqs_consomer.services;

import java.util.List;

import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.Message;

public interface Iconsomer {
    public static void messageReader() {
    };

    public static List<Message> receiveMessages(SqsClient sqsClient, String queueUrl) {
        return null;
    };

    public static void deleteMessages(SqsClient sqsClient, String queueUrl, List<Message> messages) {
    };

}
