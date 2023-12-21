package javatest;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2023/10/24 10:32
 */

public class ModifyHosts {


	public static void main(String[] args) {
		File host = new File("C:\\Windows\\System32\\drivers\\etc\\hosts");
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(host)))) {
			String line = null;
			List<String> lines = new ArrayList<>();
			while ((line = reader.readLine()) != null) {
				lines.add(line);
			}
			System.out.println(Arrays.toString(lines.toArray()));


			BufferedWriter bufferedWriter = new BufferedWriter(new BufferedWriter(new FileWriter(host)));
			bufferedWriter.write("#11111111111111111111111111");
		} catch (FileNotFoundException e) {
			throw new RuntimeException(e);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}