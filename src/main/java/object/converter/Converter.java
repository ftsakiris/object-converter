package object.converter;

import java.lang.reflect.Field;
import java.util.*;

import static object.converter.ClassTypeUtil.isCollection;
import static object.converter.ClassTypeUtil.isSimpleType;
import static object.converter.collection.CollectionConverter.collectionClassResolver;
import static object.converter.collection.CollectionConverter.convertCollection;

public class Converter {

    private Converter() {
    }

    public static Object convert(Object from, Object to) {
        for (Field fromField : from.getClass().getDeclaredFields()) {
            fromField.setAccessible(true);
            System.out.println(fromField.toString());
            try {

                final Field toField = to.getClass().getDeclaredField(fromField.getName());
                toField.setAccessible(true);
                final Object fromValue = fromField.get(from);
                if (fromValue == null) {
                    continue;
                }

                final Class toClazz = toField.getType();

                if (isSimpleType(toClazz)) {
                    toField.set(to, fromValue);
                } else if (isCollection(toClazz)) {
                    toField.set(to, convertCollection(toField, (Collection) fromValue, (Collection) collectionClassResolver(toClazz).newInstance()));
                } else {
                    toField.set(to, convert(fromValue, toClazz.newInstance()));
                }
            } catch (IllegalAccessException | NoSuchFieldException | InstantiationException e) {
                // TODO: if NoSuchFieldException then search with annotation name
                e.printStackTrace();
            }
        }
        return to;
    }

}
