import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.opencsv.CSVReader;
import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

public class Main {
    public static void parseCSV(String[] criteria, String fileName){
        ColumnPositionMappingStrategy<Employee> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(Employee.class);
        strategy.setColumnMapping(criteria);

        try(CSVReader reader = new CSVReader(new FileReader(fileName))) {
            String[] nextLine;
            CsvToBean<Employee> csv = new CsvToBeanBuilder<Employee> (reader)
                    .withMappingStrategy(strategy)
                    .build();
            List<Employee> list = csv.parse();
            String json = listToJson(list);
            writeString(json);
        } catch (IOException e) {
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
        String[] columnMapping = {"id", "firstName", "lastName", "country", "age"};
        String fileName = "data.csv";
        parseCSV(columnMapping, fileName);
    }
}
