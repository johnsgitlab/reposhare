package com.jpw.raptor.jdbc.fund;

import com.jpw.raptor.jdbc.etf.EtfDAO;
import com.jpw.raptor.model.Quote;
import com.jpw.raptor.jdbc.quote.QuoteDAO;
import com.jpw.raptor.model.Fund;
import org.junit.*;
import org.postgresql.ds.PGSimpleDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.testcontainers.containers.PostgreSQLContainer;

import java.io.File;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by john on 2/21/18.
 */
public class FundDAOTest {

    FundDAO     tbl;
    List<Fund>   l;


    private String     symbol;
    private String     name;
    private String     assetClass;
    private String     fundType;
    private String     fundSubType;
    private String     factor;
    private String     category;
    private String     tracks;
    private Date       date;
    private Date       lastUpdate;
    private String     family;

    private String     status;
    private double     minimum;
    private double     frontLoad;
    private double     backLoad;

    private Date       inception;
    private double     marketCap;
    private double     expenseRatio;
    private double     dividendYield;

    private String     morningRating;
    private int        morningStars;
    private int        lipperTotal;
    private int        lipperConsistent;
    private int        lipperPreservation;
    private int        lipperTax;
    private int        lipperExpense;

    private double     ytd;
    private double     oneDay;
    private double     oneWeek;
    private double     twoWeeks;
    private double     fourWeeks;
    private double     threeMonths;
    private double     oneYear;
    private double     threeYears;

    private double     basicMaterials;
    private double     consumerCyclical;
    private double     financialServices;
    private double     realestate;
    private double     consumerDefensive;
    private double     healthcare;
    private double     utilities;
    private double     communicationServices;
    private double     energy;
    private double     industrials;
    private double     technology;

    private double     alpha;
    private double     beta;
    private double     meanAnnualReturn;
    private double     rSquared;
    private double     deviation;
    private double     sharpeRatio;
    private double     treynorRatio;

    private double     pe;
    private double     pb;
    private double     ps;
    private double     pc;
    private double     earningsGrowth;
    private double     medianMarketCap;

    private double     bondMaturity;
    private double     bondDuration;
    private double     bondCreditQuality;
    private double     bondAaaPercent;
    private double     bondAaPercent;
    private double     bondAPercent;
    private double     bondBbbPercent;
    private double     bondBbPercent;
    private double     bondBPercent;
    private double     bondBelowbPercent;
    private double     bondUsPercent;
    private double     bondOtherPercent;

    private double     bondPosition;
    private double     cashPosition;
    private double     convertiblePosition;
    private double     otherPosition;
    private double     preferredPosition;
    private double     stockPosition;

    private String     topHoldings;
    private String     overview;

    public void init() {

        // Provide default values
        symbol = "testit";
        name = "name";
        assetClass = "assetClass";
        fundType = "fundType";
        fundSubType = "fundSubType";
        factor = "factor";
        category = "category";
        tracks = "tracks";
        date = new Date(-1);
        lastUpdate = new Date(-2);
        family = "family";

        status = "status";
        minimum = 1.0;
        frontLoad = 2.0;
        backLoad = 3.0;
        inception = new Date(-3);
        marketCap = 4.0;
        expenseRatio = 5.0;
        dividendYield = 6.0;

        morningRating = "morningRating";
        morningStars = 7;
        lipperTotal = 8;
        lipperConsistent = 9;
        lipperPreservation = 10;
        lipperTax = 11;
        lipperExpense = 12;

        ytd = 13.0;
        oneDay = 14.0;
        oneWeek = 15.0;
        twoWeeks = 16.0;
        fourWeeks = 17.0;
        threeMonths = 18.0;
        oneYear = 19.0;
        threeYears = 20.0;

        basicMaterials = 210.0;
        consumerCyclical = 220.0;
        financialServices = 230.0;
        realestate = 240.0;
        consumerDefensive = 250.0;
        healthcare = 260.0;
        utilities = 270.0;
        communicationServices = 280.0;
        energy = 290.0;
        industrials = 300.0;
        technology = 310.0;

        alpha = 320.0;
        beta = 330.0;
        meanAnnualReturn = 340.0;
        rSquared = 350.0;
        deviation = 360.0;
        sharpeRatio = 370.0;
        treynorRatio = 380.0;

        pe = 390.0;
        pb = 400.0;
        ps = 410.0;
        pc = 420.0;
        earningsGrowth = 430.0;
        medianMarketCap = 440.0;

        bondMaturity = 450.0;
        bondDuration = 460.0;
        bondCreditQuality = 470.0;
        bondAaaPercent = 480.0;
        bondAaPercent = 490.0;
        bondAPercent = 500.0;
        bondBbbPercent = 510.0;
        bondBbPercent = 520.0;
        bondBPercent = 530.0;
        bondBelowbPercent = 540.0;
        bondUsPercent = 550.0;
        bondOtherPercent = 560.0;

        bondPosition = 570.0;
        cashPosition = 580.0;
        convertiblePosition = 590.0;
        otherPosition = 600.0;
        preferredPosition = 610.0;
        stockPosition = 620.0;

        topHoldings = "top_holdings";
        overview = "overview";
    }


    public void setValues(Fund rec) {
        rec.setSymbol(symbol);
        rec.setName(name);
        rec.setAssetClass(assetClass);
        rec.setFundType(fundType);
        rec.setFundSubType(fundSubType);
        rec.setFactor(factor);
        rec.setCategory(category);
        rec.setTracks(tracks);
        rec.setDate(date);
        rec.setLastUpdate(lastUpdate);
        rec.setFamily(family);

        rec.setStatus(status);
        rec.setMinimum(minimum);
        rec.setFrontLoad(frontLoad);
        rec.setBackLoad(backLoad);

        rec.setInception(inception);
        rec.setMarketCap(marketCap);
        rec.setExpenseRatio(expenseRatio);
        rec.setDividendYield(dividendYield);

        rec.setMorningRating(morningRating);
        rec.setMorningStars(morningStars);
        rec.setLipperTotal(lipperTotal);
        rec.setLipperConsistent(lipperConsistent);
        rec.setLipperPreservation(lipperPreservation);
        rec.setLipperTax(lipperTax);
        rec.setLipperExpense(lipperExpense);

        rec.setYtd(ytd);
        rec.setOneDay(oneDay);
        rec.setOneWeek(oneWeek);
        rec.setTwoWeeks(twoWeeks);
        rec.setFourWeeks(fourWeeks);
        rec.setThreeMonths(threeMonths);
        rec.setOneYear(oneYear);
        rec.setThreeYears(threeYears);

        rec.setBasicMaterials(basicMaterials);
        rec.setConsumerCyclical(consumerCyclical);
        rec.setFinancialServices(financialServices);
        rec.setRealestate(realestate);
        rec.setConsumerDefensive(consumerDefensive);
        rec.setHealthcare(healthcare);
        rec.setUtilities(utilities);
        rec.setCommunicationServices(communicationServices);
        rec.setEnergy(energy);
        rec.setIndustrials(industrials);
        rec.setTechnology(technology);

        rec.setAlpha(alpha);
        rec.setBeta(beta);
        rec.setMeanAnnualReturn(meanAnnualReturn);
        rec.setRSquared(rSquared);
        rec.setDeviation(deviation);
        rec.setSharpeRatio(sharpeRatio);
        rec.setTreynorRatio(treynorRatio);

        rec.setPe(pe);
        rec.setPb(pb);
        rec.setPs(ps);
        rec.setPc(pc);
        rec.setEarningsGrowth(earningsGrowth);
        rec.setMedianMarketCap(medianMarketCap);

        rec.setBondMaturity(bondMaturity);
        rec.setBondDuration(bondDuration);
        rec.setBondCreditQuality(bondCreditQuality);
        rec.setBondAaaPercent(bondAaaPercent);
        rec.setBondAaPercent(bondAaPercent);
        rec.setBondAPercent(bondAPercent);
        rec.setBondBbbPercent(bondBbbPercent);
        rec.setBondBbPercent(bondBbPercent);
        rec.setBondBPercent(bondBPercent);
        rec.setBondBelowbPercent(bondBelowbPercent);
        rec.setBondUsPercent(bondUsPercent);
        rec.setBondOtherPercent(bondOtherPercent);

        rec.setBondPosition(bondPosition);
        rec.setCashPosition(cashPosition);
        rec.setConvertiblePosition(convertiblePosition);
        rec.setOtherPosition(otherPosition);
        rec.setPreferredPosition(preferredPosition);
        rec.setStockPosition(stockPosition);

        rec.setTopHoldings(topHoldings);
        rec.setOverview(overview);
    }


    public void validateValues(Fund rec) {

        assertTrue(rec.getSymbol().equalsIgnoreCase(symbol));
        assertTrue(rec.getName().equalsIgnoreCase(name));
        assertTrue(rec.getAssetClass().equalsIgnoreCase(assetClass));
        assertTrue(rec.getFundType().equalsIgnoreCase(fundType));
        assertTrue(rec.getFundSubType().equalsIgnoreCase(fundSubType));
        assertTrue(rec.getFactor().equalsIgnoreCase(factor));
        assertTrue(rec.getCategory().equalsIgnoreCase(category));
        assertTrue(rec.getTracks().equalsIgnoreCase(tracks));
//        assertTrue(rec.getDate().equals(date));
//        assertTrue(rec.getLastUpdate().equals(lastUpdate));
        assertTrue(rec.getFamily().equalsIgnoreCase(family));

        assertTrue(rec.getStatus().equalsIgnoreCase(status));
        assertEquals(rec.getMinimum (),minimum ,0.0001);
        assertEquals(rec.getFrontLoad(),frontLoad,0.0001);
        assertEquals(rec.getBackLoad(),backLoad,0.0001);

//        assertTrue(rec.getInception().equals(inception));
        assertEquals(rec.getMarketCap(),marketCap,0.0001);
        assertEquals(rec.getExpenseRatio(),expenseRatio,0.0001);
        assertEquals(rec.getDividendYield(),dividendYield,0.0001);

        assertTrue(rec.getMorningRating().equalsIgnoreCase(morningRating));
        assertEquals(rec.getMorningStars(),morningStars);

        assertEquals(rec.getLipperTotal(),lipperTotal);
        assertEquals(rec.getLipperConsistent(),lipperConsistent);
        assertEquals(rec.getLipperPreservation(),lipperPreservation);
        assertEquals(rec.getLipperTax(),lipperTax);
        assertEquals(rec.getLipperExpense(),lipperExpense);

        assertEquals(rec.getYtd(),ytd,0.0001);
        assertEquals(rec.getOneDay(),oneDay,0.0001);
        assertEquals(rec.getOneWeek(),oneWeek,0.0001);
        assertEquals(rec.getTwoWeeks(),twoWeeks,0.0001);
        assertEquals(rec.getFourWeeks(),fourWeeks,0.0001);
        assertEquals(rec.getThreeMonths(),threeMonths,0.0001);
        assertEquals(rec.getOneYear(),oneYear,0.0001);
        assertEquals(rec.getThreeYears(),threeYears,0.0001);

        assertEquals(rec.getBasicMaterials(),basicMaterials,0.0001);
        assertEquals(rec.getConsumerCyclical(),consumerCyclical,0.0001);
        assertEquals(rec.getFinancialServices(),financialServices,0.0001);
        assertEquals(rec.getRealestate(),realestate,0.0001);
        assertEquals(rec.getConsumerDefensive(),consumerDefensive,0.0001);
        assertEquals(rec.getHealthcare(),healthcare,0.0001);
        assertEquals(rec.getUtilities(),utilities,0.0001);
        assertEquals(rec.getCommunicationServices(),communicationServices,0.0001);
        assertEquals(rec.getEnergy(),energy,0.0001);
        assertEquals(rec.getIndustrials(),industrials,0.0001);
        assertEquals(rec.getTechnology(),technology,0.0001);

        assertEquals(rec.getAlpha(),alpha,0.0001);
        assertEquals(rec.getBeta(),beta,0.0001);
        assertEquals(rec.getMeanAnnualReturn(),meanAnnualReturn,0.0001);
        assertEquals(rec.getRSquared(), rSquared,0.0001);
        assertEquals(rec.getDeviation(),deviation,0.0001);
        assertEquals(rec.getSharpeRatio(),sharpeRatio,0.0001);
        assertEquals(rec.getTreynorRatio(),treynorRatio,0.0001);

        assertEquals(rec.getPe(),pe,0.0001);
        assertEquals(rec.getPb(),pb,0.0001);
        assertEquals(rec.getPs(),ps,0.0001);
        assertEquals(rec.getPc(),pc,0.0001);
        assertEquals(rec.getEarningsGrowth(),earningsGrowth,0.0001);
        assertEquals(rec.getMedianMarketCap(),medianMarketCap,0.0001);

        assertEquals(rec.getBondMaturity(),bondMaturity,0.0001);
        assertEquals(rec.getBondDuration(),bondDuration,0.0001);
        assertEquals(rec.getBondCreditQuality(),bondCreditQuality,0.0001);
        assertEquals(rec.getBondAaaPercent(),bondAaaPercent,0.0001);
        assertEquals(rec.getBondAaPercent(),bondAaPercent,0.0001);
        assertEquals(rec.getBondAPercent(),bondAPercent,0.0001);
        assertEquals(rec.getBondBbbPercent(),bondBbbPercent,0.0001);
        assertEquals(rec.getBondBbPercent(),bondBbPercent,0.0001);
        assertEquals(rec.getBondBPercent(),bondBPercent,0.0001);
        assertEquals(rec.getBondBelowbPercent(),bondBelowbPercent,0.0001);
        assertEquals(rec.getBondUsPercent(),bondUsPercent,0.0001);
        assertEquals(rec.getBondOtherPercent(),bondOtherPercent,0.0001);

        assertEquals(rec.getBondPosition(),bondPosition,0.0001);
        assertEquals(rec.getCashPosition(),cashPosition,0.0001);
        assertEquals(rec.getConvertiblePosition(),convertiblePosition,0.0001);
        assertEquals(rec.getOtherPosition(),otherPosition,0.0001);
        assertEquals(rec.getPreferredPosition(),preferredPosition,0.0001);
        assertEquals(rec.getStockPosition(),stockPosition,0.0001);

        assertTrue(rec.getTopHoldings().equalsIgnoreCase(topHoldings));
        assertTrue(rec.getOverview().equalsIgnoreCase(overview));
    }

    static class PostgresResource  {

        // postgres:9.6.8

        PostgreSQLContainer postgreSQLContainer;
        PGSimpleDataSource dataSource;

        public PostgresResource() {
            postgreSQLContainer = new PostgreSQLContainer("postgres:10.11")
                     .withDatabaseName("test-jdbc")
                    .withUsername("sa")
                    .withPassword("sa");

            postgreSQLContainer.start();

            dataSource = new PGSimpleDataSource();
            dataSource.setDatabaseName(postgreSQLContainer.getDatabaseName());
            dataSource.setServerName(postgreSQLContainer.getContainerIpAddress());
            dataSource.setPortNumber(postgreSQLContainer.getMappedPort(5432));
            dataSource.setUser(postgreSQLContainer.getUsername());
            dataSource.setPassword(postgreSQLContainer.getPassword());

        }

        public void stop() { postgreSQLContainer.stop(); }

        public PGSimpleDataSource getDataSource () {return dataSource;}

    }

    private static PostgresResource myPostgresResource;

    @BeforeClass
    public static void setUp() throws java.text.ParseException {


        myPostgresResource = new PostgresResource();
    }

    @Before
    public void setUpTest() {
        init();
    }

    @AfterClass
    public static void tearDown(){
        myPostgresResource.stop();
    }


    public Fund ca () {
        Fund rec = new Fund();
        rec.setSymbol("a");
        rec.setTracks("");
        rec.setAssetClass("assetclass");
        return rec;
    }

    public void va (Fund rec) {
        assertTrue(rec.getSymbol().equalsIgnoreCase("a"));
        assertTrue(rec.getSymbol().equalsIgnoreCase(""));
    }

    public Fund cb () {
        Fund rec = new Fund();
        rec.setSymbol("b");
        rec.setTracks("own");
        rec.setAssetClass("assetclass");
        rec.setFundType("fundtype");
        return rec;
    }

    public void vb (Fund rec) {
        assertTrue(rec.getSymbol().equalsIgnoreCase("b"));
        assertTrue(rec.getSymbol().equalsIgnoreCase("own"));
    }

    public Fund cc () {
        Fund rec = new Fund();
        rec.setSymbol("c");
        rec.setTracks("tracked");
        rec.setAssetClass("assetclass");
        rec.setFundType("fundtype");
        rec.setFundSubType("fundsubtype");
        return rec;
    }

    public void vc (Fund rec) {
        assertTrue(rec.getSymbol().equalsIgnoreCase("c"));
        assertTrue(rec.getSymbol().equalsIgnoreCase("tracked"));
    }

    public Fund cd () {
        Fund rec = new Fund();
        rec.setSymbol("d");
        rec.setTracks("relevant");
        rec.setAssetClass("assetclass");
        rec.setFundType("fundtype");
        rec.setFundSubType("fundsubtype");
        rec.setFactor("factor");
        return rec;
    }

    public void vd (Fund rec) {
        assertTrue(rec.getSymbol().equalsIgnoreCase("d"));
        assertTrue(rec.getSymbol().equalsIgnoreCase("relevant"));
    }

    @Test
    public void test00() throws Exception {

        System.out.println("Start");

        // Create connection to docker database
        tbl = new FundDAO();
        tbl.setDataSource(myPostgresResource.getDataSource());
        tbl.postConstruct();

        // Create table for testing
        File resource;
        String cmds;

        resource = new ClassPathResource("schema.sql").getFile();
        cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        resource = new ClassPathResource("data.sql").getFile();
        cmds = new String(Files.readAllBytes(resource.toPath()));
        tbl.sqlScript(cmds);

        // Write dummy record
        assertEquals(1, tbl.addEmpty("testit"));

        // read the dummy record
        Fund wr = tbl.get("testit");

        // update the record
        setValues(wr);

        // write Updated record
        assertEquals(1,tbl.update(wr));

        // read the updated record
        Fund rr = tbl.get(wr.getSymbol());

        // validate the record
        validateValues(rr);

        // delete the record
        assertEquals(1, tbl.delete(rr.getSymbol()));

        // test tracking
        tbl.addEmpty("a");
        tbl.update(ca());
        tbl.addEmpty("b");
        tbl.update(cb());
        tbl.addEmpty("c");
        tbl.update(cc());
        tbl.addEmpty("d");
        tbl.update(cd());

        // get all
        l = tbl.getAll();
        assertEquals(4, l.size());

        // get owned
        l = tbl.getOwned();
        assertEquals(1, l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase("b"));

        // get tracked
        l = tbl.getTracked();
        assertEquals(2, l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase("b"));
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase("c"));

        // get relevant
        l = tbl.getRelevant();
        assertEquals(3, l.size());
        assertTrue(l.get(0).getSymbol().equalsIgnoreCase("b"));
        assertTrue(l.get(1).getSymbol().equalsIgnoreCase("c"));
        assertTrue(l.get(2).getSymbol().equalsIgnoreCase("d"));

        // get by asset class
        l = tbl.getByAssetClass("assetclass");
        assertEquals(4, l.size());

        // get by asset class, fundtype
        l = tbl.getByAssetClassFundType("assetclass", "fundtype");
        assertEquals(3, l.size());

        // get by asset class, fundtype, fundsubtype
        l = tbl.getByAssetClassFundTypeSubType("assetclass", "fundtype", "fundsubtype");
        assertEquals(2, l.size());

        // get by asset class, fundtype, fundsubtype, factor
        l = tbl.getByAssetClassFundTypeSubTypeFactor("assetclass", "fundtype", "fundsubtype", "factor");
        assertEquals(1, l.size());

        // get by asset class, fundtype, fundsubtype, factor
        l = tbl.getFactors();
        for ( Fund e : l ) {
            System.out.println(e.getSymbol() + " " + e.getFactor());
        }
        //assertEquals(1, l.size());

        // get by asset class, fundtype, fundsubtype, factor

        //assertEquals(1, l.size());

        // get by asset class
        l = tbl.getFactorsByAssetClass("assetclass");
        //assertEquals(1, l.size());

        /*
        // get relevant
        List<String> ls = tbl.getScrapeSymbols("relevant", null);
        //assertEquals(3, l.size());
        assertTrue(ls.get(0).equalsIgnoreCase("b"));
        assertTrue(ls.get(1).equalsIgnoreCase("c"));
        assertTrue(ls.get(2).equalsIgnoreCase("d"));

        // valid fund
        assertTrue(tbl.validFund("b"));

        // get single
        List<String> ls2 = tbl.getScrapeSymbols(null, "c");
        //assertEquals(3, l.size());
        assertTrue(ls2.get(0).equalsIgnoreCase("c"));
        */
    }
}
