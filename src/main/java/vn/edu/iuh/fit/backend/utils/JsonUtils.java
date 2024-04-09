package vn.edu.iuh.fit.backend.utils;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import vn.edu.iuh.fit.backend.dto.CartListView;
import vn.edu.iuh.fit.backend.dto.ProceedCheckoutOrder;

import java.lang.reflect.Type;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

public class JsonUtils {
    public static String convertListProducts2Json(ProceedCheckoutOrder proceedCheckoutOrder) {
        Gson gson = new Gson();
        return gson.toJson(proceedCheckoutOrder);
    }
    public static ProceedCheckoutOrder convertJson2ListProducts(String json){
        Gson gson = new Gson();
        Type type = new TypeToken<ProceedCheckoutOrder>(){}.getType();
        return gson.fromJson(json, type);
    }

}
