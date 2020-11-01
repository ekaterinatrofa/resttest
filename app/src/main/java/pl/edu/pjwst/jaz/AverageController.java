package pl.edu.pjwst.jaz;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController

public class AverageController {
    @GetMapping("average")
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {

        if (numbers != null) {
            String[] arr = numbers.split(",");

            double sum = 0;

            for (String value : arr) {
                sum += Double.parseDouble(value);
            }

            BigDecimal avg = new BigDecimal(sum / arr.length);
            BigDecimal rounded = avg.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();

            return new AverageResult("Average equals " + rounded);
        } else {

            return new AverageResult("Please put parameters");

        }
    }
}