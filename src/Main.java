import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main extends Application {
  Desktop dsktp = Desktop.getDesktop();
  public Labyrint labyrintobjektet;
  private Text starttekst;
  private Ruteknapp[][] knappeArray;
  private ArrayList<String> utveiene = new ArrayList<>();
  private GridPane labyrintPane = new GridPane();

  private GridPane lagLab() {
    labyrintPane.setGridLinesVisible(false);
    this.knappeArray = new Ruteknapp[labyrintobjektet.hentKol()][labyrintobjektet.hentRad()];
    RuteknappOnClick ruteTrykket = new RuteknappOnClick(this);
    for(int y = 0; y < labyrintobjektet.hentRad(); y++) {
      for(int x = 0; x < labyrintobjektet.hentKol(); x++) {
        //System.out.println("Legger til knapp");
        knappeArray[x][y] = new Ruteknapp(x,y,labyrintobjektet.hentLab()[x][y].tilTegn());
        knappeArray[x][y].setOnAction(ruteTrykket);
        knappeArray[x][y].setMinSize(500/(labyrintobjektet.hentKol()), 500/(labyrintobjektet.hentKol()));
        knappeArray[x][y].setMaxSize(500/(labyrintobjektet.hentKol()), 500/(labyrintobjektet.hentKol()));
        labyrintPane.add(knappeArray[x][y], x, y);

      }
    }
    //System.out.print(labyrintobjektet);
    return labyrintPane;
  }

  public void finnUtVei(int kol, int rad) {
    utveiene = labyrintobjektet.finnUtvei(kol, rad);
    if(utveiene.size() > 0) { // Er det ingen løsninger
      System.out.println(labyrintobjektet.kortesteLosning(utveiene));
      displayLos(labyrintobjektet.kortesteLosning(utveiene));
      starttekst.setText("Utveiene fra: " + kol + "," + rad + " Totalt antall utveier: " + labyrintobjektet.finnUtvei(kol, rad).size() + " Totalt antall ruter besøkt: " + Labyrint.bigCounter);
      Labyrint.bigCounter = 0;
    } else {
      starttekst.setText("Ingen utveier fra: " + kol + "," + rad);
      System.out.println("Ingen utvei");
      nullStill();
    }
  }

  public void displayLos(String losningen) {
    nullStill();
    String nyString = losningen;
    nyString = nyString.replace(" ", "");
    nyString = nyString.replace("(", "");
    nyString = nyString.replace(")", "");
    nyString = nyString.replace("-", "");

    boolean[][] losVis = new boolean[labyrintobjektet.hentKol()][labyrintobjektet.hentRad()];

    for(int i = 0; i<labyrintobjektet.hentRad(); i++) {
      for(int j = 0; j<labyrintobjektet.hentKol(); j++) {
        losVis[j][i] = false;
      }
    }
    String[] subStrings = nyString.split(">");
    for(String subs : subStrings) {
      String[] midlerString = subs.split(",");
      losVis[Integer.parseInt(midlerString[0])][Integer.parseInt(midlerString[1])] = true;
    }
    for(int i = 0; i<labyrintobjektet.hentRad(); i++) {
      for (int j = 0; j < labyrintobjektet.hentKol(); j++) {
        if(losVis[j][i]) {
          knappeArray[j][i].settFarge('L');
        }
      }
    }
  }
  public void nullStill() {
    starttekst.setText("Trykk på en rute for å finne løsningen fra den ruten");
    for(int y = 0; y < labyrintobjektet.hentRad(); y++) {
      for (int x = 0; x < labyrintobjektet.hentKol(); x++) {
        if(labyrintobjektet.hentLab()[x][y].tilTegn() == ' ' || labyrintobjektet.hentLab()[x][y].tilTegn() == 'X') {
          knappeArray[x][y].settFarge(' ');
        }
      }
    }
  }

  public void start(Stage mWindow) {
    mWindow.setTitle("Labyrintløseren");

    Button filVelgerknappen = new Button("Velg en fil...");
    Image folderIkon = new Image(getClass().getResourceAsStream("resources/folder-open.png"));
    filVelgerknappen.setGraphic(new ImageView(folderIkon));


    File labyrintFilen = new FileChooser().showOpenDialog(mWindow);

    if (labyrintFilen != null) {
      try {
        labyrintobjektet = Labyrint.lesFraFil(labyrintFilen);
      } catch (FileNotFoundException e) {
        System.out.println("Fant ikke filen");
      }
    }
    System.out.println(labyrintobjektet.hentKol() + " " + labyrintobjektet.hentRad());


    starttekst = new Text("Trykk på en rute for å finne løsningen fra den ruten");
    starttekst.setFont(new Font(20));

  // VBox med infotekst og scrollpane med labyrinten
    VBox boks = new VBox();
    ScrollPane labScroll = new ScrollPane();


    if (labyrintobjektet != null) {
      boks.getChildren().add(starttekst);
      labScroll.setContent(lagLab());
      //labyrintPane.setAlignment(Pos.CENTER);
      boks.getChildren().add(labScroll);
      //boks.getChildren().add(labyrintPane);
    } else {
      starttekst.setText("Det er ikke en labyrint");
      boks.getChildren().add(starttekst);
    }

    Scene scn = new Scene(boks);
    mWindow.setScene(scn);
    mWindow.show();

  }


  public static void main(String[] args) {
    launch(args);
  }




}
