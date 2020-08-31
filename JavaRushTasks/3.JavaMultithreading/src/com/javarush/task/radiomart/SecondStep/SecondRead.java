package com.javarush.task.radiomart.SecondStep;

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
 * Created by hp on 18.07.2018.
 */
public class SecondRead {
    public void read() throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("C:\\Users\\hp\\Documents\\Пробные кампании\\Заполненые прайсы\\Для тестов программы"));
        while (scanner.hasNext())
            System.out.println(scanner.nextLine());
    }

    public void secondReadExcel(String filePath, ArrayList<SecondRowExcel> list) throws IOException {

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
            SecondRowExcel rowTerminal = new SecondRowExcel();
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
                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), "");
                            break;
                        case BOOLEAN:
                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), "");

                            break;
                        case BLANK:
                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), "");

                            break;
                        case FORMULA:
                            FormulaEvaluator evaluator = workbook.getCreationHelper().createFormulaEvaluator();
                            break;
                        case NUMERIC:

                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), Integer.toString((int) cell.getNumericCellValue()));
                            break;
                        case STRING:

                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        case ERROR:

                            setValueToRowExcelSecond(rowTerminal, cell.getColumnIndex(), Integer.toString((int) cell.getNumericCellValue()));

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

    public static void setValueToRowExcelSecond(SecondRowExcel secondRowExcel, int index, String value) {
        switch (index) {

            case 0:
                secondRowExcel.setName(value);
                break;
            case 1:
                secondRowExcel.setArticulRadiomart(value.trim());
                break;
            case 2:
                secondRowExcel.setArticulFlus(value.trim());
                break;
            case 3:
                secondRowExcel.setDiscountPrice(value.trim());
                break;
            case 4:
                secondRowExcel.setPrice(value.trim());
                break;
            case 5:
                secondRowExcel.setFirstTradePrice(value.trim());
                break;
            case 6:
                secondRowExcel.setFirstTradeAmount(value.trim());
                break;
            case 7:
                secondRowExcel.setSecondTradePrice(value.trim());
                break;
            case 8:
                secondRowExcel.setSecondTradeAmount(value.trim());
                break;
            case 9:
                secondRowExcel.setCategory(value.trim());
                break;
        }
    }

    protected void secondWriteToExcel(File file, String str) {
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
