package com.javarush.task.radiomart.Lib.Alive;

import com.javarush.task.radiomart.Read;
import com.javarush.task.radiomart.RowExcel;
import com.javarush.task.task21.task2109.Solution;
import org.apache.poi.hssf.record.formula.functions.Char;

import java.io.*;
import java.util.*;

/**
 * Created by hp on 03.08.2018.
 */
public class AAction {
    public static void main(String[] args) throws IOException {
        ARead aread = new ARead();

        ArrayList<ARowExcel> old = new ArrayList();
        ArrayList<ARowExcel> young = new ArrayList();

        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\OldGlasses Second.xls", old);
        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\GlassesSecond.xls", young);

        File fileCuttedOld = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\CuttedOld.csv");
        File fileCuttedYoung = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\CuttedYoung.csv");

        String cuttedOld = "name;cod_r;cod_f;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";
        String cuttedYoung = "name;cod_r;cod_f;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";

        ArrayList<ARowExcel> cuttedOldList = new ArrayList<>();
        ArrayList<ARowExcel> cuttedYoungList = new ArrayList<>();

        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\CuttedOld.xls", cuttedOldList);
        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\CuttedYoung.xls", cuttedYoungList);

        File filePresentGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Присутствующие.csv");
        File fileNewGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Отсутствующие.csv");

        String presentGoods = "name;cod_r;cod_f;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";
        String newGoods = "name;cod_r;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";

        ArrayList<ARowExcel> ourPriceList = new ArrayList();
        ArrayList<ARowExcel> radiomartPriceList = new ArrayList();

        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\XLS\\Радиомартный Наш Прайс Test.xls", ourPriceList);
        aread.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\XLS\\Присутствующие.xls", radiomartPriceList);

        File filePresentation = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\Шаг 4\\Презентация.csv");
        File fileNotInOurPrice = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\Шаг 4\\Новые товары.csv");

        String presentation = "name;cod_r;cod_f;priceR;priceUSDR;priceF;priceUSDF;discountPriceR;discountPriceUSDR;discountPriceF;discountPriceUSDF;" +
                "firstTradePriceR;firstTradePriceUSDR;firstTradeAmountR;firstTradePriceF;firstTradePriceUSDF;firstTradeAmountF;" +
                "secondTradePriceR;secondTradePriceUSDR;secondTradeAmountR;secondTradePriceF;secondTradePriceUSDF;secondTradeAmountF;category;" + "\r\n";
        String notInOurPrice = "name;cod_r;cod_f;price;priceUSD;discountPrice;discountPriceUSD;firstTradePrice;firstTradePriceUSD;firstTradeAmount;secondTradePrice;secondTradePriceUSD;secondTradeAmount;category;" + "\r\n";

        // Тесты на null и ""

        /*for(int i = 0; i < old.size(); i++) {
            if(String.valueOf(old.get(i).getArticulRadiomart()).equals("") || String.valueOf(old.get(i).getArticulRadiomart()).equals("null"))
                System.out.println(old.get(i).getName());
        }

        int j = 0;
        for(int i = 0; i < young.size(); i++) {
            if(String.valueOf(young.get(i).getArticulRadiomart()).equals("")|| String.valueOf(young.get(i).getArticulRadiomart()).equals("null")) {
                System.out.println(young.get(i).getName());
                System.out.println(j);
            }

            j++;
        }*/

        // Шаг 1 - удаление повторяющихся позиций в старом и новом файлах

        // удаление повторяющихся позиций в старом файле

        ArrayList<String> listOld = new ArrayList<>();
        for (int i = 0; i < old.size(); i++) {
            if (listOld.contains(old.get(i).getName())) {
                // выводит повторяющиеся товары для самопроверки
                //System.out.println(old.get(i).getName());
                old.get(i).setName("");
            }

            if (!String.valueOf(old.get(i).getName()).equals("") && !String.valueOf(old.get(i).getName()).equals("null")) {
                listOld.add(old.get(i).getName());
            }

            if (!String.valueOf(old.get(i).getName()).equals("") && !String.valueOf(old.get(i).getName()).equals("null")) {
                cuttedOld = cuttedOld + old.get(i).getName() + ";" + old.get(i).getArticulRadiomart() + ";" + old.get(i).getArticulFlus() + ";"
                        + old.get(i).getPrice() + ";" + old.get(i).getFirstTradePrice() + ";" + old.get(i).getFirstTradeAmount() + ";"
                        + old.get(i).getSecondTradePrice() + ";" + old.get(i).getSecondTradeAmount() + ";" + old.get(i).getOldPrice() + ";"
                        + old.get(i).getNewPrice() + ";" + old.get(i).getCategory() + ";"
                        + "\r\n";
            }
        }

        aread.writeToExcel(fileCuttedOld, cuttedOld);

        // удаление повторяющихся позиций в новом файле

        ArrayList<String> listYoung = new ArrayList<>();
        for (int i = 0; i < young.size(); i++) {
            if (listYoung.contains(young.get(i).getName())) {
                // выводит повторяющиеся товары для самопроверки
                System.out.println(young.get(i).getName());
                young.get(i).setName("");
            }

            if (!String.valueOf(young.get(i).getName()).equals("") && !String.valueOf(young.get(i).getName()).equals("null")) {
                listYoung.add(young.get(i).getName());
            }

            if (!String.valueOf(young.get(i).getName()).equals("") && !String.valueOf(young.get(i).getName()).equals("null")) {
                cuttedYoung = cuttedYoung + young.get(i).getName() + ";" + young.get(i).getArticulRadiomart() + ";" + young.get(i).getArticulFlus() + ";"
                        + young.get(i).getPrice() + ";" + young.get(i).getFirstTradePrice() + ";" + young.get(i).getFirstTradeAmount() + ";"
                        + young.get(i).getSecondTradePrice() + ";" + young.get(i).getSecondTradeAmount() + ";" + young.get(i).getOldPrice() + ";"
                        + young.get(i).getNewPrice() + ";" + young.get(i).getCategory() + ";"
                        + "\r\n";
            }
        }

        aread.writeToExcel(fileCuttedYoung, cuttedYoung);

        // Шаг 2 - удаления неудаляющегося пробела в ценах прайса радиомарта от 1000 и больше (пробел появляется после тисячной целой части - это Datacol)

        // тут главное чтобы в прайсе радиомарта в первой оптовой цене небыло пробелов, иначе будет ошибка (2-я оптовая цена равна 1-ой, но не обнуляется)
        // также если проблему не решить, то при обработке цен позиций не вошедших в главный список потенциально появятся ошибки
        // P.S. Изменятся будет файл-результат на Шаге 3

        // код неудаляющегося пробела который мы имеем в прайсе радиомарта в колонке первая оптовая цена
                        /*char firstSpace = ' ';
                        int firstCode = (int) firstSpace;
                        //System.out.println(firstCode);//160
                        */

        // код нормального пробела, его можно спокойно удалить
                        /*char secondSpace = ' ';
                        int secondCode = (int) secondSpace;
                        //System.out.println(secondCode);//32
                        */
        // теперь чтобы удаление пробела работало, необходимо писать вместо нормального пробела неудаляющейся (скопировать из консоли и вставить)
        for (int i = 0; i < cuttedYoungList.size(); i++) {

            // первая оптовая цена
            String[] checkFirstTradePrice = String.valueOf(cuttedYoungList.get(i).getFirstTradePrice()).split(" ");

            if (checkFirstTradePrice.length == 2) {
                String result = checkFirstTradePrice[0] + checkFirstTradePrice[1];
                //System.out.println(cuttedYoungList.get(i).getArticulRadiomart() + " " + result + " " + young.get(i).getSecondTradePrice());
                cuttedYoungList.get(i).setFirstTradePrice(result);
            }

            // старая цена
            String[] checkOldPrice = String.valueOf(cuttedYoungList.get(i).getOldPrice()).split(" ");

            if (checkOldPrice.length == 2) {
                //System.out.println(checkOldPrice[0] + checkOldPrice[1]);
                cuttedYoungList.get(i).setOldPrice(checkOldPrice[0] + checkOldPrice[1]);
            }

            // новая цена
            String[] checkNewPrice = String.valueOf(cuttedYoungList.get(i).getNewPrice()).split(" ");

            if (checkNewPrice.length == 2) {
                //System.out.println(checkNewPrice[0] + checkNewPrice[1]);
                cuttedYoungList.get(i).setNewPrice(checkNewPrice[0] + checkNewPrice[1]);
            }
        }


        // Шаг 3 - перенос артикула Flus из старого файла в новый

        ArrayList<String> checkList = new ArrayList<>();
        for (int i = 0; i < cuttedOldList.size(); i++) {

            if (!String.valueOf(cuttedOldList.get(i).getArticulRadiomart()).equals("") && !String.valueOf(cuttedOldList.get(i).getArticulRadiomart()).equals("null")) {
                for (int j = 0; j < cuttedYoungList.size(); j++) {

                    if (cuttedOldList.get(i).getArticulRadiomart().equals(cuttedYoungList.get(j).getArticulRadiomart()) && !String.valueOf(cuttedYoungList.get(j).getArticulRadiomart()).equals("")
                            && !String.valueOf(cuttedYoungList.get(j).getArticulRadiomart()).equals("null")) {
                        //System.out.println(cuttedOldList.get(i).getArticulRadiomart() + " " + cuttedYoungList.get(j).getArticulRadiomart());
                        cuttedYoungList.get(j).setArticulFlus(cuttedOldList.get(i).getArticulFlus());
                        presentGoods = presentGoods + cuttedYoungList.get(j).getName() + ";" + cuttedYoungList.get(j).getArticulRadiomart() + ";" + cuttedYoungList.get(j).getArticulFlus() + ";"
                                + cuttedYoungList.get(j).getPrice() + ";" + cuttedYoungList.get(j).getFirstTradePrice() + ";" + cuttedYoungList.get(j).getFirstTradeAmount() + ";"
                                + cuttedYoungList.get(j).getSecondTradePrice() + ";" + cuttedYoungList.get(j).getSecondTradeAmount() + ";" + cuttedYoungList.get(j).getOldPrice() + ";"
                                + cuttedYoungList.get(j).getNewPrice() + ";" + cuttedYoungList.get(j).getCategory() + ";"
                                + "\r\n";
                        checkList.add(cuttedYoungList.get(j).getName());
                    }
                }
            }
        }

        aread.writeToExcel(filePresentGoods, presentGoods);

        //Проверка на товары которых непопали из старого прайса

        for (int i = 0; i < cuttedOldList.size(); i++) {
            if (!checkList.contains(cuttedOldList.get(i).getName())) {
                // System.out.println(cuttedOldList.get(i).getName());
            }
        }

        //System.out.println("");

        //Проверка на товары которых непопали из нового прайса

        for (int i = 0; i < cuttedYoungList.size(); i++) {
            if (!checkList.contains(cuttedYoungList.get(i).getName())) {
                // System.out.println(cuttedYoungList.get(i).getName());
            }
        }

        // тут нужно вставить код для товаров которые не в наличии или что-то еще

        //Шаг 4 - преобразовываем старый и новый файл в формат необходимый для ихнего слияния в файл-презентацию (вручную делаем наш прайс по шаблону прайса радиомарта)
        double course = 27.4999973;

        //Шаг 5 - слияние двух файлов в презентационный (тут у первой оптовой цены радиомарта есть пробел после тисячной целой части, скорее всего косяк Daracol,
        //ниже будет присутствовать код для ликвидации пробела

        ArrayList<String> noOurPrice = new ArrayList<>();
        ArrayList<String> noRadiomartPrice = new ArrayList<>();
        Map<String, String> map = new HashMap<>();

        for (int i = 0; i < ourPriceList.size(); i++) {
            if (!String.valueOf(ourPriceList.get(i).getArticulFlus()).equals("") && !String.valueOf(ourPriceList.get(i).getArticulFlus()).equals("null")) {

                for (int j = 0; j < radiomartPriceList.size(); j++) {
                    if (ourPriceList.get(i).getArticulFlus().equals(radiomartPriceList.get(j).getArticulFlus())) {

                        //если оптовые цены радиомарт нулевые, то присваивает им 0 вместо null - это позволяет избежать ошибки при преобразовании в дробное и деление
                        if (String.valueOf(radiomartPriceList.get(j).getFirstTradePrice()).equals("") ||
                                String.valueOf(radiomartPriceList.get(j).getFirstTradePrice()).equals("null")) {
                            radiomartPriceList.get(j).setFirstTradePrice("0");
                            radiomartPriceList.get(j).setSecondTradePrice("0");
                        }

                        //елси вторая оптовая цена радиомарта равна первой, то вторая обнуляется (специфика Datacol)
                        if (!String.valueOf(radiomartPriceList.get(j).getFirstTradePrice()).equals("") &&
                                !String.valueOf(radiomartPriceList.get(j).getFirstTradePrice()).equals("null") &&
                                String.valueOf(radiomartPriceList.get(j).getFirstTradePrice()).equals(String.valueOf(radiomartPriceList.get(j).getSecondTradePrice()))) {

                            radiomartPriceList.get(j).setSecondTradePrice("0");
                            //System.out.println(radiomartPriceList.get(j).getArticulRadiomart());
                        }

                        if (!String.valueOf(radiomartPriceList.get(j).getNewPrice()).equals("") &&
                                !String.valueOf(radiomartPriceList.get(j).getNewPrice()).equals("null") &&
                                String.valueOf(radiomartPriceList.get(j).getPrice()).equals(String.valueOf(radiomartPriceList.get(j).getNewPrice()))) {
                            radiomartPriceList.get(j).setNewPrice("0");
                        }

                        //у нашего прайса обнуляется скидочная цена
                        if (String.valueOf(ourPriceList.get(i).getNewPrice()).equals("") || String.valueOf(ourPriceList.get(i).getNewPrice()).equals("null")) {
                            ourPriceList.get(i).setNewPrice("0");
                        }

                        //у нашего прайса обнуляется 1-я оптовая цена
                        if (String.valueOf(ourPriceList.get(i).getFirstTradePrice()).equals("") || String.valueOf(ourPriceList.get(i).getFirstTradePrice()).equals("null")) {
                            ourPriceList.get(i).setFirstTradePrice("0");
                        }

                        //у нашего прайса обнуляется 2-я оптовая цена
                        if (String.valueOf(ourPriceList.get(i).getSecondTradePrice()).equals("") || String.valueOf(ourPriceList.get(i).getSecondTradePrice()).equals("null")) {
                            ourPriceList.get(i).setSecondTradePrice("0");
                        }

                        //логика если у прайс-листа радиомарт обычная цена нулевая (тоесть есть скидочная и старая цена)
                        //когда прогеры добаваят нужные мне поля, нужно будет поменять код для обычной цены нашего прайса, для пункта где обычная цена радиомарта нулевая
                        if (String.valueOf(radiomartPriceList.get(j).getPrice()).equals("") || String.valueOf(radiomartPriceList.get(j).getPrice()).equals("null")) {
                            presentation = presentation + ourPriceList.get(i).getName() + ";" + radiomartPriceList.get(j).getArticulRadiomart() + ";" +
                                    radiomartPriceList.get(j).getArticulFlus() + ";" + radiomartPriceList.get(j).getOldPrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getOldPrice()) / course) + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getPrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getPrice()) / course) + ";" + radiomartPriceList.get(j).getNewPrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getNewPrice()) / course) + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getNewPrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getNewPrice()) / course) + ";" + radiomartPriceList.get(j).getFirstTradePrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getFirstTradePrice()) / course) + ";" + radiomartPriceList.get(j).getFirstTradeAmount() + ";" +
                                    String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getFirstTradePrice())) + ";" + String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getFirstTradePrice()) / course) + ";" +
                                    ourPriceList.get(i).getFirstTradeAmount() + ";" + radiomartPriceList.get(j).getSecondTradePrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getSecondTradePrice()) / course) + ";" +
                                    radiomartPriceList.get(j).getSecondTradeAmount() + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getSecondTradePrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getSecondTradePrice()) / course) + ";" + ourPriceList.get(i).getSecondTradeAmount() + ";" +
                                    radiomartPriceList.get(j).getCategory() + ";" + "\r\n";

                            map.put(String.valueOf(radiomartPriceList.get(j).getArticulRadiomart()), String.valueOf(radiomartPriceList.get(j).getArticulFlus()));

                            //System.out.println(ourPriceList.get(i).getName());
                            //System.out.println(radiomartPriceList.get(j).getName());
                        }
                        //логика если у прайс-листа радиомарт обычная цена не нулевая (тоесть нету скидочной цены)
                        else {

                            presentation = presentation + ourPriceList.get(i).getName() + ";" + radiomartPriceList.get(j).getArticulRadiomart() + ";" +
                                    radiomartPriceList.get(j).getArticulFlus() + ";" + radiomartPriceList.get(j).getPrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getPrice()) / course) + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getPrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getPrice()) / course) + ";" + radiomartPriceList.get(j).getNewPrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getNewPrice()) / course) + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getNewPrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getNewPrice()) / course) + ";" + radiomartPriceList.get(j).getFirstTradePrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getFirstTradePrice()) / course) + ";" + radiomartPriceList.get(j).getFirstTradeAmount() + ";" +
                                    String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getFirstTradePrice())) + ";" + String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getFirstTradePrice()) / course) + ";" +
                                    ourPriceList.get(i).getFirstTradeAmount() + ";" + radiomartPriceList.get(j).getSecondTradePrice() + ";" +
                                    String.format("%.2f", Double.parseDouble(radiomartPriceList.get(j).getSecondTradePrice()) / course) + ";" +
                                    radiomartPriceList.get(j).getSecondTradeAmount() + ";" + String.format("%.0f", Double.parseDouble(ourPriceList.get(i).getSecondTradePrice())) + ";" +
                                    String.format("%.2f", Double.parseDouble(ourPriceList.get(i).getSecondTradePrice()) / course) + ";" + ourPriceList.get(i).getSecondTradeAmount() + ";" +
                                    radiomartPriceList.get(j).getCategory() + ";" + "\r\n";

                            map.put(String.valueOf(radiomartPriceList.get(j).getArticulRadiomart()), String.valueOf(radiomartPriceList.get(j).getArticulFlus()));
                        }
                    }
                }
            }
        }

        /*for(Map.Entry<String, String> pair: map.entrySet()) {
            //System.out.println(pair.getKey() + " " + pair.getValue());
        }

        System.out.println(map.size());*/

        int countR = 0;

        /*for(int i = 0; i < radiomartPriceList.size(); i++) {
            if(!map.containsKey(String.valueOf(radiomartPriceList.get(i).getArticulRadiomart()))) {
                System.out.println(radiomartPriceList.get(i).getArticulRadiomart());
                countR++;
            }
        }

        System.out.println(countR);*/

        /*int countF = 0;

        for(int i = 0; i < ourPriceList.size(); i++) {
            if(!map.containsValue(String.valueOf(ourPriceList.get(i).getArticulFlus()))) {
                System.out.println(ourPriceList.get(i).getArticulFlus());
                countF++;
            }
        }

        System.out.println(countF);*/


        aread.writeToExcel(filePresentation, presentation);

        //Шаг 7 - сортировка по категориям

        BRead bRead = new BRead();

        ArrayList<BRowExcel> forSort = new ArrayList();

        bRead.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\Шаг 4\\Сортировка на категории\\Презентация.xls", forSort);

        File fileSorted = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Alive\\2 шаг\\Шаг 3\\Шаг 4\\Сортировка на категории\\Отсортированный.csv");

        String sorted = "name;cod_r;cod_f;priceR;priceUSDR;priceF;priceUSDF;discountPriceR;discountPriceUSDR;discountPriceF;discountPriceUSDF;" +
                "firstTradePriceR;firstTradePriceUSDR;firstTradeAmountR;firstTradePriceF;firstTradePriceUSDF;firstTradeAmountF;" +
                "secondTradePriceR;secondTradePriceUSDR;secondTradeAmountR;secondTradePriceF;secondTradePriceUSDF;secondTradeAmountF;category;" + "\r\n";

        final Comparator<BRowExcel> COMPARE_BY_COUNT = new Comparator<BRowExcel>() {
            @Override
            public int compare(BRowExcel lhs, BRowExcel rhs) {
                return String.valueOf(lhs.getCategory()).length() - String.valueOf(rhs.getCategory()).length();
            }
        };

        Collections.sort(forSort, COMPARE_BY_COUNT);

        for (int i = 0; i < forSort.size(); i++) {

            // подготовка перед ручным удалением всех "0" и null
            if (forSort.get(i).getDiscountPriceR().equals("0")) {
                forSort.get(i).setDiscountPriceR("null");
            }

            if (forSort.get(i).getDiscountPriceRUSD().equals("0")) {
                forSort.get(i).setDiscountPriceRUSD("null");
            }

            if (forSort.get(i).getDiscountPriceF().equals("0")) {
                forSort.get(i).setDiscountPriceF("null");
            }

            if (forSort.get(i).getDiscountPriceFUSD().equals("0")) {
                forSort.get(i).setDiscountPriceFUSD("null");
            }

            if (forSort.get(i).getFirstTradePriceR().equals("0")) {
                forSort.get(i).setFirstTradePriceR("null");
            }

            if (forSort.get(i).getFirstTradePriceRUSD().equals("0")) {
                forSort.get(i).setFirstTradePriceRUSD("null");
            }

            if (forSort.get(i).getFirstTradePriceF().equals("0")) {
                forSort.get(i).setFirstTradePriceF("null");
            }

            if (forSort.get(i).getFirstTradePriceFUSD().equals("0")) {
                forSort.get(i).setFirstTradePriceFUSD("null");
            }

            if (forSort.get(i).getFirstTradeAmountR().equals("0")) {
                forSort.get(i).setFirstTradeAmountR("null");
            }

            if (forSort.get(i).getFirstTradeAmountF().equals("0")) {
                forSort.get(i).setFirstTradeAmountF("null");
            }

            if (forSort.get(i).getSecondTradePriceR().equals("0")) {
                forSort.get(i).setSecondTradePriceR("null");
            }

            if (forSort.get(i).getSecondTradePriceRUSD().equals("0")) {
                forSort.get(i).setSecondTradePriceRUSD("null");
            }

            if (forSort.get(i).getSecondTradePriceF().equals("0")) {
                forSort.get(i).setSecondTradePriceF("null");
            }

            if (forSort.get(i).getSecondTradePriceFUSD().equals("0")) {
                forSort.get(i).setSecondTradePriceFUSD("null");
            }

            if (forSort.get(i).getSecondTradeAmountR().equals("0")) {
                forSort.get(i).setSecondTradeAmountR("null");
            }

            if (forSort.get(i).getSecondTradeAmountF().equals("0")) {
                forSort.get(i).setSecondTradeAmountF("null");
            }
            // запись данных
            sorted = sorted + forSort.get(i).getName() + ";" + forSort.get(i).getArticulRadiomart() + ";" + forSort.get(i).getArticulFlus() + ";" +
                    forSort.get(i).getPriceR() + ";" + forSort.get(i).getPriceRUSD() + ";" + forSort.get(i).getPriceF() + ";" + forSort.get(i).getPriceFUSD() + ";" +
                    forSort.get(i).getDiscountPriceR() + ";" + forSort.get(i).getDiscountPriceRUSD() + ";" + forSort.get(i).getDiscountPriceF() + ";" +
                    forSort.get(i).getDiscountPriceFUSD() + ";" + forSort.get(i).getFirstTradePriceR() + ";" + forSort.get(i).getFirstTradePriceRUSD() + ";" +
                    forSort.get(i).getFirstTradeAmountR() + ";" + forSort.get(i).getFirstTradePriceF() + ";" + forSort.get(i).getFirstTradePriceFUSD() + ";" +
                    forSort.get(i).getFirstTradeAmountF() + ";" + forSort.get(i).getSecondTradePriceR() + ";" + forSort.get(i).getSecondTradePriceRUSD() + ";" +
                    forSort.get(i).getSecondTradeAmountR() + ";" + forSort.get(i).getSecondTradePriceF() + ";" + forSort.get(i).getSecondTradePriceFUSD() + ";" +
                    forSort.get(i).getSecondTradeAmountF() + ";" + forSort.get(i).getCategory() + ";" + "\r\n";
        }

        bRead.writeToExcel(fileSorted, sorted);

        //Шаг 6 - презентационный файл преобразовуется для заливки на сайт

    }
}
