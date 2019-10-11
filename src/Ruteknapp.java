import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.layout.Border;
import javafx.scene.layout.*;

public class Ruteknapp extends Button {
  private int kol;
  private int rad;
  private char tegn;


  public Ruteknapp(int kol, int rad, char tegn) {
    super();
    this.kol = kol;
    this.rad = rad;
    this.tegn = tegn;
    settFarge(tegn);
  }

  public void settFarge(char f) {
    /*
    if( f == ' ' || f == 'X') {
      this.setStyle("-fx-base: #ffffff;");
    } else if (f == 'L') {
      this.setStyle("-fx-base: #2ECC71;");
    } else {
      //this.setDisable(true);
      this.setStyle("-fx-base: #000000;");
    }*/
    if( f == ' ' || f == 'X') {
      //this.setStyle("-fx-base: #ffffff;");
      this.setBackground(new Background( new BackgroundFill(Color.WHITE, null, null)));
    } else if (f == 'L') {
      //this.setStyle("-fx-base: #2ECC71;");
      this.setBackground(new Background( new BackgroundFill(Color.GREEN, null, null)));
    } else {
      //this.setDisable(true);
      //this.setStyle("-fx-base: #000000;");
      this.setBackground(new Background( new BackgroundFill(Color.BLACK, null, null)));

    }
  }

  /**
   * Brukt i RuteknappOnClick
   * @return
   */
  public char hentTegn() {
    return tegn;
  }

  public int hentKol(){
    return kol;
  }
  public int hentRad(){
    return rad;
  }
}

