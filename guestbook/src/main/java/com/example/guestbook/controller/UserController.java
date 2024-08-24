package com.example.guestbook.controller;

import com.example.guestbook.model.User;
import com.example.guestbook.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String showJoinForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }
        return "user/join";
    }

    @PostMapping("/join")
    public String processJoinForm(@ModelAttribute("user") User user, RedirectAttributes redirectAttributes) {
        try {
            userService.join(user);
            redirectAttributes.addFlashAttribute("message", "회원가입이 정상적으로 완료되었습니다.");
            return "redirect:/guestbook";
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/user/join";
        }
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        if (!model.containsAttribute("user")) {
            model.addAttribute("user", new User());
        }

        return "/user/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute("user") User user,
                                   HttpServletRequest request,
                                   RedirectAttributes redirectAttributes) {
        try {
            User authenticatedUser = userService.login(user);

            // 세션 처리
            HttpSession session = request.getSession();
            session.setAttribute("user", authenticatedUser);

            redirectAttributes.addFlashAttribute("message", "로그인 성공!");
        } catch (IllegalArgumentException e) {
            redirectAttributes.addFlashAttribute("error", e.getMessage());
            redirectAttributes.addFlashAttribute("user", user);
            return "redirect:/user/login";
        }

        return "redirect:/guestbook";
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        return "redirect:/guestbook";
    }

}
