package utilities;
import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.MultiPartEmail;

public class EmailWithAttachment {

    public static void main(String[] args) {
        final String senderEmail = "pkumarmk@gmail.com";
        final String senderPassword = "FJOkqtVm";
        final String recipientEmail = "pkumarmk@125.com";
        final String attachmentFilePath = "C:\\Users\\user\\Documents\\selenium_Course\\workspace\\TautmoreDotCom\\test-output\\emailable-report.html"; // Replace with the actual file path

        try {
            EmailAttachment attachment = new EmailAttachment();
            attachment.setPath(attachmentFilePath);
            attachment.setDisposition(EmailAttachment.ATTACHMENT);
            attachment.setDescription("Attachment Description");
            attachment.setName("attachment.txt"); // Set the desired file name

            MultiPartEmail email = new MultiPartEmail();
           // email.setHostName("smtp.gmail.com");
            email.setHostName("pro.turbo-smtp.com");
            email.setSmtpPort(587);
            email.setAuthenticator(new DefaultAuthenticator(senderEmail, senderPassword));
            email.setStartTLSRequired(true);
            email.addTo(recipientEmail);
            email.setFrom(senderEmail);
            email.setSubject("Email with Attachment");
            email.setMsg("This is the message body.");

            // Attach the file
            email.attach(attachment);

            // Send the email
            email.send();

            System.out.println("Email with attachment sent successfully.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}