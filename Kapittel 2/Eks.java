    /* Kapittel X, oppgave Y
    Tekst om programmet
    Navn - Dato
    */

    import static java.lang.System.*;
    import static javax.swing.JOptionPane.*;
    import static java.lang.Integer.*;
    import static java.lang.Double.*;
    import static java.lang.Math.*;
    import java.util.*;

    public class Eks extends EasyGraphics {
        public static void main(String[] args) {
            launch(args);
        }

        public void run() {
            int høyde = 5;


            // Fra her
            final int størrelse = 400;
            makeWindow("Grafikk", størrelse, størrelse);

            int radius = størrelse / (høyde * 2);

            int y = radius;
        //for(int rad = høyde; rad >= 1; rad--){ Hvis du vil reversere
            for(int rad = 1; rad <= høyde; rad++){
                int x = radius;
                for(int ball = 1; ball <= rad; ball++){
                    drawCircle(x, y, radius);
                    x += radius * 2;
                }
                y += radius * 2;
            }
            // Til her
        }
    }
