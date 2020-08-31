package com.javarush.task.radiomart.Lib.Alive;

import com.javarush.task.radiomart.RowExcel;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.FormulaEvaluator;
import org.apache.poi.ss.usermodel.Row;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

/**
 * Created by hp on 03.08.2018.
 */
public class ARead {
    public void read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы"));
        while (scanner.hasNext())
            System.out.println(scanner.nextLine());
    }

    public void readExcel(String filePath, ArrayList<ARowExcel> list) throws IOException {

        File file = new File(filePath);

        // Read XSL file
        FileInputStream inputStream = new FileInputStream(file);

        // Get the workbook instance for XLS file
        HSSFWorkbook workbook = new HSSFWorkbook(inputStream);

        // Get first sheet from the workbook
        HSSFSheet sheet = workbook.getSheetAt(0);

        // Get iterator to all the rows in current sheet
        Iterator<Row> rowIterator = sheet.iterator();
        //идем по строкам
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            //Создали экземпляр класса
            ARowExcel rowTerminal = new ARowExcel();
            // Get iterator to all cells of current row
            Iterator<Cell> cellIterator = row.cellIterator();
            //Пропускаем шапочку на странице
            if (row.getRowNum() > 0) {
                //Идем по ячейкам
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    CellType cellType = cell.getCellTypeEnum();

                    switch (cellType) {
                        case _NONE:
                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), "");
                            break;
                        case BOOLEAN:
                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), "");

                            break;
                        case BLANK:
                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), "");

                            break;
                        case FORMULA:
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            break;
                        case NUMERIC:

                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), Integer.toString((int) cell.getNumericCellValue()));
                            break;
                        case STRING:

                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        case ERROR:

                            setValueToRowExcel(rowTerminal, cell.getColumnIndex(), Integer.toString((int) cell.getNumericCellValue()));

                            break;
                    }
                }
            }

            if (rowTerminal.getName() != null && rowTerminal.getName() != "") {
                list.add(rowTerminal);
                //System.out.println(rowTerminal.getName()+" "+rowTerminal.getArticulFlus());
            }
        }
    }

    public static void setValueToRowExcel(ARowExcel rowExcel, int index, String value) {
        switch (index) {

            case 0:
                rowExcel.setName(value);
                break;
            case 1:
                rowExcel.setArticulRadiomart(value.trim());
                break;
            case 2:
                rowExcel.setArticulFlus(value.trim());
                break;
            case 3:
                rowExcel.setPrice(value.trim());
                break;
            case 4:
                rowExcel.setFirstTradePrice(value.trim());
                break;
            case 5:
                rowExcel.setFirstTradeAmount(value.trim());
                break;
            case 6:
                rowExcel.setSecondTradePrice(value.trim());
                break;
            case 7:
                rowExcel.setSecondTradeAmount(value.trim());
                break;
            case 8:
                rowExcel.setOldPrice(value.trim());
                break;
            case 9:
                rowExcel.setNewPrice(value.trim());
                break;
            case 10:
                rowExcel.setCategory(value.trim());
                break;
        }
    }

    protected void writeToExcel(File file, String str) {
        //если файл уже есть , удаляем его
        if (file.exists())
            file.delete();
        // Записываем строку в текстовый файл в двух кодировках (Cp866 и Cp1251)
        OutputStream os = null; // класс записи байтов в файл
        try {
            os = new FileOutputStream(file, true);
            // Записываем строку в кодировке Cp1251
            os.write(str.getBytes("Cp1251"));
            os.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
