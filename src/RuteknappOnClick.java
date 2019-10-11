import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class RuteknappOnClick implements EventHandler<ActionEvent> {
  private Main labben;
  private Ruteknapp knappTrykket;

  public RuteknappOnClick(Main labfx) {
    this.labben = labfx;
  }

  public void handle(ActionEvent e) {
    knappTrykket = (Ruteknapp)e.getSource();
    if(knappTrykket.hentTegn() != '#') { // Så lenge det ikke er en vegg
      System.out.println(knappTrykket.hentKol() + " " + knappTrykket.hentRad());
      labben.finnUtVei(knappTrykket.hentKol(), knappTrykket.hentRad());
    } else {
      labben.nullStill();
    }
  }
}
