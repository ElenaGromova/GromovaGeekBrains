package app;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
@AllArgsConstructor
public class StudentController {
    private StudentServer studentServer;

    @GetMapping
    public String showAllStudents(Model model,
                                  @RequestParam(defaultValue = "1", name = "p") Integer page)
    {
        if (page < 1) {
            page = 1;
        }
        model.addAttribute("students", studentServer.findAll(page - 1, 5));
        System.out.println("studentServer.findAll(page - 1, 5).getTotalPages()) " + studentServer.findAll(page - 1, 5).getTotalPages());

        return "students";
    }

    @PostMapping("/create")
    public String addForm(@RequestParam (name="name") String name,
                          @RequestParam (name="age") int age)
    {
        Student s = new Student(name, age);
        studentServer.saveOrUpdate(s);
        return "redirect:/students";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Student s = studentServer.findById(id).orElseThrow(() -> new ResourceNotFoundException("student with id: " + id + " doesn't exists"));
        model.addAttribute("student", s);
        return "edit_student";
    }

    @PostMapping("/edit")
    public String showEditForm(@ModelAttribute Student student) {
        studentServer.saveOrUpdate(student);
        return "redirect:/students";
    }

    @GetMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public String deleteById(@PathVariable Long id) {
        studentServer.deleteById(id);
        return "ok";
    }

}
