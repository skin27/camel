/*
 * Camel ApiMethod Enumeration generated by camel-api-component-maven-plugin
 */
package org.apache.camel.component.braintree.internal;

import java.lang.reflect.Method;
import java.util.List;

import com.braintreegateway.CreditCardVerificationGateway;

import org.apache.camel.support.component.ApiMethod;
import org.apache.camel.support.component.ApiMethodArg;
import org.apache.camel.support.component.ApiMethodImpl;

import static org.apache.camel.support.component.ApiMethodArg.arg;

/**
 * Camel {@link ApiMethod} Enumeration for com.braintreegateway.CreditCardVerificationGateway
 */
public enum CreditCardVerificationGatewayApiMethod implements ApiMethod {

    CREATE(
        com.braintreegateway.Result.class,
        "create",
        arg("request", com.braintreegateway.CreditCardVerificationRequest.class)),

    FIND(
        com.braintreegateway.CreditCardVerification.class,
        "find",
        arg("id", String.class)),

    SEARCH(
        com.braintreegateway.ResourceCollection.class,
        "search",
        arg("query", com.braintreegateway.CreditCardVerificationSearchRequest.class));

    private final ApiMethod apiMethod;

    private CreditCardVerificationGatewayApiMethod(Class<?> resultType, String name, ApiMethodArg... args) {
        this.apiMethod = new ApiMethodImpl(CreditCardVerificationGateway.class, resultType, name, args);
    }

    @Override
    public String getName() { return apiMethod.getName(); }

    @Override
    public Class<?> getResultType() { return apiMethod.getResultType(); }

    @Override
    public List<String> getArgNames() { return apiMethod.getArgNames(); }

    @Override
    public List<Class<?>> getArgTypes() { return apiMethod.getArgTypes(); }

    @Override
    public Method getMethod() { return apiMethod.getMethod(); }
}
