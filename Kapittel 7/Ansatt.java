public class Ansatt extends Person {
    
    protected int ansNr;
    public int getAnsNr(){return ansNr;}

    public Ansatt(int pNr, String fornavn, String etternavn, int ansNr){
        this.pNr = pNr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
        this.ansNr = ansNr;
    }

    public String toString(){
        return pNr + ";" + pent(fornavn + " " + etternavn) + ";" +  ansNr;
    }
}
