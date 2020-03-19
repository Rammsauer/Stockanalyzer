package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {

    private URL url;
    private double price;
    private double percent;
    private double perPrice;
    private String Notation;
    private Search search;
    private String Name;

    private String[] name;
    private String[] isin;
    private String[] price2;
    private String[] branche;
    private String[] percent2;

    //Nach 18 bis 9 Uhr ist Börsenschluss; Am Samstag und Sonntag wird nicht gehandelt


    public Request(URL url){
        this.url = url;

        Thread thread2 = new Thread(){
            public void run(){
                DownloadIndex();
            }
        };

        Thread thread3 = new Thread(){
            public void run(){
                DownloadSite();
            }
        };

        thread2.run();
        thread3.run();

        //Setting Percent and Price from Download Site
        search = new Search();
        search.searcheng();
        price = search.getPrice();
        if((price > 0) && (price < 1)){
            price = Math.round(search.getPrice()*10000)/10000.0;
        }
        else if((price > 1) && (price < 10)){
            price = Math.round(search.getPrice()*1000)/1000.0;
        }
        else if((price > 10) && (price < 100)){
            price = Math.round(search.getPrice()*100)/100.0;
        }
        else if((price > 100) && (price < 1000)){
            price = Math.round(search.getPrice()*10)/10.0;
        }
        else if((price > 1000) && (price < 10000)){
            price = Math.round(search.getPrice());
        }
        percent = search.getPercent();
        perPrice = price / 100 * percent;
        Notation = search.getNotation();
        Name = search.getName();

        Thread thread1 = new Thread(){
            public void run(){
                DownloadImage();
            }
        };

        thread1.run();
    }

    public void DownloadSite() {
        Download(url, "Stock/URL.html");

    }

    public void DownloadImage(){
        try{
            String[] s = {"1D", "5D", "10D", "3M", "6M", "1Y", "5Y"};
            for(int i = 0; i < s.length; i++) {
                URL urll = new URL("https://charts.comdirect.de/charts/rebrush/design_small.ewf.chart?DENSITY=2&HEIGHT=173&ID_NOTATION=" + Notation + "&TIME_SPAN=" + s[i] + "&TYPE=MOUNTAIN&WIDTH=256&WITH_EARNINGS=1");
                checkDown(urll, "Stock/Images/ISIN Images/" + s[i] + ".png"); //Zum checken ob ein Intervall dazwischen lieg
            }

        }
        catch(IOException e){

        }
    }

    public void DownloadIndex(){
        try{
            String[] s = {"324977", "20735", "6623216", "324985", "324724", "323547", "193736"};
            String[] g = {"Dow", "Dax", "TecDax", "Nasdaq", "SDax", "MDax", "EuroStoxx50"};
            String[] t = {"1D", "5D", "10D", "3M", "6M", "1Y", "5Y"};
            for(int i = 0; i < s.length; i++) {
                for(int n = 0; n < t.length; n++) {
                    URL urll = new URL("https://charts.comdirect.de/charts/rebrush/design_small_wide.informer.chart?HEIGHT=264&ID_NOTATION=" + s[i] + "&SHOWHL=0&TIME_SPAN=" + t[n] + "&TYPE=MOUNTAIN&WIDTH=450");
                    checkDown(urll, "Stock/Images/Stock Images/" + g[i] + "/" + g[i] + t[n] + ".png"); //Zum checken ob ein Intervall dazwischen liegt
                }
            }

        }
        catch(IOException e){

        }
    }

    //Downloade alle Kurse von den Indizies
    public void DownloadIndexK(String wert, String Index){
        try{
            Search s = new Search();
            URL urll = new URL("https://www.comdirect.de/inf/indizes/werte/" + wert);
            Download(urll, "Stock/IndexURL/" + Index + ".html");
            s.searchIndex(Index);

        }
        catch (MalformedURLException e){

        }
    }

    public void DownloadSearch(String s){
        try{
            s = s.replace(" ", "+");
            s = s.toUpperCase();
            URL urll = new URL("https://kurse.boerse.ard.de/ard/kurse_einzelkurs_suche.htn?suchbegriff=" + s + "&seite=suche&exitPoint=all&tabSearch=securityCategoryCode~SHARE");
            Download(urll, "Stock/SearchURL.html");
            search.searchsearch();
            name = search.getname();
            isin = search.getIsin();
            price2 = search.getPrice2();
            branche = search.getBranche();
            percent2 = search.getPercent2();
        }
        catch(IOException e){

        }
    }


    public void Download(URL urll, String Name){
        try {
            InputStream in = new BufferedInputStream(urll.openStream());
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int n = 0;
            while (-1 != (n = in.read(buf))) {
                out.write(buf, 0, n);
            }
            out.close();
            in.close();
            byte[] response = out.toByteArray();
            FileOutputStream fos = new FileOutputStream(Name);
            fos.write(response);
            fos.close();
        }
        catch(FileNotFoundException e){
        }
        catch (IOException e){
        }
    }

    public void checkDown(URL urll, String Path){
        File f = new File(Path);

        SimpleDateFormat m = new SimpleDateFormat("mm");
        SimpleDateFormat h = new SimpleDateFormat("HH");
        SimpleDateFormat e = new SimpleDateFormat("E");

        Date now = new Date();

        if(((e.format(now) != "Sa.") || (e.format(now) != "So."))){
            if (e.format(now) == e.format(f.lastModified())) {
                if (Integer.parseInt(m.format(now)) - Integer.parseInt(m.format(f.lastModified())) >= 5) {
                    if (Integer.parseInt(h.format(f.lastModified())) - Integer.parseInt(h.format(now)) < 1) {
                        Download(urll,  Path); //Herunterladen
                    } else {
                        //Nothing, Bilder sind noch aktuell
                    }
                } else {
                    //Nothing, Bilder sind noch aktuell
                }
            }
            else {
                Download(urll,  Path); //Herunterladen
            }
        }
        else{
            if((e.format(f.lastModified()) != "Sa.") || (e.format(f.lastModified())) != "Son."){
                Download(urll,  Path); //Herunterladen
            }
            else {
                //Nothing, Bilder sind noch aktuell
            }
        }
    }


    public double getPrice(){
        return price;
    }

    public double getPercent(){
        return percent;
    }

    public double getPerPrice(){
        return perPrice;
    }

    public String getName(){
        return Name;
    }

    public String[] getname(){ return name; }

    public String[] getIsin(){
        return isin;
    }

    public String[] getPrice2(){
        return price2;
    }

    public String[] getBranche(){
        return branche;
    }

    public String[] getPercent2(){
        return percent2;
    }

}
