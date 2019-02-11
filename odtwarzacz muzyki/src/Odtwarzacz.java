

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import java.awt.FileDialog;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;


public class Odtwarzacz implements ActionListener  {
    AudioFilePlayer MP3 = new AudioFilePlayer("F:/muzyka/");

    public static void main(String[] args) throws FileNotFoundException {
        new Odtwarzacz();

    }


    String sEkran = "Tytuł utworu";
    String[] sKey = {"Play","Stop","Next"};
    int numerUtworu=0;
    File[] files;
    Random losowanie = new Random();
    int wylosowana;

    JFrame okno = new JFrame("Odtwarzacz");
    JTextField text = new JTextField(sEkran);
    JButton play = new JButton();
    JButton stop= new JButton();
    JButton next = new JButton();
    JButton szukaj = new JButton();
    JButton losuj = new JButton();

    ImageIcon playObraz = new ImageIcon("ikony/play.png");
    ImageIcon nastepnaObraz = new ImageIcon("ikony/nastepna.png");
    ImageIcon stopObraz = new ImageIcon("ikony//stop.png");
    ImageIcon losujObraz = new ImageIcon("ikony//losuj.png");
    ImageIcon szukajObraz = new ImageIcon("ikony/szukaj.png");





    String katalog;
    String plik;

    Odtwarzacz() throws FileNotFoundException {


        play = new JButton(playObraz);
        stop = new JButton(stopObraz);
        next = new JButton(nastepnaObraz);
        szukaj = new JButton(szukajObraz);
        losuj = new JButton(losujObraz);
        okno.add(play);
        okno.add(stop);
        okno.add(next);
        okno.add(szukaj);
        okno.add(losuj);
        play.addActionListener(this);
        stop.addActionListener(this);
        next.addActionListener(this);
        szukaj.addActionListener(this);
        losuj.addActionListener(this);

        play.setBounds(10,45,65,65);
        stop.setBounds(90,45,65,65);
        next.setBounds(170,45,65,65);
        szukaj.setBounds(10,125,65,65);
        losuj.setBounds(90,125,65,65);


        text.setBounds(10,10,295,35);
        text.setFont(new Font("System",Font.BOLD,20));
        text.setEditable(false);

        okno.add(text);
        okno.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        okno.setSize(330, 285);
        okno.setLocationRelativeTo(null);
        okno.setResizable(false);
        okno.setLayout(null);
        okno.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object cel = e.getSource();

        if (cel==play){
            MP3.close();
            MP3.play();
        }
        else if (cel==stop) MP3.close();
        else if (cel==next) {
            numerUtworu++;
            if(numerUtworu>=files.length) numerUtworu=0;
            MP3.close();
            MP3.setFilename(files[numerUtworu].getAbsolutePath());
            MP3.play();
            text.setText(files[numerUtworu].getPath());
        }
        else if (cel == szukaj){


            JFileChooser chooser = new JFileChooser();
            chooser.setMultiSelectionEnabled(true);
            if(chooser.showOpenDialog(null)==JFileChooser.APPROVE_OPTION) {
                files = chooser.getSelectedFiles();
                numerUtworu = 0;
                MP3.close();
                MP3.setFilename(files[numerUtworu].getAbsolutePath());
                MP3.play();
                text.setText(files[numerUtworu].getPath());
            }
        }

        else if (cel == losuj){
            wylosowana=losowanie.nextInt(files.length);
            MP3.close();
            MP3.setFilename(files[wylosowana].getAbsolutePath());
            MP3.play();
            text.setText(files[wylosowana].getPath());
        }
    }




}
