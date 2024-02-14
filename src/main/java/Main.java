
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CurrencyFormatter myCurrencyFormatter = new CurrencyFormatter();
        System.out.println("На сколько людей разделить сумму?: ");
        Calculator myCalculator = new Calculator(myCurrencyFormatter.nextPositiveIntegerHigherThan1FromSystem(scanner));
        String stopWord = "";
        while (true) {
            String itemName;
            System.out.println("Введите наименование вашего товара: ");
            itemName = scanner.nextLine();
            System.out.println("Имя товара принято");
            System.out.println("Введите стоимость вашего товара: ");
            myCalculator.addNewItemToTheList(itemName, myCurrencyFormatter.nextPositiveDoubleHigherThan0FromSystem(scanner));
            System.out.println("Введите \"Завершить\", если закончили с составлением списка товаров, введите любой другой текст, если хотите ввести еще товар:");
            stopWord = scanner.nextLine();
            if (stopWord.equalsIgnoreCase("Завершить")) {
                break;
            }
        }

        System.out.println("Добавление товаров завершено\nДобавленные товары:");
        myCalculator.showItems();
        System.out.println("Сумма которую должен заплатить каждый человек:" + myCurrencyFormatter.showPrice(myCalculator.returnCheck()));
        scanner.close();
    }
}

class Calculator {
    Integer amountOfPeople;
    Double totalMoney;
    ArrayList<Item> itemList = new ArrayList<>();

    Calculator(Integer people) {
        amountOfPeople = people;
        totalMoney = 0.0;
    }

    void addNewItemToTheList(String name, Double price) {
        itemList.add(new Item(name, price));
        System.out.println("Товар добавлен в список");
        totalMoney += price;
    }

    void showItems() {
        for (int i = 0; i < itemList.size(); i++) {
            System.out.println((i + 1) + "-товар: " + itemList.get(i).name);
        }
    }

    double returnCheck() {
        return totalMoney / amountOfPeople;
    }
}

class CurrencyFormatter {
    String showPrice(double price) {
        int integerPartOfPrice = (int) price;
        int changeValue = (int) ((price * 100) % 100); //валюты меньше копейки нет
        String currencyPronunciation = " рублей ";
        String changePronunciation = " копеек";
        if ((integerPartOfPrice % 10) == 1) {
            currencyPronunciation = " рубль ";
        }
        if (((integerPartOfPrice % 10) > 1) && ((integerPartOfPrice % 10) <= 4)) {
            currencyPronunciation = " рубля ";
        }
        if (10 < ((integerPartOfPrice % 100)) && ((integerPartOfPrice % 100) < 15)) {
            currencyPronunciation = " рублей ";
        }
        if ((changeValue % 10) == 1) {
            changePronunciation = " копейка";
        }
        if (((changeValue % 10) > 1) && ((changeValue % 10) <= 4)) {
            changePronunciation = " копейки";
        }
        if ((10 < changeValue) && (changeValue < 15)) {
            changePronunciation = " копеек";
        }
        return (" " + integerPartOfPrice + currencyPronunciation + changeValue + changePronunciation);
    }

    Integer nextPositiveIntegerHigherThan1FromSystem(Scanner sn) {
        String myLine;
        Integer myNumber = null;
        boolean incorrectInput = true;
        while (incorrectInput) {
            myLine = sn.nextLine();
            try {
                myNumber = Integer.parseInt(myLine);
            } catch (NumberFormatException e) {
                System.err.println("Неадекватное значение. Вводите только целые числа больше 1: " + e.getMessage());
                continue;
            }
            if (myNumber > 1) {
                incorrectInput = false;
            } else {
                System.err.println("Неадекватная величина целого числа. Вводите только целые числа больше 1: " + myLine);
            }
        }
        return myNumber;
    }

    Double nextPositiveDoubleHigherThan0FromSystem(Scanner sn) {
        String myLine;
        Double myNumber = null;
        boolean incorrectInput = true;
        while (incorrectInput) {
            myLine = sn.nextLine();
            try {
                myNumber = Double.parseDouble(myLine);
                if (myNumber > 0.0) {
                    incorrectInput = false;
                } else {
                    System.err.println("Неадекватная величина дробного числа. Вводите только дробные числа больше 0: " + myLine);
                }
            } catch (NumberFormatException e) {
                System.err.println("Неадекватное значение. Вводите только дробные числа больше 0: " + e.getMessage());
                continue;
            }
        }
        return myNumber;
    }
}

class Item {
    String name;
    Double price;

    Item(String itemName, Double itemPrice) {
        name = itemName;
        price = itemPrice;
    }
}