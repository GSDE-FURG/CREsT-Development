package critical_vectors;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import datastructures.InputVector;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class CriticalVectorsUtils {

    public static void criticalVectorsListToJSON(ArrayList<InputVector> cVectors, String jsonPath) throws IOException {
        Gson gson = new Gson();
        JsonWriter jsonWriter = new JsonWriter(new FileWriter(jsonPath));
        Type listType = new TypeToken<ArrayList<InputVector>>(){}.getType();

        gson.toJson(cVectors, listType, jsonWriter);
        jsonWriter.close();
    }

    public static ArrayList<InputVector> criticalVectorsListFromJSON(String jsonPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(jsonPath));
        Type listType = new TypeToken<ArrayList<InputVector>>(){}.getType();

        ArrayList<InputVector> result = gson.fromJson(jsonReader, listType);

        return result;
    }

    public static void criticalVectorsExactListToJSON(ArrayList<InputVector> cVectors, String jsonPath, long secondsTimestamp) throws IOException {
        Gson gson = new Gson();
        CriticalVectorsExactList exactListObj = new CriticalVectorsExactList(cVectors, secondsTimestamp);

        JsonWriter jsonWriter = new JsonWriter(new FileWriter(jsonPath));

        gson.toJson(exactListObj, CriticalVectorsExactList.class, jsonWriter);
        jsonWriter.close();
    }

    public static CriticalVectorsExactList criticalVectorsExactListFromJSON(String jsonPath) throws FileNotFoundException {
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(new FileReader(jsonPath));
        //Type listType = new TypeToken<ArrayList<InputVector>>(){}.getType();

        CriticalVectorsExactList result = gson.fromJson(jsonReader, CriticalVectorsExactList.class);

        return result;
    }

}
