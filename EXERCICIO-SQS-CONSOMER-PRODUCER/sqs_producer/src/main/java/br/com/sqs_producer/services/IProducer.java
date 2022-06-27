package br.com.sqs_producer.services;

import software.amazon.awssdk.services.sqs.SqsClient;

public interface IProducer {
    public static void sendMessage(String message) {
    };

    public static void sendMessage(SqsClient sqsClient, String queueUrl, String message) {
    };
}
