package cn.ucmed.chenhong.webService;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by ucmed on 2017/12/8.
 */

@WebService
public interface IUser {
    //获取xml格式的数据
    /**
     * 根据传递的条件获取相册信息
     * xml的格式规范
     * <?xml version="1.0" encoding="UTF-8" standalone="no"?>
     *     <root>
     *         <user>
     *             <id>1</id>
     *             <name>张三</name>
     *             <sex>男</sex>
     *         </user>
     *     </root>
     * 这里的WebParam必须指定，否则调用的时候返回null
     * @return
     */
    public String getUser(@WebParam(name = "xmlStr")String xmlStr);
}
