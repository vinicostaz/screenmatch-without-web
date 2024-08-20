package br.com.screenMatchWSpring.service;

import br.com.screenMatchWSpring.model.SerieData;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConvertData implements IConvertData {
    private ObjectMapper mapper = new ObjectMapper();


    @Override
    public <T> T getData(String json, Class<T> Class) {
        try {
            return mapper.readValue(json, Class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
