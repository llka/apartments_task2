package ru.ilka.apartments.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    private static Logger logger = LogManager.getLogger(LoginController.class);

    @GetMapping()
    public String loginResult(@RequestParam(value = "error", required = false) String error,
                              @RequestParam(value = "logout", required = false) String logout,
                              @RequestParam(value = "access", required = false) String access){

        logger.debug("error " + error);
        logger.debug("logout" + logout);
        logger.debug("access" + access);

        String resultMessage = "Access denied, please, sign in";

        if (error != null && "true".equals(error)) {
            resultMessage = "Invalid username and password!";
        }

        if (logout != null && "true".equals(logout)) {
            resultMessage = "You've been logged out successfully.";
        }

        if ("allow".equals(access)) {
            resultMessage = "You've been logged in successfully.";
        }

        if ("denied".equals(access)) {
            resultMessage = "Access denied, please, sign in";
        }

        return resultMessage;
    }
}
