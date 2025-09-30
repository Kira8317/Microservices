package com.example.demo.ProductRepo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepo extends JpaRepository<Product,Long> {

}
/*@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public Student updateStudent(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
            .map(student -> {
                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                return studentRepository.save(student);
            })
            .orElseThrow(() -> new ResourceNotFoundException("Student not found with id " + id));
    }
}
*/