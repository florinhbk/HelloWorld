import org.apache.http.Header;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.io.IOException;

import static org.apache.http.entity.ContentType.getOrDefault;

public class SearchHotelsByIDTest {

    /*  @Test
      public void hardAssertStopsImmediately() throws IOException {
          //Arrange
          CloseableHttpClient client = HttpClientBuilder.create().build();

          //Act
          CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));

          //Assert
          System.out.println("First assert");
          Assert.assertEquals(response.getStatusLine().getStatusCode(),404);

          System.out.println("Second assert");
          Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(),"application/xml");

          System.out.println("Third assert");
          Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(),"UTF-8");

          client.close();
          response.close(); */
    @Test
    public void softAssertContinuesToTheEnd() throws IOException {
        //Arrange
        CloseableHttpClient client = HttpClientBuilder.create().build();
        SoftAssert softAssert = new SoftAssert();

        //Act
        CloseableHttpResponse response = client.execute(new HttpGet("https://api.github.com/"));

        //Assert
        Header[] allHeaders = response.getAllHeaders();
        System.out.println("First assert");
        //System.out.println(allHeaders.);
        softAssert.assertEquals(response.getStatusLine().getStatusCode(), 404);

        System.out.println("Second assert");
        softAssert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/xml");

        System.out.println("Third assert");
        softAssert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");

        client.close();
        response.close();
        softAssert.assertAll();
    }
}