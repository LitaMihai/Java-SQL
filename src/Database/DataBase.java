package Database;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {
    final private Connection connection;
    final private Statement statement;
    final private ArrayList<JFrame> frames;
    final private HashMap<JTable, DefaultTableModel> tables;

    public DataBase(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String dbURL = "jdbc:sqlserver://DESKTOP-U62N61T\\SQLEXPRESS;" +
                    "Database=EPTM;" +
                    "IntegratedSecurity=true;" +
                    "encrypt=true;trustServerCertificate=true";
            String userName = "sa";
            String pass = "ed308";
            this.connection = DriverManager.getConnection(dbURL, userName, pass);
            this.statement = this.connection.createStatement();
            this.tables = new HashMap<>();
            this.frames = new ArrayList<>();
        }
        catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void CloseConnection(){
        try {
            this.connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public int sendInsert(String query){
        int done;
        try{
            System.out.println(query);
            done = this.statement.executeUpdate(query);
            return done;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Statement getStatement(){
        return this.statement;
    }

    public void SendQuery(String query, boolean isAdditionalQuery, boolean isFirstAdditionalQuery){
        ResultSet resultSet;
        try {
            resultSet = this.statement.executeQuery(query);
            String tabela;

            // Clear the table
            DefaultTableModel model = new DefaultTableModel();
            JTable table = new JTable(model);

            table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 15));
            table.setFont(new Font("Poppins Medium", Font.BOLD, 15));
            table.getTableHeader().setOpaque(false);
            table.getTableHeader().setBackground(new Color(32, 136, 203));
            table.getTableHeader().setForeground(new Color(255,255,255));
            table.setRowHeight(25);
            table.setFocusable(false);
            table.setIntercellSpacing(new java.awt.Dimension(0, 0));
            table.setRowHeight(25);
            table.setSelectionBackground(new java.awt.Color(232, 57, 95));
            table.setShowVerticalLines(false);
            table.getTableHeader().setReorderingAllowed(false);
            table.setPreferredScrollableViewportSize(table.getPreferredSize());

            model.getDataVector().removeAllElements();
            model.setColumnCount(0);
            model.fireTableDataChanged();

            tabela = getTableNameFromQuery(query);
            // System.out.println(tabela);
            if(!isAdditionalQuery){
                switch (tabela) {
                    case "Doctori" -> {
                        model.addColumn("Nume");
                        model.addColumn("Prenume");
                        model.addColumn("Specializare");
                        while (resultSet.next()) {
                            String s1 = resultSet.getString(2);
                            String s2 = resultSet.getString(3);
                            String s3 = resultSet.getString(4);

                            model.addRow(new Object[]{s1, s2, s3});
                        }
                    }

                    case "Medicamente " -> {
                        model.addColumn("Denumire Medicament");
                        model.addColumn("Boala Tratata");
                        model.addColumn("Reactii Adverse Posibile");
                        while (resultSet.next()) {
                            String s1 = resultSet.getString(1);
                            String s2 = resultSet.getString(2);
                            String s3 = resultSet.getString(3);

                            model.addRow(new Object[]{s1, s2, s3});
                        }
                    }

                    case "Pacienti " -> {
                        model.addColumn("Nume");
                        model.addColumn("Prenume");
                        model.addColumn("CNP");
                        model.addColumn("Strada");
                        model.addColumn("Nr");
                        model.addColumn("Oras");
                        model.addColumn("Judet");
                        model.addColumn("Sex");
                        model.addColumn("Data Nasterii");
                        model.addColumn("Casa Sanatate");
                        model.addColumn("Medicament Testat");
                        while (resultSet.next()) {
                            String s1 = resultSet.getString(1);
                            String s2 = resultSet.getString(2);
                            String s3 = resultSet.getString(3);
                            String s4 = resultSet.getString(4);
                            String s5 = resultSet.getString(5);
                            String s6 = resultSet.getString(6);
                            String s7 = resultSet.getString(7);
                            String s8 = resultSet.getString(8);
                            String s9 = resultSet.getString(9);
                            String s10 = resultSet.getString(10);
                            String s11 = resultSet.getString(11);

                            model.addRow(new Object[]{s1, s2, s3, s4, s5, s6, s7, s8, s9, s10, s11});
                        }
                    }

                    case "CaseDeSanatate" -> {
                        model.addColumn("Nume");
                        while (resultSet.next()) {
                            String s1 = resultSet.getString(2);

                            model.addRow(new Object[]{s1});
                        }
                    }

                    case "Boli" -> {
                        model.addColumn("Nume");
                        while (resultSet.next()) {
                            String s1 = resultSet.getString(2);

                            model.addRow(new Object[]{s1});
                        }
                    }
                }
            }
            else{
                switch (tabela) {
                    case "Doctori " -> {
                        if(isFirstAdditionalQuery){
                            model.addColumn("Nume");
                            model.addColumn("Prenume");
                            model.addColumn("Specializare");
                            model.addColumn("NrPacienti");
                            while (resultSet.next()) {
                                String s1 = resultSet.getString(1);
                                String s2 = resultSet.getString(2);
                                String s3 = resultSet.getString(3);
                                String s4 = resultSet.getString(4);

                                model.addRow(new Object[]{s1, s2, s3, s4});
                            }
                        }
                        else{
                            model.addColumn("Nume");
                            model.addColumn("Prenume");
                            model.addColumn("Specializare");

                            while (resultSet.next()) {
                                String s1 = resultSet.getString(1);
                                String s2 = resultSet.getString(2);
                                String s3 = resultSet.getString(3);

                                model.addRow(new Object[]{s1, s2, s3});
                            }
                        }
                    }

                    case "Medicamente " -> {
                        model.addColumn("Denumire Medicament");
                        model.addColumn("Reactii Adverse Posibile");

                        while (resultSet.next()) {
                            String s1 = resultSet.getString(1);
                            String s2 = resultSet.getString(2);

                            model.addRow(new Object[]{s1, s2});
                        }
                    }

                    case "Pacienti " -> {
                        if(isFirstAdditionalQuery){
                            model.addColumn("Nume");
                            model.addColumn("Prenume");
                            model.addColumn("CNP");
                            model.addColumn("Casa Sanatate");

                            while (resultSet.next()) {
                                String s1 = resultSet.getString(1);
                                String s2 = resultSet.getString(2);
                                String s3 = resultSet.getString(3);
                                String s4 = resultSet.getString(4);

                                model.addRow(new Object[]{s1, s2, s3, s4});
                            }
                        }
                        else{
                            model.addColumn("Nume");
                            model.addColumn("Prenume");
                            model.addColumn("CNP");

                            while (resultSet.next()) {
                                String s1 = resultSet.getString(1);
                                String s2 = resultSet.getString(2);
                                String s3 = resultSet.getString(3);

                                model.addRow(new Object[]{s1, s2, s3});
                            }
                        }
                    }

                    case "CaseDeSanatate " -> {
                        model.addColumn("Nume");
                        model.addColumn("NrAsigurati");

                        while (resultSet.next()) {
                            String s1 = resultSet.getString(1);
                            String s2 = resultSet.getString(2);

                            model.addRow(new Object[]{s1, s2});
                        }
                    }

                    case "Boli " -> {
                        model.addColumn("Nume");
                        model.addColumn("NrPacienti");

                        while (resultSet.next()) {
                            String s1 = resultSet.getString(1);
                            String s2 = resultSet.getString(2);

                            model.addRow(new Object[]{s1, s2});
                        }
                    }
                }
            }

            tables.put(new JTable(), new DefaultTableModel());

            JFrame frame = new JFrame();
            frame.setSize(750, 550);
            frame.dispose();
            frame.add(new JScrollPane(table));
            frame.setVisible(true);
            frame.setTitle(tabela);

            frames.add(new JFrame());
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    private String getTableNameFromQuery(String query){
        // Here we re finding the index of the blank space after the table name
        int index = query.indexOf("FROM") + 5;
        while(query.charAt(index) != ' ' && index < query.length() - 1){
            index++;
        }
        index++;

        return query.substring(query.indexOf("FROM") + 5, index);
    }
}
