import javax.mail.MessagingException;
import java.io.IOException;

/**
 * Created by calle on 2016-11-07.
 */
public class Main {
    public static void main(String [] args) throws MessagingException, IOException {
        MailReceiver mailReceiver = new MailReceiver();

        SharevilleUser sharevilleUser = mailReceiver.readLastMail();

        System.out.println("User: " + sharevilleUser.getUser());
        System.out.println("Aktie: " + sharevilleUser.getAktie());
        System.out.println("Price: " + sharevilleUser.getPrice());
    }
}
