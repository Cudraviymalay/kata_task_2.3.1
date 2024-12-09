package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
}