/* Routes are mapped out here for action - Create, Read, Update, Delete data*/
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTodos(Model model){
        model.addAttribute("todos", todoRepository.findAll());
        return "list"; // list shows all entries
    }

    @GetMapping("/add")
    public String todoForm(Model model){
        model.addAttribute("todo", new Todo());
        return "todoform"; // todoform allows user to add new entry
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result){
        if (result.hasErrors()){
            return "todoform"; // todoform also validates
        }
        todoRepository.save(todo);
        return "redirect:/"; // redirects the user to default route "/"
    }

    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "show"; // show displays individual entry
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("todo",todoRepository.findById(id).get());
        return "todoform"; // todoform allows user to update existing entry chosen by ID
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        todoRepository.deleteById(id);
        return "redirect:/";

    }
}
