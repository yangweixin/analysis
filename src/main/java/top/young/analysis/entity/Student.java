package top.young.analysis.entity;


import top.young.analysis.annotation.SqlColumnCom;
import top.young.analysis.annotation.SqlInt;
import top.young.analysis.annotation.SqlTable;
import top.young.analysis.annotation.SqlVarchar;

@SqlTable(name = "USER")
public class Student {

    @SqlInt(name="user_id",common = @SqlColumnCom(primaryKey = true))
    private int userId;
    @SqlVarchar(name="student_name",value = 50)
    private String studentName;

    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getStudentName() {
        return studentName;
    }
    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
}
