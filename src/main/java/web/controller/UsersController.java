package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.DAO.UserDAO;
import web.models.User;

@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserDAO userDAO;

    public UsersController(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    @GetMapping()
    public String getUsers(Model model) {
        model.addAttribute("users", userDAO.getAllUsers());
        return "users";
    }

    @GetMapping("/{id}")
    public String userById(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userDAO.userById(id));
        return "userbyid";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new User());
        return "new";
    }

    @PostMapping()
    public String addUser(@RequestParam("name") String name,
                          @RequestParam("surname") String surname,
                          @RequestParam("age") int age,
                          Model model) {
        User user = new User();
        user.setName(name);
        user.setSurname(surname);
        user.setAge(age);
        model.addAttribute("user", user);

        userDAO.save(user);

        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") int id) {
        model.addAttribute("user", userDAO.userById(id));
        return "edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userDAO.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userDAO.delete(id);
        return "redirect:/users";
    }
}