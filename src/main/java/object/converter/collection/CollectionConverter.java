package object.converter.collection;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.*;

import static object.converter.ClassTypeUtil.isSimpleType;
import static object.converter.Converter.convert;

public class CollectionConverter {

    private CollectionConverter() {
    }

    public static Object convertCollection(Field toField, Collection fromCollection, Collection toCollection) {
        fromCollection.forEach(fromObject -> {
            try {
                if (isSimpleType(getClass(toField))) {
                    toCollection.add(fromObject);
                } else {
                    toCollection.add(convert(fromObject, getClass(toField).newInstance()));
                }
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        });
        return toCollection;
    }

    public static Class collectionClassResolver(Class clazz) {
        if (List.class.equals(clazz)) {
            return ArrayList.class;
        } else if (Set.class.equals(clazz)) {
            return HashSet.class;
        }
        return null;
    }

    private static Class getClass(Field field) {
        ParameterizedType stringListType = (ParameterizedType) field.getGenericType();
        return (Class<?>) stringListType.getActualTypeArguments()[0];
    }

}
