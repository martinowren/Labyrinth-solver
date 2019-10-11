class Aapning extends HvitRute {

  Aapning(int kolonnen, int raden, Rute[][] labyrinten) {
    super(kolonnen,raden,labyrinten);
  }
  char tilTegn() {return 'X';}

  public void gaa(Rute forrige, String utvei, Labyrint labyrint) {
    Labyrint.bigCounter++;
    utvei += String.format("(%d, %d)", kol, rad);
    labyrint.leggTilLosning(utvei);
  }
}
