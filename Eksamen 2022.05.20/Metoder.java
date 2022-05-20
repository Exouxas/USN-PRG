import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class Metoder {
    
    // Skrevet med mindre indentering for innleveringen
    public void testing(Bilde[] bildeTab){
int år = 2022;
String søkeord = "sol";
String tilVisning = "";
for(Bilde bilde : bildeTab){
    if(bilde.harRamme && (bilde.årstall >= år - 25)){
        if(bilde.dimensjon.høyde >= 100 || bilde.dimensjon.bredde >= 100){
            if(bilde.tittel.toLowerCase().indexOf(søkeord) != -1){
                if(!tilVisning.equals("")) tilVisning += "\n";
                tilVisning += bilde.toString();
            }
        }
    }
}
showMessageDialog(null, "Bilder som møter kriteriene: \n" + tilVisning);
    }


    public Bilde[] hentBilder(String filnavn){
        File fil = new File(filnavn);
        Scanner reader = null;
        int linjenummer = 0;
        Bilde[] bilder = null;

        try{
            int antallLinjer = 0;

            // Les hvor mange linjer det er.
            reader = new Scanner(fil);
            while(reader.hasNextLine()){
                antallLinjer++;
                linjenummer = antallLinjer; // Lagre hvilken linje som jobbes med for feilmelding.
            }
            reader.close();

            // Mens filen er lukket, gjør alt som ikke trenger filen.
            bilder = new Bilde[antallLinjer];

            // Fyll tabell.
            reader = new Scanner(fil);
            for(int i = 0; i < antallLinjer; i++){
                linjenummer = i + 1; // Lagre hvilken linje som jobbes med for feilmelding.

                String[] linjedeler = reader.nextLine().split(";");

                String tittel = linjedeler[0];
                String kunstner = linjedeler[1];
                String type = linjedeler[2];

                int årstall = Integer.parseInt(linjedeler[3]);

                int bredde = Integer.parseInt(linjedeler[4].split("x")[0]);
                int høyde = Integer.parseInt(linjedeler[4].split("x")[1]);
                Dimensjon dimensjon = new Dimensjon(bredde, høyde);

                boolean harRamme = linjedeler[5].equals("innrammet");

                bilder[i] = new Bilde(tittel, kunstner, type, årstall, dimensjon, harRamme);
            }
            // Trenger ikke lukke siden dette skjer i "finally".
        }catch(NumberFormatException e){
            out.println("Feil format på filen '" + filnavn + "', linjenr " + linjenummer + ", feilmelding:" + e.toString());
        }catch(IOException e){
            out.println("Feil med å åpne filen '" + filnavn + "', feilmelding:" + e.toString());
        }catch(FileNotFoundException e){
            out.println("Finner ikke filen '" + filnavn + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + filnavn + "', feilmelding:" + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Kunne ikke lukke filen '" + filnavn + "', feilmelding:" + e.toString());
                }
            }
        }

        return bilder;
    }

    public void kopierÅr(Bilde[] bilder, int årstall){
        File fil = new File(årstall + ".txt");
        FileWriter writer = null;

        try{
            // Skriv til fil.
            writer = new FileWriter(fil); // Skal skrive over, så jeg legger ikke til "append" argumentet.
            for(Bilde bilde : bilder){
                if(bilde.årstall == årstall){
                    writer.write(bilde.toString() + "\n");
                }
            }
            // Trenger ikke lukke siden dette skjer i "finally".
        }catch(IOException e){
            out.println("Feil med å åpne/lage filen '" + filnavn + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + filnavn + "', feilmelding:" + e.toString());
        }finally{
            if(writer != null){
                try{
                    writer.close();
                }catch(Exception e){ // Kunne brukt IOException
                    out.println("Kunne ikke lukke filen '" + filnavn + "', feilmelding:" + e.toString());
                }
            }
        }
    }
}
