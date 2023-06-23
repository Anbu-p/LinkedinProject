package com.meta.view;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.meta.controller.RestController;
import org.apache.cxf.BusFactory;
import org.apache.cxf.endpoint.Server;
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;

/**
 * <p>
 * It's the activator for all the bundles
 * </p>
 *
 * @author Anbu
 * @version 1.0
 */
@Component(immediate = true)
public class Activator {
    private Server server;

    @Activate
    public void activate() throws Exception {
        JAXRSServerFactoryBean bean = new JAXRSServerFactoryBean();
        bean.setAddress("/linkedin");
        bean.setBus(BusFactory.getDefaultBus());
        bean.setProvider(new JacksonJsonProvider());
        bean.setServiceBean(new RestController());
        server = bean.create();
    }

    @Deactivate
    public void stop() throws Exception {
        if (server != null) {
            server.destroy();
        }
    }
}