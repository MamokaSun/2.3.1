package hiber.config.controller;

import hiber.model.Role;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

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
        model.addAttribute("role", new String());
        return "/admin/new";
    }

    @PostMapping("/add") // was users
    public String create(@ModelAttribute("user") User user){
        userServiceImp.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String edit(ModelMap model, @PathVariable("id") int id) {
        Set<Role> roleList = new HashSet<>();
        roleList.add(userServiceImp.getRoleFromId(1));
        roleList.add(userServiceImp.getRoleFromId(2));
        model.addAttribute("user", userServiceImp.show(id));
        model.addAttribute("roleList", roleList);
        return "/admin/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id, @RequestParam(value = "setRoles", required = false) String roles) {
        userServiceImp.update(id, user, roles);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id){
        userServiceImp.delete(id);
        return "redirect:/admin";
    }

    public void createFirstUser(){
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setPassword("test");
        Set<Role> set = new HashSet<>();
        set.add(new Role(1, "ROLE_ADMIN"));
        user.setRole(set);
        userServiceImp.save(user);
    }
}
