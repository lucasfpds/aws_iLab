package br.com.sqs_sender.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;

public class SQSService {
    public static void sendMessage(String message) {
        AwsCredentialsProvider credentialsProvider = new AwsCredentialsProvider() {
            @Override
            public AwsCredentials resolveCredentials() {
                return new AwsCredentials() {
                    @Override
                    public String accessKeyId() {
                        return System.getenv("AWS_ACCESS_KEY");
                    }

                    @Override
                    public String secretAccessKey() {
                        return System.getenv("AWS_SECRET_KEY");
                    }
                };
            }
        };
        System.out.println("variavel secret: " + System.getenv("AWS_ACCESS_KEY"));
        // SqsClient sqsClient = SqsClient.builder()
        // .region(Region.US_EAST_1)
        // .credentialsProvider(credentialsProvider)
        // .build();

        // // ===== Busca uma Fila =====
        // GetQueueUrlRequest request = GetQueueUrlRequest.builder()
        // .queueName("fila-lucas") // enfia para fila fifo
        // .queueOwnerAWSAccountId("843498830536").build();
        // GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);

        // sendMessage(sqsClient, createResult.queueUrl(), message);

        // sqsClient.close();
        // }

        // public static void sendMessage(SqsClient sqsClient, String queueUrl, String
        // message) {
        // SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
        // .queueUrl(queueUrl)
        // // .messageGroupId("grupo") // Para filas fifo
        // .messageBody(message)
        // .build();
        // sqsClient.sendMessage(sendMsgRequest);

    }
}