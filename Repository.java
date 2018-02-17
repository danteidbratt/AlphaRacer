package alpharacer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

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
    
    public int registerScore(long duration) {
        int rowsAffected = -1;
        int place = 0;
        String initials = "AAA";
        List<Highscore> top5;
        String query = "insert into score (seconds, initials) values (?, ?)";
        try(Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
            PreparedStatement stmt = con.prepareStatement(query);){
            top5 = getTop5();
            for (int i = 0; i < top5.size(); i++) {
                if(duration < top5.get(i).getScore() * 1000) {
                    place = i+1;
                    break;
                }
            }
            if(place > 0) {
                initials = JOptionPane.showInputDialog("New HighScore!\nEnter Initials (3 letters)").trim().toUpperCase();
                if(initials.length() >= 3) {
                    stmt.setDouble(1, (duration*1.0)/1000);
                    stmt.setString(2, initials.substring(0, 3));
                    rowsAffected = stmt.executeUpdate();
                }
                else {
                    JOptionPane.showMessageDialog(null, "Invalid input\nTry again");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }
}