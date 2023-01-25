package todo.util;

import org.springframework.ui.Model;
import todo.model.User;

import javax.servlet.http.HttpSession;

public final class HttpSessionUtil {

    private HttpSessionUtil() {
    }

    public static void setGuest(Model model, HttpSession session) {
        User user = (User) session.getAttribute("user");
        if (user == null) {
            user = new User();
            user.setName("Гость");
        }
        model.addAttribute("user", user);
    }
}
