import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class JSONSerialization {
    public static void serialiseToFile(Object object,String filePath)throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        FileOutputStream file = new FileOutputStream(filePath);
        objectMapper.writeValue(file, object);
        file.flush();
        file.close();
    }


    public static <T> T deserializeFromFile(String filePath, Class<T> clazz)throws IOException, ClassCastException, ClassNotFoundException{
        ObjectMapper objectMapper = new ObjectMapper();
        File file = new File(filePath);
        return (T) objectMapper.readValue(file, clazz);
    }
}
