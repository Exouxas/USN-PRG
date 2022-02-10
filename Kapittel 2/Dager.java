import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Dager {
    public static void main(String[] args) {
        int dag = Min.lesHeltall(0, 6, "Gi dagnummer (mandag:0)");
        int iGår = dag - 1;
        int iMorgen = dag + 1;

        if(iGår == -1) iGår = 6;
        if(iMorgen == 7) iMorgen = 0;


        out.println("Dagnummer: " + dag);
        
        out.println("I går var det " + hentDag(iGår));
        out.println("I dag er det " + hentDag(dag));
        out.println("I morgen er det " + hentDag(iMorgen));

    }

    private static String hentDag(int dag){
        String navn = " !ugyldig nummer! ";
        
        // Logikk for å hente dag
        switch(dag){
            case 0:
                navn = "mandag";
                break;
            case 1:
                navn = "tirsdag";
                break;
            case 2:
                navn = "onsdag";
                break;
            case 3:
                navn = "torsdag";
                break;
            case 4:
                navn = "fredag";
                break;
            case 5:
                navn = "lørdag";
                break;
            case 6:
                navn = "søndag";
                break;
        }
        
        return navn;
    }
}