package com.bookstore.tracker.controller.other;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Stephane Nganou
 * @version 1.0
 */
@Controller
@Slf4j
public class OtherControllerImpl implements OtherController {

    @RequestMapping("/notfound")
    @Override
    public String redirectToNotFoundPage() {
        log.info("Returning not found page");
        return "not-found";
    }
}
