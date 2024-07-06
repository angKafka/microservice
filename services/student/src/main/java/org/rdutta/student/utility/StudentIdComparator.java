package org.rdutta.student.utility;

import org.rdutta.student.dto.StudentResponse;
import org.rdutta.student.entity.Student;

import java.util.Comparator;

public interface StudentIdComparator extends Comparator<StudentResponse> {
    @Override
    default int compare(StudentResponse student_1, StudentResponse student_2) {
       return student_1.firstname().compareTo(student_2.firstname());
    }
}
