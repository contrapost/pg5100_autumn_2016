package org.pg5100.ejb.stateless;

import org.junit.*;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.NamingException;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class UserBeanInEmbeddedContainerTest {

    protected static EJBContainer ec;
    protected static Context ctx;

    @Before
    public void initContainer() throws Exception {

        /*
            This will start an embedded container, which we ll save us
            from having to start it as an external process and deploy
            our WAR on it.

            However, embedded containers only provide reduced functionalities,
            see page 231 in Chapter 7 and
            http://arquillian.org/blog/2012/04/13/the-danger-of-embedded-containers/

            In generate, better to avoid the embedded containers
         */

        Map<String, Object> properties = new HashMap<>();
        properties.put(EJBContainer.MODULES, new File("target/classes"));
        ec = EJBContainer.createEJBContainer(properties);
        ctx = ec.getContext();
    }

    protected <T> T getEJB(Class<T> klass){
        try {
            return (T) ctx.lookup("java:global/classes/"+klass.getSimpleName()+"!"+klass.getName());
        } catch (NamingException e) {
            return null;
        }
    }

    @After
    public void closeContainer() throws Exception {
        if (ctx != null)
            ctx.close();
        if (ec != null)
            ec.close();
    }



    @Test
    public void testWithEmbeddedContainer(){

        UserBean bean = getEJB(UserBean.class);

        String userId = "foo";

        assertFalse(bean.isRegistered(userId));

        bean.registerNewUser(userId,"a","b");

        assertTrue(bean.isRegistered(userId));
    }
}
