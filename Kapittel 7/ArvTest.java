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

public class ArvTest {
    public static void main(String[] args) {
        
        Person p = new Person(1234567, "ABC", "123");
        out.println(p.getFornavn());

        Ansatt a = new Ansatt(1234567, "AnGnmA", "GoohWmAnmG", 1);
        out.println(a.toString());

        Person p2 = new Ansatt(1234567, "AnGnmA", "GoohWmAnmG", 1);

        try{
            out.println(p.getClass());
            out.println(a.getClass());
            out.println(p2.getClass());

            out.println(   ((Ansatt)p).getAnsNr()   );
            out.println(a.getAnsNr());
            out.println(   ((Ansatt)p2).getAnsNr()   );
        }
        catch(Exception e){
            out.println(e.toString());
        }
    }
}