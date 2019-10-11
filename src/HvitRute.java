class HvitRute extends Rute {

    public HvitRute(int kolonnen, int raden, Rute[][] labyrinten) {
        super(kolonnen,raden,labyrinten);
    }
    char tilTegn() {return ' ';}

    /**
     * Funksjon som sjekker om vi går på en unik løsning, og går på naboene hvis den ikke var forrige rute
     * @param forrige
     * @param utvei
     * @param labyrint
     */
    public void gaa(Rute forrige, String utvei, Labyrint labyrint) {
        Labyrint.bigCounter++;
        // Denne veien er ikke i løsningen allerede. FIKSER SYKLISK
        if(!utvei.contains(String.format("(%d, %d)->", kol, rad))) {
            utvei += String.format("(%d, %d)->", kol, rad);
            // Vi fortsetter gåinga
            for(Rute naboruten : muligeNaboer) {
                // Vil ikke besøke den vi kom fra
                if(naboruten != forrige) {
                    naboruten.gaa(this, utvei, labyrint);
                }

            }
        }
    }


    public boolean kanGaas() {
        return true;
    }
}
