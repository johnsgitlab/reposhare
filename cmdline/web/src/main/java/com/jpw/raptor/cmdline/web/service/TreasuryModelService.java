package com.jpw.raptor.cmdline.web.service;

import com.jpw.raptor.model.Treasury;
import com.jpw.raptor.model.TreasuryModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TreasuryModelService {

    public List<TreasuryModel> getTreasuryModel(List<Treasury> recs) {

        ArrayList<TreasuryModel> result = new ArrayList<>(recs.size());

        for ( Treasury rec : recs ) {
            TreasuryModel tm = new TreasuryModel(rec);
            result.add(tm);
        }

        return result;
    }
}
