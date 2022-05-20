import static java.lang.System.*;
import static javax.swing.JOptionPane.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;


public class DiplomUtskrift {
    




    public void finnBesteRundetider(Deltager[] deltagerliste){
        final String FILNAVN = "heat.txt";
        File fil;
        Scanner reader = null;
        int linjenummer = 0;

        try{
            fil = new File(FILNAVN);
            reader = new Scanner(fil);

            int linjer = 0;
            int antallDeltagere = 0;

            while(reader.hasNextLine()){
                linjer++;
                linjenummer = linjer;

                int id = Integer.parseInt(reader.nextLine().split(";")[0]);

                if(id > antallDeltagere){
                    antallDeltagere = id;
                }
            }

            reader.close();
            reader = new Scanner(fil);

            for(int i = 0; i < linjer; i++){
                linjenummer = i + 1;

                String[] linjedeler = reader.nextLine().split(";");

                int id = Integer.parseInt(linjedeler[0]);
                double rundetid = Double.parseDouble(linjedeler[2]);

                for(Deltager d : deltagerliste){
                    if(d.getKjoretoyId() == id){
                        d.registrerBesteRundetid(rundetid);
                        break;
                    }
                }
            }
        }catch(NumberFormatException e){
            out.println("Feil format på filen '" + FILNAVN + "', linjenr " + linjenummer + ", feilmelding:" + e.toString());
        }catch(IOException e){
            out.println("Feil med å åpne filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(FileNotFoundException e){
            out.println("Finner ikke filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + FILNAVN + "', feilmelding:" + e.toString());
        }finally{
            if(reader != null){
                try{
                    reader.close();
                }catch(Exception e){
                    out.println("Kunne ikke lukke filen '" + FILNAVN + "', feilmelding:" + e.toString());
                }
            }
        }
    }


    public String[] skrivDiplomer(Deltager[] deltagerliste){
        String filnavn = "";
        File fil;
        FileWriter writer = null;
        String[] returVariabel = String[deltagerliste.length];

        try{
            int i = 0;
            for(Deltager d : deltagerliste){
                filnavn = "diplom-" + d.getNavn() + ".txt";
                returVariabel[i++] = filnavn;

                String[] tilSkriving = new String[11];
                tilSkriving[0] = "---";
                tilSkriving[1] = "Gratulerer " + d.getNavn() + ",";
                tilSkriving[2] = "";
                tilSkriving[3] = "Du har gjennomført gokartkjøring hos Fart og Spenning AS.";
                tilSkriving[4] = "";
                tilSkriving[5] = "Din beste rundetid ble:";
                tilSkriving[6] = "";
                tilSkriving[7] = d.getRundetid() + " sekunder";
                tilSkriving[8] = "";
                tilSkriving[9] = "Velkommen tilbake!";
                tilSkriving[10] = "---";

                fil = new File(filnavn);
                writer = new FileWriter(fil);
                for(String s : tilSkriving){
                    writer.println(s);
                }
                writer.close();
            }

        }catch(IOException e){
            out.println("Feil med å skrive filen '" + filnavn + "', feilmelding:" + e.toString());
        }catch(Exception e){
            out.println("Generell feil oppstått med filen '" + filnavn + "', feilmelding:" + e.toString());
        }finally{
            if(writer != null){
                try{
                    writer.close();
                }catch(Exception e){
                    out.println("Kunne ikke lukke filen '" + filnavn + "', feilmelding:" + e.toString());
                }
            }
        }

        return returVariabel;
    }
}
