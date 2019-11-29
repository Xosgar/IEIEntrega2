package GUI;


import com.sun.org.apache.xpath.internal.functions.FuncFalse;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class App {

    private JPanel panelMain;
    private JComboBox Marca;
    private JTextField Modelo;
    private JRadioButton btn_Amazon;
    private JRadioButton btn_PCComp;
    private JRadioButton btn_Fnac;
    private JButton btn_Buscar;


    public App() {
        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
//lamar la clase controladora
            }
        });

        btn_Buscar.setEnabled(false);


        Modelo.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                if ((Modelo.getText().length() > 0) &&
                        (btn_Amazon.isSelected() || btn_PCComp.isSelected() || btn_Fnac.isSelected())) {
                    btn_Buscar.setEnabled(true);

                } else {
                    btn_Buscar.setEnabled(false);
                }

            }
        });


        btn_Amazon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((Modelo.getText().length() > 0) &&
                        (btn_Amazon.isSelected() || btn_PCComp.isSelected() || btn_Fnac.isSelected())) {
                    btn_Buscar.setEnabled(true);

                } else {
                    btn_Buscar.setEnabled(false);
                }
            }
        });
        btn_PCComp.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((Modelo.getText().length() > 0) &&
                        (btn_Amazon.isSelected() || btn_PCComp.isSelected() || btn_Fnac.isSelected())) {
                    btn_Buscar.setEnabled(true);

                } else {
                    btn_Buscar.setEnabled(false);
                }
            }
        });
        btn_Fnac.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if ((Modelo.getText().length() > 0) &&
                        (btn_Amazon.isSelected() || btn_PCComp.isSelected() || btn_Fnac.isSelected())) {
                    btn_Buscar.setEnabled(true);

                } else {
                    btn_Buscar.setEnabled(false);
                }
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Búsqueda de Móviles");
        frame.setContentPane(new App().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }


    private void createUIComponents() {
        // TODO: place custom component creation code here
        btn_Buscar.setEnabled(false);
    }

}