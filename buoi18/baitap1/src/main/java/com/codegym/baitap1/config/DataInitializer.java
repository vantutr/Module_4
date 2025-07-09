package com.codegym.baitap1.config;

import com.codegym.baitap1.model.Weather;
import com.codegym.baitap1.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Lớp này sẽ được thực thi khi ứng dụng khởi động.
 * Dùng để thêm một số dữ liệu mẫu vào cơ sở dữ liệu nếu CSDL trống.
 */
@Component
public class DataInitializer implements CommandLineRunner {

    private final WeatherRepository weatherRepository;

    @Autowired
    public DataInitializer(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        // Chỉ thêm dữ liệu nếu bảng trống
        if (weatherRepository.count() == 0) {
            weatherRepository.save(new Weather(null, "hanoi", "28°C", "Có mây, thỉnh thoảng có mưa rào"));
            weatherRepository.save(new Weather(null, "hochiminh", "32°C", "Nắng nóng, trời quang"));
            weatherRepository.save(new Weather(null, "danang", "30°C", "Nắng đẹp, gió nhẹ"));

            System.out.println("Đã khởi tạo dữ liệu thời tiết mẫu vào MySQL.");
        } else {
            System.out.println("CSDL đã có dữ liệu, không cần khởi tạo.");
        }
    }
}

