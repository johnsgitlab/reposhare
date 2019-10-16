package com.jpw.raptor.algorithm;

import com.jpw.raptor.model.Adl;
import com.jpw.raptor.model.Obv;
import com.jpw.raptor.model.Quote;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AdvanceDeclineLineTest {

    @Test
    public void test01() throws ParseException {

        SimpleDateFormat fmt = new SimpleDateFormat("yyyy-MM-dd");

        Quote[] dataArray = {
                new Quote("ADDN", fmt.parse("2019-01-09"), 0, 0, 0, 539, 0),
                new Quote("ADDN", fmt.parse("2019-01-08"), 0, 0, 0, -1186, 0),
                new Quote("ADDN", fmt.parse("2019-01-07"), 0, 0, 0, 602, 0),
                new Quote("ADDN", fmt.parse("2019-01-04"), 0, 0, 0, -23, 0),
                new Quote("ADDN", fmt.parse("2019-01-03"), 0, 0, 0, 1757, 0),
                new Quote("ADDN", fmt.parse("2019-01-02"), 0, 0, 0, 960, 0),
                new Quote("ADDN", fmt.parse("2018-12-31"), 0, 0, 0, -100, 0),
                new Quote("ADDN", fmt.parse("2018-12-28"), 0, 0, 0, 393, 0)
        };

        List<Quote> data = Arrays.asList( dataArray );

        long [] adlValue = {2942, 2403, 3589, 2987, 3010, 1253, 293, 393};

        long [] adlSma = {2978, 2993, 3195, 2416, 1518, 646, 343, 393};

        AdvanceDeclineLine obv = new AdvanceDeclineLine();
        List<Adl> list = obv.generateAdl(data, 3);

        for ( int i=0; i<list.size(); i++) {
            assertEquals(adlValue[i], list.get(i).getVal());
            assertEquals(adlSma[i], list.get(i).getSma());
        }

    }

}
