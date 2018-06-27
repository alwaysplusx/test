import javax.jws.WebService;

import org.junit.Ignore;

/**
 * hello class
 * 
 * @author wuxii@foxmail.com
 */
@Ignore
@WebService(serviceName = "HelloWebService")
public class Hello {

    /**
     * username
     */
    private String name;
    /**
     * description
     */
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

}
