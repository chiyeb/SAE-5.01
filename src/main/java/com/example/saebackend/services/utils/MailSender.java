package com.example.saebackend.services.utils;

import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

import java.util.Properties;

/**
 * Utility class for sending emails using the Jakarta Mail API.
 * This class provides methods for sending various types of emails such as password emails,
 * reset password notifications, and account deletion confirmations.
 *
 * <p>Emails are sent using SMTP settings defined in this class.</p>
 */
public class MailSender {

    private static final String SMTP_HOST = "smtp-impulsewordpresssae.alwaysdata.net";
    private static final String SMTP_PORT = "587";
    private static final String SMTP_USERNAME = "impulsewordpresssae@alwaysdata.net";
    private static final String SMTP_PASSWORD = "nasdas40000eurosparjour!";

    /**
     * Configures and retrieves a mail session with the specified SMTP settings.
     *
     * @return a configured {@link Session} for sending emails.
     */
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

    /**
     * Sends an email to the specified recipient containing their username and password.
     *
     * @param recipientEmail the email address of the recipient.
     * @param username       the username of the recipient.
     * @param password       the password of the recipient.
     */
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

    /**
     * Sends an email notifying the recipient of a password reset.
     *
     * @param recipientEmail the email address of the recipient.
     * @param username       the username of the recipient.
     * @param password       the new password of the recipient.
     */
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

    /**
     * Sends a confirmation email to the recipient about the successful deletion of their account.
     *
     * @param recipientEmail the email address of the recipient.
     * @param name           the name of the recipient.
     */
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

    /**
     * Sends an email with the specified recipient, subject, and body content.
     *
     * @param recipientEmail the email address of the recipient.
     * @param subject        the subject of the email.
     * @param body           the body content of the email in HTML format.
     */
    private static void sendEmail(String recipientEmail, String subject, String body) {
        try {
            Session session = getSession();
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setContent(body, "text/html");
            Transport.send(message);
        } catch (MessagingException e) {
            System.err.println("Échec de l'envoi de l'e-mail : " + e.getMessage());
            e.printStackTrace();
        }
    }
}
