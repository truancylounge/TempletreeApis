package com.templetree.auth;

import com.templetree.model.Role;
import com.templetree.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.Provider;

import java.io.IOException;
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
        if(!containerRequestContext.getUriInfo().getPath().contains(appProperties.getProperty("app.login.url"))
            && containerResponseContext.getStatus() == 200) {
            System.out.println("Context Response filter - Adding token");

            MultivaluedMap<String, String> queryParameters = containerRequestContext.getUriInfo().getQueryParameters();
            String decryptedToken = EncryptionUtil.decryptString(queryParameters.getFirst(appProperties.getProperty("app.tokenname.in.url")), appProperties);
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
