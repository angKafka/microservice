package org.rdutta.student.utility;

import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;

public class StudentSort implements StudentIdComparator{
    @Override
    public int compare(StudentResponse student_1, StudentResponse student_2) {
        return StudentIdComparator.super.compare(student_1, student_2);
    }
}
