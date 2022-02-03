import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Jobb2 {
    public static void main(String[] args) {
        String tekst = showInputDialog("Gi tekst:");
        tekst = tekst.toLowerCase();
        out.println("input: " + tekst);
        out.println();
        out.println("har den 'e'?: " + tekst.contains("e"));
        out.println();
        out.println("hvor mange t?: " + antallAv(tekst, 't'));

        out.println();

        for(char c = 'a'; c <= 'z'; c++){
            out.println("hvor mange '" + c + "'?: " + antallAv(tekst, c));
        }


        out.println();
        out.println("Antall bokstaver per ord:");
        for(String ord : tekst.split(" ")){
            out.println(ord + " - " + ord.length());
        }

        tekst = pent(tekst);

        out.println(tekst.toUpperCase());
    }

    private static String pentOrd(String input){
        char førsteBokstav = input.toUpperCase().charAt(0);
        return førsteBokstav + input.substring(1).toLowerCase();
    }

    private static String pent(String input){
        String[] alleOrd = input.split(" ");
        String output = "";
        for(String s : alleOrd){
            if(output != ""){output += " ";}
            output += pentOrd(s);
        }

        return output;
    }

    private static int antallAv(String s, char c){
        int antall = 0;
        int pointer = 0;
        while(pointer < s.length()){
            int pos = s.indexOf(c, pointer);
            
            if(pos == -1) break;

            antall++;
            pointer = pos + 1;
        }


        return antall;
    }
}