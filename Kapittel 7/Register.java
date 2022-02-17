/* Kapittel 7, Eksempel
   Program som leser studentdata fra en tekstfil og oppretter tabell med studentobjekt
   CA - 17.02.22
*/

import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Register {
    public static void main(String[] args) {
        final int MAX_ANT = 10;

        File fil = new File("eksempeldata.txt");
        Scanner leser = null;

        Student[] studentTab = new Student[MAX_ANT];

        int i = 0;
        
        try{
            leser = new Scanner(fil);
            while(leser.hasNextLine()){
                studentTab[i++] = Student.deserialize(leser.nextLine());
            }
        }
        catch(IllegalArgumentException e){
            out.println("Feil på linje " + i + " i " + fil.getName());
        }
        catch(ArrayIndexOutOfBoundsException e){
            out.println("For mange linjer i fila!");
        }
        catch(FileNotFoundException e){
            out.println("Fil ikke funnet.");
        }
        finally{
            if(leser != null){
                leser.close();
            }
        }


        for(int j = 0; j < studentTab.length; j++){
            if(studentTab[j] != null){
                out.println(studentTab[j].toString());
            }
        }
    }
}