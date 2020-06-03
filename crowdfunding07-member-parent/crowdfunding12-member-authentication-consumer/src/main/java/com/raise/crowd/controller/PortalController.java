package com.raise.crowd.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author zmj
 * @date 2020/6/3 19:26
 * @Description
 */
@Controller
public class PortalController {

    @RequestMapping("/")
    public String showPortalPage() {
        return "portal";
    }
}
