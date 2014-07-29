/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Properties;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import model.oldentities.User;
import org.apache.commons.fileupload.FileItemStream;

/**
 *
 * @author Admin
 */
public class Support {

    /**
     * Convert three args day month year to type java.sql.Date
     *
     * @param day
     * @param month
     * @param year
     * @return Date type: yyyy/MM/dd
     */
    public static Date convertToDate(String day, String month, String year) {
        Date date = null;
        try {
            String bd = year + "/" + month + "/" + day;
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date utilBD = df.parse(bd);
            date = new Date(utilBD.getTime());
        } catch (ParseException ex) {
            Logger.getLogger(Support.class.getName()).log(Level.SEVERE, null, ex);
        }
        return date;
    }

    /**
     * Send email by SSL
     *
     * @param toEmail Email of user
     * @param idActive id active authentication
     */
    public static void sendMail(String toEmail, String idActive) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(util.Constants.FROM_EMAIL, util.Constants.PASSWORD_EMAIL);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(util.Constants.FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(toEmail));
            message.setSubject("Authentication registration your account.");
            if (!idActive.isEmpty()) {
                message.setText("Dear Mail Crawler,"
                        + "\n Click to link to complete the registered , please!"
                        + "\n http://localhost:8084/Judi-JSF-PrimeFaces/active?id=" + idActive + "");
            } else {
                message.setText("OK, thank you!"
                        + "\n You have registed successfully!");
            }
            Transport.send(message);

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Send email by SSL
     *
     * @param toEmail Email of user
     * @param fullName full name
     * @param phone Phone number
     * @param title title
     * @param content Content
     * @return true if send successfully
     */
    public static boolean sendMailContact(String fullName, String toEmail, String phone, String title, String content) {
        boolean check;
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(util.Constants.FROM_EMAIL, util.Constants.PASSWORD_EMAIL);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(util.Constants.FROM_EMAIL));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(Constants.TO_EMAIL));
            message.setHeader("Content-Type", "text/plain; charset=UTF-8");
            message.setHeader("Content-Encoding", "ISO-8859-9");
            message.setSubject("Feedback from users. " + toEmail);
            String body = "Title " + title + "\n"
                    + "Email: " + toEmail + "\n"
                    + "Full Name: " + fullName + "\n"
                    + "Phone: " + phone + "\n"
                    + "Content: \n" + content;
            message.setText(body);
            message.setContent(body, "text/plain; charset=UTF-8");
            Transport.send(message);
            check = true;

        } catch (MessagingException e) {
            check = false;
            throw new RuntimeException(e);
        }
        return check;
    }

    public static String encryptMD5(String pw) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(pw.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            String hashText = number.toString(16);
            while (hashText.length() < 32) {
                hashText = "0" + hashText;
            }
            return hashText;
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException();
        }
    }

    public static boolean processFile(String filePath, FileItemStream itemStream, String imageName, String fileExtension) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                f.mkdir();
            }
            File saveFile = new File(f.getAbsolutePath() + File.separator + imageName + "." + fileExtension);
            try (FileOutputStream fos = new FileOutputStream(saveFile)) {
                InputStream is = itemStream.openStream();
                int x = 0;
                byte[] b = new byte[1024];
                while ((x = is.read(b)) != -1) {
                    fos.write(b, 0, x);
                }
                fos.flush();
                fos.close();
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public static HttpSession getSession() {
        return (HttpSession) FacesContext.getCurrentInstance().
                getExternalContext().
                getSession(false);
    }

    public static HttpServletRequest getRequest() {
        return (HttpServletRequest) FacesContext.
                getCurrentInstance().
                getExternalContext().getRequest();
    }

    public static User getCurrentUser() {
//        User user = UserDAO.getInstance().getUserByID(1);
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        User user = (User) session.getAttribute(util.Constants.CURRENT_USER);
        return user;
    }

    /**
     * get current date
     *
     * @return sql date
     */
    public static Date getCurrentDate() {
        return new Date((new java.util.Date()).getTime());
    }

    /**
     *
     * @param size length of code
     * @return
     */
    public static String randomCode(int size) {
        String str01 = "abcdefghijklmnopqrstuvwxyz";
        String str02 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String str03 = "0123456789";
        String strValid = str01 + str02 + str03;
        Random random = new Random();
        String mystring = "";
        for (int i = 0; i < size; i++) {
            int randnum = random.nextInt(strValid.length());
            mystring = mystring + strValid.charAt(randnum);
        }
        return mystring;
    }
}
