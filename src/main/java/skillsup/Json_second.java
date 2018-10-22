package skillsup;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class Json_second {
    public String toJson1(Object obj) throws IllegalAccessException {
        return obj != null ? processObj(obj, new StringBuffer()) : "";
    }

    private String processObj(Object obj, StringBuffer buffer) throws IllegalAccessException {
        buffer.append("{");
        boolean fieldProcessed = false;
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (fieldProcessed) {
                buffer.append(",");
            }
            fieldProcessed = appendField(obj, field, buffer);
        }
        buffer.append("}");
        return buffer.toString();
    }

    private boolean appendField(Object obj, Field field, StringBuffer buffer) throws IllegalAccessException {
        boolean fieldProcessed = false;
        boolean isAccessModified = !field.isAccessible();
        if (isAccessModified) {
            field.setAccessible(true);
        }
        Object result;
        result = field.get(obj);
        if (result != null) {
            appendFieldName(field, buffer);
            appendFieldValue(result, field, buffer);
            fieldProcessed = true;
        }
        return fieldProcessed;
    }

    private void appendFieldValue(Object result, Field field, StringBuffer buffer) throws IllegalAccessException {
        Class<?> fieldType = field.getType();
        if (fieldType.equals(String.class)) {
            // String
            buffer.append("\"").append(result).append("\"");
        } else if (fieldType.isAssignableFrom(LocalDate.class)) {
            //LocalDate
            Optional<String> dateformat =
                    field.isAnnotationPresent(CustomDateFormat.class) ?
                            Optional.of(field.getAnnotation(CustomDateFormat.class).format()) :
                            Optional.empty();
            appendLocalDate((LocalDate) result, dateformat, buffer);
        } else if (!fieldType.isPrimitive()) {
            processObj(result, buffer);
        } else {
            buffer.append(result);
        }
    }

    private void appendFieldName(Field field, StringBuffer buffer) {
        buffer.append("\"").append(
                field.isAnnotationPresent(JsonValue.class) ?
                        field.getAnnotation(JsonValue.class).name() :
                        field.getName())
                .append("\":");
    }

    private void appendLocalDate(LocalDate date, Optional<String> dateFormat, StringBuffer buffer) {
        buffer.append(
                dateFormat.map(s -> DateTimeFormatter.ofPattern(s).format(date)).orElseGet(date::toString)
        );
    }
}