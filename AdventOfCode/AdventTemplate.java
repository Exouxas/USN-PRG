import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class AdventTemplate {
    public static void main(String[] args) {
        Scanner reader = null;
        File adventFile = new File("OppgX.input");

        // Initialization of parameters:



        // 
        
        try{
            reader = new Scanner(adventFile);

            while(reader.hasNextInt()){
                


            }
        }catch(Exception e){
            out.println("General failure: " + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Failed to close file: " + e.toString());
                }
            }
        }
    }
}