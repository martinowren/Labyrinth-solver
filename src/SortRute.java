class SortRute extends Rute {

    SortRute(int kolonnen, int raden, Rute[][] labyrinten) {
        super(kolonnen,raden,labyrinten);
    }
    char tilTegn() {return '#';}

    public boolean kanGaas() {
        return false;
    }

    public void gaa(Rute forrige, String utvei, Labyrint labyrint) {
        // Gjør ingenting da sort ikke er en del av løsningen
    }


}
