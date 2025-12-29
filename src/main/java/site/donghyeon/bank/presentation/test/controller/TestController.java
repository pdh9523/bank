package site.donghyeon.bank.presentation.test.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import site.donghyeon.bank.presentation.resolver.CurrentUser;
import site.donghyeon.bank.presentation.resolver.GetClaims;

@RestController
@RequestMapping("/test")
public class TestController {

    @PostMapping()
    public CurrentUser test(@GetClaims CurrentUser currentUser) {
        return currentUser;
    }
}
