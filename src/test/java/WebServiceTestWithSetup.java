import org.testng.Assert;
import org.testng.annotations.Test;

import static org.apache.http.entity.ContentType.getOrDefault;


public class WebServiceTestWithSetup extends WebServiceBaseClass {


    @Test
    public void statusIs200() {
        Assert.assertEquals(response.getStatusLine().getStatusCode(), 200);
    }

    @Test
    public void typeIsJson() {
        Assert.assertEquals(getOrDefault(response.getEntity()).getMimeType(), "application/json");
    }

    @Test
    public void charSetIsUtf8() {
        Assert.assertEquals(getOrDefault(response.getEntity()).getCharset().toString(), "UTF-8");
    }
}