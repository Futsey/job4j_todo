package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.User;
import todo.service.TimeZoneService;
import todo.service.UserService;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

import static todo.util.HttpSessionUtil.setGuest;


@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TimeZoneService timeZoneService;

    @GetMapping("/addUser")
    public String addUser(Model model, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("user", new User(0, "Новый пользователь", "Введите электронную почту",
                "Введите логин", "Введите пароль", LocalDateTime.now(), LocalDateTime.now()));
        model.addAttribute("timezones", timeZoneService.findAllTZ());
        return "users/addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user,
                               @RequestParam(value = "timezone", required = false) String timeZone) {
        user.setCreated(LocalDateTime.now());
        user.setUserZone(timeZoneService.findSelectedTZ(timeZone));
        Optional<User> regUser = userService.add(user);
        if (regUser.isEmpty()) {
            model.addAttribute("message",
                    "Пользователь с такой почтой уже существует");
            return "redirect:/users/fail";
        }
        return "redirect:/users/success";
    }

    @GetMapping("/fail")
    public String fail(Model model, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("fail", "Registration failed");
        return "/users/registrationFailed";
    }

    @GetMapping("/success")
    public String success(Model model, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("user", new User());
        model.addAttribute("success", "Registration successful");
        return "/users/registrationSuccess";
    }

    @GetMapping("/loginPage")
    public String loginPage(Model model, @RequestParam(name = "fail", required = false) Boolean fail, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("fail", fail != null);
        return "users/login";
    }

    @PostMapping("/login")
    public String loginUser(@ModelAttribute User user, Model model, HttpSession session) {
        var userDb = userService.findByLoginAndPassword(user.getLogin(), user.getPassword());
        if (userDb.isEmpty()) {
            model.addAttribute("fail", "Почта или пароль введены неверно");
            return "redirect:/loginPage?fail=true";
        }
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginPage";
    }
}
