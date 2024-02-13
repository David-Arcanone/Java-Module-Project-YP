
import java.util.ArrayList;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numberOfPeople;
        System.out.println("На сколько людей разделить счет?\nВведите целое число больше 1:\n");
        while(true){
            numberOfPeople = scanner.nextInt();
            if (numberOfPeople>1) {
                System.out.println("Принято, разделим сумму на "+ numberOfPeople+ " людей");
                break;
            }
            System.out.println("Вы ввели некорректное количество людей, при котором расчет теряет смысл. попробуйте заново:\n(Примечание: Введите целое число больше 1):\n");
        }
        Calculator myCalculator = new Calculator(numberOfPeople);
        String stopWord="";
        while(true){
            double itemPrice;
            String itemName;
            System.out.println("Введите наименование вашего товара: ");
            itemName= scanner.next();
            System.out.println("Введите стоимость вашего товара: ");
            itemPrice=scanner.nextDouble();
            myCalculator.addNewItemToTheList(itemName,itemPrice);
            System.out.println("Введите \"Завершить\", если закончили с составлением списка товаров, введите любой другой текст, если хотите ввести еще товар:" );
            stopWord=scanner.next();
            if(stopWord.equalsIgnoreCase("Завершить")){break;}
        }

        System.out.println("Добавление товаров завершено\nДобавленные товары:");
        myCalculator.showItems();
        CurrencyFormatter myCurrencyFormatter = new CurrencyFormatter();
        System.out.println("Сумма которую должен заплатить каждый человек: "+ myCurrencyFormatter.showPrice(myCalculator.returnCheck()));
        scanner.close();
    }
}
class Calculator{
    int amountOfPeople;
    double totalMoney;
    ArrayList<Item> itemList= new ArrayList<>();
    Calculator (int people){amountOfPeople=people;}
    void addNewItemToTheList(String name, double price){
        if(price>0.0){
            itemList.add(new Item(name,price));
            System.out.println("Товар добавлен в список");
            totalMoney+=price;
        } else{
            System.out.println("Нельзя добавлять товары с отрицательной стоимостью");
        }
    }
    void  showItems(){
        for(int i=0; i<itemList.size();i++){
            System.out.println((i+1)+"-товар: " + itemList.get(i).name);
        }
    }
    double returnCheck (){
        return totalMoney/amountOfPeople;
    }
}

class CurrencyFormatter{
    String showPrice (double price){
        int integerPartOfPrice =(int)price;
        int changeValue=(int)(price*100)-integerPartOfPrice*100; //валюты меньше копейки нет
        String currencyPronunciation= " рублей ";
        String changePronunciation= " копеек";
        switch (integerPartOfPrice){
            case 1: currencyPronunciation=" рубль ";break;
            case 2:;
            case 3:;
            case 4: currencyPronunciation=" рубля ";break;
        }
        switch (changeValue){
            case 1: changePronunciation=" копейка";break;
            case 2:;
            case 3:;
            case 4: changePronunciation=" копейки";break;
        }
        return (""+integerPartOfPrice + currencyPronunciation + changeValue+ changePronunciation);
    }
}
class Item{
    String name;
    double price;
    Item(String itemName, double itemPrice){
        name=itemName;
        price=itemPrice;
    }
}