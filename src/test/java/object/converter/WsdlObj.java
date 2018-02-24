package object.converter;

import java.util.List;
import java.util.Set;

public class WsdlObj {

    private String name;

    private String email;

    @ObjectConverter(mapped = "desc")
    private String description;

    private Integer state;

    private WsdlObj tree;

    private List<String> list;

    private Set<String> set;

    private List<WsdlObj> objList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public WsdlObj getTree() {
        return tree;
    }

    public void setTree(WsdlObj tree) {
        this.tree = tree;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public Set<String> getSet() {
        return set;
    }

    public void setSet(Set<String> set) {
        this.set = set;
    }

    public List<WsdlObj> getObjList() {
        return objList;
    }

    public void setObjList(List<WsdlObj> objList) {
        this.objList = objList;
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
                ", desc='" + description + '\'' +
                ", state='" + state + '\'' +
                ", tree=" + tree +
                '}';
    }
}


