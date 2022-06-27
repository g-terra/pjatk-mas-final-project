package pjatk.mas.finalproject.devicemanufactureapi.utils.dummydata;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.BeanProperty;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.ContextualDeserializer;

import java.io.IOException;
import java.util.List;

public class InnerListDeserializer extends JsonDeserializer<List> implements ContextualDeserializer {

    private final JavaType propertyType;

    public InnerListDeserializer() {
        this(null);
    }

    public InnerListDeserializer(JavaType propertyType) {
        this.propertyType = propertyType;
    }

    @Override
    public List deserialize(JsonParser p, DeserializationContext context) throws IOException {
        p.nextToken(); // SKIP START_OBJECT
        p.nextToken(); // SKIP any FIELD_NAME

        List list = context.readValue(p, propertyType);

        p.nextToken(); // SKIP END_OBJECT

        return list;
    }

    @Override
    public JsonDeserializer<?> createContextual(DeserializationContext context, BeanProperty property) {
        return new InnerListDeserializer(property.getType());
    }
}