package bomba;

import com.google.common.base.Preconditions;
import com.griddynamics.jagger.providers.creators.ObjectCreator;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URISyntaxException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: amikryukov
 * Date: 5/27/13
 */
public class HttpQueryCreator implements ObjectCreator<HttpGet> {

    private static Logger log = LoggerFactory.getLogger(HttpQueryCreator.class);

    private String paramName;

    public HttpQueryCreator() {}

    public String getParamName() {
        return paramName;
    }

    public void setParamName(String paramName) {
        this.paramName = paramName;
    }

    @Override
    public HttpGet createObject(String... strings) {
        Preconditions.checkNotNull(strings);
        Preconditions.checkState(strings.length > 0);

        URIBuilder uriBuilder = new URIBuilder();
        uriBuilder.setParameter(paramName, strings[0]);
        try {
            HttpGet get =  new HttpGet(uriBuilder.build());
            log.info(" URI: " + get.getURI().toString());
            return get;
        } catch (URISyntaxException e) {
            throw new RuntimeException("URI params from: " + Arrays.toString(strings), e);
        }
    }

    @Override
    public void setHeader(String[] header) {
        //
    }
}
