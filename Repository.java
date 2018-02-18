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

    public List<Highscore> getTop5() {
        List<Highscore> top5 = new ArrayList<>();
        String query = "select * from score order by seconds asc limit 5";
        try (Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
                PreparedStatement stmt = con.prepareStatement(query);) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
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

    public String registerScore(long duration, String initials) {
        String verdict = "";
        List<Highscore> top5;
        String query = "insert into score (seconds, initials) values (?, ?)";
        try (Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
                PreparedStatement stmt = con.prepareStatement(query);) {
            top5 = getTop5();
            int place = 0;
            for (int i = 0; i < top5.size(); i++) {
                if (duration < top5.get(i).getScore() * 1000) {
                    place = i + 1;
                    break;
                }
            }
            if(place > 0) {
                verdict = "\nNew Highscore!";
            }
            stmt.setDouble(1, (duration * 1.0) / 1000);
            stmt.setString(2, initials);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return verdict;
    }

    public List<String> getStats() {
        List<String> stats = new ArrayList<>();
        String query1 = "select avg(seconds) as average from score";
        String query2 = "select count(ID) as games from score";
        String query3 = "select initials as initials, count(ID) as total from score group by initials order by total desc";
        try (Connection con = DriverManager.getConnection(log.getCode(), log.getName(), log.getPass());
             PreparedStatement stmt1 = con.prepareStatement(query1);
             PreparedStatement stmt2 = con.prepareStatement(query2);
             PreparedStatement stmt3 = con.prepareStatement(query3);
        ){
            ResultSet rs1 = stmt1.executeQuery();
            while (rs1.next()) {
                stats.add("Avarage Score:   " + String.format("%.2f",rs1.getDouble("average")));
            }
            ResultSet rs2 = stmt2.executeQuery();
            while (rs2.next()) {
                stats.add("\nGames played:   " + rs2.getInt("games"));
            }
            ResultSet rs3 = stmt3.executeQuery();
            while (rs3.next()) {
                stats.add("\nMost devoted player:   " + rs3.getString("initials"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return stats;
    }
}
