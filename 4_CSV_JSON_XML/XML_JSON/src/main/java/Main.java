import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void parseXML(String fileName) {
        try {
            ArrayList<Employee> employeeList = new ArrayList<>();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new File(fileName));
            /*Пришлось преобразовать оригинальный XML файл, потому что вместо атрибутов и значений
             возвращался null. Я не понимаю с чем это связано, прошу Вас, пожалуйста,
             при проверке прокомментировать почему это происходит. Оригинальный xml лежит в файле data1.xml.*/
            Node node = doc.getDocumentElement();
            NodeList nodeList = node.getChildNodes();
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node nodeElem = nodeList.item(i);
                if (Node.ELEMENT_NODE == nodeElem.getNodeType()){
                    Element element = (Element) nodeElem;
                    Employee employee = new Employee(Integer.parseInt(element.getAttribute("id")),
                            element.getAttribute("firstName"), element.getAttribute("lastName"),
                            element.getAttribute("country"), Integer.parseInt(element.getAttribute("age")));
                    employeeList.add(employee);
                }
            }
            writeString(listToJson(employeeList));

        }catch (IOException|ParserConfigurationException|SAXException e){
            e.printStackTrace();
        }
    }

    public static String listToJson(List<Employee> list){
        Type listType = new TypeToken<List<Employee>>() {}.getType();
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String json = gson.toJson(list, listType);
        return json;
    }

    public static void writeString(String str){
        try(FileWriter file = new FileWriter("data.json")) {
            file.write(str);
            file.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());;
        }
    }

    public static void main(String[] args) {
        String fileName = "data.xml";
        parseXML(fileName);
    }
}
