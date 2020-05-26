package sample;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Search {

    private String Notation;
    private double price;
    private double percent;
    private String Name;

    private String[] name;
    private String[] isin;
    private String[] price2;
    private String[] branche;
    private String[] percent2;

    public Search(){

    }

    public void searcheng(){
        try{
            FileReader fr = new FileReader("Stock/URL.html");
            BufferedReader br = new BufferedReader(fr);

            String zeile = "";
            String s = "";

            while( (zeile = br.readLine()) != null )
            {
                s = s + zeile;
            }

            Pattern rex1r = Pattern.compile("<span class=\"realtime-indicator--value text-size--xxlarge text-weight--medium\">(.*?)<\\/span>");
            Pattern rex1o = Pattern.compile("<span class=\"text-size--xxlarge text-weight--medium\">(.*?)</span>");

            Pattern rex2p = Pattern.compile("<span class=\"text-size--xlarge color--cd-positive\">(.*?) </span>");
            Pattern rex2n = Pattern.compile("<td class=\"simple-table__cell color--cd-negative\">(.*?)</td>");

            Pattern rex3 = Pattern.compile("DENSITY=2&amp;HEIGHT=173&amp;ID_NOTATION=(.*?)&amp;");

            Pattern name = Pattern.compile("<h1 class=\"headline headline--h1 headline--full-width headline--inline\">(.*?)<span class=\"text-size--medium\">Aktie</span>");

            Matcher m1r = rex1r.matcher(s);
            Matcher m1o = rex1o.matcher(s);

            Matcher m2p = rex2p.matcher(s);
            Matcher m2n = rex2n.matcher(s);

            Matcher m3 = rex3.matcher(s);

            Matcher mn = name.matcher(s);
            while(m1r.find()){
                String k = m1r.group(1);
                k = k.replace(" ", "");
                k = k.replace(",", ".");
                price = Double.parseDouble(k);
            }
            while(m1o.find()){
                String k = m1o.group(1);
                k = k.replace(" ", "");
                k = k.replace(",", ".");
                price = Double.parseDouble(k);
            }
            while (m2p.find()){
                String m = m2p.group(1);
                m = m.replace(" ", "");
                m = m.replace(",", ".");
                m = m.replace("&#160;%", "");
                percent = Double.parseDouble(m);
            }
            while (m2n.find()){
                String m = m2n.group(1);
                m = m.replace(" ", "");
                m = m.replace(",", ".");
                m = m.replace("&#160;%", "");
                percent = Double.parseDouble(m);
            }
            while (m3.find()){
                String n = m3.group(1);
                n = n.replace(" ", "");
                Notation = n;
            }
            while (mn.find()){
                String n = mn.group(1);
                n = n.replace("  ", "");
                n = n.replace("   ", "");
                n = n.replace("    ", "");
                n = n.replace("     ", "");
                n = n.replace("&#38;","&");
                Name = n;

            }
        }
        catch(IOException e){
        }
    }

    public void searchNews(String Wert){
        try {
            FileReader fr = new FileReader("Stock/News/" + Wert +".html");
            BufferedReader br = new BufferedReader(fr);

            List<String> Datum = new ArrayList<String>();
            List<String> Uhrzeit = new ArrayList<String>();
            List<String> Nachricht = new ArrayList<String>();
            List<String> Link = new ArrayList<String>();

            String zeile = "";
            String s = "";

            while ((zeile = br.readLine()) != null) {
                s = s + zeile;
            }

            Pattern Dat = Pattern.compile("<td class=\"table__column table__column--left text-weight--regular text-size--small color--cd-anthracite-50\">(.*?)</td>");
            //Pattern Nach = Pattern.compile("<td class=\"table__column table__column--left\"></td><a href=\"(.*?)\" class=\"link link--secondary link--seo-prepared\" data-plugin=\"(.*?)\" title=\"(.*?)\">(.*?)</a>");
            Pattern Lin = Pattern.compile("<a href=\"(.?)\" class=\"link link--secondary link--seo-prepared\"");

            Matcher m1 = Dat.matcher(s);
            Matcher m2 = Lin.matcher(s);

            String n1 = "";
            String n2 = "";
            String n3 = "";

            while (m1.find()){
                String t = m1.group(1);
                t = t.replace(" ", "");
                t = t.replace("&#160;", " ");
                n1 = n1 + ";" + t;
            }

            while (m2.find()){
                String t = m2.group(0);
                t = t.replace("&amp;", "&");
                System.out.println(t);
                n2 = n2 + ";" + t;
            }

            while (m2.find()){
                String t = m2.group(2);
                n3 = n3 + ";" + t;
            }

            //System.out.println(n1);
            System.out.println("1 " + n2);
        }
        catch (IOException e){

        }
    }

    public void searchsearch(){
        try {
            FileReader fr = new FileReader("Stock/SearchURL.html");
            BufferedReader br = new BufferedReader(fr);

            String zeile = "";
            String s = "";

            while ((zeile = br.readLine()) != null) {
                s = s + zeile;
            }

            Pattern Name = Pattern.compile("<td headers=\"bezeichnung\" class=\"tleft rightborder\"><a href=\"https://kurse.boerse.ard.de/ard/kurse_einzelkurs_uebersicht.htn\\?i=(.*?)\"><strong>(.*?)</strong></a></td>");
            Pattern Isin = Pattern.compile("<td headers=\"isin\" class=\"tleft rightborder\">(.*?)</td>");
            Pattern Price = Pattern.compile(".gif\" class=\"rightfloat\" alt=\"(.*?)\" /><span class=\"rightfloat\">(.*?)</span></td>");
            Pattern Branche = Pattern.compile("<td headers=\"branche\" class=\"tleft rightborder\">(.*?)</td>");
            Pattern Percent = Pattern.compile("<td headers=\"prozent\" class=\"tright rightborder\"><span class=\"(.*?)\">(.*?)</span></td>");

            Matcher m1 = Name.matcher(s);
            Matcher m2 = Isin.matcher(s);
            Matcher m3 = Price.matcher(s);
            Matcher m4 = Branche.matcher(s);
            Matcher m5 = Percent.matcher(s);

            String n1 = "";
            String n2 = "";
            String n3 = "";
            String n5 = "";
            String n6 = "";

            while (m1.find()){
                n1 = n1 + ";" + m1.group(2);
            }

            while (m2.find()){
                n2 = n2 + ";" + m2.group(1);
                //System.out.println(m2.group(1));
            }

            while (m3.find()){
                String t = m3.group(2);
                t = t.replace("&nbsp;&euro;&nbsp;", "");
                t = t.replace(",", ".");
                n3 = n3 + ";" + t;
            }

            while (m4.find()){
                n5 = n5 + ";" + m4.group(1);
            }

            while (m5.find()){
                String t = m5.group(2);
                t = t.replace("%", "");
                t = t.replace("&plusmn;", "");
                n6 = n6 + ";" + t;
            }

            name = n1.split(";");
            isin = n2.split(";");
            price2 = n3.split(";");
            branche = n5.split(";");
            percent2 = n6.split(";");
        }
        catch(IOException e){

        }
    }

    public void searchIndex(String Index){
        try{
            FileReader fr = new FileReader("Stock/IndexURL/" + Index + ".html");
            BufferedReader br = new BufferedReader(fr);

            String zeile = "";
            String s = "";

            while( (zeile = br.readLine()) != null )
            {
                s = s + zeile;
            }
        }
        catch(IOException e){

        }
    }


    public String[] getname(){
        return name;
    }

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

    public String getNotation(){
        return Notation;
    }

    public double getPrice(){
        return price;
    }

    public double getPercent(){
        return percent;
    }

    public String getName(){
        return Name;
    }
}
