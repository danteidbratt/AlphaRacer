package alpharacer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {
    
    LogInfo log;

    public Repository() {
        log = new LogInfo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        }
    }
    
    public List<Highscore> getTop5(){
        List<Highscore> top5 = new ArrayList<>();
        String query = "select * from score order by seconds asc limit 5";
        try(Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
            PreparedStatement stmt = con.prepareStatement(query);
        ){
            ResultSet rs = stmt.executeQuery();
            while(rs.next()){
                Highscore temp = new Highscore();
                temp.setScore(rs.getDouble("seconds"));
                temp.setInitials(rs.getString("initials"));
                top5.add(temp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return top5;
    }
    
    public void registerScore() {
        String query = "";
        try(Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
            PreparedStatement stmt = con.prepareStatement(query);){
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
