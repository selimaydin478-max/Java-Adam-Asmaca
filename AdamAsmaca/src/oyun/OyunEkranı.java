/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package oyun;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Admin
 */
public class OyunEkranı extends javax.swing.JFrame {
    // Giriş ekranından gelen sabit yolları alıyoruz
    String resimYolu = GirisEkranı.RESIM_YOLU;
    String txtYolu = GirisEkranı.TXT_YOLU;

    String secilenKelime = "";
    int yanlisSayisi = 0;
    int saniye = 0;
    javax.swing.Timer timer;
    javax.swing.JLabel[] harfEtiketleri;
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OyunEkranı.class.getName());

    /**
     * Creates new form OyunEkranı
     */
    public OyunEkranı() {
        initComponents();
        oyunuBaslat();
        tablolariDoldur();
    }
    public void oyunuBaslat() {
        try {
            // Kelimeleri dosyadan oku
            ArrayList<String> kelimeler = new ArrayList<>();
            Scanner okuyucu = new Scanner(new File(txtYolu + "kelimeler.txt"));
            while (okuyucu.hasNextLine()) {
                kelimeler.add(okuyucu.nextLine().trim().toUpperCase());
            }
            okuyucu.close();

            if (kelimeler.isEmpty()) {
                JOptionPane.showMessageDialog(this, "kelimeler.txt dosyası boş!");
                return;
            }

            // Rastgele kelime seç
            Random rnd = new Random();
            secilenKelime = kelimeler.get(rnd.nextInt(kelimeler.size()));

            // Arayüzü Sıfırla
            yanlisSayisi = 0;
            saniye = 0;
            lblSure.setText("Süre: 0");
            lblResim.setIcon(new ImageIcon(resimYolu + "1.jpg")); // İlk resmi yükle
            txtHarf.setText("");
            txtKelime.setText("");
            
            // Dinamik JLabel'leri (*) oluştur
            panelKelime.removeAll();
            harfEtiketleri = new JLabel[secilenKelime.length()];
            for (int i = 0; i < secilenKelime.length(); i++) {
                harfEtiketleri[i] = new JLabel("* ");
                harfEtiketleri[i].setFont(new java.awt.Font("Tahoma", 1, 24));
                panelKelime.add(harfEtiketleri[i]);
            }
            panelKelime.revalidate();
            panelKelime.repaint();

            // Timer başlat (her saniye artacak)
            if (timer != null) timer.stop();
            timer = new javax.swing.Timer(1000, e -> {
                saniye++;
                lblSure.setText("Süre: " + saniye);
            });
            timer.start();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Kelime dosyası okunurken hata oluştu!");
        }
    }
    
    private void oyunDurumunuKontrolEt(boolean kazandiMi) {
        if (kazandiMi) {
            timer.stop();
            JOptionPane.showMessageDialog(this, "Tebrikler, Kazandınız! Süre: " + saniye + " saniye");
            oyunuKaydet("Kazandı");
            oyunuBaslat();
        } else if (yanlisSayisi >= 10) { // 1. resimden başladık, 11 yanlış tahminde oyun biter [cite: 15]
            timer.stop();
            JOptionPane.showMessageDialog(this, "Bilemediniz, Kaybettiniz! Kelime: " + secilenKelime);
            oyunuKaydet("Kaybetti");
            oyunuBaslat();
        }
    }

    private void oyunuKaydet(String sonuc) {
        try {
            String zaman = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss"));
            FileWriter fw = new FileWriter(txtYolu + "oyunlar.txt", true);
            fw.write(zaman + " - " + sonuc + " - Süre: " + saniye + " saniye\n");
            fw.close(); // Süre, sonuç, tarih ve saat oyunlar.txt'ye kaydedilmeli [cite: 18]
        } catch (Exception e) {
            System.out.println("Oyun kaydedilirken hata: " + e.getMessage());
        }
    }
    public void tablolariDoldur() {
        // Eski Skorlar Tablosunu Doldurma
        javax.swing.table.DefaultTableModel modelSkor = new javax.swing.table.DefaultTableModel(new String[]{"Oyun Geçmişi (Tarih - Sonuç - Süre)"}, 0);
        try {
            java.util.Scanner okuyucu = new java.util.Scanner(new java.io.File(txtYolu + "oyunlar.txt"));
            while (okuyucu.hasNextLine()) {
                modelSkor.addRow(new Object[]{okuyucu.nextLine()});
            }
            okuyucu.close();
        } catch (Exception e) {}
        tabloSkorlar.setModel(modelSkor);

        // Loglar Tablosunu Doldurma
        javax.swing.table.DefaultTableModel modelLog = new javax.swing.table.DefaultTableModel(new String[]{"Giriş ve Şifre Deneme Logları"}, 0);
        try {
            java.util.Scanner okuyucu = new java.util.Scanner(new java.io.File(txtYolu + "log.txt"));
            while (okuyucu.hasNextLine()) {
                modelLog.addRow(new Object[]{okuyucu.nextLine()});
            }
            okuyucu.close();
        } catch (Exception e) {}
        tabloLoglar.setModel(modelLog);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        panelKelime = new javax.swing.JPanel();
        txtHarf = new javax.swing.JTextField();
        txtKelime = new javax.swing.JTextField();
        btnHarf = new javax.swing.JButton();
        btnKelime = new javax.swing.JButton();
        lblResim = new javax.swing.JLabel();
        lblSure = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabloSkorlar = new javax.swing.JTable();
        btnTemizleSkor = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabloLoglar = new javax.swing.JTable();
        btnTemizleLog = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane3.setViewportView(jTable2);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtHarf.addActionListener(this::txtHarfActionPerformed);

        btnHarf.setText("Harf Tahmin Et");
        btnHarf.addActionListener(this::btnHarfActionPerformed);

        btnKelime.setText("Kelime Tahmin Et");
        btnKelime.addActionListener(this::btnKelimeActionPerformed);

        lblSure.setText("Süre:0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(101, 101, 101)
                        .addComponent(lblSure, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtKelime, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(46, 46, 46)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnKelime)
                            .addComponent(btnHarf, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 142, Short.MAX_VALUE)
                .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(panelKelime, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(123, 123, 123))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelKelime, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtHarf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHarf))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtKelime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnKelime))
                        .addGap(19, 19, 19)
                        .addComponent(lblSure, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblResim, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Oyun Oynama", jPanel1);

        tabloSkorlar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabloSkorlar);

        btnTemizleSkor.setText("Temizle");
        btnTemizleSkor.addActionListener(this::btnTemizleSkorActionPerformed);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 403, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(btnTemizleSkor)))
                .addContainerGap(158, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTemizleSkor)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Eski Skorları Görme", jPanel2);

        tabloLoglar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane4.setViewportView(tabloLoglar);

        btnTemizleLog.setText("Temizle");
        btnTemizleLog.addActionListener(this::btnTemizleLogActionPerformed);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(btnTemizleLog)))
                .addContainerGap(155, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnTemizleLog)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Logları Görme", jPanel3);

        jMenu1.setText("Oyun");

        jMenuItem1.setText("Oyuna Başla");
        jMenuItem1.addActionListener(this::jMenuItem1ActionPerformed);
        jMenu1.add(jMenuItem1);

        jMenuItem2.setText("Oyunu Yeniden Başlat");
        jMenuItem2.addActionListener(this::jMenuItem2ActionPerformed);
        jMenu1.add(jMenuItem2);

        jMenuBar1.add(jMenu1);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 567, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(418, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 235, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtHarfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtHarfActionPerformed

    private void btnHarfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHarfActionPerformed
       String tahmin = txtHarf.getText().toUpperCase();
        txtHarf.setText("");
        
        if (tahmin.isEmpty() || tahmin.length() > 1) {
            JOptionPane.showMessageDialog(this, "Lütfen tek bir harf girin.");
            return;
        }

        boolean harfVarMi = false;
        boolean kelimeTamamlandi = true;

        for (int i = 0; i < secilenKelime.length(); i++) {
            if (String.valueOf(secilenKelime.charAt(i)).equals(tahmin)) {
                harfEtiketleri[i].setText(tahmin + " ");
                harfVarMi = true; // Harf tahmini yapıldığında harf varsa görüntülenmeli [cite: 13]
            }
            if (harfEtiketleri[i].getText().contains("*")) {
                kelimeTamamlandi = false;
            }
        }

        if (!harfVarMi) {
            yanlisSayisi++;
            lblResim.setIcon(new ImageIcon(resimYolu + (yanlisSayisi + 1) + ".jpg"));
        }

        oyunDurumunuKontrolEt(kelimeTamamlandi); // TODO add your handling code here:
    }//GEN-LAST:event_btnHarfActionPerformed

    private void btnKelimeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnKelimeActionPerformed
         String tahmin = txtKelime.getText().toUpperCase();
         txtKelime.setText("");
        
        if (tahmin.isEmpty()) return;

        if (tahmin.equals(secilenKelime)) {
            for (int i = 0; i < secilenKelime.length(); i++) {
                harfEtiketleri[i].setText(String.valueOf(secilenKelime.charAt(i)) + " ");
            }
            oyunDurumunuKontrolEt(true);
        } else {
            yanlisSayisi++;
            lblResim.setIcon(new ImageIcon(resimYolu + (yanlisSayisi + 1) + ".jpg"));
            oyunDurumunuKontrolEt(false);
        }    // TODO add your handling code here:
   
    }//GEN-LAST:event_btnKelimeActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
       oyunuBaslat();        // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       oyunuBaslat(); // TODO add your handling code here:
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void btnTemizleLogActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleLogActionPerformed
        String girilenSifre = javax.swing.JOptionPane.showInputDialog(this, "Logları temizlemek için şifrenizi girin:");
        if (girilenSifre == null) return;
        
        try {
            java.util.Scanner okuyucu = new java.util.Scanner(new java.io.File(txtYolu + "sifre.txt"));
            String gercekSifre = okuyucu.nextLine();
            okuyucu.close();
            
            if (girilenSifre.equals(gercekSifre)) {
                java.io.FileWriter fw = new java.io.FileWriter(txtYolu + "log.txt", false);
                fw.write("");
                fw.close();
                tablolariDoldur();
                javax.swing.JOptionPane.showMessageDialog(this, "Log kayıtları başarıyla temizlendi.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Hatalı şifre girdiniz!");
            }
        } catch (Exception ex) {}        // TODO add your handling code here:
    }//GEN-LAST:event_btnTemizleLogActionPerformed

    private void btnTemizleSkorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTemizleSkorActionPerformed
        String girilenSifre = javax.swing.JOptionPane.showInputDialog(this, "Skorları temizlemek için şifrenizi girin:");
        if (girilenSifre == null) return; // İptale basılırsa çık
        
        try {
            java.util.Scanner okuyucu = new java.util.Scanner(new java.io.File(txtYolu + "sifre.txt"));
            String gercekSifre = okuyucu.nextLine();
            okuyucu.close();
            
            if (girilenSifre.equals(gercekSifre)) {
                // Şifre doğruysa dosyayı üzerine boş yazarak sıfırla
                java.io.FileWriter fw = new java.io.FileWriter(txtYolu + "oyunlar.txt", false);
                fw.write("");
                fw.close();
                tablolariDoldur(); // Tabloyu anında güncelle
                javax.swing.JOptionPane.showMessageDialog(this, "Eski skorlar başarıyla temizlendi.");
            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Hatalı şifre girdiniz!");
            }
        } catch (Exception ex) {
            javax.swing.JOptionPane.showMessageDialog(this, "İşlem sırasında hata oluştu!");
        }        // TODO add your handling code here:
    }//GEN-LAST:event_btnTemizleSkorActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new OyunEkranı().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnHarf;
    private javax.swing.JButton btnKelime;
    private javax.swing.JButton btnTemizleLog;
    private javax.swing.JButton btnTemizleSkor;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel lblResim;
    private javax.swing.JLabel lblSure;
    private javax.swing.JPanel panelKelime;
    private javax.swing.JTable tabloLoglar;
    private javax.swing.JTable tabloSkorlar;
    private javax.swing.JTextField txtHarf;
    private javax.swing.JTextField txtKelime;
    // End of variables declaration//GEN-END:variables
}
