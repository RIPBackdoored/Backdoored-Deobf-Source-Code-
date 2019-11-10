package com.google.api.client.auth.oauth2;

import com.google.api.client.json.*;
import java.util.*;
import com.google.api.client.http.*;
import com.google.api.client.util.*;

public class RefreshTokenRequest extends TokenRequest
{
    @Key("refresh_token")
    private String refreshToken;
    
    public RefreshTokenRequest(final HttpTransport transport, final JsonFactory jsonFactory, final GenericUrl tokenServerUrl, final String refreshToken) {
        super(transport, jsonFactory, tokenServerUrl, "refresh_token");
        this.setRefreshToken(refreshToken);
    }
    
    @Override
    public RefreshTokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return (RefreshTokenRequest)super.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public RefreshTokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return (RefreshTokenRequest)super.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public RefreshTokenRequest setScopes(final Collection<String> scopes) {
        return (RefreshTokenRequest)super.setScopes(scopes);
    }
    
    @Override
    public RefreshTokenRequest setGrantType(final String grantType) {
        return (RefreshTokenRequest)super.setGrantType(grantType);
    }
    
    @Override
    public RefreshTokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return (RefreshTokenRequest)super.setClientAuthentication(clientAuthentication);
    }
    
    public final String getRefreshToken() {
        return this.refreshToken;
    }
    
    public RefreshTokenRequest setRefreshToken(final String refreshToken) {
        this.refreshToken = Preconditions.checkNotNull(refreshToken);
        return this;
    }
    
    @Override
    public RefreshTokenRequest set(final String fieldName, final Object value) {
        return (RefreshTokenRequest)super.set(fieldName, value);
    }
    
    @Override
    public /* bridge */ TokenRequest set(final String fieldName, final Object value) {
        return this.set(fieldName, value);
    }
    
    @Override
    public /* bridge */ TokenRequest setGrantType(final String grantType) {
        return this.setGrantType(grantType);
    }
    
    @Override
    public /* bridge */ TokenRequest setScopes(final Collection scopes) {
        return this.setScopes(scopes);
    }
    
    @Override
    public /* bridge */ TokenRequest setTokenServerUrl(final GenericUrl tokenServerUrl) {
        return this.setTokenServerUrl(tokenServerUrl);
    }
    
    @Override
    public /* bridge */ TokenRequest setClientAuthentication(final HttpExecuteInterceptor clientAuthentication) {
        return this.setClientAuthentication(clientAuthentication);
    }
    
    @Override
    public /* bridge */ TokenRequest setRequestInitializer(final HttpRequestInitializer requestInitializer) {
        return this.setRequestInitializer(requestInitializer);
    }
    
    @Override
    public /* bridge */ GenericData set(final String fieldName, final Object value) {
        return this.set(fieldName, value);
    }
}
