/**
 * FileName: IdeaCode
 * <p>
 * Author:   liujixiang
 * <p>
 * Date:     2021/2/25 10:37
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * 〈一句话功能简述〉<br>
 * 〈〉
 *
 * @author ljx
 * @create 2021/2/25
 * @since 1.0.0
 */

public class IdeaCode {

	static InputStream get(String url) {
		InputStream inputStream = null;
		try {
			URL readUrl = new URL(url);
			URLConnection connection = readUrl.openConnection();
			connection.setRequestProperty("accept", "*/*");
			connection.setRequestProperty("connection", "Keep-Alive");
			connection.setRequestProperty("user-agent",
					"Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
			connection.connect();
			inputStream = connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return inputStream;
	}

	static void getCode(InputStream inputStream) {
//		String code = null;
		FileOutputStream fos = null;
		ZipInputStream zis = new ZipInputStream(inputStream, Charset.forName("GBK"));
//		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			ZipEntry entry = zis.getNextEntry();
			while (entry != null) {
				// 是文件夹的话就跳过
				if (entry.isDirectory()) {
					entry = zis.getNextEntry();
					continue;
				}
				// 获取文件名
				String fileName = entry.getName();
				if (fileName.contains("之后")) {
					int len;
					byte[] bytes = new byte[1024];
					System.out.println(fileName);
					fos = new FileOutputStream("D:\\OneDrive\\Desktop\\code.txt");
					while ((len = zis.read(bytes)) != -1) {
						fos.write(bytes, 0, len);
//						baos.write(bytes, 0, len);
					}
//					code = new String(baos.toByteArray());
				}
				entry = zis.getNextEntry();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				inputStream.close();
//				baos.close();
				zis.close();
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		getCode(get("http://idea.medeming.com/a/jihuoma1.zip"));
	}
}