package org.harmony.test.farmework.activemq.controller;

import java.util.List;

import org.apache.activemq.broker.BrokerService;
import org.apache.activemq.broker.TransportConnector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wuxii@foxmail.com
 */
@RestController
@RequestMapping("/broker")
public class BrokerController {

    @Autowired
    private BrokerService brokerService;

    @GetMapping("/connectors")
    public String list() {
        StringBuilder o = new StringBuilder();
        List<TransportConnector> connectors = brokerService.getTransportConnectors();
        for (TransportConnector conn : connectors) {
            o.append(conn.getUri().toString()).append("\n");
        }
        o.append(brokerService.getVmConnectorURI().toString());
        return o.toString();
    }

    /*@RequestMapping(path = "/discovery", method = { RequestMethod.GET, RequestMethod.POST })
    public String discovery(@RequestParam("url") String url) {
        return null;
    }*/

}
