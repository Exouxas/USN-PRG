import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class StudentTest {
    public static void main(String[] args) {
        // Opprette 1 studentobjekt
        Student s1 = new Student();

        s1.studNr = 123456;
        s1.fornavn = "ANFU";
        s1.etternavn = "ilbyuv";
        s1.fødtÅr = 1204;
        s1.kjønn = 'X';


        // Opprette 1 studentobjekt med innlest verdi
        Student s2 = new Student();

        s2.studNr = Min.lesHeltall(0, 999999, "Studentnummer: ");
        s2.fornavn = showInputDialog("Fornavn: ");
        s2.etternavn = showInputDialog("Fornavn: ");
        s2.fødtÅr = Min.lesHeltall(1900, 2022 - 18, "Født: ");
        s2.kjønn = showInputDialog("Kjønn (M/K): ").charAt(0);
        
        
        
    }
}