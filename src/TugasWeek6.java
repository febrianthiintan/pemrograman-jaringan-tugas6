
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.PushbackReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author INTAN
 */

public class TugasWeek6 {
   private final Main view;

   public TugasWeek6 (Main view) {
        this.view = view;

        this.view.getBtBaca6().addActionListener((ActionEvent e) -> {
            try {
                proses();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(TugasWeek6.class.getName()).log(Level.SEVERE, null, ex);
            } catch (BadLocationException | IOException ex) {
                Logger.getLogger(TugasWeek6.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        
        this.view.getBtSimpan6().addActionListener((ActionEvent e) -> {
            save();
        });
    }
     
    private void proses()throws FileNotFoundException, BadLocationException, IOException{
    JFileChooser loadFile = view.getLoadFile6();
             StyledDocument doc = view.getTxtPane6().getStyledDocument();
             if (JFileChooser.APPROVE_OPTION == loadFile.showOpenDialog(view)) {
                 
                 PushbackReader  reader = new PushbackReader(new FileReader(loadFile.getSelectedFile()));
                 LineNumberReader re = new LineNumberReader(new FileReader(loadFile.getSelectedFile()));
                 
                  char[] words = new char[(int) loadFile.getSelectedFile().length()];
                 
                 try {
                     //inisialisasi untuk membaca kata
                    reader.read(words);
                    
                    //membuat variabel
                    String data = null;
                    String data1 = null;
                    doc.insertString(0, "", null);
                    
                    //membuat variabel untuk menghitung karakter dan kata
                    int hitungKarakter = 0; //default 0
                    int hitungKata = 0; //default 0
                    
                    if((data = new String(words)) != null) {
                        String[] wordlist = data.split("\\s+");
                      
                        hitungKarakter += words.length;
                        hitungKata += wordlist.length;
                       
                        doc.insertString(doc.getLength(), "" + data + "\n", null);
                     
                    }
                    while((data1 = re.readLine()) != null){
                       
                    }
                     int hitungBaris = re.getLineNumber();
                     
                     //pop-up menampilkan jumlah baris, kata dan karakter
                     JOptionPane.showMessageDialog(null, "File Berhasil Dibaca" + "\n"
                        + "Jumlah Baris = " + hitungBaris + "\n"
                        + "Jumlah Kata = " + hitungKata + "\n"
                        + "Jumlah Karakter = " + hitungKarakter);
                     
                 } catch (IOException | BadLocationException ex) {
                     Logger.getLogger(TugasWeek6.class.getName()).log(Level.SEVERE, null, ex);
                 } 
         }
    }

     
     
    private void save() {
         JFileChooser loadFile = view.getLoadFile6();
         if (JFileChooser.APPROVE_OPTION == loadFile.showSaveDialog(view)) {
             BufferedWriter writer = null;
             try {
                 String contents = view.getTxtPane6().getText();
                 if(contents !=null && !contents.isEmpty()){
                     writer = new BufferedWriter(new FileWriter(loadFile.getSelectedFile()));
                     writer.write(contents);
                 }
             } catch (FileNotFoundException ex) {
                Logger.getLogger(TugasWeek6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } catch (IOException ex) {
                 Logger.getLogger(TugasWeek6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
             } finally {
                 if (writer != null) {
                     try {
                         writer.flush();
                         writer.close();
                         view.getTxtPane6().setText("");
                         
                         //menambahkan pop-up untuk menampilkan data tersimpan
                        JOptionPane.showMessageDialog(null, "Data Berhasil Tersimpan !!", "PESAN INFORMASI", JOptionPane.INFORMATION_MESSAGE);
                        
                     } catch (IOException ex) {
                         java.util.logging.Logger.getLogger(TugasWeek6.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
                     }
                 } else {
                     //pop-up input-an tidak boleh kosong
                    JOptionPane.showMessageDialog(null, "Teks Tidak Boleh Kosong", "Error", JOptionPane.WARNING_MESSAGE);
                 }
             }
         }
     }
}
