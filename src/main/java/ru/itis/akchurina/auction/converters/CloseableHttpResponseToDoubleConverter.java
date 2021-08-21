package ru.itis.akchurina.auction.converters;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.itis.akchurina.auction.services.CurrencyServiceImpl;

import java.io.IOException;

@Component
public class CloseableHttpResponseToDoubleConverter implements Converter<CloseableHttpResponse, Double> {

    Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Override
    public Double convert(CloseableHttpResponse response) {

        JsonParser parser = new JsonParser();
        String result;
        try {
            result = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            logger.error("Bad response parse");
            throw new IllegalArgumentException();
        }

        JsonObject object = (JsonObject) parser.parse(result);
        JsonObject currencyArray = object.getAsJsonObject("quotes");

        return currencyArray.get("USDRUB").getAsDouble();
    }
}