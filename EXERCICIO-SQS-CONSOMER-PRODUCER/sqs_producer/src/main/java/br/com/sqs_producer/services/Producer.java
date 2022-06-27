package br.com.sqs_producer.services;

import software.amazon.awssdk.auth.credentials.AwsCredentials;
import software.amazon.awssdk.auth.credentials.AwsCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sqs.SqsClient;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlRequest;
import software.amazon.awssdk.services.sqs.model.GetQueueUrlResponse;
import software.amazon.awssdk.services.sqs.model.SendMessageRequest;

public class Producer implements IProducer {
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

        SqsClient sqsClient = SqsClient.builder()
                .region(Region.US_EAST_1)
                .credentialsProvider(credentialsProvider)
                .build();

        GetQueueUrlRequest request = GetQueueUrlRequest.builder()
                .queueName(System.getenv("QUEUE_NAME"))
                .queueOwnerAWSAccountId(System.getenv("AWS_ACCOUNT_ID")).build();
        GetQueueUrlResponse createResult = sqsClient.getQueueUrl(request);
        sendMessage(sqsClient, createResult.queueUrl(), message);
        sqsClient.close();
    }

    public static void sendMessage(SqsClient sqsClient, String queueUrl, String message) {
        SendMessageRequest sendMsgRequest = SendMessageRequest.builder()
                .queueUrl(queueUrl)
                .messageGroupId(System.getenv("QUEUE_GROUP_ID"))
                .messageBody(message)
                .build();
        sqsClient.sendMessage(sendMsgRequest);
    }
}