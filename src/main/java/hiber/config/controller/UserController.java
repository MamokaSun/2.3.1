package hiber.config.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    final UserService userServiceImp;

    public UserController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

    @GetMapping()
    public String index(ModelMap model) {
        model.addAttribute("users", userServiceImp.index());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model){
        model.addAttribute("user", userServiceImp.show(id));
        return "users/show";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String create(@ModelAttribute("user") User user){
        userServiceImp.save(user);
        return "redirect:/users";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImp.show(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImp.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userServiceImp.delete(id);
        return "redirect:/users";
    }
}
