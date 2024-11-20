package com.example.saebackend.services.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {

    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = "chihebbradai@gmail.com";
    private static final String SMTP_PASSWORD = "korx pbim rixi bqnp";

    private static Session getSession() {
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", SMTP_HOST);
        props.put("mail.smtp.port", SMTP_PORT);

        return Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(SMTP_USERNAME, SMTP_PASSWORD);
            }
        });
    }

    public static void sendPasswordEmail(String recipientEmail, String username, String password) {
        String subject = "Votre mot de passe - IMPULSE";
        String body = String.format(
                "Bonjour %s,\n\nVotre mot de passe est : %s\n\nMerci de garder ces informations confidentielles.\n\nCordialement,\nL'équipe IMPULSE",
                username, password
        );

        sendEmail(recipientEmail, subject, body);
    }

    public static void sendAccountDeletionConfirmation(String recipientEmail, String name) {
        String subject = "Confirmation de suppression de compte";
        String body = String.format(
                "Bonjour %s,\n\nVotre compte a été supprimé avec succès. Si vous n'êtes pas à l'origine de cette action, veuillez contacter le support.\n\nCordialement,\nL'équipe IMPULSE",
                name
        );

        sendEmail(recipientEmail, subject, body);
    }

    private static void sendEmail(String recipientEmail, String subject, String body) {
        try {
            Session session = getSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
