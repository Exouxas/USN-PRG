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
import java.io.*;

public class Blet1 {
    public static void main(String[] args) {
        File fil = new File(showInputDialog("Gi filnavn:"));
        String tekst = "";
        Scanner leser = null;
        try{
            leser = new Scanner(fil);
            tekst += leser.nextLine();
            tekst += leser.nextLine();
        }
        catch(FileNotFoundException e){
            out.println("File not found!!");
        }
        catch(NoSuchElementException e){
            out.println("No more lines!!");
        }
        catch(Exception e){
            out.println("Other exception! " + e.toString());
        }
        finally{
            if(leser != null){
                leser.close();
            }
        }
        
        
    }
}