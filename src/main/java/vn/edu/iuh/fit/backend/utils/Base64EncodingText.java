package vn.edu.iuh.fit.backend.utils;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class Base64EncodingText implements EncodingText {

    @Override
    public String encrypt(String jsonText) throws Exception {
        return Base64.encodeBase64String(jsonText.getBytes());
    }

    @Override
    public String decrypt(String encodeJson) throws Exception {
        return new String(Base64.decodeBase64(encodeJson));
    }

}
