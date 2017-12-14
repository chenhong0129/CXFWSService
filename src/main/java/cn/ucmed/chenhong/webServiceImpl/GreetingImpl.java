package cn.ucmed.chenhong.webServiceImpl;

import cn.ucmed.chenhong.webService.Greeting;

import javax.jws.WebService;
import java.util.Calendar;

/**
 * Created by ucmed on 2017/12/4.
 */

@WebService(endpointInterface= "cn.ucmed.chenhong.webService.Greeting")
public class GreetingImpl implements Greeting {
    public String greeting(String userName) {
        System.out.println("======="+userName);
        return "Hello " + userName + ", currentTime is "
                + Calendar.getInstance().getTime();
    }
}
