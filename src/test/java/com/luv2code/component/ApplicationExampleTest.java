package com.luv2code.component;

import com.luv2code.component.models.CollegeStudent;
import com.luv2code.component.models.StudentGrades;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ApplicationExampleTest {
    private static int count;

    @Autowired
    private ApplicationContext applicationContext;

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
    @DisplayName("Add Grade Results for student grades")
    void addGradeResultsForStudentGrades() {
        double expected = 210.3;
        assertEquals(expected,
                studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("Is grade grater")
    void isGradeGreaterStudentGrades() {
        assertTrue(studentGrades.isGradeGreater(90, 70), "Should be true");
    }

    @Test
    @DisplayName("Check null for student grades")
    void testCheckNullForStudentGrades() {
        assertNotNull(studentGrades.checkNull(collegeStudent.getStudentGrades().getMathGradeResults()));
    }

    @Test
    @DisplayName("Student without grades")
    void checkStudentWithoutGrades() {
        assertNull(applicationContext.getBean("collegeStudent", CollegeStudent.class).getStudentGrades());
    }

    @Test
    @DisplayName("Student is prototype")
    void testCollegeStudentScopeIsPrototype() {
        CollegeStudent instance2 = applicationContext.getBean("collegeStudent", CollegeStudent.class);
        assertNotSame(collegeStudent, instance2);
    }

    @Test
    @DisplayName("Grade Average")
    void testGradeAverage() {
        double sumExpected = 210.3;
        double avgExpected = 70.1;
        assertAll("Testing all asserts",
                () -> assertEquals(
                        sumExpected,
                        studentGrades.addGradeResultsForSingleClass(collegeStudent.getStudentGrades().getMathGradeResults())),
                () -> assertEquals(
                        avgExpected,
                        studentGrades.findGradePointAverage(collegeStudent.getStudentGrades().getMathGradeResults())
                ));
    }
}
