package object.converter.collection;

import java.util.*;

public class CollectionConverter {

    private CollectionConverter() {
    }

    public static Object convertCollection(Collection fromCollection, Collection toCollection) {
        fromCollection.forEach(fromObject -> {
            toCollection.add(fromObject);
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

}
