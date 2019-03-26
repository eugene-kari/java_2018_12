package ru.otus.l081;

import java.util.*;

public class ATM {

    private Map<BanknoteRUB, CashBox> boxes = new HashMap<>();
    private Map<Currency, List<CashBox>> boxGroups = new HashMap<>();

    public ATM() {
        for (BanknoteRUB banknoteRUB : BanknoteRUB.values()) {
            CashBox cashBox = new CashBox(banknoteRUB);
            boxes.put(banknoteRUB, cashBox);
            Currency currency = banknoteRUB.getCurrency();
            List<CashBox> boxGroup = boxGroups.computeIfAbsent(currency, k -> new ArrayList<>());
            boxGroup.add(cashBox);
        }
    }

    public void cashIn(BanknoteRUB banknoteRUB, int count) {
        boxes.get(banknoteRUB).push(count);
    }

    public Map<BanknoteRUB, Integer> cashOut(Currency currency, int amount) {
        Map<BanknoteRUB, Integer> result = new HashMap<>();
        List<CashBox> boxGroup = boxGroups.get(currency);

        CashBox[] sortedBoxes = boxGroup.stream()
                .filter(b -> b.getCount() > 0 && b.getBanknoteRUB().getValue() <= amount)
                .sorted(Comparator.comparingInt(b -> -b.getBanknote().getValue()))
                .toArray(CashBox[]::new);

        if (sortedBoxes.length == 0) return result;

        int tempAmount = 0;
        for (int i = 0; i < sortedBoxes.length; i++) {
            tempAmount = amount;
            for (int j = i; j < sortedBoxes.length; j++) {
                CashBox box = sortedBoxes[j];
                if (tempAmount == 0) break;
                BanknoteRUB banknoteRUB = box.getBanknoteRUB();
                int n = tempAmount / banknoteRUB.getValue();
                if (n > 0) {
                    int count = Math.min(n, box.getCount());
                    result.put(banknoteRUB, count);
                    tempAmount -= count * banknoteRUB.getValue();
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
