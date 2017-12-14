package cn.ucmed.chenhong.webServiceImpl;

import cn.ucmed.chenhong.utils.XMLHandler;
import cn.ucmed.chenhong.webService.IUser;

import javax.jws.WebService;
import java.util.List;
import java.util.Map;

/**
 * Created by ucmed on 2017/12/8.
 */
@WebService
public class IUserImpl implements IUser {

    public String getUser(String xmlStr) {
        List<Map<String, Object>> list = XMLHandler.parseXML(xmlStr); //输出格式转换为map，例：[{sex=男, name=张三, id=1}, {sex=女, name=可可, id=2}]
        return list.toString();
//        return xmlStr;  //输出为xml格式
    }
}
