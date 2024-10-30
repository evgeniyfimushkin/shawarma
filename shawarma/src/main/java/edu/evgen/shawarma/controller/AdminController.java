package edu.evgen.shawarma.controller;

import edu.evgen.shawarma.OrderAdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderAdminService adminService;

    public AdminController(OrderAdminService adminService) {
        this.adminService = adminService;
    }
    @DeleteMapping("/deleteOrders")
    public String deleteAllOrders(){
        adminService.deleteAllOrders();
        return "redirect:/admin";
    }
}
