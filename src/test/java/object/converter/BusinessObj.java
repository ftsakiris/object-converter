package object.converter;

import java.util.List;
import java.util.Set;

public class BusinessObj {

    private final String name;
    private String email;
    private final BusinessObj tree;
    private String desc;

    @ObjectConverter(mapped = "state")
    private Integer status;

    private List<String> list;
    private Set<String> set;
    private List<BusinessObj> objList;

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

    public List<BusinessObj> getObjList() {
        return objList;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getDesc() {
        return desc;
    }

    public Integer getStatus() {
        return status;
    }

    public BusinessObj getTree() {
        return tree;
    }

    @Override
    public boolean equals(Object o) {
        return this == o || this.toString().equals(o.toString());
    }

    @Override
    public String toString() {
        return "Obj{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", desc='" + desc + '\'' +
                ", state='" + status + '\'' +
                ", tree=" + tree +
                '}';
    }
}
