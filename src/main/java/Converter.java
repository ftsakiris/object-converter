import java.lang.reflect.Field;
import java.util.*;

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
                    toField.set(to, convertCollection((Collection) fromValue, (Collection) collectionClassResolver(toClazz).newInstance()));
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

    private static Object convertCollection(Collection fromCollection, Collection toCollection) {
        fromCollection.forEach(fromObject -> {
            toCollection.add(fromObject);
        });
        return toCollection;
    }

    private static Class collectionClassResolver(Class clazz) {
        if (List.class.equals(clazz)) {
            return ArrayList.class;
        } else if (Set.class.equals(clazz)) {
            return HashSet.class;
        }
        return null;
    }

    private static boolean isSimpleType(Class clazz) {
        return isTextType(clazz) || isNumericType(clazz) || isLogicalType(clazz);
    }

    private static boolean isNumericType(Class clazz) {
        try {
            return
                    Integer.class.equals(clazz) || Long.class.equals(clazz)
                            || Double.class.equals(clazz) || Float.class.equals(clazz)
                            || int.class.equals(clazz) || long.class.equals(clazz)
                            || double.class.equals(clazz) || float.class.equals(clazz)
                    ;
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isLogicalType(Class clazz) {
        try {
            return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isTextType(Class clazz) {
        try {
            return String.class.equals(clazz) || char.class.equals(clazz)
                    || Character.class.equals(clazz);
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isCollection(Class clazz) {
        try {
            return clazz != null
                    && (Collection.class.equals(clazz) || isCollection(clazz.getInterfaces()));
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isCollection(Class[] clazz) {
        for (Class aClass : clazz) {
            if (isCollection(aClass)) {
                return true;
            }
        }
        try {
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
