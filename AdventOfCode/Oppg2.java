import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Oppg2 {
    public static void main(String[] args) {

        Scanner reader = null;
        
        try{
            reader = new Scanner(new File("Oppg2.input"));

            String input = reader.nextLine();
            IntComp computer = new IntComp(input);
            reader.close();

            computer.ops[1] = 12;
            computer.ops[2] = 2;

            out.println("Answer part 1: " + computer.RunProgram());


            int answer = 0;
            int noun = 0;
            int verb = 0;
            while(true){
                IntComp comp = new IntComp(input);
                comp.ops[1] = noun;
                comp.ops[2] = verb;
                answer = comp.RunProgram();

                if(answer == 19690720){
                    break;
                }else{
                    verb++;
                    if(verb > 99){
                        noun++;
                        verb = 0;
                    }
                }
            }

            out.println("Answer part 2: " + (100 * noun + verb));

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
    }
}