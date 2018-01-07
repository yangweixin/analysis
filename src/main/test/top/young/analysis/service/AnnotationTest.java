package top.young.analysis.service;

import org.junit.Test;
import top.young.analysis.annotation.SqlColumnCom;
import top.young.analysis.annotation.SqlInt;
import top.young.analysis.annotation.SqlTable;
import top.young.analysis.annotation.SqlVarchar;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationTest extends BaseTest {
    @Test
    public void test() throws Exception{
        Class<?> clazz = Class.forName("top.young.analysis.entity.Student");
        SqlTable table = clazz.getAnnotation(SqlTable.class);
        List<String> columns = new ArrayList<>();
        for(Field field : clazz.getDeclaredFields()){
            String columnName = null;
            Annotation[] anns = field.getAnnotations();
            if(anns.length < 1){
                continue;
            }

            if(anns[0] instanceof SqlVarchar){
                SqlVarchar sStr = (SqlVarchar)anns[0];
                if(sStr.name().length() < 1){
                    columnName = field.getName().toUpperCase();
                }else{
                    columnName = sStr.name();
                }
                columns.add(columnName + " VARCHAR(" + sStr.value() + ")" + getComms(sStr.common()));
            }
            if(anns[0] instanceof SqlInt){
                SqlInt sInt = (SqlInt)anns[0];
                if(sInt.name().length() < 1){
                    columnName = field.getName().toUpperCase();
                }else{
                    columnName = sInt.name();
                }
                columns.add(columnName + " INT" + getComms(sInt.common()));
            }
        }

        StringBuilder creator = new StringBuilder( "CREATE TABLE " + table.name() + " (");
        for (String line : columns){
            creator.append(line + ",");
        }
        creator.deleteCharAt(creator.length() - 1);
        creator.append(" )");

        System.out.println("------>"+ creator);
    }

    private static String getComms(SqlColumnCom comm){
        String comStr = "";
        if( !comm.allowNull() ){
            comStr += " NOT NULL ";
        }
        if( !comm.primaryKey() ){
            comStr += " PRIMARY KEY ";
        }
        if( !comm.unique() ){
            comStr += " UNIQUE ";
        }

        return comStr;
    }
}
