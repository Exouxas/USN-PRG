import static java.lang.System.*;
import static java.lang.Integer.*;
import static java.lang.Double.*;
import static java.lang.Math.*;
import java.util.*;

public class Medlem {
    public int getMNr(){
        return mNr;
    }
    public void setMNr(int value){
        mNr = value;
    }
    private int mNr;
    
    public String getFornavn(){
        return fornavn;
    }
    public void setFornavn(String value){
        fornavn = value;
    }
    private String fornavn;
    
    public String getEtternavn(){
        return etternavn;
    }
    public void setEtternavn(String value){
        etternavn = value;
    }
    private String etternavn;
    
    public String getAdresse(){
        return adresse;
    }
    public void setAdresse(String value){
        adresse = value;
    }
    private String adresse;
    
    public int getTlf(){
        return tlf;
    }
    public void setTlf(int value){
        tlf = value;
    }
    private int tlf;


    public Medlem(){
        mNr = 0;
        fornavn = "";
        etternavn = "";
        adresse = "";
        tlf = 0;
    }

    public Medlem(String text){
        String[] splitValues = text.split(";");

        // Member ID
        try{
            mNr = Integer.parseInt(splitValues[0]);
        }catch(Exception e){
            out.println("Failed to read member ID. '" + splitValues[0] + "'' is not a valid number.");
        }

        fornavn = splitValues[1];
        etternavn = splitValues[2];
        adresse = splitValues[3];

        // Phone number
        if(splitValues.length > 4){
            try{
                tlf = Integer.parseInt(splitValues[4]);
            }catch(Exception e){
                out.println("Failed to read phone number. '" + splitValues[4] + "'' is not a valid number.");
            }
        }
    }

    public String toString(){
        String returnValue = mNr + ";" + fornavn + ";" + etternavn + ";" + adresse;
        if(tlf > 0){
            returnValue += ";" + tlf;
        }
        return returnValue;
    }

    public boolean isValid(){
        return fornavn != "" & etternavn != "" & adresse != "";
    }
}
