package ru.itis.akchurina.auction.services;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Value("${api.currency.host}")
    private String host;

    Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    private ConversionService conversionService;

    @Override
    public Double convertCurrencyToRub(Double value) {
        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(host);

        List<NameValuePair> urlParameters = new ArrayList<>();

        urlParameters.add(new BasicNameValuePair("access_key", "21641fdedd6b4e70b91dfb7b3d4634c6"));
        urlParameters.add(new BasicNameValuePair("currencies", "RUB"));

        URI uri;
        try {
            uri = new URIBuilder(httpGet.getURI())
                    .addParameters(urlParameters)
                    .build();
        } catch (URISyntaxException e) {
            logger.error("Bad uri build");
            throw new IllegalArgumentException();
        }

        httpGet.setURI(uri);

        CloseableHttpResponse response;
        try {
            response = client.execute(httpGet);
        } catch (IOException e) {
            logger.error("Bad httpclient execute");
            throw new IllegalArgumentException();
        }

        return conversionService.convert(response, Double.class) * value;

    }
}
