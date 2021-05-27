
package dao;

import helpers.JdbcHelper;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import model.user;

public class userDAO {
    public void insert(user model) {
        String sql = "INSERT INTO [user] (username, password) VALUES (?, ?)";
        JdbcHelper.executeUpdate(sql,model.getUsername(),model.getPassword());
    }
    public void update(user model){
        String sql = "UPDATE [user] SET password=? WHERE username=?";
        JdbcHelper.executeUpdate(sql,model.getPassword(), model.getUsername());
    }
    public List<user> selectUser(String username) {
   //     String sql = "SELECT * FROM user";
        String sql = "SELECT * FROM [user] where username = ?";
        return select(sql,username);
    }


    private List<user> select(String sql, Object... args) {
        List<user> list = new ArrayList<>();
        try {
            ResultSet rs = null;
            try {
                rs = JdbcHelper.executeQuery(sql, args);
                while (rs.next()) {
                    user model = readFromResultSet(rs);
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

    private user readFromResultSet(ResultSet rs) throws SQLException {
    
        user model = new user();
        model.setUsername(rs.getString("username"));
        model.setPassword(rs.getString("password"));


        return model;
    }
}
