package cart.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import cart.service.JwpCartService;

@Controller
public class SettingsController {
    private final JwpCartService jwpCartService;

    public SettingsController(JwpCartService jwpCartService) {
        this.jwpCartService = jwpCartService;
    }

    @GetMapping("/settings")
    public String settings(Model model) {
        List<MemberResponse> members = jwpCartService.findAllMembers();
        model.addAttribute()
        return "settings";
    }
}
