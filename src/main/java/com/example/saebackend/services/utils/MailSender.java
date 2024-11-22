package com.example.saebackend.services.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

public class MailSender {

    private static final String SMTP_HOST = "smtp-impulsewordpresssae.alwaysdata.net";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = "impulsewordpresssae@alwaysdata.net";
    private static final String SMTP_PASSWORD = "nasdas40000eurosparjour!";

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
                """
                <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                        <h2 style="color: #4CAF50;">Bonjour %s,</h2>
                        <p>Votre mot de passe est :</p>
                        <p style="font-size: 18px; font-weight: bold; color: #333;">%s</p>
                        <p>Merci de garder ces informations confidentielles.</p>
                        <p>Cordialement,</p>
                        <p><strong>L'équipe IMPULSE</strong></p>
                    </body>
                </html>
                """, username, password
        );

        sendEmail(recipientEmail, subject, body);
    }
    public static void sendResetPassword(String recipientEmail, String username, String password) {
        String subject = "Votre nouveau mot de passe - IMPULSE";
        String body = String.format(
                """
            <html>
                <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                    <h2 style="color: #4CAF50;">Bonjour %s,</h2>
                    <p>Vous avez modifié votre mot de passe, votre nouveau mot de passe est :</p>
                    <p style="font-size: 18px; font-weight: bold; color: #333;">%s</p>
                    <p>Merci de garder ces informations confidentielles.</p>
                    <p style="color: red; font-weight: bold;">Si vous n'êtes pas à l'origine de cette action, veuillez contacter votre agence.</p>
                    <p>Cordialement,</p>
                    <p><strong>L'équipe IMPULSE</strong></p>
                </body>
            </html>
            """, username, password
        );

        sendEmail(recipientEmail, subject, body);
    }

    public static void sendAccountDeletionConfirmation(String recipientEmail, String name) {
        String subject = "Confirmation de suppression de compte";
        String body = String.format(
                """
                <html>
                    <body style="font-family: Arial, sans-serif; line-height: 1.6;">
                        <h2 style="color: #4CAF50;">Bonjour %s,</h2>
                        <p>Votre compte a été supprimé avec succès.</p>
                        <p>Si vous n'êtes pas à l'origine de cette action, veuillez contacter notre support.</p>
                        <p>Cordialement,</p>
                        <p><strong>L'équipe IMPULSE</strong></p>
                    </body>
                </html>
                """, name
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
            message.setContent(body, "text/html"); // Envoi en format HTML
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
