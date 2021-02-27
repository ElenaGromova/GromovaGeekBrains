package app;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class StudentServer {
    private StudentRepository studentRepository;

    public Page<Student> findAll(int page, int size) {
        return studentRepository.findAll(PageRequest.of(page, size));
    }

    public List<Student> findAllStudents(){
        return studentRepository.findAll();
    }


    public Student saveOrUpdate(Student student){
        return studentRepository.save(student);
    };

    public void deleteById(Long id){
        studentRepository.deleteById(id);
    };

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);
    }


}
