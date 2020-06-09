package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    private ComboBox<String> seatMenu1 = new ComboBox<>();

    @FXML
    private ComboBox<String> seatMenu2 = new ComboBox<>();

    @FXML
    private ComboBox<String> seatMenu3 = new ComboBox<>();

    @FXML
    private Label LabelCombo;

    @FXML
    private Label LabelEu;

    @FXML
    private Label LabelSt;

    @FXML
    private Label Kurs;

    @FXML
    private TextField StackText;

    @FXML
    private RadioButton Radio1;

    @FXML
    private RadioButton Radio2;

    @FXML
    private Label LabelEUR1;

    @FXML
    private Label LabelEUR2;

    @FXML
    private Label LabelPerEu;

    @FXML
    private TextField TextKurs;

    @FXML
    private Label LabelPercent;

    @FXML
    private Label LabelEinkauf;

    @FXML
    private TextField TextZiel;

    @FXML
    private Label LabelZiel;

    @FXML
    private Label LabelKauf;

    @FXML
    private Label LabelVerkauf;

    @FXML
    private Label LabelGew;

    @FXML
    private ImageView Img1;

    @FXML
    private ImageView DAXImg;

    @FXML
    private Pane DaxPane;

    @FXML
    private TextField TextSearch;

    @FXML
    private ComboBox ComboIndex;

    @FXML
    private ComboBox Indextime;

    @FXML
    private Label gewinn1;

    @FXML
    private Label gewinn2;

    @FXML
    private Label gewinn3;

    @FXML
    private Pane SearchPane;

    @FXML
    private Label Watch;

    @FXML
    private Label Name;

    @FXML
    private ComboBox Perprice;

    @FXML
    private Label PercentZiel;

    @FXML
    private Label PercentEuro;

    @FXML
    private ComboBox ComboNews;

    @FXML
    private TabPane Tabpane;

    @FXML
    private Tab Übersicht;

    @FXML
    private Tab AV;

    @FXML
    private Tab News;

    @FXML
    private Tab Indizes;

    @FXML
    private Tab Suche;

    @FXML
    private Tab Analyse;

    @FXML
    private Button BNews;

    @FXML
    private Button BAb;

    private String AIsin = "NO0010081235";

    private String[] Dax;
    private String[] Daxisin;

    private String[] TecDAX;
    private String[] TecDaxisin;

    private String[] SDAX;
    private String[] SDaxisin;

    private String[] MDAX;
    private String[] MDaxisin;

    private String[] Dow;
    private String[] Dowisin;

    private String[] Nasdaq;
    private String[] Nasdaqisin;

    private String[] EuroStoxx50;
    private String[] EuroStoxx50isin;

    private String[] name;
    private String[] isin;
    private String[] price2;
    private String[] branche;
    private String[] percent2;

    private AnchorPane rootPane;
    private Request req;

    private static int sec = 0;
    private static int min = 0;
    private static int hour = 0;
    private static boolean state = true;

    private DecimalFormat df = new DecimalFormat("00");

    private Thread stop = new Thread(){
        public void run() {
            if(state == true) {
                while (true) {
                    try {
                        sleep(1000);
                        if (sec == 59) {
                            sec = -1;
                            min++;
                        }
                        if (min == 59) {
                            sec = -1;
                            min = 0;
                            hour++;
                        }
                        Platform.runLater(() -> Watch.setText(df.format(hour) + ":" + df.format(min) + ":" + df.format(sec)));
                        sec++;
                    } catch (Exception e) {

                    }
                }
            }
            else{
                sec = -1;
                min = 0;
                hour = 0;
                state = true;
            }
        }
    };

    //Do not change!
    public static Controller getInstance() {
        FXMLLoader loader = new FXMLLoader();
        try {
            loader.setLocation(Controller.class.getResource("sample.fxml"));
            AnchorPane rootPane = (AnchorPane) loader.load();
            Controller fxmldocumentController = loader.<Controller>getController();
            fxmldocumentController.setPane(rootPane);
            return fxmldocumentController;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    //Initialisierungsmethode beim Start
    public void init(){
        //Database init
        Database db = new Database();
        Dax = db.getDax();
        Daxisin = db.getDaxisin();

        TecDAX = db.getTecDAX();
        TecDaxisin = db.getTecDaxisin();

        SDAX = db.getSDAX();
        SDaxisin = db.getSDaxisin();

        MDAX = db.getMDAX();
        MDaxisin = db.getMDaxisin();

        Dow = db.getDow();
        Dowisin = db.getDowisin();

        Nasdaq = db.getNasdaq();
        Nasdaqisin = db.getNasdaqisin();

        EuroStoxx50 = db.getEuroStoxx50();
        EuroStoxx50isin = db.getEuroStoxx50isin();

        //Comboboxen Initialisieren
        seatMenu1.getItems().addAll("Tradegate", "Frankfurt", "Stuttgart", "Gettex", "Berlin", "München","Quotrix", "Hamburg");
        seatMenu1.getSelectionModel().select("Tradegate");

        seatMenu2.getItems().addAll("Intraday", "5 Tage", "10 Tage", "3 Monate", "6 Monate", "1 Jahr", "5 Jahre");
        seatMenu2.getSelectionModel().select("6 Monate");

        seatMenu3.getItems().addAll("Stückzahl", "Preis");
        seatMenu3.getSelectionModel().select("Stückzahl");

        ComboIndex.getItems().addAll("DAX", "MDAX", "TecDAX", "SDAX", "Dow", "Nasdaq 100", "EuroStoxx50");
        ComboIndex.getSelectionModel().select("DAX");

        Indextime.getItems().addAll("Intraday", "5 Tage", "10 Tage", "3 Monate", "6 Monate", "1 Jahr", "5 Jahre");
        Indextime.getSelectionModel().select("6 Monate");

        Perprice.getItems().addAll("€", "%");
        Perprice.getSelectionModel().select("€");

        ComboNews.getItems().addAll("DAX", "TecDAX", "SDAX", "MDAX", "Nasdaq 100", "EuroStoxx50", "Dow");
        ComboNews.getSelectionModel().select("DAX");

        //Lime sieht schrecklich aus
        gewinn1.setTextFill(Color.GREEN);
        gewinn2.setTextFill(Color.GREEN);
        gewinn3.setTextFill(Color.GREEN);

        //Beachte btnevent; umbauen zu einer anderen Methode mit festem Wert bei init();



        Thread thread1 = new Thread(){
            public void run(){
                click("NO0010081235");
                stop.start();
            }
        };

        Thread thread2 = new Thread(){
            public void run(){
                DynamicChange();
                initIndex();
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                News();
                req.IndexKurs();
            }
        };

        thread1.run();
        thread2.run();
        thread3.run();
    }

    //Funktion zum wechseln der aktuellen Aktien News in der Überischt
    public void Newsclick(){
        System.out.println("1");
    }

    //Funktion zum abspeichern der Aktie in der Übersicht
    public void Saveclick(){
        System.out.println("1");
    }


    //zum abfragen der aktuellen News; wird beim Start ausgeführt
    public void News(){
        if(ComboNews.getValue() == "DAX"){
            req.DownloadNews("DAX");
        }
        else if(ComboNews.getValue() == "SDAX"){
            req.DownloadNews("SDAX");
        }
        else if(ComboNews.getValue() == "MDAX"){
            req.DownloadNews("MDAX");
        }
        else if(ComboNews.getValue() == "TecDAX"){
            req.DownloadNews("TecDAX");
        }
        else if(ComboNews.getValue() == "Dow"){
            req.DownloadNews("Dow");
        }
        else if(ComboNews.getValue() == "Nasdaq 100"){
            req.DownloadNews("Nasdaq 100");
        }
        else if(ComboNews.getValue() == "EuroStoxx50"){
            req.DownloadNews("EuroStoxx50");
        }
    }

    //Initialisieren der SearchPane
    public void Initsearch(){
        SearchPane.getChildren().clear();
        req.DownloadSearch(TextSearch.getText());

        name = req.getname();
        isin = req.getIsin();
        price2 = req.getPrice2();
        branche = req.getBranche();
        percent2 = req.getPercent2();

        Line line2 = new Line();
        Line line3 = new Line();
        Line line4 = new Line();
        Line line5 = new Line();

        line2.setStartX(132);
        line2.setEndX(132);
        line2.setStartY(0);
        line2.setEndY(32*(name.length-1)-4);

        line3.setStartX(267);
        line3.setEndX(267);
        line3.setStartY(0);
        line3.setEndY(32*(name.length-1)-4);


        line4.setStartX(402);
        line4.setEndX(402);
        line4.setStartY(0);
        line4.setEndY(32*(name.length-1)-4);

        line5.setStartX(527);
        line5.setEndX(527);
        line5.setStartY(0);
        line5.setEndY(32*(name.length-1)-4);

        SearchPane.getChildren().add(line2);
        SearchPane.getChildren().add(line3);
        SearchPane.getChildren().add(line4);
        SearchPane.getChildren().add(line5);

        int k = 0;
        SearchPane.setMinHeight(32+(32*(name.length-2))); //
        for(int i = 1; i < name.length; i++) {
            Label ln2;
            if(name[i].length() > 14){
                ln2 = new Label(name[i].substring(0,13));
            }
            else {
                ln2 = new Label(name[i]);
            }
            Label isin2 = new Label(isin[i]);
            Label kurs2 = new Label(price2[i].replace("&nbsp", " ") + "€");
            Label bran2 = new Label(branche[i]);
            Label perc2 = new Label(percent2[i] + "%");
            Button btn1 = new Button("Analyse");
            Button btn2 = new Button("News");
            ImageView steig = new ImageView();
            Line line = new Line();

            ln2.setLayoutX(10);
            ln2.setLayoutY((32*(i-1)));
            ln2.setFont(new Font(14));

            line.setStartX(0);
            line.setEndX(800);
            line.setStartY(30+(64*(k)));
            line.setEndY(30+(64*(k)));

            kurs2.setLayoutX(270);
            kurs2.setLayoutY((32*(i-1)));
            kurs2.setFont(new Font(14));

            bran2.setLayoutX(405);
            bran2.setLayoutY((32*(i-1)));
            bran2.setFont(new Font(14));

            perc2.setLayoutX(530);
            perc2.setLayoutY((32*(i-1)));
            perc2.setFont(new Font(14));

            steig.setLayoutX(577);
            steig.setLayoutY((32*(i-1)));

            try {
                percent2[i] = percent2[i].replace(",", ".");
                if (Double.parseDouble(percent2[i]) < 0) {
                    Image image = new Image(new File("Stock/down.png").toURI().toURL().toExternalForm());
                    steig.setImage(image);
                } else if (Double.parseDouble(percent2[i]) > 0) {
                    Image image = new Image(new File("Stock/up.png").toURI().toURL().toExternalForm());
                    steig.setImage(image);
                } else if (Double.parseDouble(percent2[i]) == 0) {
                    Image image = new Image(new File("Stock/normal.png").toURI().toURL().toExternalForm());
                    steig.setImage(image);
                }
            }
            catch (IOException e){

            }

            isin2.setLayoutX(135);
            isin2.setLayoutY((32*(i-1)));
            isin2.setFont(new Font(14));

            btn1.setPrefHeight(10);
            btn1.setPrefWidth(75);
            btn1.setLayoutX(600);
            btn1.setLayoutY((32*(i-1)));

            btn2.setPrefHeight(10);
            btn2.setPrefWidth(95);
            btn2.setLayoutX(680);
            btn2.setLayoutY((32*(i-1)));

            btn1.setOnAction(actionEvent -> {
                click(isin2.getText());
                Tabpane.getSelectionModel().select(Übersicht);
                state = false;
            });

            btn2.setOnAction(actionEvent -> {
                //Aktien News anzeigen
            });


            SearchPane.getChildren().add(ln2);
            SearchPane.getChildren().add(line);
            SearchPane.getChildren().add(perc2);
            SearchPane.getChildren().add(bran2);
            SearchPane.getChildren().add(isin2);
            SearchPane.getChildren().add(kurs2);
            SearchPane.getChildren().add(steig);
            SearchPane.getChildren().add(btn1);
            SearchPane.getChildren().add(btn2);
            if(i % 2 == 0){
                k++;
            }
        }
    }

    //Actionmethode fürs verändern ob der Kauf mittels Stückzahl oder fester Ausgabe getätigt werden soll
    public void focuslost(){
        try {
            if (seatMenu3.getValue() == "Preis") {
                int f = 0;
                int g = 0;
                if (Kurs.isVisible() == true) {
                    f = (int) (Float.parseFloat(StackText.getText()) / Float.parseFloat(Kurs.getText()));
                    g = (int)(f * Float.parseFloat(Kurs.getText()));
                } else {
                    f = (int) (Float.parseFloat(StackText.getText()) / Float.parseFloat(TextKurs.getText()));
                    g = (int)(f * Float.parseFloat(TextKurs.getText()));
                }
                String t = f + "";
                String s = g + "";
                LabelCombo.setText(t);
                LabelEinkauf.setText(s);

            }
            else if (seatMenu3.getValue() == "Stückzahl") {
                float f = 0;
                if (Kurs.isVisible() == true) {
                    f = Float.parseFloat(StackText.getText()) * Float.parseFloat(Kurs.getText());
                } else {
                    f = Float.parseFloat(StackText.getText()) * Float.parseFloat(TextKurs.getText());
                }
                String t = ((float) (int) (f * 100) / 100) + "";
                LabelCombo.setText(t);
                LabelEinkauf.setText(t);
            }
        }
        catch(NumberFormatException e){
            StackText.setText("1");
            TextKurs.setText("1");
        }
    }

    //Actionmethode fürs ausrechnen des Zielkurses
    public void Ziellost(){
        try{
            if(seatMenu3.getValue() == "Stückzahl"){
                float f;
                if(Perprice.getValue() == "€"){
                    f = (Float.parseFloat(StackText.getText())) * (Float.parseFloat(TextZiel.getText()));
                    PercentEuro.setVisible(false);
                    PercentZiel.setVisible(false);
                }
                else{
                    float n = Float.parseFloat(Kurs.getText()) + (Float.parseFloat(Kurs.getText()) / 100 * Float.parseFloat(TextZiel.getText()));
                    f = (Integer.parseInt(StackText.getText())) * n;
                    PercentEuro.setVisible(true);
                    PercentZiel.setVisible(true);
                    PercentZiel.setText(n + "");
                    PercentZiel.setAlignment(Pos.CENTER_RIGHT);
                }
                DecimalFormat s = new DecimalFormat("0.00");
                String t = s.format(f);
                LabelZiel.setText(t.replace(",", "."));
            }
            else{
                float f;
                if(Perprice.getValue() == "€"){
                    f = (Float.parseFloat(StackText.getText())) * (Float.parseFloat(TextZiel.getText()));
                    PercentEuro.setVisible(false);
                    PercentZiel.setVisible(false);
                }
                else{
                    float n = Float.parseFloat(Kurs.getText()) + (Float.parseFloat(Kurs.getText()) / 100 * Float.parseFloat(TextZiel.getText()));
                    f = (Integer.parseInt(StackText.getText())) * n;
                    PercentEuro.setVisible(true);
                    PercentZiel.setVisible(true);
                    PercentZiel.setText(n + "");
                    PercentZiel.setAlignment(Pos.CENTER_RIGHT);
                }
                DecimalFormat s = new DecimalFormat("0.00");
                String t = s.format(f);
                LabelZiel.setText(t.replace(",", "."));
            }
        }
        catch (NumberFormatException e){
            TextZiel.setText("1");
        }
    }

    //Actionmethode fürs verändern des Börsenplatzes und anschließend Gewinnberechnung
    public void changeseatMenu1(){
        try {
            if (seatMenu1.getValue() == "Tradegate") {
                LabelKauf.setText("9.30");
                LabelVerkauf.setText("15.30");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Frankfurt") {
                LabelKauf.setText("14.80");
                LabelVerkauf.setText("20.80");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Stuttgart") {
                LabelKauf.setText("12.55");
                LabelVerkauf.setText("18.55");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Gettex") {
                LabelKauf.setText("9.30");
                LabelVerkauf.setText("15.30");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Berlin") {
                LabelKauf.setText("12.55");
                LabelVerkauf.setText("18.55");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "München") {
                LabelKauf.setText("12.30");
                LabelVerkauf.setText("18.55");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Quotrix") {
                LabelKauf.setText("9.30");
                LabelVerkauf.setText("15.30");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            } else if (seatMenu1.getValue() == "Hamburg") {
                LabelKauf.setText("11.80");
                LabelVerkauf.setText("17.80");

                float f = Float.parseFloat(LabelZiel.getText()) - Float.parseFloat(LabelEinkauf.getText()) - Float.parseFloat(LabelKauf.getText()) - Float.parseFloat(LabelVerkauf.getText());
                String t = ((float) ((int) (f * 100) / 100)) + "";
                if (f > 0) {
                    LabelGew.setTextFill(Color.GREEN);
                    LabelGew.setText(t);
                } else {
                    LabelGew.setTextFill(Color.RED);
                    LabelGew.setText(t);
                }
            }
        }
        catch (NumberFormatException e){
        }
    }

    //Actionmethode für die Combobox ob Preis oder Stückzahl
    public void changeCombo() {
        if (seatMenu3.getValue() == "Preis") {
            LabelSt.setText("Euro");
            LabelEu.setText("Stück");
            LabelCombo.setText("");
            StackText.setText("1");
        } else if (seatMenu3.getValue() == "Stückzahl") {
            LabelSt.setText("Stück");
            LabelEu.setText("Euro");
            LabelCombo.setText("");
            StackText.setText("1");
        }
    }

    //Actionmethode fürs verändern des Bildes in Tab2
    public void changeseatMenu2(){
        if(seatMenu2.getValue() == "Intraday"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/1D.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "5 Tage"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/5D.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "10 Tage"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/10D.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "3 Monate"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/3M.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "6 Monate"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/6M.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "1 Jahr"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/1Y.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(seatMenu2.getValue() == "5 Jahre"){
            try {
                Image image = new Image(new File("Stock/Images/ISIN Images/5Y.png").toURI().toURL().toExternalForm());
                Img1.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    public void initIndex(){
        Database db = new Database();
        for(int i = 0; i < 7; i++){
            String k = db.getKName()[i];
            String s = db.getSName()[i];
            req.DownloadIndexK(k, s);
        }
        changeIndex();

    }

    //Actionmethode fürs verändern der Combobox bei den Indizes
    public void changeIndex(){
        if(ComboIndex.getValue() == "DAX"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/Dax/Dax6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("DAX");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "MDAX"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/MDax/MDax6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("MDAX");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "TecDAX"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/TecDax/TecDax6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("TecDAX");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "SDAX"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/SDax/SDax6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("SDAX");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "Dow"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/Dow/Dow6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("Dow");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "Nasdaq 100"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/Nasdaq/Nasdaq6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("Nasdaq 100");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        if(ComboIndex.getValue() == "EuroStoxx50"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/EuroStoxx50/EuroStoxx506M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
                Indextime.getSelectionModel().select("6 Monate");
                editPane("EuroStoxx50");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    //Verändern des Kurses zu aktuellem Wert
    public void Radio1(){
        Radio2.setSelected(false);
        TextKurs.setVisible(false);
        LabelEUR2.setVisible(false);

        LabelEUR1.setVisible(true);
        LabelPercent.setVisible(true);
        LabelPerEu.setVisible(true);
        Kurs.setVisible(true);
    }

    public void changeIndextime(){
        if(ComboIndex.getValue() == "DAX"){
            String index = "Dax";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "MDAX"){
            String index = "MDax";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "TecDAX"){
            String index = "TecDax";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "SDAX"){
            String index = "SDax";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "Dow"){
            String index = "Dow";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "Nasdaq 100"){
            String index = "Nasdaq";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
        if(ComboIndex.getValue() == "EuroStoxx50"){
            String index = "EuroStoxx50";
            if(Indextime.getValue() == "Intraday"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "10 Tage"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "3 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "6 Monate"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "1 Jahr"){
                Imagechange(index);
            }
            else if(Indextime.getValue() == "5 Jahre"){
                Imagechange(index);
            }
        }
    }

    //Verändern des Kurses zu eigenem Wert
    public void Radio2(){
        Radio1.setSelected(false);
        TextKurs.setVisible(true);
        LabelEUR2.setVisible(true);

        LabelEUR1.setVisible(false);
        LabelPercent.setVisible(false);
        LabelPerEu.setVisible(false);
        Kurs.setVisible(false);
    }

    public void editPane(String s){
        DaxPane.getChildren().clear();

        int size = 0;

        if(s == "DAX"){
            size = Dax.length;
        }
        else if(s == "TecDAX"){
            size = TecDAX.length;
        }
        else if(s == "SDAX"){
            size = SDAX.length;
        }
        else if(s == "MDAX"){
            size = MDAX.length;
        }
        else if(s == "Dow"){
            size = Dow.length;
        }
        else if(s == "Nasdaq 100"){
            size = Nasdaq.length;
        }
        else if(s == "EuroStoxx50"){
            size = EuroStoxx50.length;
        }

        DaxPane.setPrefHeight(58*size);

        for(int i = 0; i < size; i++){
            Button btn = new Button("Analyse");
            Label is = new Label("ISIN:");
            Label ks = new Label("Kurs:");
            Label ksn = new Label("");
            Label trenn = new Label("-------------------------------------------");
            Label ln = null;
            Label isk = null;

            if(s == "DAX"){
                ln = new Label (Dax[i]);
                isk = new Label(Daxisin[i]);
            }
            else if(s == "TecDAX"){
                ln = new Label (TecDAX[i]);
                isk = new Label(TecDaxisin[i]);
            }
            else if(s == "SDAX"){
                ln = new Label (SDAX[i]);
                isk = new Label(SDaxisin[i]);
            }
            else if(s == "MDAX"){
                ln = new Label (MDAX[i]);
                isk = new Label(MDaxisin[i]);
            }
            else if(s == "Dow"){
                ln = new Label (Dow[i]);
                isk = new Label(Dowisin[i]);
            }
            else if(s == "Nasdaq 100"){
                ln = new Label (Nasdaq[i]);
                isk = new Label(Nasdaqisin[i]);
            }
            else if(s == "EuroStoxx50"){
                ln = new Label (EuroStoxx50[i]);
                isk = new Label(EuroStoxx50isin[i]);
            }

            btn.setLayoutX(135);
            btn.setLayoutY(10 + 57*i);
            btn.setPrefHeight(25);
            btn.setMinHeight(25);
            btn.setMaxHeight(25);
            final Label ln1 = ln;
            final Label isk1 = isk;
            btn.setOnAction(actionEvent -> {
                click(isk1.getText());
            });

            ln.setLayoutX(5);
            ln.setLayoutY(7 + 57*i);

            ks.setLayoutX(5);
            ks.setLayoutY(25 + 57*i);

            is.setLayoutX(5);
            is.setLayoutY(47 + 57*i);

            isk.setLayoutX(35);
            isk.setLayoutY(47 + 57*i);

            ksn.setLayoutX(10);
            ksn.setLayoutY(25 + 57*i);

            trenn.setLayoutX(0);
            trenn.setLayoutY(53 + 57*i);

            DaxPane.getChildren().add(btn);
            DaxPane.getChildren().add(ln);
            DaxPane.getChildren().add(is);
            DaxPane.getChildren().add(ks);
            DaxPane.getChildren().add(isk);
            DaxPane.getChildren().add(ksn);
            DaxPane.getChildren().add(trenn);
        }
    }

    public void Imagechange(String Index){
        if(Indextime.getValue() == "Intraday"){
            try {
                Image image = new Image(new File( "Stock/Images/Stock Images/" + Index + "/" + Index + "1D.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "5 Tage"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/" + Index + "/" + Index + "5D.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "10 Tage"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/" + Index + "/" + Index + "10D.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "3 Monate"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/" + Index + "/" + Index + "3M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "6 Monate"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/" + Index + "/" + Index + "6M.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "1 Jahr"){
            try {
                Image image = new Image(new File(  "Stock/Images/Stock Images/" + Index + "/" + Index + "1Y.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        else if(Indextime.getValue() == "5 Jahre"){
            try {
                Image image = new Image(new File("Stock/Images/Stock Images/" + Index + "/" + Index + "5Y.png").toURI().toURL().toExternalForm());
                DAXImg.setImage(image);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
    }

    //Lambda Methode in der Scrollbox der Indizes
    public void click(String isin){
        try {
            sec = 0;
            min = 0;
            hour = 0;

            String t = "https://www.comdirect.de/inf/aktien/" + isin;
            URL url = new URL(t);
            req = new Request(url);
            Kurs.setText(req.getPrice() + "");
            TextZiel.setText(req.getPrice() + 1 + "");
            Name.setText(req.getName());  //Später wieder ausbauen, sieht einfach schrecklich aus

            if(req.getPercent() > 0){
                LabelPercent.setTextFill(Color.GREEN);
                LabelPercent.setText("+" + ((float)((int) (req.getPercent()*100))/100) + "%");

                LabelPerEu.setText("+" + ((float)((int) (req.getPerPrice()*100))/100) + "€");
                LabelPerEu.setTextFill(Color.GREEN);
            }
            else{
                LabelPercent.setTextFill(Color.RED);
                LabelPercent.setText(((float)((int) (req.getPercent()*100))/100) + "%");

                LabelPerEu.setText(((float)((int) (req.getPerPrice()*100))/100) + "€");
                LabelPerEu.setTextFill(Color.RED);
            }

            Image image = new Image(new File("Stock/Images/ISIN Images/6M.png").toURI().toURL().toExternalForm());
            Img1.setImage(image);
            seatMenu2.getSelectionModel().select("6 Monate");
            DynamicChange();
        }
        catch (IOException e){
        }

        AIsin = isin;
    }

    public void refreshbutton(){
        click(AIsin);
        state = false;
        seatMenu2.getSelectionModel().select("6 Monate");
    }

    public void DynamicChange(){
        Ziellost();
        focuslost();
        changeseatMenu1();
    }

    //Getter für die Main()
    public Parent getRoot() {
        return rootPane;
    }

    //Setter für die Main
    private void setPane(javafx.scene.layout.AnchorPane rootPane) {
        this.rootPane = rootPane;
    }
}