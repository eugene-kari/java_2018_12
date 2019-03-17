package ru.otus.l071;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ATM {

    private Map<Banknote, CashBox> boxes = new HashMap<>();
    private Map<Currency, List<CashBox>> boxGroups = new HashMap<>();

    public ATM() {
        for (Banknote banknote : Banknote.values()) {
            CashBox cashBox = new CashBox(banknote);
            boxes.put(banknote, cashBox);
            Currency currency = banknote.getCurrency();
            List<CashBox> boxGroup = boxGroups.computeIfAbsent(currency, k -> new ArrayList<>());
            boxGroup.add(cashBox);
        }
    }

    public void cashIn(Banknote banknote, int count) {
        boxes.get(banknote).push(count);
    }

    public Map<Banknote, Integer> cashOut(Currency currency, int amount) {
        Map<Banknote, Integer> result = new HashMap<>();
        List<CashBox> boxGroup = boxGroups.get(currency);


    }
//
//        public void Balance {
//        }



}
