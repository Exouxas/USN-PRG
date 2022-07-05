import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Oppg1 {
    public static void main(String[] args) {

        Scanner reader = null;
        int sum = 0;
        try{
            reader = new Scanner(new File("Oppg1.input"));

            while(reader.hasNextInt()){
                sum += calcFuel(reader.nextInt());
            }
        }catch(Exception e){
            out.println("O shit: " + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Fuck");
                }
            }
        }
        
        out.println("Sum of fuel: " + sum);


        int sum2 = 0;
        try{
            reader = new Scanner(new File("Oppg1.input"));

            while(reader.hasNextInt()){
                int mass = reader.nextInt();

                while(calcFuel(mass) > 0){
                    int calc = calcFuel(mass);

                    sum2 += calc;
                    mass = calc;
                }
            }
        }catch(Exception e){
            out.println("O shit: " + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Fuck");
                }
            }
        }
        
        out.println("Sum2 of fuel: " + sum2);




    }

    public static int calcFuel(int in){
        return (in / 3) - 2;
    }
}