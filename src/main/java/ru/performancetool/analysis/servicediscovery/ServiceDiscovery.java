package ru.performancetool.analysis.servicediscovery;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.performancetool.analysis.browser.Api;

import java.util.Map;

@Slf4j
@Data
@Controller("/discovery")
public class ServiceDiscovery {

    @RequestMapping(method = RequestMethod.POST, value = "/registryService")
    public void registryService(String name, Api api){
        services.put(name, api);
    }

    private Map<String, Api> services;
    
}
