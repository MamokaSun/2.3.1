package hiber.config.controller;

import hiber.service.UserService;
import hiber.service.UserServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class ControllerForUser {

    private final UserService userService;

    public ControllerForUser(UserService userService) {
        this.userService = userService;
    }
    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("user", userService.index());
        return "/user/index";
    }
//    @GetMapping("/{id}")
//    public String show(@PathVariable("id") int id, ModelMap model){
//        model.addAttribute("user", userService.show(id));
//        return "user/show";
//    }
}
