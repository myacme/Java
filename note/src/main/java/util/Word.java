package util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;
import org.apache.poi.util.Units;
import org.apache.poi.xwpf.usermodel.*;
import org.apache.xmlbeans.XmlCursor;
import org.openxmlformats.schemas.wordprocessingml.x2006.main.*;

import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/4/8 14:15
 */
public class Word {
	public static void substitutionVariable() {
		try {
			// 打开现有的Word文档
			XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get("C:\\Users\\MyAcme\\Desktop\\基本类型向上转型.docx")));
			// 替换文档中的变量
			replaceText(document, "${A1}", "--Replacement Text 1--");
//			replaceText(document, "${A2}", "---Replacement Text 2---");
			List<String> head = List.of("A1", "A2", "A3");
			List<Map<String, Object>> data = List.of(
					Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 3---"),
					Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 2---"));
			replaceTable(document, "${A2}", head, data);
			// 保存文档
			FileOutputStream out = new FileOutputStream("C:\\Users\\MyAcme\\Desktop\\replace.docx");
			document.write(out);
			out.close();
			document.close();
			System.out.println("Word文档中的变量替换完成！");
		} catch (Exception e) {
			System.err.println("发生异常：" + e.getMessage());
			e.printStackTrace();
		}
	}

	// 替换文本的辅助方法
	private static void replaceText(XWPFDocument doc, String findText, String replaceText) {
		for (XWPFParagraph p : doc.getParagraphs()) {
			for (XWPFRun r : p.getRuns()) {
				String text = r.getText(0);
				if (text != null && text.contains(findText)) {
					text = text.replace(findText, replaceText);
					r.setText(text, 0);
				}
			}
		}
	}

	private static void replaceTable(XWPFDocument doc, String key, List<String> head, List<Map<String, Object>> data) throws FileNotFoundException {
		for (XWPFParagraph p : doc.getParagraphs()) {
			for (XWPFRun r : p.getRuns()) {
				String text = r.getText(0);
				if (text != null) {
					text = text.trim();
					if (text.contains(key)) {
						r.setText(text.replace(key, ""), 0);
						XmlCursor cursor = p.getCTP().newCursor();
						// 在指定游标位置插入表格
						XWPFTable table = doc.insertNewTbl(cursor);
						CTTblPr tablePr = table.getCTTbl().getTblPr();
						CTTblWidth width = tablePr.addNewTblW();
						//设置表格宽度
						width.setW(BigInteger.valueOf(7000));
						insertInfo(table, head, data);
						return;
					}
				}
			}
		}
	}

	private static void insertInfo(XWPFTable table, List<String> head, List<Map<String, Object>> data) {
		//获取第一行
		XWPFTableRow row = table.getRow(0);
		//改表格宽度
//        CTTblPr tblPr = table.getCTTbl().getTblPr();
		//改变长度策略为自己调整 默认为auto
//        tblPr.getTblW().setType(STTblWidth.DXA);
		//设置表格宽度为7000
//        tblPr.getTblW().setW(BigInteger.valueOf(10));
		//根据头创建表格head
		//默认会创建一列，即从第2列开始
		for (int col = 1; col < head.size(); col++) {
			// 第一行创建了多少列，后续增加的行自动增加列
			CTTcPr cPr = row.createCell().getCTTc().addNewTcPr();
			cPr.addNewVAlign().setVal(STVerticalJc.CENTER);
			//设置单元格高度为500
//			row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(500));
			//可以用来设置单元格长度
			//CTTblWidth width = cPr.addNewTcW();
			//width.setW(BigInteger.valueOf(2000));
		}
		//循环给表格添加头信息
		for (int i = 0; i < head.size(); i++) {
			//往表格里面写入头信息
			row.getCell(i).setText(head.get(i));
		}
		//循环填充body列表
		for (Map<String, Object> datum : data) {
			row = table.createRow();
			//修改行高为500
//			row.getCtRow().addNewTrPr().addNewTrHeight().setVal(BigInteger.valueOf(500));
			Set<Map.Entry<String, Object>> entries = datum.entrySet();
			int i = 0;
			for (Map.Entry<String, Object> key : entries) {
				row.getCell(i).setText(String.valueOf(key.getValue()));
				i++;
			}
		}
	}

	/**
	 * 合并单元格
	 *
	 * @param table   table
	 * @param col     第几列
	 * @param fromRow 开始行
	 * @param toRow   结束行
	 */
	// word跨行并单元格
	public static void mergeCellsVertically(XWPFTable table, int col, int fromRow, int toRow) {
		for (int rowIndex = fromRow; rowIndex <= toRow; rowIndex++) {
			XWPFTableCell cell = table.getRow(rowIndex).getCell(col);
			if (rowIndex == fromRow) {
				// The first merged cell is set with RESTART merge value
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.RESTART);
			} else {
				// Cells which join (merge) the first one, are set with CONTINUE
				cell.getCTTc().addNewTcPr().addNewVMerge().setVal(STMerge.CONTINUE);
			}
		}
	}

	/**
	 * 替换图片
	 *
	 * @param doc
	 * @param key
	 * @param inputStream
	 * @param name
	 * @throws Exception
	 */
	private static void replacePicture(XWPFDocument doc, String key, InputStream inputStream, String name) throws Exception {
		for (XWPFParagraph p : doc.getParagraphs()) {
			for (XWPFRun r : p.getRuns()) {
				String text = r.getText(0);
				if (text != null) {
					text = text.trim();
					if (text.contains(key)) {
						r.setText(text.replace(key, ""), 0);
						int width = Units.pixelToEMU(300);
						int height = Units.pixelToEMU(200);
						r.addPicture(inputStream, XWPFDocument.PICTURE_TYPE_PNG, name, width, height);
						return;
					}
				}
			}
		}
	}

	public static void deepoove() throws IOException {
		List<String> head = List.of("A1", "A2", "A3");
		List<Map<String, Object>> data = List.of(
				Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 3---"),
				Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 2---"));
		Map<String, Object> fileValue = new HashMap<>();
		fileValue.put("${A1}", "----替换文本----");
		List<TextRenderData> rowData = List.of(
				new TextRenderData("A1"),
				new TextRenderData("A2"),
				new TextRenderData("A3"));
		RowRenderData rowRenderData = new RowRenderData(rowData);
		List<RowRenderData> datas = List.of(rowRenderData, rowRenderData);
		fileValue.put("${A2}", new MiniTableRenderData(rowRenderData,datas));
		fileValue.put("${A3}", new PictureRenderData(300, 200, "C:\\Users\\MyAcme\\Desktop\\picture.png"));
		FileInputStream file = new FileInputStream("C:\\Users\\MyAcme\\Desktop\\基本类型向上转型.docx");
		FileInputStream in = new FileInputStream("C:\\Users\\MyAcme\\Desktop\\picture.png");
		XWPFTemplate template = XWPFTemplate.compile("C:\\Users\\MyAcme\\Desktop\\基本类型向上转型.docx").render(fileValue);
		FileOutputStream out = new FileOutputStream("C:\\Users\\MyAcme\\Desktop\\replace.docx");
		template.write(out);
	}


	public static void main(String[] args) throws IOException {
		try {
			// 打开现有的Word文档
			XWPFDocument document = new XWPFDocument(Files.newInputStream(Paths.get("C:\\Users\\MyAcme\\Desktop\\replace.docx")));
			FileInputStream in = new FileInputStream("C:\\Users\\MyAcme\\Desktop\\picture.png");
			// 替换文档中的变量
//			replaceText(document, "${A1}", "--Replacement Text 1--");
//			replaceText(document, "${A2}", "---Replacement Text 2---");
			replacePicture(document, "${A1}", in, "picture.png");
			// 保存文档
			FileOutputStream out = new FileOutputStream("C:\\Users\\MyAcme\\Desktop\\replace.docx");
			document.write(out);
			out.close();
			document.close();
			System.out.println("Word文档中的变量替换完成！");
		} catch (Exception e) {
			System.out.println("发生异常：" + e.getMessage());
			e.printStackTrace();
		}
	}
}
