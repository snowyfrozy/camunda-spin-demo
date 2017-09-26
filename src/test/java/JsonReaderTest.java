import org.camunda.spin.SpinList;
import org.camunda.spin.json.SpinJsonNode;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.camunda.spin.Spin.*;

public class JsonReaderTest {

    @Test
    public void getPropertiesFromObject() {
        SpinJsonNode json = JSON("{\"customer\": \"Kermit\"}");
        SpinJsonNode customerProperty = json.prop("customer");
        String customerName = customerProperty.stringValue();
        assertEquals(customerName, "Kermit");
    }

    @Test
    public void getFirstElementFromArray() {
        SpinJsonNode json = JSON("{\"customer\": [\"Kermit\", \"Waldo\"]}");
        SpinJsonNode customerProperty = json.prop("customer");
        SpinList customers = customerProperty.elements();
        SpinJsonNode customer = (SpinJsonNode) customers.get(0);
        String customerName = customer.stringValue();
        assertEquals(customerName, "Kermit");
    }

    // TODO
    @Test
    public void queryingAnElement() {
        String jsonInString = "{\"child\": [{\"id\": 1,\"name\": \"Lucy\",\"sex\": \"female\"},{\"id\": 2,\"name\": \"Tracy\",\"sex\": \"female\"}],\"number\": 1,\"boolean\": true}";
        SpinJsonNode json = JSON(jsonInString);
        SpinList<SpinJsonNode> childs = json.jsonPath("$.child").elementList();
        System.out.println(childs.size());
        for (SpinJsonNode node : childs) {

        }
    }

    @Test
    public void queryingByJsonPath() {
        String jsonInString = "{\n" +
                "  \"options\": {\n" +
                "      \"apple\" :{\n" +
                "          \"displayName\" : \"Apfel\",\n" +
                "          \"selected\" : \"true\"\n" +
                "      }, \n" +
                "      \"kiwi\" :{\n" +
                "          \"displayName\" : \"Kiwi\",\n" +
                "          \"selected\" : \"false\"\n" +
                "      }\n" +
                "  } \n" +
                "}";
        SpinJsonNode json = JSON(jsonInString);
        SpinList<SpinJsonNode> childs = json.jsonPath("$..apple.selected").elementList();
        String s = childs.get(0).stringValue();
        assertEquals("true", s);
    }


}
