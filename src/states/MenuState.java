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

public class MenuState implements PackageState, ActionListener {
    final private Color backgroundColor = new Color(0, 10, 20);
    final private Color textColor = new Color(237, 242, 244);
    final private Color inputColor = new Color(0, 150, 170);

    private DataBase dataBase;
    final private JLabel title;
    final private JButton doctorsButton, pillsButton, patientButton, healthInsuranceHousesButton, diseasesButton, logoutButton, queryButton;
    final private JFrame frame;
    private boolean isAdmin;
    private int nextStateNumber;

    public MenuState(JFrame frame, boolean isAdmin){
        try{
            dataBase = new DataBase();
        }
        catch (Exception e){
            JOptionPane.showMessageDialog(null, e.getMessage());
        }

        this.frame = frame;
        this.frame.getContentPane().removeAll();
        this.frame.repaint();
        this.frame.setTitle("Queries");

        this.title = new JLabel();
        this.doctorsButton = new JButton();
        this.pillsButton = new JButton();
        this.patientButton = new JButton();
        this.healthInsuranceHousesButton = new JButton();
        this.diseasesButton = new JButton();
        this.logoutButton = new JButton();
        this.queryButton = new JButton();

        this.nextStateNumber = -1;

        this.isAdmin = isAdmin;

        InitVariables();
        AddToPanel();

        this.frame.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                RepositionGUI();
            }
        });
    }

    private void InitVariables(){
        RepositionGUI();

        this.frame.getContentPane().setBackground(this.backgroundColor);

        this.title.setText("Meniu Principal");
        this.title.setFont(new Font("Poppins Medium", Font.BOLD, 30));
        this.title.setForeground(this.textColor);
        this.title.setHorizontalAlignment(SwingConstants.CENTER);

        this.doctorsButton.setText("Doctori");
        this.doctorsButton.addActionListener(this);
        this.doctorsButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.doctorsButton.setFocusable(false);
        this.doctorsButton.setForeground(this.textColor);
        this.doctorsButton.setBackground(this.inputColor);

        this.pillsButton.setText("Medicamente");
        this.pillsButton.addActionListener(this);
        this.pillsButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.pillsButton.setFocusable(false);
        this.pillsButton.setForeground(this.textColor);
        this.pillsButton.setBackground(this.inputColor);

        this.patientButton.setText("Pacienti");
        this.patientButton.addActionListener(this);
        this.patientButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.patientButton.setFocusable(false);
        this.patientButton.setForeground(this.textColor);
        this.patientButton.setBackground(this.inputColor);

        this.healthInsuranceHousesButton.setText("Case Sanatate");
        this.healthInsuranceHousesButton.addActionListener(this);
        this.healthInsuranceHousesButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.healthInsuranceHousesButton.setFocusable(false);
        this.healthInsuranceHousesButton.setForeground(this.textColor);
        this.healthInsuranceHousesButton.setBackground(this.inputColor);

        this.diseasesButton.setText("Boli");
        this.diseasesButton.addActionListener(this);
        this.diseasesButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.diseasesButton.setFocusable(false);
        this.diseasesButton.setForeground(this.textColor);
        this.diseasesButton.setBackground(this.inputColor);

        this.logoutButton.setText("Logout");
        this.logoutButton.addActionListener(this);
        this.logoutButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.logoutButton.setFocusable(false);
        this.logoutButton.setForeground(this.textColor);
        this.logoutButton.setBackground(this.inputColor);

        this.queryButton.setText("Testele de azi");
        this.queryButton.addActionListener(this);
        this.queryButton.setFont(new Font("Poppins Medium", Font.BOLD, 20));
        this.queryButton.setFocusable(false);
        this.queryButton.setForeground(this.textColor);
        this.queryButton.setBackground(this.inputColor);
    }

    private void RepositionGUI(){
        this.title.setBounds(this.frame.getWidth() / 2 - (243 / 2), this.frame.getHeight() / 2 - (90 + 108), 243, 30);
        this.doctorsButton.setBounds(this.title.getX() + 35, this.title.getY() + 77, 170, 30);
        this.pillsButton.setBounds(this.doctorsButton.getX(), this.doctorsButton.getY() + 57, 170, 30);
        this.patientButton.setBounds(this.pillsButton.getX(), this.pillsButton.getY() + 57, 170, 30);
        this.healthInsuranceHousesButton.setBounds(this.patientButton.getX(), this.patientButton.getY() + 57, 170, 30);
        this.diseasesButton.setBounds(this.healthInsuranceHousesButton.getX(), this.healthInsuranceHousesButton.getY() + 57, 170, 30);
        this.logoutButton.setBounds(this.title.getX() + 273, this.title.getY() - 100, 170, 30);
        this.queryButton.setBounds(this.title.getX() - 203, this.title.getY() - 100, 170, 30);
    }

    private void AddToPanel(){
        this.frame.add(this.title);
        this.frame.add(this.doctorsButton);
        this.frame.add(this.pillsButton);
        this.frame.add(this.patientButton);
        this.frame.add(this.healthInsuranceHousesButton);
        this.frame.add(this.diseasesButton);
        this.frame.add(this.logoutButton);
        this.frame.add(this.queryButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == this.doctorsButton){
            this.nextStateNumber = 0;
            this.next(Package.pkg);
        }
        else if(e.getSource() == this.pillsButton){
            this.nextStateNumber = 1;
            this.next(Package.pkg);
        }
        else if(e.getSource() == this.patientButton){
            this.nextStateNumber = 2;
            this.next(Package.pkg);
        }
        else if(e.getSource() == this.healthInsuranceHousesButton){
            this.nextStateNumber = 3;
            this.next(Package.pkg);
        }
        else if(e.getSource() == this.diseasesButton){
            this.nextStateNumber = 4;
            this.next(Package.pkg);
        }
        else if(e.getSource() == this.logoutButton){
            this.prev(Package.pkg);
        }
        else if(e.getSource() == this.queryButton){
            dataBase.sendQuery("  SELECT Pacienti.Nume AS NumePacient, Pacienti.Prenume AS PrenumePacient, Doctori.Nume AS NumeDoctor, Doctori.Prenume AS PrenumeDoctor, PacientiDoctori.Data_Incepere, PacientiDoctori.Data_Final\n" +
                    "  FROM PacientiDoctori INNER JOIN Pacienti ON Pacienti.PacientID = PacientiDoctori.PacientID INNER JOIN Doctori ON PacientiDoctori.DoctorID = Doctori.DoctorID\n" +
                    "  WHERE PacientiDoctori.Data_Final >= (SELECT CAST( GETDATE() AS Date ))", false, false);
        }
    }

    @Override
    public void next(Package pkg) {
        switch(this.nextStateNumber){
            case 0:
                pkg.setState(new DoctorsState(this.frame, this.dataBase, this.isAdmin));
                break;
            case 1:
                pkg.setState(new PillsState(this.frame, this.dataBase, this.isAdmin));
                break;
            case 2:
                pkg.setState(new PatientsState(this.frame, this.dataBase, this.isAdmin));
                break;
            case 3:
                pkg.setState(new HealthInsuranceHousesState(this.frame, this.dataBase, this.isAdmin));
                break;
            case 4:
                pkg.setState(new DiseasesState(this.frame, this.dataBase, this.isAdmin));
                break;
        }
    }

    @Override
    public void prev(Package pkg) {
        this.dataBase.CloseConnection();
        pkg.setState(new LoginState(this.frame));
    }

    @Override
    public void printStatus() {
        System.out.println("Query State");
    }
}
