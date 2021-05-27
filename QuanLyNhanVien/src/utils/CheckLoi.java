package utils;
import java.awt.Color;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
public class CheckLoi {
    public static boolean checkRong(JTextField txt,StringBuilder bd, String mess){
        boolean ok = true;
        if (txt.getText().equals("")) {
            bd.append(mess).equals("\n");
            txt.setBackground(Color.yellow);
            txt.requestFocus();
            ok = false;
        }else{
            txt.setBackground(Color.white);
        }
        return ok;
    }
    
    public static void checkRongPass(JPasswordField txt, StringBuilder bd, String mess){
        String pass = new String(txt.getPassword());
        if (pass.equals("")) {
            bd.append(mess).append("\n");
            txt.setBackground(Color.yellow);
            txt.requestFocus();
        }else{
            txt.setBackground(Color.white);
        }        
    }
     
    public static boolean checkSo(JTextField txt, StringBuilder bd,String mess,String ten){
        boolean ok = true;
        if (!checkRong(txt, bd,mess)) {
            return false;
        }
        try {
            float so = Float.parseFloat(txt.getText());
            if (so < 0) {
                bd.append(ten+" phải lớn hơn hoặc bằng 0\n");
                txt.setBackground(Color.yellow);
                txt.requestFocus();
                ok = false;
            }
        } catch (Exception e) {
            bd.append(ten +" không được ký tự khác.Phải là số\n");
            txt.setBackground(Color.yellow);
            txt.requestFocus();
            ok = false;
        }
        return ok;
    }
    

    public static boolean checkNgaySinh(JTextField txt, StringBuilder bd){
        boolean ok = true;
        if (!checkRong(txt, bd,"Ngày chưa nhập\n")) {
            return false;
        }
        SimpleDateFormat date = new SimpleDateFormat();
        date.applyPattern("yyyy-MM-dd");
        try {
            Date dob = date.parse(txt.getText());
            txt.setBackground(Color.white);
        } catch (Exception e) {
            bd.append("Ngày nhập không đúng định dạng (vd: 1999/01/24)\n");
            txt.setBackground(Color.yellow);
        }
        return ok;
    }
    
    public static boolean checkNgayNhap(JTextField txt, StringBuilder bd){
        boolean ok = true;
        if (!checkRong(txt, bd,"Ngày chưa nhập\n")) {
            return false;
        }
        SimpleDateFormat date = new SimpleDateFormat();
        date.applyPattern("yyyy-MM-dd");
        try {
            Date dob = date.parse(txt.getText());
            txt.setBackground(Color.white);
        } catch (Exception e) {
            bd.append("Ngày nhập không đúng định dạng (vd: 1999-01-01)\n");
            txt.setBackground(Color.yellow);
        }
        return ok;
    }
}
