import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Hovedprogram {

    public static void main(String[] args) {

        File fil = new File("src/4.in");
        Labyrint l = null;
        try {
            l = Labyrint.lesFraFil(fil);
        } catch (FileNotFoundException e) {
            System.out.printf("FEIL: Kunne ikke lese fra '%s'\n", "1.in");
            System.exit(1);
        }
        System.out.println(l);
        //System.out.println(l.hentLab()[4][4]);
        ArrayList<String> utveier = l.finnUtvei(1,1);
        if(utveier.size() == 0 ) {
            System.out.println("Det finnes ingen utveier");
        } else {
            System.out.println("Alle løsningene");
            for(String losning : utveier) {
                System.out.println(losning);
            }
            System.out.println("Korteste løsningen er:");
            System.out.println(l.kortesteLosning(utveier));
            l.printKorteste(l.kortesteLosning(utveier));
        }




    }

}
