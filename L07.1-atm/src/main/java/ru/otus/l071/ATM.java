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

        CashBox[] sortedBoxes = boxGroup.stream()
                .filter(b -> b.getCount() > 0 && b.getBanknote().getValue() <= amount)
                .sorted(Comparator.comparingInt(b -> -b.getBanknote().getValue()))
                .toArray(CashBox[]::new);

        if (sortedBoxes.length == 0) return result;

        int tempAmount = 0;
        for (int i = 0; i < sortedBoxes.length; i++) {
            tempAmount = amount;
            for (int j = i; j < sortedBoxes.length; j++) {
                CashBox box = sortedBoxes[j];
                if (tempAmount == 0) break;
                Banknote banknote = box.getBanknote();
                int n = tempAmount / banknote.getValue();
                if (n > 0) {
                    int count = Math.min(n, box.getCount());
                    result.put(banknote, count);
                    tempAmount -= count * banknote.getValue();
                }
            }
            if (tempAmount == 0) {
                result.entrySet().stream()
                        .forEach(entry -> boxes.get(entry.getKey()).pop(entry.getValue()));
                return result;
            }
            result.clear();
        }
        if (tempAmount != 0) {
            result.put(null, 0);
        }
        return result;
    }

    public Map<Currency, Integer> getBalance() {
        Map<Currency, Integer> result = new HashMap<>();
        for(Currency currency : boxGroups.keySet()) {
            int sum = boxGroups.get(currency)
                    .stream()
                    .mapToInt(CashBox::getBalance).sum();
            result.put(currency, sum);
        }
        return result;
    }
}
