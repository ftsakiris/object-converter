package object.converter;

import java.util.Collection;

public class ClassTypeUtil {

    private ClassTypeUtil() {
    }

    public static boolean isSimpleType(Class clazz) {
        return isTextType(clazz) || isNumericType(clazz) || isLogicalType(clazz);
    }

    public static boolean isNumericType(Class clazz) {
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

    public static boolean isLogicalType(Class clazz) {
        try {
            return Boolean.class.equals(clazz) || boolean.class.equals(clazz);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isTextType(Class clazz) {
        try {
            return String.class.equals(clazz) || char.class.equals(clazz)
                    || Character.class.equals(clazz);
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCollection(Class clazz) {
        try {
            return clazz != null
                    && (Collection.class.equals(clazz) || isCollection(clazz.getInterfaces()));
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean isCollection(Class[] clazz) {
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
