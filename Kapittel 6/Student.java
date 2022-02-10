import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Student {
    //objektvariabler, attributter, eller instansvariabler
    private int studNr;
    public int getStudNr(){
        return studNr;
    }
    public void setStudÅr(int fødtÅr){
        this.studNr = studNr;
    }

    private String fornavn;
    public String getFornavn(){
        return fornavn;
    }
    public void setFornavn(String fornavn){
        this.fornavn = pent(fornavn);
    }

    private String etternavn;
    public String getEtternavn(){
        return etternavn;
    }
    public void setEtternavn(String etternavn){
        this.etternavn = pent(etternavn);
    }
    
    private int fødtÅr;
    public int getFødtÅr(){
        return fødtÅr;
    }
    public void setFødtÅr(int fødtÅr){
        this.fødtÅr = fødtÅr;
    }
    
    private char kjønn;
    public char getKjønn(){
        return kjønn;
    }
    public void setKjønn(char kjønn){
        this.kjønn = kjønn;
    }
    



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
