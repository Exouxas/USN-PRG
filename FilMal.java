import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Mal {
    public static void main(String[] args) {
        File fil = new File("tallfil.txt"); // Lag fil-peker
        Scanner leser = new Scanner(fil); // opprett leser
        leser.useLocale(Locale.US); // bruk punktum som separator
        // leser.useDelimitier(";"); // endre skilletegn
        
        leser.hasNext(); // bool
        leser.next(); // String

        leser.hasNextLine(); // bool
        leser.nextLine(); // String

        leser.hasNextInt(); // bool
        leser.nextInt(); // int

        leser.hasNextDouble(); // bool
        leser.nextDouble(); // double


        
    }
}