import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Symmetri {
    public static void main(String[] args) {
        
    }

    private static int symmetruTall(){
        int tall1 = Min.trekkTall(1, 9);
        int tall2 = Min.trekkTall(0, 9);

        // Versjon 1
        int tall = tall1;
        tall *= 10;
        tall += tall2;
        tall *= 10;
        tall += tall1;

        // Versjon 2
        String tekstTall = "";
        tekstTall += tall1;
        tekstTall += tall2;
        tekstTall += tall1;
        tall = parseInt(tekstTall);

        // Versjon 2.5
        tall = parseInt("" + tall1 + tall2 + tall1);

        // Versjon 3
        tall = (tall1 * 100 + tall2 * 10 + tall1);

        // Versjon 3.5
        tall = (tall1 * 101 + tall2 * 10);

        return tall;
    }
}