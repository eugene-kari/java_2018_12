package ru.otus.l071;

import java.util.*;

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

        CashBox[] sortedBoxes = boxGroup
                .stream()
                .filter(b -> b.getCount() > 0 && b.getBanknote().getValue() <= amount)
                .sorted(Comparator.comparingInt(b -> -b.getBanknote().getValue()))
                .toArray(CashBox[]::new);

        if (sortedBoxes.length == 0) return result;

        for (int start = 0; start < sortedBoxes.length; start++) {
            int tempAmount = amount;
            for (int i = start; i < sortedBoxes.length; i++) {
                CashBox box = sortedBoxes[i];
                if (tempAmount == 0) break;
                Banknote banknote = box.getBanknote();
                int n = tempAmount / banknote.getValue();
                if (n > 0) {
                    int count = Math.min(n, box.getCount());
                    result.put(banknote, count);
                    tempAmount -= count  * banknote.getValue();
                }
            }
            if (tempAmount == 0) {
                for (Map.Entry<Banknote, Integer> entry : result.entrySet()) {
                    boxes.get(entry.getKey()).push(entry.getValue());
                }
                return result;
            }
            result.clear();
        }
        return result;
    }

    public Map<Currency, Integer> getRemainder() {
        Map<Currency, Integer> result = new HashMap<>();
        for(Currency currency : boxGroups.keySet()) {
            int sum = boxGroups.get(currency).stream().mapToInt(CashBox::getBalance).sum();
            result.put(currency, sum);
        }
        return result;
    }

    //
//        public void Balance {
//        }
}
