
package model;

import java.sql.Date;

public class NhanVien {
    private String MaNV;
    private String HoTen;
    private Boolean GioiTinh;
    private Date NgaySinh;
    private String QueQuan;
    private String ChuyenMon;
    private String TrinhDo;
    private String ChucVu;
    private Float LuongCoBan;
    private Float HeSoLuong;
    private Float PhuCap;
    private Float TienAnTrua;
    private Float ThucLinh;
    private Float TienPhat;
    private Float UngLuong;
    private Date TGHuongHSLHT;

    public NhanVien(String MaNV, String HoTen, Boolean GioiTinh, Date NgaySinh, String QueQuan, String ChuyenMon, String TrinhDo, String ChucVu, Float LuongCoBan, Float HeSoLuong, Float PhuCap, Float TienAnTrua, Float ThucLinh) {
        this.MaNV = MaNV;
        this.HoTen = HoTen;
        this.GioiTinh = GioiTinh;
        this.NgaySinh = NgaySinh;
        this.QueQuan = QueQuan;
        this.ChuyenMon = ChuyenMon;
        this.TrinhDo = TrinhDo;
        this.ChucVu = ChucVu;
        this.LuongCoBan = LuongCoBan;
        this.HeSoLuong = HeSoLuong;
        this.PhuCap = PhuCap;
        this.TienAnTrua = TienAnTrua;
        this.ThucLinh = ThucLinh;
    }

    public Float getTienPhat() {
        return TienPhat;
    }

    public void setTienPhat(Float TienPhat) {
        this.TienPhat = TienPhat;
    }

    public Float getUngLuong() {
        return UngLuong;
    }

    public void setUngLuong(Float UngLuong) {
        this.UngLuong = UngLuong;
    }
    
    
    
    public Date getTGHuongHSLHT() {
        return TGHuongHSLHT;
    }

    public void setTGHuongHSLHT(Date TGHuongHSLHT) {
        this.TGHuongHSLHT = TGHuongHSLHT;
    }
    
    
    public NhanVien() {
    }
    
    public Float Luong(){
        return getLuongCoBan()*getHeSoLuong()+getPhuCap()+getTienAnTrua()-getTienPhat()-getUngLuong();
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

    public String getTrinhDo() {
        return TrinhDo;
    }

    public void setTrinhDo(String TrinhDo) {
        this.TrinhDo = TrinhDo;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String ChucVu) {
        this.ChucVu = ChucVu;
    }

    public Float getLuongCoBan() {
        return LuongCoBan;
    }

    public void setLuongCoBan(Float LuongCoBan) {
        this.LuongCoBan = LuongCoBan;
    }

    public Float getHeSoLuong() {
        return HeSoLuong;
    }

    public void setHeSoLuong(Float HeSoLuong) {
        this.HeSoLuong = HeSoLuong;
    }

    public Float getPhuCap() {
        return PhuCap;
    }

    public void setPhuCap(Float PhuCap) {
        this.PhuCap = PhuCap;
    }

    public Float getTienAnTrua() {
        return TienAnTrua;
    }

    public void setTienAnTrua(Float TienAnTrua) {
        this.TienAnTrua = TienAnTrua;
    }

    public Float getThucLinh() {
        return ThucLinh;
    }

    public void setThucLinh(Float ThucLinh) {
        this.ThucLinh = ThucLinh;
    }

    
    
    
}
