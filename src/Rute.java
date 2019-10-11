import java.util.ArrayList;

abstract class Rute {
  protected int kol;
  protected int rad;
  private boolean besokt;
  private Rute[][] refTilLab;

  Rute(int kolonnen, int raden, Rute[][] labyrinten) {
    this.kol = kolonnen;
    this.rad = raden;
    this.refTilLab = labyrinten;
  }

  public int hentRad() {return rad;}
  public int hentKol() {return kol;}


  // Arraylist med mulige naboer
  protected ArrayList<Rute> muligeNaboer = new ArrayList<>();
  protected Rute nord;
  protected Rute oest;
  protected Rute sor;
  protected Rute vest;

  public void settNord(Rute rute) { nord = rute;}
  public void settOest(Rute rute) { oest = rute;}
  public void settSor(Rute rute) { sor = rute;}
  public void settVest(Rute rute) { vest = rute;}
  public void settBesokt() {besokt = true;}

  public Rute hentNord() {return nord;}
  public Rute hentOest() {return oest;}
  public Rute hentSor() {return sor;}
  public Rute hentVest() {return vest;}
  public boolean erBesøkt() {
    if (besokt) {
      return true;
    } else {
      return false;
    }
  }


  abstract char tilTegn();

  /**
   * Abstrakt metode som ikke fortsetter i sort, fortsetter i hvit og er i mål ved åpning
   * @param forrige
   * @param utvei
   * @param labyrint
   */
  abstract public void gaa(Rute forrige, String utvei, Labyrint labyrint);

  /**
   * Funksjon som starter finn utveiprosessen
   * @param labyrint
   */
  public void finnUtvei(Labyrint labyrint) {
    if(kanGaas()) {
      // Vi kan starte fra denne ruten
      // Looper igjennom naboene så slipper vi å bestemt gjøre retning.gaa på alt
      for(Rute naboruten : muligeNaboer) {
        naboruten.gaa(this, String.format("(%d, %d)->", kol, rad), labyrint);
      }
    } else {
      // Vi kan ikke starte fra denne ruten
      System.out.println("Du kan ikke starte fra denne ruten");
    }
  }

  /**
   * Hjelpefunksjon som legger til naboene i en arraylist
   * @param naboen
   */
  public void leggTilNabo(Rute naboen) {
    muligeNaboer.add(naboen);
  }

  /**
   * Hjelpefunksjon som returnerer true hvis ruten kan gåes igjennom.
   * @return
   */
  abstract public boolean kanGaas();

}