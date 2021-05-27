
package helpers;


import dao.NhanVienDAO;
import dao.NhanVienHDDAO;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import model.NhanVien;
import model.NhanVienHD;

/**
 *
 * @author mac
 */
public class ThuExcelWriter {
    private static HSSFCellStyle createStyleForTitle(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setBold(true);
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFont(font);
        return style;
    }
    public static void ghifileexle(String linkfile,List<NhanVien> list) throws FileNotFoundException, IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        //NhanVienDAO nvDAO = new NhanVienDAO();
        //List<NhanVien> list=nvDAO.select();
        
        int rownum = 2;
        Cell cell;
        Row row;
        Row row1;
        
        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã nhân viên");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Họ Tên");
        cell.setCellStyle(style);
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Giới tính");
        cell.setCellStyle(style);
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Ngày sinh");
        cell.setCellStyle(style);
        
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Quê quán");
        cell.setCellStyle(style);
        
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Chuyên môn");
        cell.setCellStyle(style);
        
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Trình độ");
        cell.setCellStyle(style);
        
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Chức vụ");
        cell.setCellStyle(style);
        
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Lương cơ bản");
        cell.setCellStyle(style);
        
        cell = row.createCell(9, CellType.STRING);
        cell.setCellValue("Hệ số lương");
        cell.setCellStyle(style);
        
        cell = row.createCell(10, CellType.STRING);
        cell.setCellValue("Phụ cấp chức vụ");
        cell.setCellStyle(style);
        
        cell = row.createCell(11, CellType.STRING);
        cell.setCellValue("Tiền ăn trưa");
        cell.setCellStyle(style);
        
        cell = row.createCell(12, CellType.STRING);
        cell.setCellValue("Tiền phạt");
        cell.setCellStyle(style);
        
        cell = row.createCell(13, CellType.STRING);
        cell.setCellValue("Ứng lương");
        cell.setCellStyle(style);
        
        
        cell = row.createCell(14, CellType.STRING);
        cell.setCellValue("Thời gian hưởng hệ số lương hiện tại");
        cell.setCellStyle(style);
        
        cell = row.createCell(15, CellType.STRING);
        cell.setCellValue("Thực lĩnh");
        cell.setCellStyle(style);
        
        
        //---------data-----------------------------
        // dư liêu trong cơ sơ lieu đươc đưa voà 
        for(NhanVien nv : list){
           rownum++;
            System.out.println(nv.getHoTen());
           row=sheet.createRow(rownum);
           cell = row.createCell(0, CellType.STRING);
           cell.setCellValue(nv.getMaNV());
           
           cell = row.createCell(1, CellType.STRING);
           cell.setCellValue(nv.getHoTen());
           
           cell = row.createCell(2, CellType.STRING);
           String gt="Nữ";
           if (nv.getGioiTinh()==true){
               gt="Nam";
           }
           cell.setCellValue(gt);
           
           cell = row.createCell(3, CellType.STRING);
           cell.setCellValue(String.valueOf(nv.getNgaySinh()));
           
           cell = row.createCell(4, CellType.STRING);
           cell.setCellValue(nv.getQueQuan());
           
           cell = row.createCell(5, CellType.STRING);
           cell.setCellValue(nv.getChuyenMon());
           
           cell = row.createCell(6, CellType.STRING);
           cell.setCellValue(nv.getTrinhDo());
           
           cell = row.createCell(7, CellType.STRING);
           cell.setCellValue(nv.getChucVu());
           
           cell = row.createCell(8, CellType.STRING);
           cell.setCellValue(nv.getLuongCoBan());
           
           cell = row.createCell(9, CellType.STRING);
           cell.setCellValue(nv.getHeSoLuong());
           
           cell = row.createCell(10, CellType.STRING);
           cell.setCellValue(nv.getPhuCap());
           
           cell = row.createCell(11, CellType.STRING);
           cell.setCellValue(nv.getTienAnTrua());
           
           cell = row.createCell(12, CellType.STRING);
           cell.setCellValue(nv.getTienPhat());
           
           cell = row.createCell(13, CellType.STRING);
           cell.setCellValue(nv.getUngLuong());
           
           cell = row.createCell(14, CellType.STRING);
           cell.setCellValue(String.valueOf(nv.getTGHuongHSLHT()));
           
           cell = row.createCell(15, CellType.STRING);
           cell.setCellValue(nv.getThucLinh());
           
        }
        row1 = sheet.createRow(1);

        File file=new File(linkfile);
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        
    }
    
    public static void ghifileexleNVHD(String linkfile) throws FileNotFoundException, IOException{
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Employees sheet");
        NhanVienHDDAO nvhdDAO = new NhanVienHDDAO();
        List<NhanVienHD> list=nvhdDAO.select();
        
        int rownum = 0;
        Cell cell;
        Row row;
        Row row1;
        
        HSSFCellStyle style = createStyleForTitle(workbook);
        row = sheet.createRow(rownum);
        
        cell = row.createCell(0, CellType.STRING);
        cell.setCellValue("Mã nhân viên");
        cell.setCellStyle(style);
        
        cell = row.createCell(1, CellType.STRING);
        cell.setCellValue("Họ Tên");
        cell.setCellStyle(style);
        
        cell = row.createCell(2, CellType.STRING);
        cell.setCellValue("Giới tính");
        cell.setCellStyle(style);
        
        cell = row.createCell(3, CellType.STRING);
        cell.setCellValue("Ngày sinh");
        cell.setCellStyle(style);
        
        cell = row.createCell(4, CellType.STRING);
        cell.setCellValue("Quê quán");
        cell.setCellStyle(style);
        
        cell = row.createCell(5, CellType.STRING);
        cell.setCellValue("Chuyên môn");
        cell.setCellStyle(style);
        
        cell = row.createCell(6, CellType.STRING);
        cell.setCellValue("Lương");
        cell.setCellStyle(style);
        
        cell = row.createCell(7, CellType.STRING);
        cell.setCellValue("Thời gian hợp đồng");
        cell.setCellStyle(style);
        
        cell = row.createCell(8, CellType.STRING);
        cell.setCellValue("Thời gian hết hợp đồng");
        cell.setCellStyle(style);
        

        
        //---------data-----------------------------
        // dư liêu trong cơ sơ lieu đươc đưa voà 
        for(NhanVienHD nv : list){
           rownum++;
           row=sheet.createRow(rownum);
           cell = row.createCell(0, CellType.STRING);
           cell.setCellValue(nv.getMaNV());
           
           cell = row.createCell(1, CellType.STRING);
           cell.setCellValue(nv.getHoTen());
           
           cell = row.createCell(2, CellType.STRING);
           cell.setCellValue(nv.getGioiTinh());
           
           cell = row.createCell(3, CellType.STRING);
           cell.setCellValue(String.valueOf(nv.getNgaySinh()));
           
           cell = row.createCell(4, CellType.STRING);
           cell.setCellValue(nv.getQueQuan());
           
           cell = row.createCell(5, CellType.STRING);
           cell.setCellValue(nv.getChuyenMon());
           
           cell = row.createCell(6, CellType.STRING);
           cell.setCellValue(nv.getLuong());
           
           cell = row.createCell(7, CellType.STRING);
           cell.setCellValue(String.valueOf(nv.getThoiGianHD()));
           
           cell = row.createCell(8, CellType.STRING);
           cell.setCellValue(String.valueOf(nv.getThoiGianHetHD()));
           
        }
        row1 = sheet.createRow(1);

        File file=new File(linkfile);
        file.getParentFile().mkdirs();
 
        FileOutputStream outFile = new FileOutputStream(file);
        workbook.write(outFile);
        System.out.println("Created file: " + file.getAbsolutePath());
        
    }
    
}
