package todo.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import todo.model.User;
import todo.service.UserService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.Optional;

import static todo.util.HttpSessionUtil.setGuest;

@Controller
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/addUser")
    public String addUser(Model model, HttpSession session) {
        setGuest(model, session);
        model.addAttribute("user", new User(0, "Новый пользователь", "Введите электронную почту",
                "Введите логин", "Введите пароль", LocalDateTime.now()));
        return "users/addUser";
    }

    @PostMapping("/registration")
    public String registration(Model model, @ModelAttribute User user) {
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
    public String login(@ModelAttribute User user, HttpServletRequest req) {
        Optional<User> userDb = userService.findByLogin(user.getLogin());
        if (userDb.isEmpty()) {
            return "redirect:/users/loginPage?fail=true";
        }
        HttpSession session = req.getSession();
        session.setAttribute("user", userDb.get());
        return "redirect:/index";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/users/loginPage";
    }
}
