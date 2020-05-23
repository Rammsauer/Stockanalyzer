package sample;

public class Database {

    private String[] TecDAX = {"1&1 Drillisch", "Aixtron", "Bechtle AG", "Cancom", "Carl Zeiss", "CompuGroup", "Deutsche Telekom", "Dialog Semiconducter",
            "Drägerwerk Vz", "Evotec", "Freenet", "Infeon","Isra Vision", "Jenoptik","Morphosys", "Nemetschek", "Nordex", "Pfeiffer Vacuum",
            "Qiagen", "RIB Software", "S&T", "SAP", "Sartorius VZ", "Siemens Healthineers", "Siltronic", "Software AG", "Telefonica Deutschland Holding",
            "United Internet", "Wirecard", "Xing"};
    private String[] TecDaxisin = {"DE0005545503", "DE000A0WMPJ6", "DE0005158703", "DE0005419105", "DE0005313704", "DE0005437305", "DE0005557508", "GB0059822006",
            "DE0005550636", "DE0005664809", "DE000A0Z2ZZ5", "DE0006231004", "DE0005488100", "DE000A2NB601", "DE0006632003", "DE0006452907",
            "DE000A0D6554", "DE0006916604", "NL0012169213", "DE000A0Z2XN6", "AT0000A0E9W5", "DE0007164600", "DE0007165631",
            "DE000SHL1006", "DE000WAF3001", "DE000A2GS401", "DE000A1J5RX9", " DE0005089031", "DE0007472060", "DE000XNG8888"};

    private String[] Dax = {"Adidas", "Allianz", "BASF", "Bayer", "Beiersdorf", "BMW", "Continental", "COVESTRO AG O.N.", "Daimler", "Deutsche Bank", "Deutsche Boerse", "Deutsche Lufthansa",
            "Deutsche Post", "Deutsche Telekom", "E.ON", "Fresenius", "FRESENIUS MED. CARE", "HeidelbergCement AG", "HENKEL Vz", "Infineon Technologies", "LINDE PLC EO 0,001",
            "Merck Kgaa", "Münchener Rück", "RWE", "SAP SE", "Siemens", "Thyssenkrupp", "Volkswagen VZ", "Vonovia SE", "Wirecard"};
    private String[] Daxisin = {"DE000A1EWWW0", "DE0008404005", "DE000BASF111", "DE000BAY0017", "DE0005200000", "DE0005190003", "DE0005439004", "DE0006062144", "DE0007100000", "DE0005140008",
            "DE0005810055", "DE0008232125", "DE0005552004", "DE0005557508", "DE000ENAG999", "DE0005785604", "DE0005785802", "DE0006047004", "DE0006048432", "DE0006231004", "IE00BZ12WP82",
            "DE0006599905", "DE0008430026", " DE0007037129", "DE0007164600", "DE0007236101", "DE0007500001", "DE0007664039", "DE000A1ML7J1", "DE0007472060"};

    private String[] Nasdaq = {"Activision Blizzard", "Adobe", "Alexion Pharmaceuticals", "Align Technology", "Alphabet A", "Alphabet C", "Amazon", "AMD", "American Airlines", "Amgen",
            "Analog Devices", "Apple", "Applied Materials", "ASML", "Autodesk", "Automatic Data Processing", "Baidu.com", "Biogen", "Biomarin Pharmaceutical", "Broadom", "Cadance Design Systems",
            "Celgene", "Cerner", "Charter A", "Check Point Software", "Cintas", "Cisco", "Citrix Systems", "Cognizant", "Comcast", "Costco Wholesale", "CSX", "Ctrip.com", "Dentsply Sirona", "Dish Network",
            "Dollar Tree", "eBay", "Electronic Arts", "Expedia", "Facebook", "Fastenal", "Fiserv", "Fox", "Gilead Sciences", "Hasbro", "Henry Schein", "Hologic", "IDEXX Laboratories", "Illumina", "Incyte",
            "Intel", "Intuit", "Intuitive Surgical", "J.B. Hunt Transportation Services", "JD.com", "KLA-Tencor", "Lam Research", "Liberty Global", "Liberty Global A", "Liberty Interactive A",
            "Marriott", "Maxim Integrated Products", "MercadoLibre", "Microchip Technology", "Micron Technology", "Microsoft", "Mondelez", "Monster Beverage", "Mylan", "Netease", "Netflix",
            "NVIDIA", "O Reilly Automotive", "Paccar", "Paychex", "PayPal", "Qualcomm", "Regeneron Pharmaceuticals", "Ross Stores", "Seagate", "Shire", "Sirius XM", "Skyworks Solutions", "Starbucks",
            "Symantec", "Synopsys", "Take Two", "Tesla", "Texas Instruments", "The Kraft Heinz Company", "T-Mobile US", "Ulta Beauty", "Verisk Analytics", "Vertex Pharmaceuticals", "Vodafone Group",
            "Walgreens Boots Alliance", "Western Digital", "Workday A", "Wynn Resorts", "Xilinx"};
    private String[] Nasdaqisin = {"US00507V1098", "US00724F1012", "US0153511094", "US0162551016", "US02079K3059", "US02079K1079", "US0231351067", "US0079031078", "US02376R1023", "US0311621009", "US0326541051",
            "US0378331005", "US0382221051", "USN070592100", "US0527691069", "US0530151036", "US0567521085", "US09062X1037", "US09061G1013", "US11135F1012", "US1273871087", "US1510201049", "US1567821046",
            "US16119P1084", "IL0010824113", "US1729081059", "US17275R1023", "US1773761002", "US1924461023", "US20030N1019", "US22160K1051", "US1264081035", "US22943F1003", "US24906P1093", "US25470M1099",
            "US2567461080", "US2786421030", "US2855121099", "US30212P3038", "US30303M1027", "US3119001044", "US3377381088", "US35137L1052", "US3755581036", "US4180561072", "US8064071025", "US4364401012",
            "US45168D1046", "US4523271090", "US45337C1027", "US4581401001", "US4612021034", "US46120E6023", "US4456581077", "US47215P1066", "US4824801009", "US5128071082", "GB00B8W67B19", "GB00B8W67662",
            "US74915M1009", "US5719032022", "US57772K1016", "US58733R1023", "US5950171042", "US5951121038", "US5949181045", "US6092071058", "US61174X1090", "NL0011031208", "US64110W1027", "US64110L1061",
            "US67066G1040", "US67103H1077", "US6937181088", "US7043261079", "US70450Y1038", "US7475251036", "US75886F1075", "US7782961038", "IE00B58JVZ52", "US82481R1068", "US82968B1035", "US83088M1027",
            "US8552441094", "US8715031089", "US8716071076", "US8740541094", "US88160R1014", "US8825081040", "US5007541064", "US8725901040", "US90384S3031", "US92345Y1064", "US92532F1003", "US92857W3088",
            "US9314271084", "US9581021055", "US98138H1014", "US9831341071", "US9839191015"};

    private String[] Dow = {"3M", "American Express", "Apple", "Boeing", "Caterpillar", "Chevron", "Cisco", "Coca-Cola", "DowDuPont", "ExxonMobil", "Goldman Sachs", "HomeDepot", "IBM", "Intel", "Johnson Johnson",
            "JPMorgan Chase", "McDonalds", "Merck", "Microsoft", "Nike", "Pfizer", "Procter Gamble", "Travelers", "United Technologies", "UnitedHealth", "Verizon", "Visa", "Walgreens Boots Alliance", "Walmart",
            "Walt Disney"};
    private String[] Dowisin = {"US88579Y1010", "US0258161092", "US0378331005", "US0970231058", "US1491231015", "US1667641005", "US17275R1023", "US1912161007", "US26078J1007", "US30231G1022", "US38141G1040",
            "US4370761029", "US4592001014", "US4581401001", "US4781601046", "US46625H1005", "US5801351017", "US58933Y1055", "US5949181045", "US6541061031", "US7170811035", "US7427181091", "US89417E1091",
            "US9130171096", "US91324P1021", "US92343V1044", "US92826C8394", "US9314271084", "US9311421039", "US2546871060"};

    private String[] SDAX = {"1&1 Drillisch", "ADO Properties", "ADVA", "AIXTRON", "Amadeus FiRe", "BayWa", "Befesa", "Bertrandt", "Bilfinger", "Borussia Dortmund", "Ceconomy St.", "CEWE Stiftung",
            "Corestate Capital", "Dermapharm", "Deutsche Beteiligungs", "Deutsche Euroshop", "DEUTZ", "DIC Asset", "DMG MORI", "Drägerwerk", "DWS Group", "Ziegler Medizintechnik",
            "Elmos Semiconductor", "ENCAVIS", "Fielmann", "HAMBORNER REIT", "HHLA", "HORNBACH", "Hypoport", "INDUS", "Instone Real Estate", "JENOPTIK", "JOST Werke", "Jungheinrich", "Klöckner",
            "Koenig Bauer", "KRONES", "KWS SAAT", "LEONI", "LPKF Laser Electronics", "New Work", "Nordex", "NORMA Group", "PATRIZIA Immobilien", "Pfeiffer Vacuum", "RHÖN-KLINIKUM", "S&T", "SAF-Holland SA",
            "Salzgitter", "Schaeffler", "Shop Apotheke Europe", "Sixt", "Sixt Leasing", "SMA Solar", "SNP", "Stabilus", "Steinhoff", "STRATEC", "Ströer", "Südzucker", "TAKKT",
            "Talanx", "TRATON", "Vossloh", "WACKER CHEMIE", "Wacker Neuson", "WashTec", "Wüstenrot", "ZEAL Network", "zooplus"};
    private String[] SDaxisin = {"DE0005545503", "LU1250154413", "DE0005103006", "DE000A0WMPJ6", "DE0005093108", "DE0005194062", "LU1704650164", "DE0005232805", "DE0005909006", "DE0005493092", "DE0007257503",
            "DE0005403901", "LU1296758029", "DE000A2GS5D8", "DE000A1TNUT7", "DE0007480204", "DE0006305006", "DE000A1X3XX4", "DE0005878003", "DE0005550636", "DE000DWS1007", "DE0005659700", "DE0005677108",
            "DE0006095003", "DE0005772206", "DE0006013006", "DE000A0S8488", "DE0006083405", "DE0005493365", "DE0006200108", "DE000A2NBX80", "DE000A2NB601", "DE000JST4000", "DE0006219934", "DE000KC01000",
            "DE0007193500", "DE0006335003", "DE0007074007", "DE0005408884", "DE0006450000", "DE000NWRK013", "DE000A0D6554", "DE000A1H8BV3", "DE000PAT1AG3", "DE0006916604", "DE0007042301", "AT0000A0E9W5",
            "LU0307018795", "DE0006202005", "DE000SHA0159", "NL0012044747", "DE0007231326", "DE000A0DPRE6", "DE000A0DJ6J9", "DE0007203705", "LU1066226637", "NL0011375019", "DE000STRA555", "DE0007493991",
            "DE0007297004", "DE0007446007", "DE000TLX1005", "DE000TRAT0N7", "DE0007667107", "DE000WCH8881", "DE000WACK012", "DE0007507501", "DE0008051004", "DE000ZEAL241", "DE0005111702"};

    private String[] MDAX = {""};
    private String[] MDaxisin = {""};

    private String[] EuroStoxx50 = {"AB InBev SA-NV", "Adidas", "Ahold Delhaize", "Air Liquide", "Airbus", "Allianz", "Amadeus IT", "ASML NV", "AXA", "Basf", "Bayer", "BBVA", "BMW", "BNP Paribas", "CRH", "Daimler",
            "Danone", "Deutsche Post", "Deutsche Telekom", "Enel", "Engie", "Eni", "Essilor", "Fresenius", "Iberdrola SA", "Inditex", "ING Group", "Intesa Sanpaolo", "Kering", "Linde", "LOréal", "LVMH Moet Hennessy",
            "Münchener Rück", "Nokia", "Orange", "Philips", "Safran", "Sanofi", "Santander", "SAP", "Schneider Electric", "Siemens", "Société Générale", "Telefonica", "Total", "Unibail-Rodamco", "Unilever", "Vinci",
            "Vivendi", "Volkswagen VZ"};
    private String[] EuroStoxx50isin = {"BE0974293251", "DE000A1EWWW0", "NL0011794037", "FR0000120073", "NL0000235190", "DE0008404005", "ES0109067019", "NL0010273215", "FR0000120628", "DE000BASF111", "DE000BAY0017",
            "ES0113211835", "DE0005190003", "FR0000131104", "IE0001827041", "DE0007100000", "FR0000120644", "DE0005552004", "DE0005557508", "IT0003128367", "FR0010208488", "IT0003132476", "FR0000121667",
            "DE0005785604", "ES0144580Y14", "ES0148396007", "NL0011821202", "IT0000072618", "FR0000121485", "IE00BZ12WP82", "FR0000120321", "FR0000121014", "DE0008430026", "FI0009000681", "FR0000133308",
            "NL0000009538", "FR0000073272", "FR0000120578", "ES0113900J37", "DE0007164600", "FR0000121972", "DE0007236101", "FR0000130809", "ES0178430E18", "FR0000120271", "FR0013326246", "NL0000009355",
            "FR0000125486", "FR0000127771", "DE0007664039"};

    private String[] IName = {"dax-index-DE0008469008", "DE0008467416", "DE0007203275", "DE0009653386", "dow-jones-US2605661048", "", "EU0009658145"};

    private String[] SName = {"Dax", "MDAX", "TexDax", "SDax", "Dow", "Nasdaq", "EuroStoxx50"};

    public Database(){

    }

    public String[] getKName(){ return IName; }

    public String[] getSName(){ return SName; }

    public String[] getDax(){
        return Dax;
    }

    public String[] getDaxisin(){
        return Daxisin;
    }

    public String[] getTecDAX(){
        return TecDAX;
    }

    public String[] getTecDaxisin(){
        return TecDaxisin;
    }

    public String[] getNasdaq(){
        return Nasdaq;
    }

    public String[] getNasdaqisin(){
        return Nasdaqisin;
    }

    public String[] getMDAX(){
        return MDAX;
    }

    public String[] getMDaxisin(){
        return MDaxisin;
    }

    public String[] getDow(){
        return Dow;
    }

    public String[] getDowisin(){
        return Dowisin;
    }

    public String[] getSDAX(){
        return SDAX;
    }

    public String[] getSDaxisin(){
        return SDaxisin;
    }

    public String[] getEuroStoxx50(){
        return EuroStoxx50;
    }

    public String[] getEuroStoxx50isin(){
        return EuroStoxx50isin;
    }
}
