package com.wfy.work4.e13.control;


import com.wfy.work4.e13.model.Actions;
import com.wfy.work4.e13.model.People;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Main extends JFrame {

    private MenuBar menubar;
    private Menu menu;
    private MenuItem add, update, delete, query;
    private JPanel contentPane;
    private JTextField txtName;
    private JTextField txtSex;
    private JTextField txtNumber;
    private JTextField txtEmail;
    private JTextField txtAddress;
    private JLabel label;
    private JComboBox comboBox;
    private Actions actions = new Actions();
    private JTextField txtID;

    public Main() {
        setResizable(false);
        setTitle("通讯录");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        menubar = new MenuBar();
        menu = new Menu("操作");
        add = new MenuItem("添加");
        add.addActionListener(e -> {
            People people = new People(
                    Integer.parseInt(txtID.getText()),
                    txtName.getText(),
                    txtSex.getText(),
                    txtNumber.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()
            );
            try {
                People queriedPeople = actions.queryId(people.getId());
                if (queriedPeople != null) {
                    JOptionPane.showMessageDialog(null, "The input name already exists!");
                } else {

                    actions.add(people);
                    update();
                    JOptionPane.showMessageDialog(null, "Add successfully!");
                }

            } catch (Exception e1) {
                JOptionPane.showMessageDialog(null, "Duplicate entry " + txtID.getText() + " for key 'PRIMARY'");
            }
        });
        update = new MenuItem("更新");
        update.addActionListener(e -> {
            int i = Integer.parseInt(txtID.getText());
            People people = new People(
                    i,
                    txtName.getText(),
                    txtSex.getText(),
                    txtNumber.getText(),
                    txtEmail.getText(),
                    txtAddress.getText()
            );
            try {

                actions.update(people, i);
                update();
                JOptionPane.showMessageDialog(null, "Update successfully!");

            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        delete = new MenuItem("删除");
        delete.addActionListener(e -> {
            try {
                int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
                if (n == JOptionPane.YES_OPTION) {
                    actions.delete(comboBox.getSelectedItem().toString());
                    update();
                    JOptionPane.showMessageDialog(null, "Delete successfully!");
                }

            } catch (NumberFormatException e1) {
                e1.printStackTrace();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        });
        query = new MenuItem("查询");
        query.addActionListener(e -> {
            try {
                People people = actions.queryString(comboBox.getSelectedItem().toString());
                txtName.setText(people.getName());
                txtEmail.setText(people.getEmail());
                txtNumber.setText(people.getNumber());
                txtAddress.setText(people.getAddress());
                txtSex.setText(people.getSex());
                txtID.setText(people.getId() + "");

            } catch (Exception e1) {
                e1.printStackTrace();
                JOptionPane.showMessageDialog(null, "Query failed!");
            }
        });
        menu.add(query);
        menu.add(add);
        menu.add(update);
        menu.add(delete);
        menubar.add(menu);
        setMenuBar(menubar);

        JLabel lblName = new JLabel("Name:");
        lblName.setBounds(10, 46, 54, 15);
        contentPane.add(lblName);

        JLabel lblSex = new JLabel("Sex:");
        lblSex.setBounds(10, 82, 54, 15);
        contentPane.add(lblSex);

        JLabel lblNumber = new JLabel("Num:");
        lblNumber.setBounds(10, 122, 43, 15);
        contentPane.add(lblNumber);

        JLabel lblAddress = new JLabel("Address:");
        lblAddress.setBounds(10, 160, 54, 15);
        contentPane.add(lblAddress);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 198, 43, 15);
        contentPane.add(lblEmail);

        txtName = new JTextField();
        txtName.setBounds(55, 43, 134, 21);
        contentPane.add(txtName);
        txtName.setColumns(10);

        txtSex = new JTextField();
        txtSex.setBounds(55, 79, 134, 21);
        contentPane.add(txtSex);
        txtSex.setColumns(10);

        txtNumber = new JTextField();
        txtNumber.setBounds(55, 119, 134, 21);
        contentPane.add(txtNumber);
        txtNumber.setColumns(10);

        txtAddress = new JTextField();
        txtAddress.setBounds(55, 157, 134, 21);
        contentPane.add(txtAddress);
        txtAddress.setColumns(10);

        txtEmail = new JTextField();
        txtEmail.setBounds(55, 195, 134, 21);
        contentPane.add(txtEmail);
        txtEmail.setColumns(10);

        label = new JLabel("Contacts：");
        label.setBackground(Color.BLUE);
        label.setBounds(230, 10, 76, 18);
        contentPane.add(label);

        JButton btnUpdate = new JButton("Update");
        btnUpdate.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int i = Integer.parseInt(txtID.getText());
                People people = new People(
                        i,
                        txtName.getText(),
                        txtSex.getText(),
                        txtNumber.getText(),
                        txtEmail.getText(),
                        txtAddress.getText()
                );
                try {

                    actions.update(people, i);
                    update();
                    JOptionPane.showMessageDialog(null, "Update successfully!");

                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnUpdate.setBounds(331, 179, 93, 28);
        contentPane.add(btnUpdate);

        JButton btnDelete = new JButton("Delete"); // Delete the people from MySQL DataBase.
        btnDelete.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {
                    int n = JOptionPane.showConfirmDialog(null, "确认删除吗?", "确认删除框", JOptionPane.YES_NO_OPTION);
                    if (n == JOptionPane.YES_OPTION) {
                        actions.delete(comboBox.getSelectedItem().toString());
                        update();
                        JOptionPane.showMessageDialog(null, "Delete successfully!");
                    }

                } catch (NumberFormatException e1) {
                    e1.printStackTrace();
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            }
        });
        btnDelete.setBounds(331, 230, 93, 28);
        contentPane.add(btnDelete);

        JButton btnQuery = new JButton("Query"); // Query the detail of people from MySQL DataBase.
        btnQuery.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try {

                    People people = actions.queryString(comboBox.getSelectedItem().toString());
                    txtName.setText(people.getName());
                    txtEmail.setText(people.getEmail());
                    txtNumber.setText(people.getNumber());
                    txtAddress.setText(people.getAddress());
                    txtSex.setText(people.getSex());
                    txtID.setText(people.getId() + "");

                } catch (Exception e1) {
                    e1.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Query failed!");
                }
            }
        });
        btnQuery.setBounds(331, 131, 93, 28);
        contentPane.add(btnQuery);

        JButton btnAdd = new JButton("Add");//Add the people into MySQL DataBase.
        btnAdd.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                People people = new People(
                        Integer.parseInt(txtID.getText()),
                        txtName.getText(),
                        txtSex.getText(),
                        txtNumber.getText(),
                        txtEmail.getText(),
                        txtAddress.getText()
                );
                try {
                    People queriedPeople = actions.queryId(people.getId());
                    if (queriedPeople != null) {
                        JOptionPane.showMessageDialog(null, "The input name already exists!");
                    } else {

                        actions.add(people);
                        update();
                        JOptionPane.showMessageDialog(null, "Add successfully!");
                    }

                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(null, "Duplicate entry " + txtID.getText() + " for key 'PRIMARY'");
                }
            }
        });
        btnAdd.setBounds(331, 82, 93, 28);
        contentPane.add(btnAdd);

        comboBox = new JComboBox();//把数据库中的所有联系人添加到下拉列表中
        comboBox.setBounds(228, 37, 196, 33);
        update();

        JButton btnClear = new JButton("Clear");//Clear all of JTextField.
        btnClear.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clear();
            }
        });
        btnClear.setBounds(230, 230, 93, 28);
        contentPane.add(btnClear);

        txtID = new JTextField();
        txtID.setBounds(55, 10, 134, 21);
        contentPane.add(txtID);
        txtID.setColumns(10);

        JLabel lblNewLabel = new JLabel("ID:");
        lblNewLabel.setBounds(10, 13, 54, 15);
        contentPane.add(lblNewLabel);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {

                Main frame = new Main();
                frame.setVisible(true);

            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public void clear() {
        txtName.setText("");
        txtAddress.setText("");
        txtEmail.setText("");
        txtNumber.setText("");
        txtSex.setText("");
        txtID.setText("");
    }

    public void update() {//Update the contacts according to MySQL DataBase;
        java.util.List<People> list;
        try {

            list = actions.query();
            String[] strings = new String[list.size()];
            int i = 0;
            for (People people : list) {
                strings[i++] = people.getName();
            }
            comboBox.setModel(new DefaultComboBoxModel<>(strings));
            clear();

        } catch (Exception e) {
        }
        contentPane.add(comboBox);
    }
}
