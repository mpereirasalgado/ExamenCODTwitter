package ejemploexamentwitter4j;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.conf.ConfigurationBuilder;

/**
 * @author micaelr
 */
public class EjemploExamenTwitter4j {

    /**
     *
     * @param args
     * @throws TwitterException
     */
    public static void main(String[] args) throws TwitterException {

        
        ConfigurationBuilder cb = new ConfigurationBuilder();
        TwitterFactory tf = new TwitterFactory(cb.build());
        Twitter twitter = tf.getInstance();
        EjemploExamenTwitter4j.menu(twitter);
    }

    /**
     * metodo de creacion de menu de la aplicacion 
     * @param twitter 
     */
    public static void menu(Twitter twitter) {
        int opcion;
        do {
            opcion = Integer.parseInt(JOptionPane.showInputDialog("***MENU***\n"
                    + "1.- Mostrar la linea de tiempo\n"
                    + "2.- Buscar Hastag\n"
                    + "3.- Hacer un twit\n"
                    + "4.- Salir\n"));
            switch (opcion) {
                case 1:
                    EjemploExamenTwitter4j.timeLine(twitter);
                    break;
                case 2:
                    EjemploExamenTwitter4j.searchString(twitter);
                    break;
                case 3:
                    EjemploExamenTwitter4j.twitt(twitter);
                    break;
                case 4:
                    System.exit(0);
            }
        } while (opcion != 4);
    }

    /**
     * metodo para generar el timeLine de Twitter
     * @param twitter
     */
    public static void timeLine(Twitter twitter) {
        try {
            List<Status> lineaDeTiempo = twitter.getHomeTimeline();
            System.out.println("TimeLine: ");
            for (Status status : lineaDeTiempo) {
                System.out.println(status.getUser().getName() + " :  " + status.getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(EjemploExamenTwitter4j.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo para buscar en twitter mediante un string
     * @param twitter
     */
    public static void searchString(Twitter twitter) {
        try {
            Query solicitud = new Query(JOptionPane.showInputDialog("Introducir hastag"));
            QueryResult respuesta = twitter.search(solicitud);
            for (Status status : respuesta.getTweets()) {
                System.out.println("@" + status.getUser().getScreenName() + ":" + status.getText());
            }
        } catch (TwitterException ex) {
            Logger.getLogger(EjemploExamenTwitter4j.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * metodo para twittear
     * @param twitter
     */
    public static void twitt(Twitter twitter) {
        try {
            Status twittear = twitter.updateStatus(JOptionPane.showInputDialog("Esribe el twit"));
            System.out.println("Successfully updated the status to [" + twittear.getText() + "].");
        } catch (TwitterException ex) {
            Logger.getLogger(EjemploExamenTwitter4j.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
