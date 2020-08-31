package com.javarush.task.radiomart;

import com.javarush.task.radiomart.SecondStep.SecondRead;
import com.javarush.task.radiomart.SecondStep.SecondRowExcel;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by hp on 02.06.2018.
 */
public class Action {
    public static void main(String[] args) throws IOException {
        Read read = new Read();
        SecondRead secondRead = new SecondRead();
        ArrayList<RowExcel> middle = new ArrayList<>();
        ArrayList<RowExcel> old = new ArrayList();
        ArrayList<RowExcel> young = new ArrayList();
        //Может выдавать ошибку о том, что файл не найден из-за неосоответствие формата файла к указаному.
        read.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\OldGlasses Second.xls", old);
        read.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\GlassesSecond.xls", young);
        File filePresentGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Присутствующие.csv");
        File fileNewGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Отсутствующие.csv");
        String presentGoods = "name;cod_r;cod_f;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";
        String newGoods = "name;cod_r;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;old_price;new_price;category;" + "\r\n";

        /*for(int i = 0; i < old.size(); i++) {
            for(int j = 0; j < young.size(); j ++) {
                if (old.get(i).getArticulRadiomart().equals(young.get(j).getArticulRadiomart())) {
                    young.get(j).setArticulFlus(old.get(i).getArticulFlus());
                    presentGoods = presentGoods +young.get(j).getName() + ";" + young.get(j).getArticulRadiomart() + ";" + young.get(j).getArticulFlus() + ";"
                            + young.get(j).getPrice() + ";" + young.get(j).getFirstTradePrice() + ";" + young.get(j).getFirstTradeAmount() + ";"
                            + young.get(j).getSecondTradePrice() + ";" + young.get(j).getSecondTradeAmount() + ";" + young.get(j).getOldPrice() + ";"
                            + young.get(j).getNewPrice() + ";" + young.get(j).getCategory() + ";"
                            + "\r\n";
                }
            }

            System.out.println(old.get(i).getArticulFlus());
        } */ //comment first step

        //comment first step
        // read.writeToExcel(filePresentGoods, presentGoods);

        /*ArrayList<String> list = new ArrayList<>();

        for(int i = 0; i < young.size(); i++) {
            if (young.get(i).getArticulRadiomart() == null && !list.contains(young.get(i).getName())) {
                newGoods = newGoods + young.get(i).getName() + ";" + "\r\n";
            }

            list.add(young.get(i).getName());
        }*/

        //read.writeToExcel(fileNewGoods, newGoods);

        /*for(int i = 0; i < young.size(); i++)
            System.out.println(young.get(i).getArticulRadiomart() + " " + young.get(i).getArticulFlus());*/

        read.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\ПрисутствующиеSecond.xls", middle);
        File fileMiddleGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Промежуточный 19.07.csv");
        String middleGoods = "name;cod_r;cod_f;discount_price;price;first_trade_price;first_trade_amount;second_trade_price;second_trade_amount;category;" + "\r\n";

        int g = 0;
        ArrayList<String> first = new ArrayList<>();
        ArrayList<String> second = new ArrayList<>();

        for (int i = 0; i < young.size(); i++) {
            first.add(young.get(i).getArticulRadiomart());
        }

        for (int i = 0; i < middle.size(); i++) {
            second.add(middle.get(i).getArticulRadiomart());
        }

        for (int i = 0; i < young.size(); i++) {
            if (!second.contains(first.get(i))) {
                g++;
                System.out.println(first.get(i));
            }
        }

        System.out.println(g);

        /*for(int i = 0; i < middle.size(); i++) {
            if(middle.get(i).getPrice() == null || middle.get(i).getPrice().equals("null") || middle.get(i).getPrice().equals("")) {
                middleGoods = middleGoods + middle.get(i).getName() + ";" + middle.get(i).getArticulRadiomart() + ";" + middle.get(i).getArticulFlus() + ";" + middle.get(i).getNewPrice() + ";" +
                        middle.get(i).getOldPrice() + ";" + middle.get(i).getFirstTradePrice() + ";" + middle.get(i).getFirstTradeAmount() + ";" + middle.get(i).getSecondTradePrice() + ";" +
                        middle.get(i).getSecondTradeAmount() + ";" + middle.get(i).getCategory() + ";" + "\r\n";
                System.out.println(middle.get(i).getArticulFlus());
            } else {
                middleGoods = middleGoods + middle.get(i).getName() + ";" + middle.get(i).getArticulRadiomart() + ";" + middle.get(i).getArticulFlus() + ";" + middle.get(i).getOldPrice() + ";" +
                        middle.get(i).getPrice()+ ";" + middle.get(i).getFirstTradePrice() + ";" + middle.get(i).getFirstTradeAmount() + ";" + middle.get(i).getSecondTradePrice() + ";" +
                        middle.get(i).getSecondTradeAmount() + ";" + middle.get(i).getCategory()
                        + ";" + "\r\n";
                System.out.println(middle.get(i).getArticulFlus());
            }
        }

        read.writeToExcel(fileMiddleGoods, middleGoods);*/ // second step

       /* ArrayList<RowExcel> presented = new ArrayList<>();
        ArrayList<RowExcel> ourPrice = new ArrayList<>();
        read.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Промежуточный 19.07.xls", presented);
        read.readExcel("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\Наш Прайс Test.xls", ourPrice);
        File filePresentedGoods = new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы\\На утверждение.csv");
        String presentedGoods = "Имя;Артикул R;Артикул F;Скидочная Цена R; Цена R;Цена F;ОптЦен R;ОптКол R;ОптЦен F;ОптКол F;ОптЦен 2 R;ОптКол 2 R;Категория;" + "\r\n";

        for(int i = 0; i < ourPrice.size(); i++) {
           for(int j = 0; j < presented.size(); j++) {
               if(ourPrice.get(i).getArticulRadiomart().equals(presented.get(j).getArticulFlus())) {
                   if(presented.get(j).getOldPrice() == null || presented.get(j).getOldPrice().equals("null")
                           || presented.get(j).getOldPrice().equals("")) {
                       presented.get(j).setSecondTradeAmount(null);
                   }
                   presentedGoods = presentedGoods + ourPrice.get(i).getName() + ";" + presented.get(j).getArticulRadiomart() + ";" + presented.get(j).getArticulFlus() + ";" +
                           presented.get(j).getPrice() + ";" + presented.get(j).getFirstTradePrice()+ ";" + ourPrice.get(i).getArticulFlus() + ";" + presented.get(j).getFirstTradeAmount() + ";" +
                           presented.get(j).getSecondTradePrice() + ";" + ourPrice.get(i).getFirstTradeAmount() + ";" + ourPrice.get(i).getFirstTradePrice() + ";" + presented.get(j).getSecondTradeAmount() + ";" +
                           presented.get(j).getOldPrice() + ";" + presented.get(j).getNewPrice() + ";"
                           + "\r\n";
               }
           }

            System.out.println(ourPrice.get(i).getArticulRadiomart());
        }

        read.writeToExcel(filePresentedGoods, presentedGoods); */// step 3

    }


}
