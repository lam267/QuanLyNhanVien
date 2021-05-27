
package dao;

import helpers.JdbcHelper;
import model.NhanVien;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO {
    public void insert(NhanVien model) {
            String sql = "INSERT INTO NhanVien (MaNV, HoTen, GioiTinh, NgaySinh, QueQuan, ChuyenMon, TrinhDo, ChucVu, LuongCoBan, HeSoLuong, PhuCap, TienAnTrua, ThucLinh, TGHuongHSLHT, TienPhat, UngLuong) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            JdbcHelper.executeUpdate(sql,model.getMaNV() ,model.getHoTen(), model.getGioiTinh(), model.getNgaySinh(), model.getQueQuan(),model.getChuyenMon(),model.getTrinhDo(),model.getChucVu(),model.getLuongCoBan(),model.getHeSoLuong(), model.getPhuCap(),model.getTienAnTrua(),model.getThucLinh(),model.getTGHuongHSLHT(),model.getTienPhat(),model.getUngLuong());

    }
    public void update(NhanVien model){
        String sql = "UPDATE NhanVien SET HoTen=?, GioiTinh=?, NgaySinh=?, QueQuan=?, ChuyenMon=?, TrinhDo=?, ChucVu=?, LuongCoBan=?, HeSoLuong=?, PhuCap=?, TienAnTrua=?, ThucLinh=?, TGHuongHSLHT=?, TienPhat = ?, UngLuong=? WHERE MaNV=?";
        JdbcHelper.executeUpdate(sql,model.getHoTen(), model.getGioiTinh(), model.getNgaySinh(), model.getQueQuan(),model.getChuyenMon(),model.getTrinhDo(),model.getChucVu(),model.getLuongCoBan(),model.getHeSoLuong(), model.getPhuCap(),model.getTienAnTrua(),model.getThucLinh(),model.getTGHuongHSLHT(),model.getTienPhat(),model.getUngLuong(),model.getMaNV());
    }
    public void updateHeSoLuong(NhanVien model){
        String sql = "UPDATE NhanVien SET HeSoLuong=?, ThucLinh=?, TGHuongHSLHT=? Where MaNV=?";
        JdbcHelper.executeUpdate(sql,model.getHeSoLuong(),model.getThucLinh(),model.getTGHuongHSLHT(),model.getMaNV());
    }
     public void delete(String MaNV) {
        String sql = "Delete from NhanVien where MaNV=?";
        JdbcHelper.executeUpdate(sql, MaNV);
    }
    public List<NhanVien> select() {
   //     String sql = "SELECT * FROM NhanVien";
        String sql = "SELECT * FROM NhanVien";
        return select(sql);
    }
    public List<NhanVien> findName(String key){
        String sql = "select * from NhanVien where HoTen like N'%"+key+"%'";
        return select(sql);
    }
    public List<NhanVien> findMaNV(String key){
        String sql = "select * from NhanVien where MaNV like N'%"+key+"%'";
        return select(sql);
    }

    private List<NhanVien> select(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    NhanVien model = readFromResultSet(rs);
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

    private NhanVien readFromResultSet(ResultSet rs) throws SQLException {
       // MaNV, HoTen, GioiTinh, NgaySinh, QueQuan, ChuyenMon, TrinhDo, ChucVu, LuongCoBan, HeSoLuong, PhuCapChucVu, TienAnTrua, ThucLinh, TGHuongHSLHT
        NhanVien model = new NhanVien();
        model.setMaNV(rs.getString("MaNV"));
        model.setHoTen(rs.getString("HoTen"));
        model.setGioiTinh(rs.getBoolean("GioiTinh"));
        model.setNgaySinh(rs.getDate("NgaySinh"));
        model.setQueQuan(rs.getString("QueQuan"));
        model.setChuyenMon(rs.getString("ChuyenMon"));
        model.setTrinhDo(rs.getString("TrinhDo"));
        model.setChucVu(rs.getString("ChucVu"));
        model.setLuongCoBan(rs.getFloat("LuongCoBan"));
        model.setHeSoLuong(rs.getFloat("HeSoLuong"));
        model.setPhuCap(rs.getFloat("PhuCap"));
        model.setTienAnTrua(rs.getFloat("TienAnTrua"));
        model.setThucLinh(rs.getFloat("ThucLinh"));
        model.setTGHuongHSLHT(rs.getDate("TGHuongHSLHT"));
        model.setTienPhat(rs.getFloat("TienPhat"));
        model.setUngLuong(rs.getFloat("UngLuong"));

        return model;
    }
}
