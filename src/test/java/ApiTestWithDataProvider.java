import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class ApiTestWithDataProvider {

    private CloseableHttpClient client;
    private CloseableHttpResponse response;

    @BeforeMethod
    public void buildClient(){
        client = HttpClientBuilder.create().build();
    }

    @DataProvider
    public static Object[][] endpointsRequiringAthentication(){
        return new Object[][] {
                {"user"},
                {"user/followers"},
                {"notifications"}
        };
    }


    @Test(dataProvider = "endpointsRequiringAuthentication")
    public void userEndpointReturns401(String endpoint) throws IOException {

        response = client.execute(new HttpGet("https://api.github.com/user" + endpoint));
        int actualStatusCode = response.getStatusLine().getStatusCode();

        Assert.assertEquals(actualStatusCode, 401);
    }

    @AfterMethod
    public void cleanup() throws IOException{
        client.close();
        response.close();
    }


}
