package ru.itis.project.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonSerializer {
    private static final ObjectMapper mapper = new ObjectMapper().enable(SerializationFeature.INDENT_OUTPUT);

    public static <T> void serilaize(T object, String file) throws IOException {
        mapper.writeValue(new File(file),object);

    }
    public static <T> void serializeList(List<T> object, String file) throws  IOException{
        mapper.writeValue(new File(file),object);
    }
    public static <T> T deserialize(String file,Class<T> valueType) throws IOException{
        return mapper.readValue(new File(file),valueType);

    }
    public static <T> List<T> deserializeList(String file, Class<T> elementType) throws IOException{
        return mapper.readValue(new File(file),
                mapper.getTypeFactory().constructCollectionType(List.class,elementType));
    }

}
