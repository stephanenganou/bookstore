package com.bookstore.tracker.controller.other;

import org.springframework.stereotype.Controller;

/**
 * @author Stephane Nganou
 * @version 1.0
 * <p>
 * This interface enforces specifics methods (OtherController related) for classes which will implement it.
 */
@Controller
public interface OtherController {

    /**
     * This method redirects to the not found page.
     */
    String redirectToNotFoundPage();
}
