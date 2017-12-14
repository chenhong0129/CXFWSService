package cn.ucmed.chenhong.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.StringReader;
import java.util.*;

/**
 * 生成xml格式的文件
 * 解释xml格式的字符串
 * Created by ucmed on 2017/12/8.
 */
public class XMLHandler {
    /**
     * 创建xml
     * @return
     */
    public static String createXML(String xmlSource){
        String xmlStr="";
        //doc 的工厂

        return xmlStr;
    }

    /*public static void main(String[] args) {
        createXML();
    }*/
    /**
     * 转化xml格式的字符串
     * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     *     <root>
     *         <user>
     *             <id>1</id>
     *             <name>张三</name>
     *             <sex>男</sex>
     *         </user>
     *     </root>
     *
     * 为 List<map<String,Object>>格式
     * 输出示例：[{sex=男, name=张三, id=1}, {sex=女, name=可可, id=2}]
     */
    public static List<Map<String,Object>> parseXML(String xmlStr){
        List<Map<String,Object>> list=null;
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            StringReader sr = new StringReader(xmlStr);
            InputSource is = new InputSource(sr);
            Document doc = builder.parse(is);
            //
            Element rootElement = doc.getDocumentElement();
            //
            NodeList users = rootElement.getElementsByTagName("user");
            //
            list=new ArrayList<Map<String, Object>>();
            for(int i=0;i<users.getLength();i++){
                Node node = users.item(i);
                NodeList childs = node.getChildNodes();
                Map<String,Object> map=new HashMap<String, Object>();
                for(int j=0;j<childs.getLength();j++){
                    Node childNode = childs.item(j);
                    String nodeName=childNode.getNodeName();
                    String value=childNode.getTextContent();
                    map.put(nodeName,value);

                }
                list.add(map);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return list;
    }

    public static void main(String[] args) {
        createXMLDemo();
        String xmlStr1="<?xml version='1.0' encoding='UTF-8' standalone='no'?><root><user><id>1</id><name>张三</name><sex>男</sex></user></root>";
        String xmlStr2 = "<?xml version='1.0' encoding='UTF-8' standalone='no'?><root><user><id>1</id><name>张三</name><sex>男</sex></user><user><id>2</id><name>可可</name><sex>女</sex></user></root>";
        List<Map<String, Object>> list1 = parseXML(xmlStr1);
        List<Map<String, Object>> list2 = parseXML(xmlStr2);
        System.out.println("xml1解析结果："+list1.toString());
        System.out.println("xml2解析结果："+list2.toString());
        System.out.println("创建xml结果："+createXMLDemo());
        String listStr = "[{sex=男, name=张三, id=1}, {sex=女, name=可可, id=2}]";
        // 使用Gson转换成你想要的样子
        Gson gson = new Gson();
        List<Map<String,Object>> list = gson.fromJson(listStr, new TypeToken<List<Map<String, Object>>>() {}.getType());
        System.out.println("listStr="+list.toString());
        for (Map<String, Object> m : list)
        {
            System.out.println(m);
            /*for (String k : m.keySet())
            {
                System.out.println(k + " : " + m.get(k));
            }*/

        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("name","张三");
        map.put("sex","男");
        map.put("id","1");
        System.out.println("map=="+map.toString());
        System.out.println(map2Xmlstring(map));
    }


    /**
     * 创建xml工具
     * @return
     */
    public static String createXMLDemo(){
        String xmlStr="";
        //doc 的工厂
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try{
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.newDocument();
            document.setXmlVersion("1.0");
            //
            Element root = document.createElement("root");
            document.appendChild(root);
            //
            Element user = document.createElement("user");
            //
            Element id = document.createElement("id");
            id.setTextContent("1");
            user.appendChild(id);
            //
            Element name = document.createElement("name");
            name.setTextContent("张三");
            user.appendChild(name);
            //
            Element sex = document.createElement("sex");
            sex.setTextContent("男");
            user.appendChild(sex);
            //
            root.appendChild(user);
            //
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transFormer = transFactory.newTransformer();
            DOMSource domSource = new DOMSource(document);

            //export string
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            transFormer.transform(domSource, new StreamResult(bos));
            xmlStr = bos.toString();
            System.out.println("输出结果"+xmlStr);
            //通过输出流输出
            File file = new File("D:/Git/xml/user.xml");
            if(!file.exists()){
                file.createNewFile();
            }
            FileOutputStream out = new FileOutputStream(file);
            StreamResult xmlResult = new StreamResult(out);
            transFormer.transform(domSource, xmlResult);
        }catch(Exception e){
            e.printStackTrace();
        }
        return xmlStr;
    }

    /**
     * Map转换成Xml
     * @param map
     * @return
     */
    public static String map2Xmlstring(Map<String,Object> map){
        StringBuffer sb = new StringBuffer("");
        sb.append("<xml version=\"1.0\" encoding=\"UTF-8\"?>");

        Set<String> set = map.keySet();
        for(Iterator<String> it=set.iterator(); it.hasNext();){
            String key = it.next();
            Object value = map.get(key);
            sb.append("<").append(key).append(">");
            sb.append(value);
            sb.append("</").append(key).append(">");
        }
//        sb.append("</xml>");
        return sb.toString();
    }
}
