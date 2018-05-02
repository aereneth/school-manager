import java.sql.*;
import javafx.collections.*;
    
public class Database {
    public static final String DRIVER = "org.apache.derby.jdbc.ClientDriver";
    public static final String URL = "jdbc:derby://localhost:1527/school";
    
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;
    
    public static void connect() {
        try {
            Class.forName(DRIVER);
            con = DriverManager.getConnection(URL, "root", "1234");
            stmt = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        } catch (Exception e) {}
    }
    
    public static void close() {
        try {
            con.close();
        } catch (Exception e) {}
    }
    
    public static void execute(String query) {
        try {
            connect();
            stmt.executeUpdate(query);
            close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
    }
    
    public static Student getStudent(int studentId) {
        Student student = new Student();
        try {
            connect();
            
            String query = String.format("SELECT * FROM STUDENTS WHERE ID = %d", studentId);
            rs = stmt.executeQuery(query);
            rs.first();
            student.setId(rs.getInt("id"));
            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            student.setGender(rs.getInt("gender") == 0 ? 'F' : 'M');
            student.setPhoneNumber(rs.getString("phone_number"));
            student.setAddress(rs.getString("address"));
            
            student.setBalance(rs.getDouble("tuition_fee") - rs.getDouble("amount_paid"));
            student.setAmountPaid(rs.getDouble("amount_paid"));
            student.setTuitionFee(rs.getDouble("tuition_fee"));
            
            student.setModuleOld(rs.getInt("old_module"));
            student.setModuleNew(rs.getInt("new_module"));
            student.setModuleTotal(rs.getInt("old_module") + rs.getInt("new_module"));
            close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return student;
    }
    
    public static ObservableList<Student> getStudentMany() {
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            connect();
            
            String query = "SELECT * FROM STUDENTS";
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setFirstName(rs.getString("first_name"));
                student.setLastName(rs.getString("last_name"));
                student.setGender(rs.getInt("gender") == 0 ? 'F' : 'M');
                student.setPhoneNumber(rs.getString("phone_number"));
                student.setAddress(rs.getString("address"));
                
                student.setTuitionFee(rs.getDouble("tuition_fee"));
                student.setAmountPaid(rs.getDouble("amount_paid"));
                student.setBalance(rs.getDouble("tuition_fee") - rs.getDouble("amount_paid"));
                
                student.setModuleOld(rs.getInt("old_module"));
                student.setModuleNew(rs.getInt("new_module"));
                student.setModuleTotal(rs.getInt("old_module") + rs.getInt("new_module"));
                
                students.add(student);
            }
            close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return students;
    }
    
    public static Teacher getTeacher(int teacherId) {
        Teacher teacher = new Teacher();
        try {
            connect();
            
            String query = String.format("SELECT * FROM TEACHERS WHERE ID = %d", teacherId);
            rs = stmt.executeQuery(query);
            rs.first();
            teacher.setId(rs.getInt("id"));
            teacher.setFirstName(rs.getString("first_name"));
            teacher.setLastName(rs.getString("last_name"));
            teacher.setGender(rs.getInt("gender") == 0 ? 'F' : 'M');
            teacher.setPhoneNumber(rs.getString("phone_number"));
            teacher.setAddress(rs.getString("address"));
            
            teacher.setDepartment(rs.getString("department"));
            teacher.setDesignation(rs.getString("designation"));
            teacher.setSalary(rs.getDouble("salary"));
            
            close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return teacher;
    }
    
    public static ObservableList<Teacher> getTeacherMany() {
        ObservableList<Teacher> teachers = FXCollections.observableArrayList();
        try {
            connect();
            
            String query = "SELECT * FROM TEACHERS";
            rs = stmt.executeQuery(query);
            while(rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getInt("id"));
                teacher.setFirstName(rs.getString("first_name"));
                teacher.setLastName(rs.getString("last_name"));
                teacher.setGender(rs.getInt("gender") == 0 ? 'F' : 'M');
                teacher.setPhoneNumber(rs.getString("phone_number"));
                teacher.setAddress(rs.getString("address"));
                
                teacher.setDepartment(rs.getString("department"));
                teacher.setDesignation(rs.getString("designation"));
                teacher.setSalary(rs.getDouble("salary"));
                
                teachers.add(teacher);
            }
            close();
        } catch (Exception e) { System.err.println(e.getMessage()); }
        return teachers;
    }
}
