import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Student {
    //objektvariabler, attributter, eller instansvariabler
    int studNr;
    String fornavn;
    String etternavn;
    int fødtÅr;
    char kjønn;



    // CTOR
    public Student(){

    }

    public Student(int _studNr, String _fornavn, String _etternavn, int _fødtÅr, char _kjønn){
        studNr = _studNr;
        fornavn = _fornavn;
        etternavn = _etternavn;
        fødtÅr = _fødtÅr;
        kjønn = _kjønn;
    }

    // Metoder
    public String toString(){
        return studNr + ": " + fornavn + " " + etternavn;
    }

    public String navn(){
        return fornavn + " " + etternavn;
    }
}
