package org.harmony.test.microservices.swram;

import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.wildfly.swarm.Swarm;
import org.wildfly.swarm.jaxrs.JAXRSArchive;

/**
 * @author wuxii@foxmail.com
 */
public class SimpleApplication {

    static {
        // see org.jboss.modules.maven.MavenSettings
        System.setProperty("remote.maven.repo", "http://repository.jboss.org/nexus/content/groups/public/");
        System.setProperty("maven.download.message", "true");
    }

    public static void main(String[] args) throws Exception {

        String applicationName = (args.length > 0 ? args[0] : "simple-application") + ".war";

        System.out.println("start " + applicationName + "!");
        new Swarm(args)//
                .start()//
                .deploy(ShrinkWrap//
                        .create(JAXRSArchive.class, applicationName)//
                        .addPackage(SimpleApplication.class.getPackage())//
                        .addAllDependencies());

        System.out.println(applicationName + " started!");
    }

}