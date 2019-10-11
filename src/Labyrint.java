import java.util.*;
import java.io.*;
public class Labyrint {
    Rute[][] labyrinten;
    char[][] losVis;
    public static int bigCounter = 0;

    int antRad;
    int antKol;
    private ArrayList<Rute> losning = new ArrayList<Rute>();
    private ArrayList<String> utveiListe = new ArrayList<>();

    private Labyrint(Rute[][] taInnLab, int r, int k) {
        labyrinten = taInnLab;
        antRad = r;
        antKol = k;
        // Vi setter naboene til hver rute med referanser
        for(int kol = 0; kol < antKol; kol++) {
            for(int rad = 0; rad < antRad; rad++) {
                settNaboeneTilRutene(kol,rad);
            }
        }


    }

    public int hentRad() {
        return antRad;
    }
    public int hentKol() {
        return antKol;
    }
    public Rute[][] hentLab() {
        return labyrinten;
    }
    // Bruker denne til å printe ut labyrinten
    public String toString() {
        String denTommeString = "";
        for (int y = 0; y < antRad; y++) {
            for (int x = 0; x < antKol; x++) {
                denTommeString += labyrinten[x][y].tilTegn() + " ";
                //denTommeString += i + j;
            }
            denTommeString += "\n";
        }
        return denTommeString;
    }

    /**
     * Funksjon som starter finnUtei fra Kol Rad og returnerer en ArrayList med løsningene
     * @param kolonne
     * @param rad
     * @return
     */

    public ArrayList<String> finnUtvei(int kolonne, int rad) {
        utveiListe.clear();
        labyrinten[kolonne][rad].finnUtvei(this);

        return utveiListe;
    }

    /**
     * Funksjon som legger til en løsning i listen utveiListe. Bruker ArrayList da den er raskere enn Lenketliste
     * @param utveien
     */

    public void leggTilLosning(String utveien) {
        utveiListe.add(utveien);
    }

    /**
     * Hjelpefunksjon som legger til naborutnene til ruten som er gitt som parameter
     * @param kol
     * @param rad
     */
    public void settNaboeneTilRutene(int kol, int rad) {
        if(rad>0) { // Nord
            labyrinten[kol][rad].leggTilNabo(labyrinten[kol][rad-1]);
        }
        if(rad<antRad-1) { // Sør
            labyrinten[kol][rad].leggTilNabo(labyrinten[kol][rad+1]);
        }
        if(kol>0) { // Vest
            labyrinten[kol][rad].leggTilNabo(labyrinten[kol-1][rad]);
        }
        if(kol<antKol-1) { // Øst
            labyrinten[kol][rad].leggTilNabo(labyrinten[kol+1][rad]);
        }
    }

    public String kortesteLosning(ArrayList<String> losningne) {
        String kortestelosning = losningne.get(0);
        for(String losning : losningne) {
            if(losning.length() < kortestelosning.length()) {
                kortestelosning = losning;
            }
        }
        return kortestelosning;
    }

    public void printKorteste(String losingnen) {
        String nyString = losingnen;
        //nyString = nyString.replace(",", "");
        nyString = nyString.replace(" ", "");
        nyString = nyString.replace("(", "");
        nyString = nyString.replace(")", "");
        nyString = nyString.replace("-", "");
        //nyString = nyString.replace(">", "");
        //System.out.println(nyString);

        char[][] losVis = new char[antKol][antRad];
        for(int i = 0; i<antRad; i++) {
            for(int j = 0; j<antKol; j++) {
                losVis[j][i] = labyrinten[j][i].tilTegn();
            }
        }
        String[] subStrings = nyString.split(">");
        for(String subs : subStrings) {
            String[] midlerString = subs.split(",");
            losVis[Integer.parseInt(midlerString[0])][Integer.parseInt(midlerString[1])] = 'O';
        }


                    //losVis[vei.hentKol()][vei.hentRad()] = 'O';
                    //System.out.println(vei);


        // Løsningen
        for(int i = 0; i<antRad; i++) {
            for(int j = 0; j<antKol; j++) {
                System.out.print(losVis[j][i] + " ");
            }
            System.out.print("\n");
        }
    }
    public static Labyrint lesFraFil(File fil) throws FileNotFoundException {
        Scanner scanner = new Scanner(fil);

        String innlest = scanner.nextLine();
        String[] infoOmLab = innlest.split(" ");
        int antRader = Integer.parseInt(infoOmLab[0]);
        int antKolon = Integer.parseInt(infoOmLab[1]);
        Rute[][] labyrinten = new Rute[antKolon][antRader];
        int y = 0;

        // Fyller inn 2darrayet labyrinten[][] med rute

        while (scanner.hasNextLine()) { // Y Rader
            innlest = scanner.nextLine();
            String[] raden = innlest.split("");
            for(int x = 0; x < antKolon; x++) {
                // Er det en Hvit rute eller åpning
                if (raden[x].compareTo(".") == 0) {
                    if (y == 0 || y == (antRader - 1) || x == 0 || x == (antKolon - 1)) {
                        Aapning nyrut = new Aapning(x, y, labyrinten);
                        labyrinten[x][y] = nyrut;
                    } else {
                        HvitRute nyrut = new HvitRute(x, y, labyrinten);
                        labyrinten[x][y] = nyrut;
                    }
                } else {
                    SortRute nyrut = new SortRute(x, y, labyrinten);
                    labyrinten[x][y] = nyrut;
                }
            }
            y++;
        }

        // Printer ut labyrinten
        /*for (y = 0; y<antRader; y++) {
            for(int x = 0; x<antKolon;x++) {
                System.out.print(labyrinten[x][y].tilTegn());
            }
            System.out.print("\n");
        }*/

        // Fyller naboene til rutene i Rute[][]
        for(int yAkse = 0; yAkse < antRader; yAkse++) {
            for(int xAkse = 0; xAkse < antKolon; xAkse++) {
                //System.out.println(xAkse + " " +  yAkse);
                if(yAkse>0) { // Hvis vi er på kolonnen før || Nord
                    labyrinten[xAkse][yAkse].settNord(labyrinten[xAkse][yAkse - 1]);
                }
                if(xAkse<(antKolon-1)) { // Hvis vi er på raden etter || Øst
                    //System.out.println(x);
                    labyrinten[xAkse][yAkse].settOest(labyrinten[xAkse + 1][yAkse]);
                }
                if(yAkse<(antRader-1)) { // Hvis vi er på kolonnen etter || Sør
                    labyrinten[xAkse][yAkse].settSor(labyrinten[xAkse][yAkse + 1]);
                }
                if(xAkse>0) { // Hvis vi er på raden før || Vest
                    labyrinten[xAkse][yAkse].settVest(labyrinten[xAkse - 1][yAkse]);
                }
            }
        }

        /*
        *  N
        * WXE
        *  S
        * x x x
        * y
        * y
        * y
        * */
        Labyrint lab = new Labyrint(labyrinten, antRader, antKolon);
        return lab;
    }



}
