/**
 * Created by calle on 2016-11-07.
 */
import java.io.*;
import java.util.*;
import javax.mail.*;
import javax.mail.Flags.Flag;
import javax.mail.internet.*;

import com.sun.mail.imap.IMAPFolder;
import com.sun.mail.imap.IMAPMessage;


public class MailReceiver {

    public MailReceiver() throws MessagingException, IOException {

    }

    public SharevilleUser readLastMail(){
        try {
            Properties props = System.getProperties();
            props.setProperty("mail.store.protocol", "imaps");
            Session session = Session.getDefaultInstance(props, null);
            Store store = session.getStore("imaps");
            store.connect("imap.gmail.com", "aktiefollower@gmail.com", "OnePassword");
            IMAPFolder folder = (IMAPFolder) store.getFolder("[Gmail]/All Mail");
            String content = "";


            if (!folder.isOpen()) {
                folder.open(Folder.READ_WRITE);
            }

            Message[] messages = folder.getMessages();
            Message msg = messages[messages.length - 1];

            //System.out.println("Subject: " + msg.getSubject());
            //System.out.println("From: " + msg.getFrom()[0]);
            //System.out.println("Body: \n"+ msg.getContent());

            MimeMultipart multipart = (MimeMultipart) msg.getContent();
            for (int j = 0; j < multipart.getCount(); j++) {
                BodyPart bodyPart = multipart.getBodyPart(j);
                content += bodyPart.getContent();
            }

            folder.close(true);
            store.close();

            return parseContent(content);
        }catch(Exception e) {
            return null;
        }
    }

    private SharevilleUser parseContent(String content){
        try{
            content = content.substring(content.indexOf("http://"), content.length());
            content = content.substring(content.indexOf('[') + 1, content.length());
            String user = content.substring(0, content.indexOf(']'));
            content = content.substring(content.indexOf("Köpte") + 6, content.length());
            String aktie = content.substring(0, content.indexOf("http://") - 1);
            content = content.substring(content.indexOf("till kurs") + 10, content.length());
            double price = Double.parseDouble(content.substring(0, content.indexOf(' ')));

            return new SharevilleUser(user, aktie, price);
        }catch(Exception e){
            //Parsing error
            return null;
        }

    }
}
