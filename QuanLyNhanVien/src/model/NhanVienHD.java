
package model;

import java.sql.Date;

public class NhanVienHD {
    private String MaNV;
    private String HoTen;
    private Boolean GioiTinh;
    private Date NgaySinh;
    private String QueQuan;
    private  String ChuyenMon;
    private Float Luong;
    private  Date ThoiGianHD;
    private  Date ThoiGianHetHD;

    public NhanVienHD() {
    }

    public NhanVienHD(String MaNV, String HoTen, Boolean GioiTinh, Date NgaySinh, String QueQuan, String ChuyenMon, Float Luong, Date ThoiGianHD, Date ThoiGianHetHD) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.QueQuan = QueQuan;
        this.ChuyenMon = ChuyenMon;
        this.Luong = Luong;
        this.ThoiGianHD = ThoiGianHD;
        this.ThoiGianHetHD = ThoiGianHetHD;
    }
    
    
    
    public String getMaNV() {
        return MaNV;
    }

    public void setMaNV(String MaNV) {
        this.MaNV = MaNV;
    }

    public String getHoTen() {
        return HoTen;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public Boolean getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(Boolean GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public Date getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(Date NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public String getChuyenMon() {
        return ChuyenMon;
    }

    public void setChuyenMon(String ChuyenMon) {
        this.ChuyenMon = ChuyenMon;
    }

    public Float getLuong() {
        return Luong;
    }

    public void setLuong(Float Luong) {
        this.Luong = Luong;
    }

    public Date getThoiGianHD() {
        return ThoiGianHD;
    }

    public void setThoiGianHD(Date ThoiGianHD) {
        this.ThoiGianHD = ThoiGianHD;
    }

    public Date getThoiGianHetHD() {
        return ThoiGianHetHD;
    }

    public void setThoiGianHetHD(Date ThoiGianHetHD) {
        this.ThoiGianHetHD = ThoiGianHetHD;
    }
    
    
    
}
