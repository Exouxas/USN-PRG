import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Deltager {
    //objektvariabler, attributter, eller instansvariabler
    private String navn;
    public String getNavn(){
        return navn;
    }
    public void setNavn(String navn){
        this.navn = navn;
    }
    
    private String epost;
    public String getEpost(){
        return epost;
    }
    public void setEpost(String epost){
        this.epost = epost;
    }
    
    private int telefonnummer;
    public int getTelefonnummer(){
        return telefonnummer;
    }
    public void setTelefonnummer(int telefonnummer){
        this.telefonnummer = telefonnummer;
    }

    private java.time.LocalDate signertRisikoSkjema;
    public java.time.LocalDate getSignertRisikoSkjema(){
        return signertRisikoSkjema;
    }
    public void setSignertRisikoSkjema(java.time.LocalDate signertRisikoSkjema){
        this.signertRisikoSkjema = signertRisikoSkjema;
    }
    

    private int rundetid;
    public int getRundetid(){
        return rundetid;
    }

    // CTOR
    public Student(String navn, String epost, int telefonnummer, java.time.LocalDate signertRisikoSkjema){
        this.navn = navn;
        this.epost = epost;
        this.telefonnummer = telefonnummer;
        this.signertRisikoSkjema = signertRisikoSkjema;

        rundetid = 0; 
    }


    // Metoder
    public String toString(){
        return "" + studNr;
    }

    public void registrerBesteRundetid(int tid){
        if(tid < rundetid || rundetid == 0){
            rundetid = tid;
        }
    }
}
