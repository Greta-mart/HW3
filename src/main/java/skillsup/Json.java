package skillsup;

import com.google.gson.JsonObject;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Json {
    private String serializeWithJsonObject(Object object) throws IllegalArgumentException, IllegalAccessException {
        if (object == null) {
            return "";
        }
        JsonObject jsonObject = new JsonObject();
        for (Field field : object.getClass().getDeclaredFields()) {
                field.setAccessible(true);
            Object fieldValue = field.get(object);
            if (fieldValue == null) {
                continue;
            }
            String property = property(field);
            Class<?> fieldType = field.getType();
            if (fieldType.equals(String.class)) {
                jsonObject.addProperty(property, (String) fieldValue);
            } else if (fieldType.equals(LocalDate.class)) {
                final LocalDate localDate = (LocalDate) fieldValue;
                final Optional<String> format = field.isAnnotationPresent(CustomDateFormat.class)
                        ? Optional.of(field.getAnnotation(CustomDateFormat.class).format())
                        : Optional.empty();
                jsonObject.addProperty(property, format.map(fmt -> DateTimeFormatter.ofPattern(fmt).format(localDate))
                        .orElseGet(localDate::toString));
            } else if (fieldType.isPrimitive() == false) {
                jsonObject.addProperty(property, serializeWithJsonObject(fieldValue));
            } else {
                jsonObject.addProperty(property, fieldValue.toString());
            }
        }
        return jsonObject.toString();
    }

    private String property(final Field field) {
        if (field.isAnnotationPresent(JsonValue.class)) {
            return field.getAnnotation(JsonValue.class).name();
        }
        return field.getName();
    }

    public String toJson(Object obj) throws IllegalAccessException {
        return serializeWithJsonObject(obj);
    }
}