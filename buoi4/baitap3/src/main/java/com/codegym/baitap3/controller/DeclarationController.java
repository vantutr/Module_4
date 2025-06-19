package com.codegym.baitap3.controller;

import com.codegym.baitap3.model.MedicalDeclaration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
public class DeclarationController {

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("declaration", new MedicalDeclaration());
        addSelectionData(model);
        return "create";
    }

    @PostMapping("/submit")
    public String submitForm(@ModelAttribute("declaration") MedicalDeclaration declaration, Model model) {
        Map<String, String> errors = new HashMap<>();

        if (declaration.getName() == null || declaration.getName().trim().isEmpty()) {
            errors.put("nameError", "Họ tên không được để trống");
        }
        if (declaration.getYearOfBirth() == null || declaration.getYearOfBirth().isEmpty()) {
            errors.put("yearOfBirthError", "Vui lòng chọn năm sinh");
        }
        if (declaration.getGender() == null || declaration.getGender().isEmpty()) {
            errors.put("genderError", "Vui lòng chọn giới tính");
        }
        if (declaration.getNationality() == null || declaration.getNationality().isEmpty()) {
            errors.put("nationalityError", "Vui lòng chọn quốc tịch");
        }
        if (declaration.getIdCard() == null || declaration.getIdCard().trim().isEmpty()) {
            errors.put("idCardError", "Số CMND/Hộ chiếu không được để trống");
        }

        if (declaration.getStartDay() == null || declaration.getStartDay().isEmpty() ||
                declaration.getStartMonth() == null || declaration.getStartMonth().isEmpty() ||
                declaration.getStartYear() == null || declaration.getStartYear().isEmpty()) {
            errors.put("startDateError", "Ngày khởi hành không được để trống");
        }
        if (declaration.getEndDay() == null || declaration.getEndDay().isEmpty() ||
                declaration.getEndMonth() == null || declaration.getEndMonth().isEmpty() ||
                declaration.getEndYear() == null || declaration.getEndYear().isEmpty()) {
            errors.put("endDateError", "Ngày kết thúc không được để trống");
        }
        if (declaration.getTravelHistory14Days() == null || declaration.getTravelHistory14Days().trim().isEmpty()) {
            errors.put("travelHistoryError", "Vui lòng nhập lịch sử đi lại trong 14 ngày");
        }

        if (declaration.getProvince() == null || declaration.getProvince().isEmpty()) {
            errors.put("provinceError", "Vui lòng chọn Tỉnh/Thành");
        }
        if (declaration.getDistrict() == null || declaration.getDistrict().trim().isEmpty()) {
            errors.put("districtError", "Quận/Huyện không được để trống");
        }
        if (declaration.getWard() == null || declaration.getWard().trim().isEmpty()) {
            errors.put("wardError", "Phường/Xã không được để trống");
        }
        if (declaration.getAddress() == null || declaration.getAddress().trim().isEmpty()) {
            errors.put("addressError", "Địa chỉ nơi ở không được để trống");
        }
        if (declaration.getPhone() == null || declaration.getPhone().trim().isEmpty()) {
            errors.put("phoneError", "Số điện thoại không được để trống");
        } else {
            String phoneRegex = "^(0?)(3[2-9]|5[6|8|9]|7[0|6-9]|8[0-6|8|9]|9[0-4|6-9])[0-9]{7}$";
            if (!Pattern.matches(phoneRegex, declaration.getPhone().trim())) {
                errors.put("phoneError", "Số điện thoại không đúng định dạng.");
            }
        }

        if (declaration.getSymptomFever() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomCough() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomShortBreath() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomSoreThroat() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomVomit() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomDiarrhea() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomHemorrhage() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");
        if (declaration.getSymptomRash() == null) errors.put("symptomError", "Vui lòng chọn Có/Không cho tất cả các triệu chứng");

        if (declaration.getExposureAnimal() == null) errors.put("exposureError", "Vui lòng trả lời tất cả các câu hỏi về lịch sử phơi nhiễm");
        if (declaration.getExposureCovid() == null) errors.put("exposureError", "Vui lòng trả lời tất cả các câu hỏi về lịch sử phơi nhiễm");

        if (!errors.isEmpty()) {
            model.addAttribute("errors", errors);
            addSelectionData(model);
            return "create";
        }

        model.addAttribute("declaration", declaration);
        return "result";
    }

    private void addSelectionData(Model model) {
        List<String> years = IntStream.rangeClosed(1920, 2025).mapToObj(String::valueOf).collect(Collectors.toList());
        List<String> days = IntStream.rangeClosed(1, 31).mapToObj(s -> String.format("%02d", s)).collect(Collectors.toList());
        List<String> months = IntStream.rangeClosed(1, 12).mapToObj(s -> String.format("%02d", s)).collect(Collectors.toList());

        model.addAttribute("years", years);
        model.addAttribute("days", days);
        model.addAttribute("months", months);
        model.addAttribute("genders", Arrays.asList("Nam", "Nữ", "Khác"));
        model.addAttribute("nationalities", Arrays.asList("Việt Nam", "Mỹ", "Nhật Bản", "Hàn Quốc", "Trung Quốc"));
        model.addAttribute("provinces", Arrays.asList("Hà Nội", "Đà Nẵng", "TP. Hồ Chí Minh"));
    }
}