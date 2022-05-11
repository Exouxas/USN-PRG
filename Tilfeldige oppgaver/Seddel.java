public class Seddel {
    private String land;
    public String getLand(){
        return land;
    }
    
    private int verdi;
    public int getVerdi(){
        return verdi;
    }


    private String valuta;
    public void setValuta(String val){
        valuta = val;
        regnUtPris();
    }

    private int år;


    private double pris;
    public double getPris(){
        return pris;
    }
    
    
    private int kvalitet;
    public int getKvalitet(){
        return kvalitet;
    }
    public void setKvalitet(int val){
        kvalitet = val;
    }
    


    public Seddel(String land, int verdi){
        this.land = land;
        this.verdi = verdi;
    }

    
    public String toString(){
        return år + ";" + verdi + ";" + valuta + ";" + kvalitet + ";" + pris;
    }


    private void regnUtPris(){
        switch(valuta){
            case "NOK":
                pris = verdi * 3;
                break;
            case "SEK":
                pris = verdi * 3.1;
                break;
            case "GBP":
                pris = verdi * 1;
                break;
        }
    }
}
