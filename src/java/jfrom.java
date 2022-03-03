package java; /**
 * Copyright (C), 2015-2020, XXX有限公司
 * <p>
 * FileName: java.jfrom
 * <p>
 * Author:   MyAcme
 * <p>
 * Date:     2020/1/2 16:11
 * <p>
 * Description:
 * <p>
 * History:
 *
 * <author>          <time>          <version>          <desc>
 * <p>
 * 作者姓名           修改时间           版本号              描述
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

/**
 * 〈一句话功能简述〉<br> 
 * 〈〉
 *
 * @author MyAcme

 * @create 2020/1/2

 * @since 1.0.0

 */

public class jfrom extends JFrame {
    public jfrom(){
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        setSize(dimension.height/2,dimension.height/2);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        MenuBar mb = new MenuBar();
        Menu fileMenu = new Menu("File");
        Menu fileMenu1 = new Menu("File1");
        mb.add(fileMenu1);
        mb.add(fileMenu);
        MenuItem open = new MenuItem("open");
        MenuItem close = new MenuItem("close");
        open.addActionListener((ActionEvent e)->JOptionPane.showMessageDialog(jfrom.this, "菜单被点击"));
        close.addActionListener((ActionEvent e)->System.exit(0));
        fileMenu.add(open);
        fileMenu.add(close);
        setMenuBar(mb);
        setVisible(true);
    }

    public static void main(String[] args) {
        new jfrom();
    }

}