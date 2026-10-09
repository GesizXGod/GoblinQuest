import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Database {
    private static final String URL = "jdbc:sqlite:goblinquest.db";

    public static void createTable() {
        String sql = "CREATE TABLE IF NOT EXISTS habits (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL UNIQUE, " +
                "expReward INTEGER NOT NULL, " +
                "isDefeated BOOLEAN NOT NULL, " +
                "player_id INTEGER, " +
                "FOREIGN KEY (player_id) REFERENCES players(id))";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица создана успешно");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы: " + e.getMessage());
        }
    }
    public static void createPlayersTable() {
        String sql = "CREATE TABLE IF NOT EXISTS players (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "name TEXT NOT NULL UNIQUE, " +
                "hp INTEGER NOT NULL, " +
                "exp INTEGER NOT NULL)";   // колонка для опыта

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
            System.out.println("Таблица players создана");
        } catch (SQLException e) {
            System.out.println("Ошибка создания таблицы players: " + e.getMessage());
        }
    }
    public static void insertHabit(Habit habit) {
        String sql = "INSERT OR IGNORE INTO habits (name, expReward, isDefeated) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, habit.getName());
            ps.setInt(2, habit.getExpReward());
            ps.setBoolean(3, habit.isDefeated());
            ps.executeUpdate();
            System.out.println("Привычка сохранена: " + habit.getName());
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения привычки: " + e.getMessage());
        }

    }
    public static void savePlayer(Player player) {
        String sql = "INSERT OR IGNORE INTO players (name, hp, exp) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, player.getName());
            ps.setInt(2, player.getHp());
            ps.setInt(3, player.getExp());     // опыт
            ps.executeUpdate();
            System.out.println("Игрок сохранён: " + player.getName());
        } catch (SQLException e) {
            System.out.println("Ошибка сохранения игрока: " + e.getMessage());
        }
    }
    public static ArrayList<Habit> loadHabits() {
        ArrayList<Habit> habits = new ArrayList<>();
        String sql = "SELECT name, expReward, isDefeated FROM habits";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String name = rs.getString("name");
                int expReward = rs.getInt("expReward");        // достать число из колонки expReward
                boolean isDefeated = rs.getBoolean("isDefeated");   // достать boolean из колонки isDefeated

                Habit habit = new Habit(name, expReward);
                if (isDefeated) {
                    habit.defeat();              // пометить привычку побеждённой
                }
                habits.add(habit);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки: " + e.getMessage());
        }
        return habits;

    }
    public static Player loadPlayer(String name) {
        String sql = "SELECT hp, exp FROM players WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int hp = rs.getInt("hp");
                    int exp = rs.getInt("exp");
                    return new Player(name, hp, exp);
                }
            }
        } catch (SQLException e) {
            System.out.println("Ошибка загрузки игрока: " + e.getMessage());
        }
        return null;
    }
    public static void updateHabit(Habit habit) {
        String sql = "UPDATE habits SET isDefeated = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setBoolean(1, habit.isDefeated());   // новый статус победы
            ps.setString(2, habit.getName());    // имя привычки
            ps.executeUpdate();
            System.out.println("Your habit is win"); // сообщение об успехе
        } catch (SQLException e) {
            System.out.println("Your habit is wrong"); // сообщение об ошибке
        }
    }
    public static void updatePlayer(Player player) {
        String sql = "UPDATE players SET hp = ?, exp = ? WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, player.getHp());      // здоровье
            ps.setInt(2, player.getExp());      // опыт
            ps.setString(3, player.getName());   // имя игрока
            ps.executeUpdate();
            System.out.println("Игрок обновлён: " + player.getName());
        } catch (SQLException e) {
            System.out.println("Ошибка обновления игрока: " + e.getMessage());
        }
    }
    public static void deleteHabit(String name) {
        String sql = "DELETE FROM habits WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(URL);
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);   // имя удаляемой привычки
            int rows = ps.executeUpdate();
            System.out.println("Удалено привычек: " + rows);
        } catch (SQLException e) {
            System.out.println("Ошибка удаления привычки: " + e.getMessage());
        }
    }
    public static void printHabitsWithPlayer() {
        String sql = "SELECT habits.name, habits.expReward, players.name AS player " +
                "FROM habits " +
                "JOIN players ON habits.player_id = players.id";

        try (Connection conn = DriverManager.getConnection(URL);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                String habitName = rs.getString("name");
                int expReward = rs.getInt("expReward");
                String playerName = rs.getString("player");
                System.out.println(habitName + " | " + expReward + " | " + playerName);
            }
        } catch (SQLException e) {
            System.out.println("Ошибка JOIN: " + e.getMessage());
        }
    }
}