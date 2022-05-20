import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Bilde {
    //objektvariabler, attributter, eller instansvariabler
    public String tittel;
    
    public String kunstner;
    
    public String type;
    
    public int årstall;
    
    public Dimensjon dimensjon;
    
    public boolean harRamme;

    
    // CTOR
    public Bilde(String tittel, String kunstner, String type, int årstall, Dimensjon dimensjon, boolean harRamme){
        this.tittel = tittel;
        this.kunstner = kunstner;
        this.type = type;
        this.årstall = årstall;
        this.dimensjon = dimensjon;
        this.harRamme = harRamme;
    }


    // Metoder
    public String toString(){
        String returOppbygging = "";
        returOppbygging += tittel + ";";
        returOppbygging += kunstner + ";";
        returOppbygging += type + ";";
        returOppbygging += årstall + ";";
        returOppbygging += dimensjon + ";";
        returOppbygging += harRamme;

        return returOppbygging;
    }

    public boolean equals(Bilde bilde){
        boolean erLik = true;

        // Skriver lokale variable med "this" for å eksplisitt vise til programmereren at det er lokale variabler.
        // Det blir fort surr når du har to av samme objekt i en metode.
        erLik &= this.tittel.equals(bilde.tittel);
        erLik &= this.kunstner.equals(bilde.kunstner);
        erLik &= this.type.equals(bilde.type);

        erLik &= this.årstall == bilde.årstall;

        erLik &= this.dimensjon.bredde == bilde.dimensjon.bredde;
        erLik &= this.dimensjon.høyde == bilde.dimensjon.høyde;

        erLik &= this.harRamme == bilde.harRamme;

        // Så lenge alle sjekker har vært true så skal dette returnere true.
        return erLik;
    }
}
