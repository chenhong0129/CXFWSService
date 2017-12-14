package cn.ucmed.chenhong.webService;

import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by ucmed on 2017/12/4.
 */

@WebService
public interface Greeting {
    public String greeting(@WebParam(name="username")String userName);
}
