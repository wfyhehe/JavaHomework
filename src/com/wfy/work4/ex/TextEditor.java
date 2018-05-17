package com.wfy.work4.ex;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements ActionListener {
    String tempString;
    //上次保存后的文件名和地址  
    String fileName = "";
    JTextArea wen = new JTextArea(20, 50);

    JMenuItem font = new JMenuItem("字体");
    JMenuItem color = new JMenuItem("颜色");
    //定义菜单项
    JMenuItem newFile = new JMenuItem("新建");
    JMenuItem open = new JMenuItem("打开");
    JMenuItem save = new JMenuItem("保存 ");
    JMenuItem saveAs = new JMenuItem("另存为");
    JMenuItem exit = new JMenuItem("退出");

    JMenuItem cut = new JMenuItem("剪切 ");
    JMenuItem copy = new JMenuItem("复制");
    JMenuItem cast = new JMenuItem("粘贴");
    JMenuItem delete = new JMenuItem("删除 ");

    JMenuItem normal = new JMenuItem("普通");
    JMenuItem bold = new JMenuItem("粗体");
    JMenuItem italic = new JMenuItem("斜体");

    JMenuItem black = new JMenuItem("黑色");
    JMenuItem red = new JMenuItem("红色");
    JMenuItem blue = new JMenuItem("蓝色");

    TextEditor() {
        super("文本编辑器");
        setBounds(250, 100, 700, 450);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                int option = JOptionPane.showConfirmDialog(
                        TextEditor.this, "Sure?", "Exit", JOptionPane.YES_NO_OPTION);
                if (option == JOptionPane.YES_OPTION)
                    if (e.getWindow() == TextEditor.this) {
                        System.exit(0);
                    } else {
                        return;
                    }
            }
        });
        //热键设置  
        newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
        cast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));

        //定义面板  
        add(new JScrollPane(wen));
        wen.setFont(new Font("楷体", Font.PLAIN, 20));

        //菜单栏的创建  
        JMenuBar menuBar = new JMenuBar();
        this.setJMenuBar(menuBar);
        menuBar.setOpaque(true);
        JMenu fileMenu = new JMenu("文件");
        fileMenu.add(newFile);
        fileMenu.add(open);
        fileMenu.add(save);
        fileMenu.add(saveAs);
        fileMenu.addSeparator();
        fileMenu.add(exit);
        menuBar.add(fileMenu);
        JMenu editMenu = new JMenu("编辑");
        editMenu.add(cut);
        editMenu.add(copy);
        editMenu.add(cast);
        editMenu.add(delete);
        menuBar.add(editMenu);

        JMenu format = new JMenu("格式");
        JMenu optionsMenu = new JMenu("字体");
        JMenu colorOptionsMenu = new JMenu("颜色");
        format.add(optionsMenu);
        format.add(colorOptionsMenu);
        optionsMenu.add(normal);
        optionsMenu.add(bold);
        optionsMenu.add(italic);
        colorOptionsMenu.add(black);
        colorOptionsMenu.add(red);
        colorOptionsMenu.add(blue);
        menuBar.add(format);

        //增加监听器
        newFile.addActionListener(this);
        open.addActionListener(this);
        save.addActionListener(this);
        saveAs.addActionListener(this);
        exit.addActionListener(this);
        cut.addActionListener(this);
        copy.addActionListener(this);
        cast.addActionListener(this);
        delete.addActionListener(this);
        font.addActionListener(this);
        color.addActionListener(this);
        normal.addActionListener(this);
        bold.addActionListener(this);
        italic.addActionListener(this);
        black.addActionListener(this);
        blue.addActionListener(this);
        red.addActionListener(this);
    }

    public static void main(String[] args) {
        TextEditor w = new TextEditor();
        w.pack();
        w.setVisible(true);
    }

    //重写方法
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if (e.getSource() == newFile) {
            newfile();
        } else if (e.getSource() == open) {
            openfile();
        } else if (e.getSource() == save) {
            savefile();
        } else if (e.getSource() == saveAs) {
            lsavefile();
        } else if (e.getSource() == cut) {
            cutfile();
        } else if (e.getSource() == copy) {
            copyfile();
        } else if (e.getSource() == cast) {
            castfile();
        } else if (e.getSource() == delete) {
            deletefile();
        } else if (e.getSource() == normal) {
            afile();
        } else if (e.getSource() == bold) {
            bfile();
        } else if (e.getSource() == italic) {
            cfile();
        } else if ("退出".equals(actionCommand)) {
            System.exit(0);
        }
    }

    // 方法定义
    public void newfile() {
        savefile();
        wen.setText(null);
        fileName = "";
    }

    //打开
    public void openfile() {
        FileDialog df = new FileDialog(this, "打开文件", FileDialog.LOAD);
        df.setVisible(true);
        //建立新文件
        File f = new File(df.getDirectory() + df.getFile());
        //用此文件的长度建立一个字符数组
        char ch[] = new char[(int) f.length()];
        //异常处理
        try {
            //读出数据，并存入字符数组ch中
            BufferedReader bw = new BufferedReader(new FileReader(f));
            bw.read(ch);
            bw.close();
        } catch (FileNotFoundException fe) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException ie) {
            System.out.println("IO error");
            System.exit(0);
        }
        String s = new String(ch);
        wen.setText(s);
    }

    //保存
    public void savefile() {
        if (fileName.equals("")) {
            FileDialog df = new FileDialog(this, "保存文件", FileDialog.SAVE);
            df.addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent ee) {
                    System.exit(0);
                }
            });
            df.setVisible(true);
            String s = wen.getText();
            try {
                File f = new File(df.getDirectory() + df.getFile());
                fileName = df.getDirectory() + df.getFile();
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(s, 0, s.length());
                bw.close();
            } catch (FileNotFoundException fe_) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException ie_) {
                System.out.println(" IO error");
                System.exit(0);
            }
        } else {  // 如果文件已经保存过
            String s = wen.getText();
            try {
                File f = new File(fileName);
                BufferedWriter bw = new BufferedWriter(new FileWriter(f));
                bw.write(s, 0, s.length());
                bw.close();

            } catch (FileNotFoundException fe_) {
                System.out.println("file not found");
                System.exit(0);
            } catch (IOException ie_) {
                System.out.println(" IO error");
                System.exit(0);
            }
        }
    }

    //另存为
    public void lsavefile() {
        FileDialog df = new FileDialog(this, "另存为", FileDialog.SAVE);
        df.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent ee) {
                System.exit(0);
            }
        });
        df.setVisible(true);
        String s = wen.getText();
        try {
            File f = new File(df.getDirectory() + df.getFile());
            BufferedWriter bw = new BufferedWriter(new FileWriter(f));
            bw.write(s, 0, s.length());
            bw.close();
        } catch (FileNotFoundException fe_) {
            System.out.println("file not found");
            System.exit(0);
        } catch (IOException ie_) {
            System.out.println(" IO error");
            System.exit(0);
        }
    }

    //剪切
    public void cutfile() {
        tempString = wen.getSelectedText();
        StringBuffer tmp = new StringBuffer(wen.getText());
        int start = wen.getSelectionStart();
        int len = wen.getSelectedText().length();
        tmp.delete(start, start + len);
        wen.setText(tmp.toString());
    }

    //复制
    public void copyfile() {
        tempString = wen.getSelectedText();
    }

    //粘贴
    public void castfile() {
        StringBuffer tmp = new StringBuffer(wen.getText());
        //得到要粘贴的位置
        int start = wen.getSelectionStart();
        tmp.insert(start, tempString);
        //用新文本设置原文本
        wen.setText(tmp.toString());
    }

    //删除
    public void deletefile() {
        StringBuffer tmp = new StringBuffer(wen.getText());
        int start = wen.getSelectionStart();
        int len = wen.getSelectedText().length();
        tmp.delete(start, start + len);
        wen.setText(tmp.toString());
    }

    //字体
    public void afile() {
        wen.setFont(new Font("楷体", Font.PLAIN, wen.getFont().getSize()));//普通文字
    }

    public void bfile() {
        wen.setFont(new Font("楷体", Font.BOLD, wen.getFont().getSize()));//粗体文字
    }

    public void cfile() {
        wen.setFont(new Font("楷体", Font.ITALIC, wen.getFont().getSize()));//斜体文字
    }
}