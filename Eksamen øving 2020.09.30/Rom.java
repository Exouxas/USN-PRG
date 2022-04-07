


public class Rom
{
    private String type;
    public String getType(){
        return type;
    }
    public void setType(String type){
        this.type = type;
    }

    private String romNummer;
    public String getRomNummer(){
        return romNummer;
    }
    public void setRomNummer(String romNummer){
        this.romNummer = romNummer;
    }

    private int plasser;
    public int getPlasser(){
        return plasser;
    }
    public void setPlasser(int plasser){
        this.plasser = plasser;
    }

    private boolean kanHaDatautstyr;
    public boolean getKanHaDatautstyr(){
        return kanHaDatautstyr;
    }
    public void setKanHaDatautstyr(boolean kanHaDatautstyr){
        this.kanHaDatautstyr = kanHaDatautstyr;
    }



    public Rom(String type, String romNummer, int plasser, boolean kanHaDatautstyr){
        this.type = type;
        this.romNummer = romNummer;
        this.plasser = plasser;
        this.kanHaDatautstyr = kanHaDatautstyr;
    }

    public Rom(String type, String romNummer){
        //this(type, romNummer, 0, false); // alternativ til det under

        this.type = type;
        this.romNummer = romNummer;
        this.plasser = 0;
        this.kanHaDatautstyr = false;
    }


    public String toString(){
        return type + ";" + romNummer + ";" + plasser + ";" + kanHaDatautstyr;
    }

    public boolean equals(Rom rom){
        boolean typeLik = this.type == rom.type;
        boolean romNummerLik = this.romNummer == rom.romNummer;
        boolean plasserLik = this.plasser == rom.plasser;
        boolean kanHaDatautstyrLik = this.kanHaDatautstyr == rom.kanHaDatautstyr;

        return typeLik && romNummerLik && plasserLik && kanHaDatautstyrLik;
    }

}





