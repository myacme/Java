import org.apache.commons.collections.MapUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @version 1.0
 * @date 2022/6/29
 */

public class ExcelUtil {


    public static void export(String title, List<String> keyList, List<String> fieldList, List<Map<String, Object>> dataList, String fileName) throws Exception {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(fileName);
        CellStyle style1 = workbook.createCellStyle();
        CellStyle style2 = workbook.createCellStyle();
        CellStyle style3 = setStyle3(workbook);
        style1.setAlignment(HorizontalAlignment.CENTER);
        style1.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setAlignment(HorizontalAlignment.CENTER);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setVerticalAlignment(VerticalAlignment.CENTER);
        style2.setWrapText(true);
        Font font1 = workbook.createFont();
        Font font2 = workbook.createFont();
        font1.setFontHeightInPoints((short) 18);
        font1.setFontName("宋体");
        font1.setBold(true);
        font2.setFontHeightInPoints((short) 11);
        font2.setFontName("宋体");
        font2.setBold(true);
        style1.setFont(font1);
        style2.setFont(font2);
        Row row = sheet.createRow(0);
        Cell cell = row.createCell(0);
        cell.setCellStyle(style1);
        cell.setCellValue(title);
        CellRangeAddress region = new CellRangeAddress(0, 0, 0, fieldList.size());
        RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
        RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
        sheet.addMergedRegion(region);
        for (int i = 0; i < fieldList.size(); i++) {
            // 自动调整列宽 不支持中文
            sheet.autoSizeColumn(i, true);
            if (i == 0) {
                sheet.setColumnWidth(i, 30 * 256);
            } else {
                sheet.setColumnWidth(i, 20 * 256);
            }
        }
        row = sheet.createRow(1);
        for (int i = 0; i < fieldList.size(); i++) {
            cell = row.createCell(i);
            cell.setCellStyle(style2);
            cell.setCellValue(fieldList.get(i));
        }
        for (int i = 0; i < dataList.size(); i++) {
            row = sheet.createRow(i + 2);
            Map<String, Object> dataMap = dataList.get(i);
            for (int j = 0; j < keyList.size(); j++) {
                cell = row.createCell(j, CellType.STRING);
                cell.setCellValue(MapUtils.getString(dataMap, keyList.get(j), "-"));
                cell.setCellStyle(style3);
            }
        }
    }

    public static CellStyle setStyle3(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        style.setAlignment(HorizontalAlignment.CENTER);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setVerticalAlignment(VerticalAlignment.CENTER);
        style.setDataFormat(workbook.createDataFormat().getFormat("@"));
        style.setWrapText(true);
        Font font3 = workbook.createFont();
        font3.setFontHeightInPoints((short) 10);
        font3.setFontName("宋体");
        style.setFont(font3);
        return style;
    }


    /**
     * 导出无表头excle  poi:4.1.2
     *
     * @param keyList
     * @param dataList
     * @param response
     * @throws Exception
     */

    public static void export(List<String> keyList, List<LinkedHashMap<String, Object>> dataList) throws Exception {
        try (Workbook workbook = new XSSFWorkbook()) {
            Sheet sheet = workbook.createSheet("sheet0");
            for (int i = 0; i < keyList.size(); i++) {
                sheet.setColumnWidth(i, 30 * 256);
            }
            CellStyle style = workbook.createCellStyle();
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setBorderBottom(BorderStyle.THIN);
            style.setBorderTop(BorderStyle.THIN);
            style.setBorderLeft(BorderStyle.THIN);
            style.setBorderRight(BorderStyle.THIN);
            Font font = workbook.createFont();
            font.setFontHeightInPoints((short) 14);
            font.setFontName("宋体");
            style.setFont(font);
            for (int i = 0; i < dataList.size(); i++) {
                Row row = sheet.createRow(i);
                LinkedHashMap<String, Object> dataMap = dataList.get(i);
                for (int j = 0; j < keyList.size(); j++) {
                    Cell cell = row.createCell(j);
                    cell.setCellValue(MapUtils.getString(dataMap, keyList.get(j), ""));
                    cell.setCellStyle(style);
                }
            }
//            workbook.write(response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}