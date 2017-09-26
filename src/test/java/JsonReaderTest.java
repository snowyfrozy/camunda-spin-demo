import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.camunda.spin.Spin.*;

public class JsonReaderTest {

    @Test
    public void getPropertiesFromObject(){
        SpinJsonNode json = JSON("{\"customer\": \"Kermit\"}");
        SpinJsonNode customerProperty = json.prop("customer");
        String customerName = customerProperty.stringValue();
        assertEquals(customerName, "Kermit");
    }

    @Test
    public void getFirstElementFromArray(){
        SpinJsonNode json = JSON("{\"customer\": [\"Kermit\", \"Waldo\"]}");
        SpinJsonNode customerProperty = json.prop("customer");
        SpinList customers = customerProperty.elements();
        SpinJsonNode customer = (SpinJsonNode) customers.get(0);
        String customerName = customer.stringValue();
        assertEquals(customerName, "Kermit");
    }
}
