public class Person {
    protected int pNr;
    public int getPNr(){return pNr;}

    protected String fornavn;
    public String getFornavn(){return fornavn;}

    protected String etternavn;
    public String getEtternavn(){return etternavn;}

    public Person(){

    }

    public Person(int pNr, String fornavn, String etternavn){
        this.pNr = pNr;
        this.fornavn = fornavn;
        this.etternavn = etternavn;
    }

    protected static String pentOrd(String input){
        char førsteBokstav = input.toUpperCase().charAt(0);
        return førsteBokstav + input.substring(1).toLowerCase();
    }

    protected static String pent(String input){
        String[] alleOrd = input.split(" ");
        String output = "";
        for(String s : alleOrd){
            if(output != ""){output += " ";}
            output += pentOrd(s);
        }

        return output;
    }
}
