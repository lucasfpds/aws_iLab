package br.com.sessender.util;

public class StringEmail {
    static String bodyText = "Olá este é um email de teste";
    static String bodyHTML = "<html>"
            + "<head></head>" + "<body>" + "<h1>Olá este é um teste!</h1>" + "<p> Enviado pelo Java.</p>" + "</body>"
            + "</html>";

    public static String getBodyText() {
        return bodyText;
    }

    public void setBodyText(String bodyText) {
        StringEmail.bodyText = bodyText;
    }

    public static String getBodyHTML() {
        return bodyHTML;
    }

    public void setBodyHTML(String bodyHTML) {
        StringEmail.bodyHTML = bodyHTML;
    }
}
