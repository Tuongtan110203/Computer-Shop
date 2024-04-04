/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tuongnt.loginGoogle;

import javax.mail.PasswordAuthentication;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Session;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author tuong
 */
public class JavaMailUtils {

    public static void sendMail(String recepient, String productName, double price, int quantity, double totalMoney) throws Exception {
        System.out.println("Preparing to send email");
        Properties properties = new Properties();
        properties.put("mail.smtp.auth", true);
        properties.put("mail.smtp.starttls.enable", true);
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        String myAccountEmail = "computershop1102@gmail.com";
        String password = "cympgnwriwzcyrpq";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myAccountEmail, password);
            }
        });

        Message message = prepareMessage(session, myAccountEmail, recepient, productName, price, quantity, totalMoney);
        Transport.send(message);
        System.out.println("Message sent successfully");
    }

    private static Message prepareMessage(Session session, String myAccountEmail, String recepient, String productName, double price, int quantity, double totalMoney) {
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Order confirm message");
            // Tạo nội dung email với thông tin về sản phẩm, giá và tổng tiền
            String emailContent = "Dear Customer, \n Your order for " + productName + " has been confirmed.\n";
            emailContent += "Price: " + price + "VND" + "\n";
            emailContent += "Quantity: " + quantity + "\n";
            emailContent += "Total: " + totalMoney + "VND" + "\n";
            emailContent += "Your order will be processed promptly!";

            message.setText(emailContent);
            return message;
        } catch (Exception ex) {
            Logger.getLogger(JavaMailUtils.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
