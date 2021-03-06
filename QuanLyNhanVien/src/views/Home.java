package views;

import dao.NhanVienDAO;
import dao.NhanVienHDDAO;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.NhanVien;
import model.NhanVienHD;
import utils.CheckLoi;

public class Home extends javax.swing.JFrame {

    public Home() {
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception ex) {

        }
        initComponents();
        setLocationRelativeTo(null);
        for (NhanVien nhanVien : listNV) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date date = new java.util.Date();
            Date datee = Date.valueOf(dateFormat.format(date));
            Calendar c2 = Calendar.getInstance();
                c2.setTime(nhanVien.getTGHuongHSLHT());
                c2.roll(Calendar.YEAR, 2);
            if (dateFormat.format(datee).compareTo(dateFormat.format(c2.getTime())) >= 0) {
                nhanVien.setHeSoLuong(nhanVien.getHeSoLuong()+1);
                nhanVien.setThucLinh(nhanVien.Luong());
                nhanVien.setTGHuongHSLHT(Date.valueOf(dateFormat.format(c2.getTime())));
                NhanVienDAO dao = new NhanVienDAO();
                try {
                    dao.update(nhanVien);
                } catch (Exception e) {
                }
                System.out.println(dateFormat.format(c2.getTime()));
            }
        }
        this.fillToTableNV(listNV);
        this.fillToTableNVHD(listNVHD);
        txtTimKiemNVHD.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent de) {
                timKiemNVHD();
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
                timKiemNVHD();
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
                timKiemNVHD();
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
    NhanVienDAO nvDAO = new NhanVienDAO();
    List<NhanVien> listNV = nvDAO.select();
    NhanVienHDDAO nvhdDAO = new NhanVienHDDAO();
    List<NhanVienHD> listNVHD = nvhdDAO.select();

    void timKiemNVHD() {
        listNVHD = nvhdDAO.findName(txtTimKiemNVHD.getText());
        fillToTableNVHD(listNVHD);
    }

    void timKiem() {
        listNV = nvDAO.findName(txtTimKiem.getText());
        fillToTableNV(listNV);
    }

    void xuatfile() {

    }

    void fillToTableNV(List<NhanVien> list) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        try {
            //list = dao.select();
            int i = 1;
            for (NhanVien ch : list) {
                String gt = "N???";
                if (ch.getGioiTinh()) {
                    gt = "Nam";
                }
                Object[] row = {
                    ch.getMaNV(),
                    ch.getHoTen(),
                    gt,
                    ch.getNgaySinh(),
                    ch.getQueQuan(),
                    ch.getChuyenMon(),
                    ch.getTrinhDo(),
                    ch.getChucVu(),
                    ch.getLuongCoBan(),
                    ch.getHeSoLuong(),
                    ch.getPhuCap(),
                    ch.getTienAnTrua(),
                    ch.getTienPhat(),
                    ch.getUngLuong(),
                    ch.getThucLinh(),
                    ch.getTGHuongHSLHT()

                };
                model.addRow(row);
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "l???i truy v???n d??? li???u");
        }
    }

    void fillToTableNVHD(List<NhanVienHD> list) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVienHD.getModel();
        model.setRowCount(0);
        try {
            //list = dao.select();
            int i = 1;
            for (NhanVienHD ch : list) {
                String gt = "N???";
                if (ch.getGioiTinh()) {
                    gt = "Nam";
                }
                Object[] row = {
                    ch.getMaNV(),
                    ch.getHoTen(),
                    ch.getNgaySinh(),
                    gt,
                    ch.getQueQuan(),
                    ch.getChuyenMon(),
                    ch.getLuong(),
                    ch.getThoiGianHD(),
                    ch.getThoiGianHetHD()
                };
                model.addRow(row);
                i++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "l???i truy v???n d??? li???u");
        }
    }

    void themNV() {
        if (checkFormNV() == true) {
            try {
                NhanVien model = model();
                nvDAO.insert(model);
                JOptionPane.showMessageDialog(this, "Th??m nh??n vi??n th??nh c??ng");
                listNV = nvDAO.select();
                fillToTableNV(listNV);
                txtThucLinh.setText(String.valueOf(model.getThucLinh()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Th??m nh??n vi??n th???t b???i" + e);
                System.out.println(e.toString());
            }
        }
    }

    void themNVHD() {

        if (checkFormNVHD() == true) {
            try {
                nvhdDAO.insert(modelHD());
                JOptionPane.showMessageDialog(this, "Th??m nh??n vi??n th??nh c??ng");
                listNVHD = nvhdDAO.select();
                fillToTableNVHD(listNVHD);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Th??m nh??n vi??n th???t b???i");
            }
        }
    }

    void editNV() {
        if (checkFormNV() == true) {
            try {
                NhanVien model = model();
                nvDAO.update(model);
                JOptionPane.showMessageDialog(this, "Ch???nh s???a nh??n vi??n th??nh c??ng");
                listNV = nvDAO.select();
                fillToTableNV(listNV);
                txtThucLinh.setText(String.valueOf(model.getThucLinh()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ch???nh s???a nh??n vi??n th???t b???i");
            }
        }
    }

    void editNVHD() {
        if (checkFormNVHD() == true) {
            try {
                nvhdDAO.update(modelHD());
                JOptionPane.showMessageDialog(this, "Ch???nh s???a nh??n vi??n th??nh c??ng");
                listNVHD = nvhdDAO.select();
                fillToTableNVHD(listNVHD);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Ch???nh s???a nh??n vi??n th???t b???i");
            }
        }
    }

    void deleteNV() {
        int i = tblNhanVien.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n nh??n vi??n c???n x??a");
        } else {
            if (JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n x??a nh??n vi??n ?") == 0) {
                try {
                    nvDAO.delete(listNV.get(i).getMaNV());
                    JOptionPane.showMessageDialog(this, "X??a nh??n vi??n th??nh c??ng");
                    listNV = nvDAO.select();
                    fillToTableNV(listNV);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "X??a nh??n vi??n th???t b???i");
                }
            }
        }
    }

    void deleteNVHD() {
        int i = tblNhanVienHD.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Vui l??ng ch???n nh??n vi??n c???n x??a");
        } else {
            if (JOptionPane.showConfirmDialog(this, "B???n c?? ch???c mu???n x??a nh??n vi??n ?") == 0) {
                try {
                    nvhdDAO.delete(listNVHD.get(i).getMaNV());
                    JOptionPane.showMessageDialog(this, "X??a nh??n vi??n th??nh c??ng");
                    listNVHD = nvhdDAO.select();
                    fillToTableNVHD(listNVHD);
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "X??a nh??n vi??n th???t b???i");
                }
            }
        }
    }

    boolean checkFormNV() {
        Boolean check = true;
        StringBuilder bd = new StringBuilder();
        CheckLoi.checkRong(txtMaNV, bd, "M?? nh??n vi??n ch??a nh???p\n");
        CheckLoi.checkRong(txtHoTen, bd, "H??? t??n nh??n vi??n ch??a nh???p\n");
        CheckLoi.checkRong(txtGioiTinh, bd, "Gi???i t??nh ch??a nh???p\n");
        CheckLoi.checkNgaySinh(txtNgaySinh, bd);
        CheckLoi.checkSo(txtLuongCoBan, bd, "L????ng c?? b???n ch??a nh???p\n", "L????ng c?? b???n");
        CheckLoi.checkSo(txtHeSoLuong, bd, "H??? s??? l????ng ch??a nh???p\n", "H??? s??? l????ng");
        CheckLoi.checkSo(txtPhuCap, bd, "Ph???c c???p ch??a nh???p\n", "Ph??? c???p");
        CheckLoi.checkSo(txtTienAnTrua, bd, "Ti???n ??n tr??a ch??a nh???p\n", "Ti???n ??n tr??a");
        if (bd.length() > 0) {
            JOptionPane.showMessageDialog(this, bd.toString(), "Th??ng b??o",
                    JOptionPane.ERROR_MESSAGE);
            check = false;
        }
        return check;
    }

    boolean checkFormNVHD() {
        Boolean check = true;
        StringBuilder bd = new StringBuilder();
        CheckLoi.checkRong(txtMaNVHD, bd, "M?? nh??n vi??n ch??a nh???p\n");
        CheckLoi.checkRong(txtHoTenHD, bd, "H??? t??n nh??n vi??n ch??a nh???p\n");
        CheckLoi.checkRong(txtGioiTinhHD, bd, "Gi???i t??nh ch??a nh???p\n");
        CheckLoi.checkRong(txtChuyenMonHD, bd, "Chuy??n m??n ch??a nh???p\n");
        CheckLoi.checkNgaySinh(txtNgaySinhHD, bd);
        CheckLoi.checkSo(txtLuongHD, bd, "L????ng c?? b???n ch??a nh???p\n", "L????ng c?? b???n");
        CheckLoi.checkNgaySinh(txtTG, bd);
        CheckLoi.checkNgaySinh(txtTGHet, bd);
        if (bd.length() > 0) {
            JOptionPane.showMessageDialog(this, bd.toString(), "Th??ng b??o",
                    JOptionPane.ERROR_MESSAGE);
            check = false;
        }
        return check;
    }

    void fill() {
        int i = tblNhanVien.getSelectedRow();
        txtMaNV.setText(listNV.get(i).getMaNV());
        txtHoTen.setText(listNV.get(i).getHoTen());
        String gt = "N???";
        if (listNV.get(i).getGioiTinh()) {
            gt = "Nam";
        }
        txtGioiTinh.setText(gt);
        txtNgaySinh.setText(String.valueOf(listNV.get(i).getNgaySinh()));
        txtQueQuan.setText(listNV.get(i).getQueQuan());
        txtChuyenMon.setText(listNV.get(i).getChuyenMon());
        txtTrinhDo.setText(listNV.get(i).getTrinhDo());
        txtChucVu.setText(listNV.get(i).getChucVu());
        txtLuongCoBan.setText(String.format("%.0f", listNV.get(i).getLuongCoBan()));
        txtHeSoLuong.setText(String.format("%.0f", listNV.get(i).getHeSoLuong()));
        txtPhuCap.setText(String.format("%.0f", listNV.get(i).getPhuCap()));
        txtTienAnTrua.setText(String.format("%.0f", listNV.get(i).getTienAnTrua()));
        txtTienPhat.setText(String.format("%.0f", listNV.get(i).getTienPhat()));
        txtUngLuong.setText(String.format("%.0f", listNV.get(i).getUngLuong()));
        txtThucLinh.setText(String.format("%.0f", listNV.get(i).getThucLinh()));
        txtTGHuongHSHT.setText(String.valueOf(listNV.get(i).getTGHuongHSLHT()));
    }

    void fillNVHD() {
        int i = tblNhanVienHD.getSelectedRow();
        txtMaNVHD.setText(listNVHD.get(i).getMaNV());
        txtHoTenHD.setText(listNVHD.get(i).getHoTen());
        String gt = "N???";
        if (listNVHD.get(i).getGioiTinh()) {
            gt = "Nam";
        }
        txtGioiTinhHD.setText(gt);
        txtNgaySinhHD.setText(String.valueOf(listNVHD.get(i).getNgaySinh()));
        txtQueQuanHD.setText(listNVHD.get(i).getQueQuan());
        txtChuyenMonHD.setText(listNVHD.get(i).getChuyenMon());
        txtLuongHD.setText(String.format("%.0f",listNVHD.get(i).getLuong()));
        txtTG.setText(String.valueOf(listNVHD.get(i).getThoiGianHD()));
        txtTGHet.setText(String.valueOf(listNVHD.get(i).getThoiGianHetHD()));
    }

    void reset() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtGioiTinh.setText("");
        txtNgaySinh.setText("");
        txtQueQuan.setText("");
        txtChuyenMon.setText("");
        txtTrinhDo.setText("");
        txtChucVu.setText("");
        txtLuongCoBan.setText("");
        txtHeSoLuong.setText("");
        txtPhuCap.setText("");
        txtTienAnTrua.setText("");
        txtThucLinh.setText("");
        txtTGHuongHSHT.setText("");
    }

    void resetNVHD() {
        txtMaNVHD.setText("");
        txtHoTenHD.setText("");
        txtGioiTinhHD.setText("");
        txtNgaySinhHD.setText("");
        txtQueQuanHD.setText("");
        txtChuyenMonHD.setText("");
        txtLuongHD.setText("");
        txtTG.setText("");
        txtTGHet.setText("");
    }

    NhanVienHD modelHD() {
        NhanVienHD model = new NhanVienHD();
        model.setMaNV(txtMaNVHD.getText());
        model.setHoTen(txtHoTenHD.getText());
        model.setGioiTinh(txtGioiTinhHD.getText().equalsIgnoreCase("nam") ? true : false);
        model.setNgaySinh(Date.valueOf(txtNgaySinhHD.getText()));
        model.setQueQuan(txtQueQuanHD.getText());
        model.setChuyenMon(txtChuyenMonHD.getText());
        model.setLuong(Float.valueOf(txtLuongHD.getText()));
        model.setThoiGianHD(Date.valueOf(txtTG.getText()));
        model.setThoiGianHetHD(Date.valueOf(txtTGHet.getText()));
        return model;
    }

    NhanVien model() {
        NhanVien model = new NhanVien();
        model.setMaNV(txtMaNV.getText());
        model.setHoTen(txtHoTen.getText());
        model.setGioiTinh(txtGioiTinh.getText().equalsIgnoreCase("nam") ? true : false);
        model.setNgaySinh(Date.valueOf(txtNgaySinh.getText()));
        model.setQueQuan(txtQueQuan.getText());
        model.setChuyenMon(txtChuyenMon.getText());
        model.setTrinhDo(txtTrinhDo.getText());
        model.setChucVu(txtChucVu.getText());
        model.setLuongCoBan(Float.valueOf(txtLuongCoBan.getText()));
        model.setHeSoLuong(Float.valueOf(txtHeSoLuong.getText()));
        model.setPhuCap(Float.valueOf(txtPhuCap.getText()));
        model.setTienAnTrua(Float.valueOf(txtTienAnTrua.getText()));
        model.setTGHuongHSLHT(Date.valueOf(txtTGHuongHSHT.getText()));
        if(txtTienPhat.getText().equals("")){
            txtTienPhat.setText("0");
        }
        if(txtUngLuong.getText().equals("")){
            txtUngLuong.setText("0");
        }
        model.setTienPhat(Float.valueOf(txtTienPhat.getText()));
        model.setUngLuong(Float.valueOf(txtUngLuong.getText()));
        model.setThucLinh(model.Luong());
        return model;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        NhanVienChinhThuc = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel25 = new javax.swing.JLabel();
        txtTimKiem = new javax.swing.JTextField();
        btnThem = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        btnXuatFile = new javax.swing.JButton();
        btnXoa = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        txtMaNV = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtHoTen = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtGioiTinh = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNgaySinh = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtQueQuan = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtChuyenMon = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        txtTrinhDo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtChucVu = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtLuongCoBan = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtHeSoLuong = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtPhuCap = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtTienAnTrua = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        txtThucLinh = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtTGHuongHSHT = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        txtUngLuong = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtTienPhat = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        NhanVienHopDongThoiVu = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblNhanVienHD = new javax.swing.JTable();
        txtMaNVHD = new javax.swing.JTextField();
        txtNgaySinhHD = new javax.swing.JTextField();
        txtHoTenHD = new javax.swing.JTextField();
        txtQueQuanHD = new javax.swing.JTextField();
        txtGioiTinhHD = new javax.swing.JTextField();
        txtTG = new javax.swing.JTextField();
        txtTGHet = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        txtLuongHD = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtChuyenMonHD = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        txtTimKiemNVHD = new javax.swing.JTextField();
        btnThemHD = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Trang ch???");
        setResizable(false);

        NhanVienChinhThuc.setBackground(new java.awt.Color(255, 255, 255));

        tblNhanVien.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? NV", "H??? T??n", "Gi???i t??nh", "Ng??y sinh", "Qu?? qu??n", "Chuy??n m??n", "Tr??nh ?????", "Ch???c v???", "L????ng c?? b???n", "H??? s??? l????ng", "Ph??? c???p", "Ti???n ??n tr??a", "Ti???n ph???t", "???ng l????ng", "th???c l??nh", "TGHHSLHT"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblNhanVien);
        if (tblNhanVien.getColumnModel().getColumnCount() > 0) {
            tblNhanVien.getColumnModel().getColumn(9).setResizable(false);
        }

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel25.setText("Nh???p t??n ????? t??m ki???m:");

        txtTimKiem.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnThem.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnThem.setText("Th??m");
        btnThem.setPreferredSize(new java.awt.Dimension(65, 30));
        btnThem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThemActionPerformed(evt);
            }
        });

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnReset.setText("Reset");
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });

        btnSua.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnSua.setText("S???a");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        btnXuatFile.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXuatFile.setText("Xu???t excel");
        btnXuatFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnXuatFileMouseClicked(evt);
            }
        });

        btnXoa.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        btnXoa.setText("X??a");
        btnXoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnXoaActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jButton3.setText("Chi ti???t l????ng");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnReset)
                .addGap(18, 18, 18)
                .addComponent(btnSua, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnXuatFile)
                .addGap(18, 18, 18)
                .addComponent(btnXoa, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnReset, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(btnThem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btnXoa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel25, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtTimKiem)
                    .addComponent(btnXuatFile, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnSua, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        txtMaNV.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setText("M?? nh??n vi??n:");

        txtHoTen.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("H??? v?? t??n:");

        txtGioiTinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Gi???i t??nh:");

        txtNgaySinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel10.setText("Ng??y sinh:");

        txtQueQuan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtQueQuan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtQueQuanActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel13.setText("Qu?? qu??n:");

        txtChuyenMon.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Chuy??n m??n:");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap(36, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(18, 18, 18)
                        .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel13)
                            .addComponent(jLabel10)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtChuyenMon, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(txtNgaySinh)
                            .addComponent(txtQueQuan))))
                .addGap(40, 40, 40))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHoTen, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtQueQuan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChuyenMon, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        txtTrinhDo.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Tr??nh ?????:");

        txtChucVu.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Ch???c v???:");

        txtLuongCoBan.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtLuongCoBan.setText("1490000");
        txtLuongCoBan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtLuongCoBanActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel11.setText("L????ng c?? b???n:");

        txtHeSoLuong.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel22.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel22.setText("H??? s??? l????ng:");

        txtPhuCap.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel7.setText("Ph??? c???p ch???c v???:");

        txtTienAnTrua.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel8.setText("Ti???n ??n tr??a:");

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel11)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(txtTrinhDo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtLuongCoBan, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtPhuCap)
                    .addComponent(txtTienAnTrua)))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTrinhDo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtChucVu, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLuongCoBan, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtHeSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPhuCap, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTienAnTrua, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        txtThucLinh.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel9.setText("Th???c l??nh:");

        txtTGHuongHSHT.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel12.setText("Th???i gian h?????ng");

        jLabel26.setText("H??? s??? l????ng hi???n t???i:");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, 0))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(jLabel12)
                .addGap(0, 0, 0)
                .addComponent(jLabel26))
        );

        txtUngLuong.setText("0");

        jLabel27.setText("???ng l????ng:");

        txtTienPhat.setText("0");

        jLabel28.setText("Ti???n ph???t:");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel28, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel27, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtThucLinh, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtTGHuongHSHT, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(txtUngLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 210, Short.MAX_VALUE)
                    .addComponent(txtTienPhat)))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTienPhat, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtUngLuong, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE)
                    .addComponent(jLabel27, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTGHuongHSHT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtThucLinh, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 122, Short.MAX_VALUE)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout NhanVienChinhThucLayout = new javax.swing.GroupLayout(NhanVienChinhThuc);
        NhanVienChinhThuc.setLayout(NhanVienChinhThucLayout);
        NhanVienChinhThucLayout.setHorizontalGroup(
            NhanVienChinhThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(NhanVienChinhThucLayout.createSequentialGroup()
                .addGroup(NhanVienChinhThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienChinhThucLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        NhanVienChinhThucLayout.setVerticalGroup(
            NhanVienChinhThucLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienChinhThucLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 330, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Nh??n vi??n ch??nh th???c", NhanVienChinhThuc);

        NhanVienHopDongThoiVu.setBackground(new java.awt.Color(255, 255, 255));

        tblNhanVienHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tblNhanVienHD.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "M?? NV", "H??? T??n", "Ng??y Sinh", "Gi???i T??nh", "Qu?? Qu??n", "Chuy??n M??n", "L????ng", "Th???i Gian H??", "Th???i Gian H???t H??"
            }
        ));
        tblNhanVienHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienHDMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblNhanVienHD);

        txtMaNVHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtNgaySinhHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtHoTenHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtQueQuanHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtGioiTinhHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTG.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        txtTGHet.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel14.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel14.setText("M?? nh??n vi??n:");

        jLabel15.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel15.setText("H??? v?? t??n:");

        jLabel16.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel16.setText("Ng??y sinh:");

        jLabel17.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel17.setText("Gi???i t??nh:");

        jLabel18.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel18.setText("Qu?? qu??n:");

        jLabel20.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel20.setText("Th???i gian h???p ?????ng:");

        jLabel21.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel21.setText("Th???i gian h???t h???p ?????ng:");

        txtLuongHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel19.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel19.setText("L????ng:");

        txtChuyenMonHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel23.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel23.setText("Chuy??n m??n:");

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel24.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel24.setText("Nh???p t??n ????? t??m ki???m:");

        txtTimKiemNVHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        btnThemHD.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        btnThemHD.setText("Th??m");
        btnThemHD.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemHDMouseClicked(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton5.setText("S???a");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton6.setText("X??a");
        jButton6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton6MouseClicked(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton1.setText("Reset");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton1MouseClicked(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jButton2.setText("Xu???t excel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimKiemNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 493, Short.MAX_VALUE)
                .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTimKiemNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnThemHD, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jButton2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout NhanVienHopDongThoiVuLayout = new javax.swing.GroupLayout(NhanVienHopDongThoiVu);
        NhanVienHopDongThoiVu.setLayout(NhanVienHopDongThoiVuLayout);
        NhanVienHopDongThoiVuLayout.setHorizontalGroup(
            NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(NhanVienHopDongThoiVuLayout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel14)
                    .addComponent(jLabel15)
                    .addComponent(jLabel16))
                .addGap(18, 18, 18)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NhanVienHopDongThoiVuLayout.createSequentialGroup()
                        .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(txtMaNVHD, javax.swing.GroupLayout.DEFAULT_SIZE, 236, Short.MAX_VALUE)
                            .addComponent(txtHoTenHD, javax.swing.GroupLayout.Alignment.LEADING))
                        .addGap(41, 41, 41)
                        .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.TRAILING)))
                    .addGroup(NhanVienHopDongThoiVuLayout.createSequentialGroup()
                        .addComponent(txtNgaySinhHD, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addComponent(jLabel23)))
                .addGap(18, 18, 18)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(NhanVienHopDongThoiVuLayout.createSequentialGroup()
                        .addComponent(txtGioiTinhHD, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(149, 149, 149)
                        .addComponent(jLabel19)
                        .addGap(23, 23, 23))
                    .addGroup(NhanVienHopDongThoiVuLayout.createSequentialGroup()
                        .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtChuyenMonHD)
                            .addComponent(txtQueQuanHD))
                        .addGap(59, 59, 59)
                        .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)))
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtLuongHD, javax.swing.GroupLayout.DEFAULT_SIZE, 238, Short.MAX_VALUE)
                    .addComponent(txtTG)
                    .addComponent(txtTGHet))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienHopDongThoiVuLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        NhanVienHopDongThoiVuLayout.setVerticalGroup(
            NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, NhanVienHopDongThoiVuLayout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtMaNVHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtGioiTinhHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtLuongHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtHoTenHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtQueQuanHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTG, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, 31, Short.MAX_VALUE))
                .addGap(25, 25, 25)
                .addGroup(NhanVienHopDongThoiVuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTGHet, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtChuyenMonHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNgaySinhHD, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel23, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
        );

        jTabbedPane1.addTab("Nh??n vi??n h???p ?????ng th???i v???", NhanVienHopDongThoiVu);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1329, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtQueQuanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtQueQuanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtQueQuanActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        this.editNV();
    }//GEN-LAST:event_btnSuaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        this.editNVHD();
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        this.fill();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed
        this.reset();
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnThemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThemActionPerformed
        this.themNV();
    }//GEN-LAST:event_btnThemActionPerformed

    private void btnXoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnXoaActionPerformed
        this.deleteNV();
    }//GEN-LAST:event_btnXoaActionPerformed

    private void tblNhanVienHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienHDMouseClicked
        this.fillNVHD();
    }//GEN-LAST:event_tblNhanVienHDMouseClicked

    private void jButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1MouseClicked
        this.resetNVHD();
    }//GEN-LAST:event_jButton1MouseClicked

    private void btnThemHDMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemHDMouseClicked
        this.themNVHD();
    }//GEN-LAST:event_btnThemHDMouseClicked

    private void jButton6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton6MouseClicked
        this.deleteNVHD();
    }//GEN-LAST:event_jButton6MouseClicked

    private void btnXuatFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnXuatFileMouseClicked
        XuatFileXl x = new XuatFileXl(listNV);
        x.show();

    }//GEN-LAST:event_btnXuatFileMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        XuatFileXl x = new XuatFileXl(listNV);
        x.lblNhanVien.setText("Nh??n vi??n h???p ?????ng");
        x.show();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void txtLuongCoBanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtLuongCoBanActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtLuongCoBanActionPerformed

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        chiTietLuong ctLuong = new chiTietLuong();
        ctLuong.show();
    }//GEN-LAST:event_jButton3MouseClicked

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel NhanVienChinhThuc;
    private javax.swing.JPanel NhanVienHopDongThoiVu;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.JButton btnThemHD;
    private javax.swing.JButton btnXoa;
    private javax.swing.JButton btnXuatFile;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTable tblNhanVienHD;
    private javax.swing.JTextField txtChucVu;
    private javax.swing.JTextField txtChuyenMon;
    private javax.swing.JTextField txtChuyenMonHD;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtGioiTinhHD;
    private javax.swing.JTextField txtHeSoLuong;
    private javax.swing.JTextField txtHoTen;
    private javax.swing.JTextField txtHoTenHD;
    private javax.swing.JTextField txtLuongCoBan;
    private javax.swing.JTextField txtLuongHD;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMaNVHD;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtNgaySinhHD;
    private javax.swing.JTextField txtPhuCap;
    private javax.swing.JTextField txtQueQuan;
    private javax.swing.JTextField txtQueQuanHD;
    private javax.swing.JTextField txtTG;
    private javax.swing.JTextField txtTGHet;
    private javax.swing.JTextField txtTGHuongHSHT;
    private javax.swing.JTextField txtThucLinh;
    private javax.swing.JTextField txtTienAnTrua;
    private javax.swing.JTextField txtTienPhat;
    private javax.swing.JTextField txtTimKiem;
    private javax.swing.JTextField txtTimKiemNVHD;
    private javax.swing.JTextField txtTrinhDo;
    private javax.swing.JTextField txtUngLuong;
    // End of variables declaration//GEN-END:variables
}
