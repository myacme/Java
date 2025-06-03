package util;

import com.deepoove.poi.XWPFTemplate;
import com.deepoove.poi.data.MiniTableRenderData;
import com.deepoove.poi.data.PictureRenderData;
import com.deepoove.poi.data.RowRenderData;
import com.deepoove.poi.data.TextRenderData;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/4/8 14:15
 */
public class WordOfDeepoove {


	public static void deepoove() throws IOException {
		List<String> head = List.of("A1", "A2", "A3");
		List<Map<String, Object>> data = List.of(
				Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 3---"),
				Map.of("A1", "--Replacement Text 1--", "A2", "---Replacement Text 2---", "A3", "---Replacement Text 2---"));
		Map<String, Object> fileValue = new HashMap<>();
		fileValue.put("A1", "----替换文本----");
		List<TextRenderData> rowData = List.of(
				new TextRenderData("A1"),
				new TextRenderData("A2"),
				new TextRenderData("A3"));
		RowRenderData rowRenderData = new RowRenderData(rowData);
		List<RowRenderData> datas = List.of(rowRenderData, rowRenderData);
		fileValue.put("A2", new MiniTableRenderData(rowRenderData,datas));
		fileValue.put("A3", new PictureRenderData(300, 200, "C:/Users/MyAcme/Desktop/picture.png"));
//		FileInputStream file = new FileInputStream("C:\\Users\\MyAcme\\Desktop\\基本类型向上转型.docx");
//		FileInputStream in = new FileInputStream("C:\\Users\\MyAcme\\Desktop\\picture.png");
		XWPFTemplate template = XWPFTemplate.compile("基本类型向上转型.docx").render(fileValue);
		FileOutputStream out = new FileOutputStream("C:\\Users\\MyAcme\\Desktop\\replace.docx");
		template.write(out);
		out.flush();          //刷新
		out.close();         //关流
		template.close();
	}


	public static void main(String[] args) throws IOException {
		deepoove();
	}
}
