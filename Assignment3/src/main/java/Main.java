import java.sql.*;

public class Main {
    static String url = "jdbc:postgresql://localhost:5432/Assignment3";
    static String user = "postgres";
    static String password = "Ybh25765";
    static Connection connection;
    public static void main (String[] args){

        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();

            getAllStudents();

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void getAllStudents() {
        try {
            Statement statement = connection.createStatement();
            statement.executeQuery("SELECT * FROM students");
            ResultSet resultSet = statement.getResultSet();
            while (resultSet.next()){
                System.out.println(
                        "\nStudent ID:" + resultSet.getInt("student_id")
                        + "  |   First Name:" + resultSet.getString("first_name")
                        + "  |   Last Name:" + resultSet.getString("last_name")
                        + "  |   Email:" + resultSet.getString("email")
                        + "  |   Enrollment Date:" + resultSet.getDate("enrollment_date")
                );
                System.out.println("---------------------------------------------------------------------------------------------------------------------------------");
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void addStudent(String first_name, String last_name, String email, Date enrollment_date){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("INSERT INTO students (first_name, last_name, email, enrollment_date) " + "VALUES ('%s', '%s', '%s', '%s')", first_name, last_name, email, enrollment_date);
            statement.executeUpdate(query);
        }
        catch (SQLException e){
            e.printStackTrace();
        }
    }

    public static void updateStudentEmail(Integer student_id, String new_email){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("UPDATE students SET email = '%s' WHERE student_id = %d", new_email, student_id);
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void deleteStudent(Integer student_id){
        try {
            Statement statement = connection.createStatement();
            String query = String.format("DELETE FROM students WHERE student_id = %d", student_id);
            statement.executeUpdate(query);
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
