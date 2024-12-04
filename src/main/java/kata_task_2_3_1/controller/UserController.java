package kata_task_2_3_1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {

    @GetMapping("/all_users")
    public String allUsers () {
        //какая-то логика
        return null;
    }
}
