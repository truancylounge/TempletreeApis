package com.templetree.auth;

import com.templetree.model.Role;
import com.templetree.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
import java.net.URLDecoder;
import java.util.Properties;
import java.util.regex.Pattern;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author Lalith Mannur
 */
@Provider
public class ResponseFilter implements ContainerResponseFilter {
    private static final Logger LOGGER = getLogger(ResponseFilter.class);

    @Autowired
    @Qualifier("mainControllerProperties")
    private Properties appProperties;

    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        containerResponseContext.getHeaders().add("Access-Control-Expose-Headers", appProperties.getProperty("app.login.token.headername"));
        containerResponseContext.getHeaders().add("Access-Control-Allow-Origin", "*");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Templteree-Auth-Token");
        containerResponseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST, OPTIONS, DELETE, PUT, HEAD");

        if(!containerRequestContext.getUriInfo().getPath().contains(appProperties.getProperty("app.login.url"))
            && (containerResponseContext.getStatus() == 200 || containerResponseContext.getStatus() == 204 ) &&
                !containerRequestContext.getMethod().equals("OPTIONS")) {
            System.out.println("Context Response filter - Adding token");

            String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

            String decryptedToken = EncryptionUtil.decryptString(URLDecoder.decode(token, "UTF-8"), appProperties);
            String[] tokenParts = decryptedToken.split(Pattern.quote(appProperties.getProperty("app.login.delimiter")));

            String templetreeAuthToken;
            try {
                templetreeAuthToken = EncryptionUtil.generateAccessToken(tokenParts[0], Role.valueOf(tokenParts[2]), appProperties.getProperty("app.login.delimiter"), appProperties);
            } catch (Exception e) {
                LOGGER.error("Error during Token generation : ", e);
                throw new IOException(e);
            }

            containerResponseContext.getHeaders().add(appProperties.getProperty("app.login.token.headername"), templetreeAuthToken);
        }
    }
}
