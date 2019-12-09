package com.jenking.spandroid;

import android.util.Log;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String sql  = "UPDATE user_course SET user_course_score = CASE id WHEN 12 THEN 35 WHEN 16 THEN 45 WHEN 10 THEN 123 END WHERE id IN (12,16,10,)";
        sql = sql.substring(0,sql.length()-1);
        System.out.println(sql);
    }
}