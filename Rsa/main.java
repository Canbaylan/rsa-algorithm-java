package Rsa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class main extends JFrame {
    public static void main(String[] args) {
        decryption dec = new decryption();


        JFrame f = new JFrame("Rsa Encryption");
        JLabel l1, l2, l3, l4, l5;
        JTextField t1;
        JButton b1;

        l1 = new JLabel("Gonderici");
        l2 = new JLabel("Alici");
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        l2.setFont(new Font("Serif", Font.BOLD, 20));
        l1.setBounds(100, 10, 100, 30);
        l2.setBounds(500, 10, 100, 30);

        l3 = new JLabel("Metin Girin :");
        l3.setBounds(40, 60, 100, 30);
        l4 = new JLabel("");
        l4.setBounds(40, 130, 300, 40);
        l5 = new JLabel("");
        l5.setBounds(40, 160, 300, 40);
        t1 = new JTextField();
        t1.setBounds(130, 60, 150, 30);
        b1 = new JButton("Sifrele");
        b1.setBounds(100, 105, 100, 30);
        List<String> temp = new ArrayList<>(5);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String metin = t1.getText();
                temp.addAll(dec.encrypt(metin));
                l4.setText(temp.get(0));
                l5.setText(temp.get(2));
            }
        });


        f.add(l1);
        f.add(l2);
        f.add(t1);
        f.add(l3);
        f.add(b1);
        f.add(l5);
        f.add(l4);


        // ALICI
        JLabel la3, la4, la5, la6;
        JTextField te1, te2;
        JButton bu2;

        la3 = new JLabel("Sifreli Metni Girin :");
        la3.setBounds(370, 60, 150, 30);
        la4 = new JLabel("Desifreleme Anahtari Girin : ");
        la4.setBounds(370, 90, 170, 30);
        la5 = new JLabel("");
        la5.setBounds(440, 160, 300, 30);
        la6 = new JLabel("");
        la6.setBounds(440, 190, 300, 30);
        te2 = new JTextField();
        te2.setBounds(530, 60, 150, 30);
        te1 = new JTextField();
        te1.setBounds(530, 90, 150, 30);
        bu2 = new JButton("DeSifrele");
        bu2.setBounds(500, 135, 100, 30);


        bu2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if (te2.getText().equals(temp.get(0)) && te1.getText().equals(temp.get(5))) {

                    la5.setText(temp.get(3));
                    la6.setText("Acik Metin: " + temp.get(1));

                } else
                    la5.setText("Yanlis giris.");

            }
        });

        f.add(la3);
        f.add(bu2);
        f.add(la4);
        f.add(te2);
        f.add(la5);
        f.add(te1);
        f.add(la6);
        f.setSize(800, 400);
        f.setLayout(null); //default = BorderLayout
        f.setVisible(true);
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}

