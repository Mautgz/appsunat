package pe.sunat.sunatapi.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import io.swagger.models.Model;

@Controller
@RequestMapping(value = "app/dashboard")
public class DashboardController {
    private static final String DASHBOARD_INDEX = "layout/panel";
    @GetMapping(value = "/")
    public String index(Model model){
        return DASHBOARD_INDEX;
    }
}
