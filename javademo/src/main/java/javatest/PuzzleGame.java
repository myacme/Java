package javatest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class PuzzleGame extends JFrame {
    private JPanel panel;
    private int size = 3; // 3x3拼图
    private int tileSize = 100;
    private int[][] tiles;
    private int emptyX, emptyY;

    public PuzzleGame() {
        setTitle("拼图游戏");
        setSize(size * tileSize + 16, size * tileSize + 38);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        initTiles();
        createPanel();
        shuffle();
    }

    private void initTiles() {
        tiles = new int[size][size];
        int count = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                tiles[i][j] = count++;
            }
        }
        tiles[size-1][size-1] = 0;
        emptyX = size-1;
        emptyY = size-1;
    }

    private void createPanel() {
        panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawTiles(g);
            }
        };
        panel.setPreferredSize(new Dimension(size * tileSize, size * tileSize));
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                clickTile(e.getX(), e.getY());
            }
        });
        add(panel);
    }

    private void drawTiles(Graphics g) {
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(tiles[i][j] != 0) {
                    g.setColor(Color.BLUE);
                    g.fillRect(j * tileSize, i * tileSize, tileSize - 1, tileSize - 1);
                    g.setColor(Color.WHITE);
                    String text = String.valueOf(tiles[i][j]);
                    g.setFont(new Font("Arial", Font.BOLD, 32));
                    FontMetrics fm = g.getFontMetrics();
                    int x = j * tileSize + (tileSize - fm.stringWidth(text)) / 2;
                    int y = i * tileSize + (tileSize + fm.getAscent() - fm.getDescent()) / 2;
                    g.drawString(text, x, y);
                }
            }
        }
    }

    private void clickTile(int x, int y) {
        int tileX = y / tileSize;
        int tileY = x / tileSize;

        if((Math.abs(tileX - emptyX) == 1 && tileY == emptyY) ||
                (Math.abs(tileY - emptyY) == 1 && tileX == emptyX)) {
            tiles[emptyX][emptyY] = tiles[tileX][tileY];
            tiles[tileX][tileY] = 0;
            emptyX = tileX;
            emptyY = tileY;
            panel.repaint();

            if(checkWin()) {
                JOptionPane.showMessageDialog(this, "恭喜你赢了！");
                shuffle();
            }
        }
    }

    private void shuffle() {
        Random random = new Random();
        for(int i = 0; i < 1000; i++) {
            int direction = random.nextInt(4);
            int newEmptyX = emptyX;
            int newEmptyY = emptyY;

            switch(direction) {
                case 0: newEmptyX--; break; // 上
                case 1: newEmptyX++; break; // 下
                case 2: newEmptyY--; break; // 左
                case 3: newEmptyY++; break; // 右
            }

            if(newEmptyX >= 0 && newEmptyX < size && newEmptyY >= 0 && newEmptyY < size) {
                tiles[emptyX][emptyY] = tiles[newEmptyX][newEmptyY];
                tiles[newEmptyX][newEmptyY] = 0;
                emptyX = newEmptyX;
                emptyY = newEmptyY;
            }
        }
        panel.repaint();
    }

    private boolean checkWin() {
        int count = 1;
        for(int i = 0; i < size; i++) {
            for(int j = 0; j < size; j++) {
                if(tiles[i][j] != 0 && tiles[i][j] != count++) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new PuzzleGame().setVisible(true);
        });
    }
}
