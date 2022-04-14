// Oppgave 1a

public class Turpost {
    private int id;
    public int getId(){
        return id;
    }
    private void setId(int id){
        if(id < 1 || id > 200){
            this.id = -1; // Bruker -1 som "feilmeldig" hvis ID er out of bounds.
        }
        else{
            this.id = id;
        }
    }

    private String navn;
    public String getNavn(){
        return navn;
    }

    public int poeng;
    private void setPoeng(int poeng){
        switch(poeng){
            case 5:
            case 10:
            case 15:
            case 20:
            case 30:
            case 50:
                this.poeng = poeng;
                break;
            default:
                this.poeng = -1; // Bruker -1 som "feilmeldig" hvis poeng ikke er satt riktig.
        }
    }

    public int besøk;

    public Turpost(int id, String navn, int poeng, int besøk){
        setId(id);
        this.navn = navn;
        this.poeng = poeng;
        this.besøk = besøk;
    }

    public Turpost(int id, String navn){
        this(id, navn, 0, 0);
    }

    public String toString(){
        return id + ";" + navn + ";" + poeng + ";" + besøk;
    }
}
