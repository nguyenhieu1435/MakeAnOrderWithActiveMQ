package vn.edu.iuh.fit.frontend.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.text.DecimalFormat;

@Component
public interface SUtils {

    static String convertToStandardPriceFormat(double price){
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");

        // Format the double price to a String
        String formattedPrice = decimalFormat.format(price);
        return formattedPrice;
    }
}
