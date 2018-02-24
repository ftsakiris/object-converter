import java.util.List;
import java.util.Set;

public class BusinessObj {

    private final String name;
    private String email;
    private final BusinessObj tree;
    private List<String> list;
    private Set<String> set;

    public BusinessObj() {
        this(null, null);
    }

    public BusinessObj(String name, BusinessObj tree) {
        this.name = name;
        this.tree = tree;
    }

    public Set<String> getSet() {
        return set;
    }

    public List<String> getList() {
        return list;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public BusinessObj getTree() {
        return tree;
    }
}
