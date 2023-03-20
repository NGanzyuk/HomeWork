import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class Main {
    public static ObjectMapper map = new ObjectMapper();
    public static void main(String[] args) throws IOException {
        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setDefaultRequestConfig(RequestConfig.custom()
                        .setConnectTimeout(5000)    // максимальное время ожидание подключения к серверу
                        .setSocketTimeout(30000)    // максимальное время ожидания получения данных
                        .setRedirectsEnabled(false) // возможность следовать редиректу в ответе
                        .build())
                .build();

        HttpGet request = new HttpGet("https://api.nasa.gov/planetary/apod?api_key=xrpXaPehPspVT2kQTehJOE76fq6lSjrKFmbbcw66");
        CloseableHttpResponse response = httpClient.execute(request);
        map.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        List<NasaResponse> nasaResponse =  map.readValue(
                response.getEntity().getContent(), new TypeReference<List<NasaResponse>>() {}
        );
        String url = nasaResponse.get(0).getUrl();
        HttpGet nextRequest = new HttpGet(url);
        CloseableHttpResponse nextResponse = httpClient.execute(nextRequest);
        String body = new String(nextResponse.getEntity().getContent().readAllBytes(), StandardCharsets.UTF_8);
        try(FileWriter fw = new FileWriter("NasaResponse.txt")){
            fw.write(body);
            fw.flush();
            fw.close();
        }catch (IOException ex){
            ex.getMessage();
        }

    }
}
