package br.com.sqs_producer.util;

import java.time.LocalDate;
import java.util.Random;

public class Message {
    private String message;
    private LocalDate date;

    public Message(String message, LocalDate date) {
        this.message = message;
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {

        Random random = new Random();
        int randomNumber = (int) (random.nextDouble() * 100);

        return message + randomNumber + " - " + date;
    }

}
