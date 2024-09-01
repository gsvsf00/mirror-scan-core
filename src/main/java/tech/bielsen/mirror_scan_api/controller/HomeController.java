package tech.bielsen.mirror_scan_api.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class HomeController {

    private final HttpServletRequest httpServletRequest;

    public HomeController(HttpServletRequest httpServletRequest) {
        this.httpServletRequest = httpServletRequest;
    }

    @GetMapping
    public String home(Principal principal) {
        return "Hello, " + httpServletRequest.getSession().getId();
    }

    @GetMapping("/secured")
    public String secured() {
        return "Secured endpoint";
    }

}
