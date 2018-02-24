package object.converter;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.Collection;

import static object.converter.ClassTypeUtil.isCollection;
import static object.converter.ClassTypeUtil.isSimpleType;
import static object.converter.collection.CollectionConverter.collectionClassResolver;
import static object.converter.collection.CollectionConverter.convertCollection;

public class Converter {

    private Converter() {
    }

    public static <T> T convert(Object from, T to) {
        for (Field fromField : from.getClass().getDeclaredFields()) {
            fromField.setAccessible(true);
            System.out.println(fromField.toString());
            try {

                final Field toField = to.getClass().getDeclaredField(fromField.getName());
                setField(from, to, fromField, toField);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
                String fromFieldName = getNameFromAnnotation(fromField);
                if (StringUtils.isEmpty((fromFieldName))) {
                    fromFieldName = searchField(fromField.getName(), to);
                }
                try {
                    final Field toField = to.getClass().getDeclaredField(fromFieldName);
                    setField(from, to, fromField, toField);
                } catch (NoSuchFieldException e1) {
                    e1.printStackTrace();
                }
            }
        }
        return to;
    }

    private static String searchField(String name, Object obj) {
        for (Field field : obj.getClass().getDeclaredFields()) {
            if (name.equals(getNameFromAnnotation(field))) {
                return field.getName();
            }
        }
        return null;
    }

    private static void setField(Object from, Object to, Field fromField, Field toField) {
        try {
            toField.setAccessible(true);
            final Object fromValue = fromField.get(from);
            if (fromValue == null) {
                return;
            }

            final Class toClazz = toField.getType();

            if (isSimpleType(toClazz)) {
                toField.set(to, fromValue);
            } else if (isCollection(toClazz)) {
                toField.set(to, convertCollection(toField, (Collection) fromValue, (Collection) collectionClassResolver(toClazz).newInstance()));
            } else {
                toField.set(to, convert(fromValue, toClazz.newInstance()));
            }
        } catch (IllegalAccessException | InstantiationException e) {
            e.printStackTrace();
        }
    }

    private static String getNameFromAnnotation(Field field) {
        try {
            return field.getAnnotation(ObjectConverter.class).mapped();
        } catch (NullPointerException e) {
            return null;
        }
    }

}
