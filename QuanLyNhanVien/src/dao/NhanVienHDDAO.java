
package dao;

import helpers.JdbcHelper;
import model.NhanVienHD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienHDDAO {
    public void insert(NhanVienHD model) {
        String sql = "INSERT INTO NhanVienHD (MaNV, HoTen, GioiTinh, NgaySinh, QueQuan, ChuyenMon, Luong, ThoiGianHD, ThoiGianHetHD) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        JdbcHelper.executeUpdate(sql,model.getMaNV() ,model.getHoTen(), model.getGioiTinh(), model.getNgaySinh(), model.getQueQuan(),model.getChuyenMon(),model.getLuong(),model.getThoiGianHD(),model.getThoiGianHetHD());
    }
    public void update(NhanVienHD model){
        String sql = "UPDATE NhanVienHD SET HoTen = ?, GioiTinh = ?, NgaySinh = ?, QueQuan = ?, ChuyenMon = ?, Luong = ?, ThoiGianHD = ?, ThoiGianHetHD = ? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,model.getHoTen(), model.getGioiTinh(), model.getNgaySinh(), model.getQueQuan(),model.getChuyenMon(),model.getLuong(),model.getThoiGianHD(),model.getThoiGianHetHD(),model.getMaNV());
    }
     public void delete(String MaNV) {
        String sql = "Delete from NhanVienHD where MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
    public List<NhanVienHD> select() {
   //     String sql = "SELECT * FROM NhanVien";
        String sql = "SELECT * FROM NhanVienHD";
        return select(sql);
    }
    public List<NhanVienHD> findName(String key){
        String sql = "select * from NhanVienHD where HoTen like N'%"+key+"%'";
        return select(sql);
    }

    private List<NhanVienHD> select(String sql, Object... args) {
        List<NhanVienHD> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVienHD model = readFromResultSet(rs);
                    list.add(model);
                }
            } finally {
                rs.getStatement().getConnection().close();
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return list;
    }

    private NhanVienHD readFromResultSet(ResultSet rs) throws SQLException {
       // MaNV, HoTen, GioiTinh, NgaySinh, QueQuan, ChuyenMon, TrinhDo, ChucVu, LuongCoBan, HeSoLuong, PhuCapChucVu, TienAnTrua, ThucLinh, TGHuongHSLHT
        NhanVienHD model = new NhanVienHD();
        model.setMaNV(rs.getString("MaNV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setQueQuan(rs.getString("QueQuan"));
        model.setChuyenMon(rs.getString("ChuyenMon"));
        model.setLuong(rs.getFloat("Luong"));
        model.setThoiGianHD(rs.getDate("ThoiGianHD"));
        model.setThoiGianHetHD(rs.getDate("ThoiGianHetHD"));

        return model;
    }
}
