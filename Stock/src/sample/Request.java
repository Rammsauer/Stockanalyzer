package sample;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLOutput;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Request {

    private URL url;
    private double price;
    private double percent;
    private double perPrice;
    private String Notation;
    private String ONotation = "204941498";
    private Search search;
    private String Name;

    private String[] name;
    private String[] isin;
    private String[] price2;
    private String[] branche;
    private String[] percent2;
    private String[] Date;
    private String[] News;
    private String[] Href;

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

    //Downloaden der Kurse für den Index; Wird beim Start ausgeführt
    public void IndexKurs(){
        String[] tempurl = {"https://www.comdirect.de/inf/indizes/detail/werte/standard.html?_=&DETAILS_OFFSET=0&ID_NEWS=976406637&ID_NOTATION=20735&NEWS_CATEGORY=EWF&NEWS_HASH=7bcc144b757975833e2517777de78974e9043ca&OFFSET=0&SEARCH_VALUE=DE0008469008",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_INSTRUMENT=83277&REDIRECT_TYPE=SYMBOL&REFERER=search.general&SEARCH_REDIRECT=true&SEARCH_VALUE=MDAX",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_NOTATION=6623216&REDIRECT_TYPE=WHITELISTED&REFERER=search.general&SEARCH_REDIRECT=true&SEARCH_VALUE=TECDAX",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_NOTATION=324724",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_NOTATION=35803359&REDIRECT_TYPE=WHITELISTED&REFERER=search.general&SEARCH_REDIRECT=true&SEARCH_VALUE=DOW",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_NOTATION=35803362&REDIRECT_TYPE=WHITELISTED&REFERER=search.general&SEARCH_REDIRECT=true&SEARCH_VALUE=NASDAQ+100",
                            "https://www.comdirect.de/inf/indizes/detail/werte/standard.html?ID_NOTATION=193736&REDIRECT_TYPE=WHITELISTED&REFERER=search.general&SEARCH_REDIRECT=true&SEARCH_VALUE=EUROSTOXX50"};
        for(int i = 0; i < tempurl.length; i++) {
            search.IndexSearch(tempurl[i], i);
        }
    }

    public void DownloadSite() {
        Download(url, "Stock/URL.html");

    }

    //Downloaden der jeweiligen News Seite
    public void DownloadNews(String Wert){
        if (Wert == "DAX") {
            String urll = "https://www.comdirect.de/inf/indizes/detail/news.html?ID_NOTATION=20735";

            search.searchNews(urll);

            Date = search.getDate();
            News = search.getNews();
            Href = search.getHref();

        } else if (Wert == "SDAX") {
        } else if (Wert == "MDAX") {
        } else if (Wert == "TecDAX") {
        } else if (Wert == "Dow") {
        } else if (Wert == "Nasdaq 100") {
        } else if (Wert == "EuroStoxx50") {
        }
    }

    public void DownloadImage(){
        try{
            String[] s = {"1D", "5D", "10D", "3M", "6M", "1Y", "5Y"};
            for(int i = 0; i < s.length; i++) {
                URL urll = new URL("https://charts.comdirect.de/charts/rebrush/design_small.ewf.chart?DENSITY=2&HEIGHT=173&ID_NOTATION=" + Notation + "&TIME_SPAN=" + s[i] + "&TYPE=MOUNTAIN&WIDTH=256&WITH_EARNINGS=1");
                if(ONotation.equals(Notation)){
                    CheckISIN(urll, "Stock/Images/ISIN Images/" + s[i] + ".png"); //Zum checken ob ein Intervall dazwischen lieg
                }
                else {
                    Download(urll, "Stock/Images/ISIN Images/" + s[i] + ".png");
                }
            }
            ONotation = Notation;
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
                    CheckStock(urll, "Stock/Images/Stock Images/" + g[i] + "/" + g[i] + t[n] + ".png"); //Zum checken ob ein Intervall dazwischen liegt
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

    public void CheckStock(URL urll, String Path){
        File f = new File(Path);

        SimpleDateFormat m = new SimpleDateFormat("mm");
        SimpleDateFormat h = new SimpleDateFormat("HH");
        SimpleDateFormat e = new SimpleDateFormat("E");
        SimpleDateFormat mt = new SimpleDateFormat("MM");
        SimpleDateFormat d = new SimpleDateFormat("dd");
        SimpleDateFormat y = new SimpleDateFormat("yyyy");

        Date now = new Date();

        if(y.format(f.lastModified()).equals(y.format(now))){
            if(mt.format(f.lastModified()).equals(mt.format(now))){
                if(d.format(f.lastModified()).equals(d.format(now))){
                    if((e.format(f.lastModified()) != "Sa.") || (e.format(f.lastModified()) != "So.")){
                        if(Integer.parseInt(h.format(f.lastModified())) - Integer.parseInt(h.format(now)) == 0){
                            switch (Integer.parseInt(m.format(f.lastModified())) - Integer.parseInt(m.format(now))){
                                case -5:
                                    break;
                                case -4:
                                    break;
                                case -3:
                                    break;
                                case -2:
                                    break;
                                case -1:
                                    break;
                                case 0:
                                    break;
                                case 1:
                                    break;
                                case 2:
                                    break;
                                case 3:
                                    break;
                                case 4:
                                    break;
                                case 5:
                                    break;
                                case 55:
                                    break;
                                case 56:
                                    break;
                                case 57:
                                    break;
                                case 58:
                                    break;
                                case 59:
                                    break;
                                default:
                                    Download(urll,  Path);
                                    break;
                                //Download if größer
                            }
                        }
                        else {
                            Download(urll,  Path);
                            //Stunden sind ungleich
                        }
                    }
                    else {
                        //Nichts da Samstag und Sonntags nichts passiert
                    }
                }
                else {
                    Download(urll,  Path);
                    //Trotzdem Herunterladen da Tage verschieden
                }
            }
            else{
                Download(urll,  Path);
                //Trotzdem Herunterladen da Monate verschieden
            }
        }
        else{
            Download(urll,  Path);
            //Trotzdem Herunterladen da Jahre verschieden
        }
    }

    public void CheckISIN(URL urll, String Path){
        File f = new File(Path);

        SimpleDateFormat m = new SimpleDateFormat("mm");
        SimpleDateFormat h = new SimpleDateFormat("HH");
        SimpleDateFormat mt = new SimpleDateFormat("MM");
        SimpleDateFormat d = new SimpleDateFormat("dd");
        SimpleDateFormat y = new SimpleDateFormat("yyyy");

        Date now = new Date();

        if(y.format(f.lastModified()).equals(y.format(now))){
            if(mt.format(f.lastModified()).equals(mt.format(now))){
                if(d.format(f.lastModified()).equals(d.format(now))){
                    if(Integer.parseInt(h.format(f.lastModified())) - Integer.parseInt(h.format(now)) == 0){
                        switch (Integer.parseInt(m.format(f.lastModified())) - Integer.parseInt(m.format(now))){
                            case -5:
                                break;
                            case -4:
                                break;
                            case -3:
                                break;
                            case -2:
                                break;
                            case -1:
                                break;
                            case 0:
                                break;
                            case 1:
                                break;
                            case 2:
                                break;
                            case 3:
                                break;
                            case 4:
                                break;
                            case 5:
                                break;
                            case 55:
                                break;
                            case 56:
                                break;
                            case 57:
                                break;
                            case 58:
                                break;
                            case 59:
                                break;
                            default:
                                Download(urll,  Path);
                                break;
                                //Download if größer
                        }
                    }
                    else {
                        Download(urll,  Path);
                        //Stunden sind ungleich
                    }
                }
                else {
                    Download(urll,  Path);
                    //Trotzdem Herunterladen da Tage verschieden
                }
            }
            else{
                Download(urll,  Path);
                //Trotzdem Herunterladen da Monate verschieden
            }
        }
        else{
            Download(urll,  Path);
            //Trotzdem Herunterladen da Jahre verschieden
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

    public String[] getDate(){
        return Date;
    }

    public String[] getNews(){
        return News;
    }

    public String[] getHref(){
        return Href;
    }

}
