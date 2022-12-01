package be.abis.superdupersandwichorder.factory;



import be.abis.superdupersandwichorder.interceptor.MyHttpRequestInterceptor;
import org.apache.http.HttpHost;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@Qualifier("orderTemplate")
public class RestTemplateFactory
        implements FactoryBean<RestTemplate>, InitializingBean {
    private RestTemplate restTemplate;
    public RestTemplate getObject() {
        return restTemplate;
    }
    public Class<RestTemplate> getObjectType() {
        return RestTemplate.class;
    }
    public boolean isSingleton() {
        return true;
    }
    public void afterPropertiesSet() {
        HttpHost host = new HttpHost("localhost", 9000, "http");
        restTemplate = new RestTemplate
                (new MyHttpRequestInterceptor(host));
    }
}
