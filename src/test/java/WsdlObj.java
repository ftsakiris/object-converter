import java.util.List;
import java.util.Set;

@ObjectConverter(to = BusinessObj.class)
public class WsdlObj implements IObjectConverter<BusinessObj> {

    @Override
    public Class<BusinessObj> getToClass() {
        return BusinessObj.class;
    }

    private String name;

    private String email;

    private WsdlObj tree;

    private List<String> list;

    private Set<String> set;

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public WsdlObj getTree() {
        return tree;
    }

    public void setTree(WsdlObj tree) {
        this.tree = tree;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}


