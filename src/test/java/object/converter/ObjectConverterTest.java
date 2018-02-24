package object.converter;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObjectConverterTest {

    @Test
    public void convert() {

        final String name = "fotis";
        final String email = "tsak@dsd.com";
        final String desc = "Metallica";
        final Integer status = 999;

        WsdlObj wsdlObj = new WsdlObj();
        wsdlObj.setName(name);
        wsdlObj.setDescription(desc);
        wsdlObj.setEmail(email);
        wsdlObj.setState(status);

        //set tree
        WsdlObj tree = new WsdlObj();
        tree.setName(name);
        tree.setEmail(email);
        wsdlObj.setTree(tree);

        //set list
        List<String> list = new ArrayList<>();
        list.add("aei na doume");
        list.add("noubas");
        list.add("pws tous");
        wsdlObj.setList(list);

        //set list
        Set<String> set = new HashSet<>();
        set.add("aei na doume");
        set.add("noubas");
        set.add("pws tous");
        wsdlObj.setSet(set);

        //set list
        List<WsdlObj> WsdlObjList = new ArrayList<>();
        WsdlObj wsdlObjL = new WsdlObj();
        wsdlObjL.setName(name);
        wsdlObjL.setEmail(email);
        WsdlObjList.add(wsdlObjL);
        WsdlObjList.add(wsdlObjL);
        wsdlObj.setObjList(WsdlObjList);

        BusinessObj businessObj = Converter.convert(wsdlObj, new BusinessObj());

        Assert.assertEquals(name, businessObj.getName());
        Assert.assertEquals(email, businessObj.getEmail());
        Assert.assertEquals(desc, businessObj.getDesc());
        Assert.assertEquals(status, businessObj.getStatus());

        Assert.assertEquals(name, businessObj.getTree().getName());
        Assert.assertEquals(email, businessObj.getTree().getEmail());

        Assert.assertTrue(list.containsAll(businessObj.getList()));
        Assert.assertTrue(businessObj.getList().containsAll(list));

        Assert.assertTrue(set.containsAll(businessObj.getSet()));
        Assert.assertTrue(businessObj.getSet().containsAll(set));

        Assert.assertTrue(WsdlObjList.containsAll(businessObj.getObjList()));
        Assert.assertTrue(businessObj.getObjList().containsAll(WsdlObjList));
    }
}