
package views;

import dao.NhanVienDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;

public class chiTietLuong extends javax.swing.JFrame {
        NhanVienDAO dao  =  new NhanVienDAO();
    public chiTietLuong() {
        initComponents();
        
        list = dao.select();
        fillToTableNV(list);
        setLocationRelativeTo(null);
        cboSapXep.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Sort();
            }
        });
        cboTK.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                timKiem();
            }
        });
        txtTimKiem.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                timKiem();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                timKiem();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                timKiem();
            }
        });
    }
    List<NhanVien> list;
    void timKiem(){
        
        
        //Tìm kiếm theo tên, Tìm kiếm theo mã NV
                
        if(cboTK.getSelectedItem().equals("Tìm kiếm theo tên")){
            list = dao.findName(txtTimKiem.getText());
        } else{
            list = dao.findMaNV(txtTimKiem.getText());
        }
        fillToTableNV(list);
        
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        cboSapXep = new javax.swing.JComboBox<>();
        txtTimKiem = new javax.swing.JTextField();
        cboTK = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Chi tiết lương");
        setResizable(false);

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã NV", "Họ tên", "Ngày sinh", "Lương cơ bản", "Hệ số lương", "Phụ cấp", "Tiền ăn trưa", "Tiền phạt", "Ứng lương", "Thời gian hưởng hệ số lương hiện tại", "Thực lĩnh"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class, java.lang.Float.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(10).setResizable(false);
        }

        jButton1.setText("In file excel");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        cboSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sắp xếp theo mã NV", "Sắp xếp theo tên nhân viên", "Sắp xếp theo ngày tăng lương", "Sắp xếp theo lương" }));

        cboTK.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tìm kiếm theo tên", "Tìm kiếm theo mã NV" }));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(cboTK, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 174, Short.MAX_VALUE)
                        .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 263, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(206, 206, 206)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboTK, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 37, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        XuatFileXl Excel = new XuatFileXl(list);
        Excel.show();
    }//GEN-LAST:event_jButton1MouseClicked
    
        void fillToTableNV(List<NhanVien> list) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            //list = dao.select();
            int i = 1;
            for (NhanVien ch : list) {
                String gt = "Nữ";
                if (ch.getGioiTinh()) {
                    gt = "Nam";
                }
                Object[] row = {
                    ch.getMaNV(),
                    ch.getHoTen(),
                    ch.getNgaySinh(),
                    ch.getLuongCoBan(),
                    ch.getHeSoLuong(),
                    ch.getPhuCap(),
                    ch.getTienAnTrua(),
                    ch.getTienPhat(),
                    ch.getUngLuong(),
                    ch.getTGHuongHSLHT(),
                    ch.getThucLinh()

                };
                model.addRow(row);
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi truy vẫn dữ liệu");
        }
    }
        
        void Sort(){
                    Comparator<NhanVien> sortName = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien s1, NhanVien s2) {
                return s1.getHoTen().compareTo(s2.getHoTen());
            }
        };
        Comparator<NhanVien> sortMaNV = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien s1, NhanVien s2) {
                return s1.getMaNV().compareTo(s2.getMaNV());
            }
        };
        Comparator<NhanVien> sortLuong = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien s2, NhanVien s1) {
                return s1.getThucLinh().compareTo(s2.getThucLinh());
            }
        };
        Comparator<NhanVien> sortNgayTang = new Comparator<NhanVien>() {
            @Override
            public int compare(NhanVien s1, NhanVien s2) {
                return s1.getTGHuongHSLHT().compareTo(s2.getTGHuongHSLHT());
            }
        };
        //Sắp xếp theo mã NV, Sắp xếp theo tên nhân viên, Sắp xếp theo ngày tăng lương
        if (cboSapXep.getSelectedItem().toString().equals("Sắp xếp theo mã NV")) {
            Collections.sort(list, sortMaNV);
        }
        if (cboSapXep.getSelectedItem().toString().equals("Sắp xếp theo tên nhân viên")) {
            Collections.sort(list, sortName);
        }
        if (cboSapXep.getSelectedItem().toString().equals("Sắp xếp theo ngày tăng lương")) {
            Collections.sort(list, sortNgayTang);
        }
        if (cboSapXep.getSelectedItem().toString().equals("Sắp xếp theo lương")) {
            Collections.sort(list, sortLuong);
        }
            fillToTableNV(list);
        }
    
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(chiTietLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(chiTietLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(chiTietLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(chiTietLuong.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new chiTietLuong().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboSapXep;
    private javax.swing.JComboBox<String> cboTK;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
