package hiber.config.controller;

import hiber.model.User;
import hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
public class AdminController {

    final UserService userServiceImp;

    public AdminController(UserService userServiceImp) {
        this.userServiceImp = userServiceImp;
    }

//    @GetMapping(value = "/login")
//    public String getLoginPage() {
//        return "/admin/login";
//    }

    @GetMapping
    public String index(ModelMap model) {
        model.addAttribute("users", userServiceImp.index());
        return "/admin/index";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable("id") int id, ModelMap model){
        model.addAttribute("user", userServiceImp.show(id));
        return "/admin/show";
    }

    @GetMapping("/new")
    public String newUser(ModelMap model){
        model.addAttribute("user", new User());
        return "/admin/new";
    }

    @PostMapping("/users")
    public String create(@ModelAttribute("user") User user){
        userServiceImp.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        model.addAttribute("user", userServiceImp.show(id));
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userServiceImp.update(id, user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userServiceImp.delete(id);
        return "redirect:/admin";
    }
}
