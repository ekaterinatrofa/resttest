package pl.edu.pjwst.jaz;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class AverageController {
    public AverageResult getAverage(@RequestParam(value = "numbers", required = false) String numbers) {
        //numbers...

        return new AverageResult("Please put parameters");

    }
}
