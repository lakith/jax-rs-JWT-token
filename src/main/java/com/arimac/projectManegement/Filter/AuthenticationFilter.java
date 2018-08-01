package com.arimac.projectManegement.Filter;


import com.arimac.projectManegement.Annotations.Secured;
import com.arimac.projectManegement.Exeption.PMExpecption;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.ext.Provider;
import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.lang.reflect.AnnotatedElement;
import java.lang.reflect.Method;
import java.security.Principal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Nimesha Buddhika on 7/20/2018
 */

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class  AuthenticationFilter implements ContainerRequestFilter {

    @Context
    private ResourceInfo resourceInfo;

    @Override
    public void filter(final ContainerRequestContext requestContext) throws IOException {
        String authorizationHeader =
                requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);
        String key =
                requestContext.getHeaderString("Secret-key");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer "))
            throw new PMExpecption("Authorization headers are required.");
        String token = authorizationHeader.substring("Bearer".length()).trim();
        final Claims claims = validateToken(token, key);
        Class<?> resourceClass = resourceInfo.getResourceClass();
        Set<String> classRoles = extractRoles(resourceClass);
        Method resourceMethod = resourceInfo.getResourceMethod();
        Set<String> methodRoles = extractRoles(resourceMethod);

        if (methodRoles.size() > 0)
            checkPermissions(methodRoles, claims.getSubject());
        else if (classRoles.size() > 0)
            checkPermissions(classRoles, claims.getSubject());


        requestContext.setSecurityContext(new SecurityContext() {
            @Override
            public Principal getUserPrincipal() {
                return new Principal() {
                    @Override
                    public String getName() {
                        return claims.getId();
                    }
                };
            }

            @Override
            public boolean isUserInRole(String role) {
                return role.equals(claims.getSubject());
            }

            @Override
            public boolean isSecure() {
                return requestContext.getSecurityContext().isSecure();
            }

            @Override
            public String getAuthenticationScheme() {
                return null;
            }
        });

    }

    private Claims validateToken(String token, String key) {
        try {
            return Jwts.parser()
                    .setSigningKey(DatatypeConverter.parseBase64Binary(key))
                    .parseClaimsJws(token).getBody();
        } catch (Exception e) {
            throw new PMExpecption("Unauthorized request.");
        }
    }

    // Extract the roles from the annotated element
    private Set<String> extractRoles(AnnotatedElement annotatedElement) {
        if (annotatedElement == null) {
            return new HashSet<>();
        } else {
            Secured secured = annotatedElement.getAnnotation(Secured.class);
            if (secured == null) {
                return new HashSet<>();
            } else {
                String[] allowedRoles = secured.value();
                return new HashSet<>(Arrays.asList(allowedRoles));
            }
        }
    }

    private void checkPermissions(Set<String> allowedRoles, String role) {
        if (!allowedRoles.contains(role))
            throw new PMExpecption("Unauthorized user.");
    }

}