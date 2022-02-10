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

        s1.setStudNr(123456);
        s1.setFornavn("ANFU");
        s1.setEtternavn("ilbyuv");
        s1.setFødtÅr(1204);
        s1.setKjønn('X');


        // Opprette 1 studentobjekt med innlest verdi
        Student s2 = new Student();

        s2.setStudNr(Min.lesHeltall(0, 999999, "Studentnummer: "));
        s2.setFornavn(showInputDialog("Fornavn: "));
        s2.setEtternavn(showInputDialog("Etternavn: "));
        s2.setFødtÅr(Min.lesHeltall(1900, 2022 - 18, "Født: "));
        s2.setKjønn(showInputDialog("Kjønn (M/K): ").charAt(0));
        
        out.println(s2.toString());
        
    }
}