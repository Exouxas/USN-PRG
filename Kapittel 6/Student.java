import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Student {
    //objektvariabler, attributter, eller instansvariabler
    private int studNr;
    String fornavn;
    String etternavn;
    private int fødtÅr;
    private char kjønn;



    // CTOR
    public Student(){
        studNr = 0;
        fornavn = "Fornavn";
        etternavn = "Etternavn";
        fødtÅr = 0;
        kjønn = ' ';
    }

    public Student(int studNr, String fornavn, String etternavn, int fødtÅr, char kjønn){
        this.studNr = studNr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.fødtÅr = fødtÅr;
        this.kjønn = kjønn;
    }

    public Student(int studNr){
        this(studNr, "", "", 0, ' ');
    }

    public Student(int studNr, String fornavn, String etternavn, char kjønn){
        this(studNr, fornavn, etternavn, 0, kjønn);
    }

    // Metoder
    public String toString(){
        return studNr + ": " + pent(fornavn) + " " + pent(etternavn);
    }

    public String navn(){
        return fornavn + " " + etternavn;
    }

    private static String pentOrd(String input){
        char førsteBokstav = input.toUpperCase().charAt(0);
        return førsteBokstav + input.substring(1).toLowerCase();
    }

    private static String pent(String input){
        String[] alleOrd = input.split(" ");
        String output = "";
        for(String s : alleOrd){
            if(output != ""){output += " ";}
            output += pentOrd(s);
        }

        return output;
    }
}
