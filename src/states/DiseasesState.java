package states;

import Database.DataBase;
import stateDesign.Package;
import stateDesign.PackageState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class DiseasesState implements PackageState, ActionListener {
    final private Color backgroundColor = new Color(0, 10, 20);
    final private Color textColor = new Color(237, 242, 244);
    final private Color inputColor = new Color(0, 150, 170);
    final private JFrame frame;
    final private DataBase dataBase;
    final private JLabel title, query1Text;
    final private JButton viewButton, deleteButton, insertButton, backButton, query1Button;

    public DiseasesState(JFrame frame, DataBase dataBase) {
        this.frame = frame;
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
        this.frame.setTitle("Boli");

        this.dataBase = dataBase;

        this.title = new JLabel();
        this.query1Text = new JLabel();

        this.viewButton = new JButton();
        this.deleteButton = new JButton();
        this.insertButton = new JButton();
        this.backButton = new JButton();
        this.query1Button = new JButton();

        InitVariables();
        AddToPanel();

        this.frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RepositionGUI();
            }
        });
    }

    private void InitVariables() {
        RepositionGUI();

        this.frame.getContentPane().setBackground(this.backgroundColor);

        this.title.setText("Boli");
        this.title.setFont(new Font("Poppins Medium", Font.BOLD, 30));
        this.title.setForeground(this.textColor);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);

        this.query1Text.setText("<html><center>" + "Bolile care au infectat cel putin 8 pacienti" + "</center></html>");
        this.query1Text.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.query1Text.setForeground(this.textColor);
        this.query1Text.setHorizontalAlignment(SwingConstants.CENTER);

        this.viewButton.setText("View");
        this.viewButton.addActionListener(this);
        this.viewButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.viewButton.setFocusable(false);
        this.viewButton.setForeground(this.textColor);
        this.viewButton.setBackground(this.inputColor);

        this.deleteButton.setText("Delete");
        this.deleteButton.addActionListener(this);
        this.deleteButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.deleteButton.setFocusable(false);
        this.deleteButton.setForeground(this.textColor);
        this.deleteButton.setBackground(this.inputColor);

        this.insertButton.setText("Insert");
        this.insertButton.addActionListener(this);
        this.insertButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.insertButton.setFocusable(false);
        this.insertButton.setForeground(this.textColor);
        this.insertButton.setBackground(this.inputColor);

        this.backButton.setText("Back");
        this.backButton.addActionListener(this);
        this.backButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.backButton.setFocusable(false);
        this.backButton.setForeground(this.textColor);
        this.backButton.setBackground(this.inputColor);

        this.query1Button.setText("Execute");
        this.query1Button.addActionListener(this);
        this.query1Button.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.query1Button.setFocusable(false);
        this.query1Button.setForeground(this.textColor);
        this.query1Button.setBackground(this.inputColor);
    }

    private void RepositionGUI(){
        int auxWidth = this.frame.getWidth() / 2;
        int auxHeight = this.frame.getHeight() / 2;

        this.title.setBounds(auxWidth - (313), auxHeight - (90 + 68), 190, 30);
        this.viewButton.setBounds(this.title.getX() + 10, this.title.getY() + 77, 170, 30);
        this.deleteButton.setBounds(this.viewButton.getX(), this.viewButton.getY() + 57, 170, 30);
        this.insertButton.setBounds(this.deleteButton.getX(), this.deleteButton.getY() + 57, 170, 30);
        this.backButton.setBounds(this.title.getX() + 500, this.title.getY() - 110, 170, 30);
        this.query1Text.setBounds(this.viewButton.getX() + 300, this.viewButton.getY(), 313, 60);
        this.query1Button.setBounds(this.query1Text.getX() + (313/2) - (170/2), this.query1Text.getY() + 80, 170, 30);
    }

    private void AddToPanel(){
        this.frame.add(this.title);
        this.frame.add(this.viewButton);
        this.frame.add(this.deleteButton);
        this.frame.add(this.insertButton);
        this.frame.add(this.backButton);
        this.frame.add(this.query1Text);
        this.frame.add(this.query1Button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.viewButton)
            dataBase.SendQuery("SELECT * FROM Boli");

        if(e.getSource() == this.insertButton)
            this.next(Package.pkg);

        else if(e.getSource() == this.backButton)
            this.prev(Package.pkg);
    }

    @Override
    public void next(Package pkg) {
        pkg.setState(new InsertState(this.frame, "Diseases", this.dataBase));
    }

    @Override
    public void prev(Package pkg) {
        pkg.setState(new MenuState(this.frame));
    }

    @Override
    public void printStatus() {
        System.out.println("Diseases State");
    }
}