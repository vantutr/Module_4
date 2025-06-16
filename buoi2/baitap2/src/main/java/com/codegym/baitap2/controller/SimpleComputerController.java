package com.codegym.baitap2.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SimpleComputerController {

    @GetMapping("/")
    public String showForm() {
        return "index";
    }

    @PostMapping("/calculate")
    public String calculate(
            @RequestParam("firstOperand") double firstOperand,
            @RequestParam("secondOperand") double secondOperand,
            @RequestParam("operator") String operator,
            Model model) {

        double result = 0;
        String error = "";

        try {
            switch (operator) {
                case "Addition(+)":
                    result = firstOperand + secondOperand;
                    break;
                case "Subtraction(-)":
                    result = firstOperand - secondOperand;
                    break;
                case "Multiplication(x)":
                    result = firstOperand * secondOperand;
                    break;
                case "Division(/)":
                    if (secondOperand != 0) {
                        result = firstOperand / secondOperand;
                    } else {
                        error = "Không thể chia cho 0!";
                    }
                    break;
                default:
                    error = "Phép toán không hợp lệ!";
            }
        } catch (Exception e) {
            error = "Dữ liệu nhập không hợp lệ!";
        }

        if (!error.isEmpty()) {
            model.addAttribute("error", error);
        } else {
            model.addAttribute("result", result);
        }

        model.addAttribute("firstOperand", firstOperand);
        model.addAttribute("secondOperand", secondOperand);

        return "index";
    }
}
