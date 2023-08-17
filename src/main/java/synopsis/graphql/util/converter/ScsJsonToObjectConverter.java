package synopsis.graphql.util.converter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.exc.UnrecognizedPropertyException;
import lombok.extern.slf4j.Slf4j;
import synopsis.graphql.excpetion.CustomJsonPropertyException;
import synopsis.graphql.excpetion.JsonToObjectException;
import synopsis.graphql.model.scs.ScsResult;

@Slf4j
public class ScsJsonToObjectConverter {

    private ScsJsonToObjectConverter() {
        throw new IllegalStateException("Scs Json To Object Converter");
    }

    public static ScsResult convert(String jsonData) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            return objectMapper.readValue(jsonData, ScsResult.class);
        } catch (UnrecognizedPropertyException e) {
            throw new CustomJsonPropertyException("SCS result에서 예상치 못한 json field 발견 " + e.getMessage());
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            throw new JsonToObjectException("Error found at " + e.getLocation() + " | SCS Json Object Mapping Error : " + e.getMessage());
        }
        return null;
    }
}
