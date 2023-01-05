package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class ApplicationExampleTest {
    private static int count;

    @Value(value = "${info.school.name}")
    private String schoolName;

    @Value(value = "${info.app.name}")
    private String appName;

    @Value(value = "${info.app.description}")
    private String appDescription;

    @Value(value = "${info.app.version}")
    private String appVersion;

    @Autowired
    private CollegeStudent collegeStudent;

    @Autowired
    private StudentGrades studentGrades; //would be better to use abstraction

    @BeforeEach
    public void beforeEach() {
        count += 1;
        System.out.printf("Testing: %s which is %s. Version: %s. Execution of test method: %d\n",
                appName, appDescription, appVersion, count);
        collegeStudent.setFirstname("Ali");
        collegeStudent.setLastname("Hamzayev");
        collegeStudent.setEmailAddress("alihmzyv@gmail.com");
        studentGrades.setMathGradeResults(List.of(50.1, 60.1, 100.1));
        collegeStudent.setStudentGrades(studentGrades);
    }

    @Test
    void basicTest() {
    }

    @Test
    void basicTest2() {
    }
}
