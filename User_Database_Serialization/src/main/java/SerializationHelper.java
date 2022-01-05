import java.io.*;

public class SerializationHelper{

    public static void serialiseToFile(Object object,String filePath)throws IOException{
        FileOutputStream fileOutputStream = new FileOutputStream(filePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(object);
        objectOutputStream.flush();
        objectOutputStream.close();
    }


    public static Object deserializeFromFile(String filePath) throws IOException, ClassCastException, ClassNotFoundException{
        FileInputStream fileInputStream = new FileInputStream("yourfile.txt");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        Object object = objectInputStream.readObject();
        objectInputStream.close();
        return object;
    }
}
