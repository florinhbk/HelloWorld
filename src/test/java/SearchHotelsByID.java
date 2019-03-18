import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.SkipException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import java.io.IOException;

public class SearchHotelsByID {
    CloseableHttpClient client;
    CloseableHttpResponse response;

    @BeforeClass
    public void ParentLocalSetupMethod() throws IOException {
        client = HttpClientBuilder.create().build();
        response = client.execute(new HttpGet("https://api.github.com/helloworld"));

        int actualStatusCode = response.getStatusLine().getStatusCode();

        if (actualStatusCode != 200) {
            throw new SkipException("Basic criteria failed," +
                    "was expecting code 200, but got: " + actualStatusCode);
        }

    }

    @AfterClass
    public void cleanup() throws IOException {
        client.close();
        response.close();
    }

}