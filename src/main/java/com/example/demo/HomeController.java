/* Routes are mapped out here for action - Create, Read, Update, Delete data*/
package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class HomeController {

    @Autowired
    TodoRepository todoRepository;

    @RequestMapping("/")
    public String listTodos(Model model) {
        model.addAttribute("todos", todoRepository.findAll());
        return "list"; // list shows all entries
    }

    @GetMapping("/add")
    public String todoForm(Model model) {
        model.addAttribute("todo", new Todo());
        return "todoform"; // todoform allows user to add new entry
    }

    @PostMapping("/process")
    public String processForm(@Valid Todo todo, BindingResult result,
                              @RequestParam("dueDate") String dueDate) {
        if (result.hasErrors()) {
            return "todoform"; // todoform also validates entries
        }

        Date date = new Date();
//        String myString = dateFormat.format(date);
//        System.out.println(myString);

        try {
            date = new SimpleDateFormat("MM-dd-YY").parse(dueDate);
        }
        catch (Exception e){
            e.printStackTrace();
        }

        todo.setDueDate(date);
        todoRepository.save(todo);
        return "redirect:/"; // redirects the user to default route "/"
    }


    /* change this method so that detailed page shows current task in different layout than other tasks.
     * This will be show in show.html*/
    @RequestMapping("/detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "show"; // show displays individual entry
    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model) {
        model.addAttribute("todo", todoRepository.findById(id).get());
        return "todoform"; // todoform allows user to update existing entry chosen by ID
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id) {
        todoRepository.deleteById(id);
        return "redirect:/"; // delete then redirect to list

    }
}
