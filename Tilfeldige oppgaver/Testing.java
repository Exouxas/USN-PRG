import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;
import java.io.*;

public class Testing {
    public static void main(String[] args) {
        /*
        Seddel minSeddel = new Seddel("Norge", 32);
        Seddel minAndreSeddel = new Seddel("Sverige", 10);
        
        out.println(minSeddel.toString());
        out.println(minAndreSeddel.toString());

        out.println("Før: " + minSeddel.getPris());
        minSeddel.setValuta("SEK");
        out.println("Etter: " + minSeddel.getPris());


        Seddel[] seddelTab = new Seddel[3];
        seddelTab[0] = new Seddel("Norge", 32);
        seddelTab[1] = new Seddel("Sverige", 10);
        seddelTab[2] = new Seddel("Finland", 82);


        //
        String resultat = "";
        for(int i = 0; i < seddelTab.length; i++){
            if(seddelTab[i].getÅr() < 1960 && seddelTab[i].getVerdi() >= 100){
                resultat += seddelTab[i].toString();
            }
        }

        showMessageDialog(resultat);
        //
        */

        Scanner reader = null;
        FileWriter writer = null;
        String land = showInputDialog("Hvilket land?");
        String[] linjer = null;

        try{
            File fil = new File(land + ".txt");
            
            /// Lesing
            reader = new Scanner(fil);

            // Les antall linjer
            int antLinjer = 0;
            while(reader.hasNextLine()){
                antLinjer++;
                reader.nextLine();
            }

            reader.close();
            reader = new Scanner(fil);

            // Hent alle linjene
            linjer = new String[antLinjer];
            for(int i = 0; i < antLinjer; i++){
                linjer[i] = reader.nextLine();
            }
            reader.close();
            ///


            /// Skriving
            writer = new FileWriter(fil, true);
            int år = Integer.parseInt(showInputDialog("år:"));
            int verdi = Integer.parseInt(showInputDialog("verdi:"));
            String valuta = showInputDialog("valuta:");
            int kvalitet = Integer.parseInt(showInputDialog("kvalitet:"));
            double pris = Double.parseDouble(showInputDialog("pris:"));

            //Seddel nySeddel = new Seddel(år, verdi, valuta, kvalitet, pris);
            //writer.write(nySeddel.toString() + "\n");
            writer.close();
            ///

        }catch(Exception e){
            out.println("Det har skjedd en feil");
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("oops, can't close reader");
                }
            }
            if(writer != null){
                try{
                    writer.close();
                }catch(Exception e){
                    out.println("oops, can't close writer");
                }
            }
        }

        Arrays.sort(linjer);

        for(int i = 0; i < linjer.length; i++){
            out.println(linjer[i].toString());
        }


    }

    public int totalVerdi(Seddel[] liste){
        int sum = 0;
        for(int i = 0; i < liste.length; i++){
            //sum += liste[i].verdi;
        }

        return sum;
    }
}