package com.templetree.auth;

import com.templetree.exception.ExceptionMessages;
import com.templetree.model.ErrorMessage;
import com.templetree.model.User;
import com.templetree.utils.EncryptionUtil;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
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
@Priority(Priorities.AUTHORIZATION)
public class AuthFilter implements ContainerRequestFilter {

    private static final Logger LOGGER = getLogger(AuthFilter.class);

    @Autowired
    @Qualifier("mainControllerProperties")
    private Properties appProperties;

    public AuthFilter() {
        System.out.println("Inside Auth Filter");
    }

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String absolutePath = containerRequestContext.getUriInfo().getAbsolutePath().toString();
        String requestUri = containerRequestContext.getUriInfo().getPath();

        System.out.println("Request URI Absolute Path: " + absolutePath);
        System.out.println("Request URI Relative Path: " + requestUri);

        System.out.println("App.login.url property attribute: " + appProperties.getProperty("app.login.url"));
        System.out.println("Method Type: " + containerRequestContext.getMethod());


        if(requestUri.contains(appProperties.getProperty("app.login.url"))) {
            System.out.println("Hitting Login url --> Authenticating");
            return;
        } else if(containerRequestContext.getMethod().equals("OPTIONS")) {
            return;
        }
        /*
        MultivaluedMap<String, String> queryParameters =
                containerRequestContext.getUriInfo().getQueryParameters();
        String token = queryParameters.getFirst("token");
        System.out.println("Token is : " + token); */
        String token = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if(token != null) {
            String decryptedToken = EncryptionUtil.decryptString(URLDecoder.decode(token, "UTF-8"), appProperties);
            LOGGER.debug("Decrypted Token : {}", decryptedToken);
            String[] tokenParts = decryptedToken.split(Pattern.quote(appProperties.getProperty("app.login.delimiter")));
            if(tokenParts.length == 3) {
                if(EncryptionUtil.isTokenValid(tokenParts[1], Integer.parseInt(appProperties.getProperty("app.token.timeout")))) {
                    System.out.println("Valid Token time");

                }
                else {
                    containerRequestContext.abortWith(Response
                                    .status(Response.Status.FORBIDDEN)
                                    .entity(new ErrorMessage(Response.Status.FORBIDDEN.getStatusCode(), "TEST", ExceptionMessages.TOKEN_ENCYPTION_ERROR,
                                            ExceptionMessages.TOKEN_ENCYPTION_ERROR))
                                    .build()
                    );
                }


            } else {
                containerRequestContext.abortWith(Response
                                .status(Response.Status.FORBIDDEN)
                                .entity(new ErrorMessage(Response.Status.FORBIDDEN.getStatusCode(), "TEST", ExceptionMessages.TOKEN_ENCYPTION_ERROR,
                                        ExceptionMessages.TOKEN_ENCYPTION_ERROR))
                                .build()
                );
            }

        } else {
            containerRequestContext.abortWith(Response
                            .status(Response.Status.FORBIDDEN)
                            .entity(new ErrorMessage(Response.Status.FORBIDDEN.getStatusCode(), "TEST", ExceptionMessages.TOKEN_NOT_FOUND,
                                    ExceptionMessages.TOKEN_NOT_FOUND))
                            .build()
            );
        }
    }
}
