package todo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

import static todo.util.HttpSessionUtil.setGuest;

@Controller
public class IndexControl {

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        setGuest(model, session);
        return "index";
    }
}
