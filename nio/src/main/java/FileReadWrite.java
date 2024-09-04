import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.List;

/**
 * @author ljx
 * @version 1.0.0
 * @create 2024/8/6 下午3:19
 */
public class FileReadWrite {

    public static void copy() throws IOException {
        Path read = Paths.get("r0.txt");
        Path write = Paths.get("w0.txt");
        //存在则替换
        Files.copy(read, write, StandardCopyOption.REPLACE_EXISTING);
        //移动/重命名
        Files.move(read, write);
    }

    /**
     * 遍历查询
     */
    public static void traversalQuery() throws IOException {
        Path path = Paths.get("D://");
        String fileName = "/r0.txt";
        Files.walkFileTree(path, new SimpleFileVisitor<Path>(){
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                if (file.toString().endsWith(fileName)){
                    System.out.println(file);
                    //终止
                    return FileVisitResult.TERMINATE;
                }
                //继续
                return FileVisitResult.CONTINUE;
            }
        });
    }

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("r0.txt");
        // 路径格式化  path.normalize();
        List<String> lines = Files.readAllLines(path, StandardCharsets.UTF_8);
        for (String line : lines) {
            System.out.println(line);
        }
        Path path1 = Paths.get("w0.txt");
        Files.write(path1, lines, StandardCharsets.UTF_8);
        //删除
        Files.delete(path1);
    }
}