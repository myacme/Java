/**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: ReadExcel
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/9/8 14:08
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/9/8

 * @since 1.0.0

 */

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;

public class ReadExcel {

    public static void test(){
        try{
            jxl.Workbook wb =null;
            InputStream is = new FileInputStream("E://接口地址说明.xlsx");
            wb = Workbook.getWorkbook(is);

            int sheetSize = wb.getNumberOfSheets();
            Sheet sheet = wb.getSheet(0);
            int row_total = sheet.getRows();
            for (int j = 0; j < row_total; j++) {
                if(j == 0){
                    Cell[] cells = sheet.getRow(j);

                    System.out.println(cells[0].getContents());
                    System.out.println(cells[1].getContents());
                    System.out.println(cells[2].getContents());
                }
            }
        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (BiffException e){
            e.printStackTrace();
        }
    }
    public static void test1(){
        InputStream is =null;
        byte[] bytes=new byte[1000];
        try {
             is = new FileInputStream("E://接口地址说明.xlsx");
             is.read(bytes);
            System.out.println(bytes.toString());

            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {

            byte[] secretBytes = MessageDigest.getInstance("md5").digest(
                    "123".getBytes());
            System.out.println(secretBytes.toString());
        }catch (Exception e){

        }

    }

}