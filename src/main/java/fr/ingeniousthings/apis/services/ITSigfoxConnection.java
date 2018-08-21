package fr.ingeniousthings.apis.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;

public class ITSigfoxConnection<S,T> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private static final String SIGFOX_BACKEND = "https://backend.sigfox.com";

    private static String[] authorizedHeader = {
            "user-agent", "accept", "origin", "referer"
    };

    private String sigfoxBasicString;

    /**
     * Create a Connection to Sigfox API with a given Login / Password combination
     * @param apiLogin
     * @param apiPassword
     */
    public ITSigfoxConnection(String apiLogin, String apiPassword) {
        String plainCreds = apiLogin+":"+apiPassword;
        byte[] base64CredsBytes = Base64.encodeBase64(plainCreds.getBytes(Charset.forName("US-ASCII")));
        this.sigfoxBasicString = "Basic " + new String(base64CredsBytes);
    }


    /**
     * Compose a HttpHeader list from a HttpRequest content
     * @param request
     * @return
     */
    public static HttpHeaders getHeadersFromHttpRequest(HttpServletRequest request) {

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            for ( String s : authorizedHeader ) {
                if ( s.compareToIgnoreCase(headerName) == 0) {
                    headers.add(headerName, headerValue);
                    break;
                }
            }
        }
        return headers;
    }

    public static HttpMethod getHttpMethodFromString(String method) {
        switch (method) {
            case "GET": return HttpMethod.GET;
            case "POST": return  HttpMethod.POST;
            case "DELETE": return HttpMethod.DELETE;
            case "PUT": return  HttpMethod.PUT;
        }
        return  HttpMethod.GET;
    }

    /**
     * Compose a request to Sigfox backend.
     * @param apiPath
     * @param queryString
     * @param headers
     * @param body
     * @return
     * @throws ITSigfoxConnectionException
     */
    public T execute(
            String httpMethod,
            String apiPath,
            String queryString,
            HttpHeaders headers,
            S body,
            Class<T> typeRetrunedClass
    )  throws ITSigfoxConnectionException {

        T response = null;

        String url = SIGFOX_BACKEND+apiPath;
        if ( queryString != null && queryString.length() > 0 )
            url +='?'+queryString;

        if ( headers == null ) headers = new HttpHeaders();
        headers.add("Authorization", sigfoxBasicString);

        HttpEntity<String> he;
        if (    httpMethod.compareToIgnoreCase("GET")  == 0
                || httpMethod.compareToIgnoreCase("DELETE")  == 0
                ) {
            headers.setContentType(MediaType.TEXT_HTML);
            he = new HttpEntity<String>(headers);
        } else {
            headers.setContentType(MediaType.APPLICATION_JSON);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
                he = new HttpEntity<String>(mapper.writeValueAsString(body), headers);
                log.info("body : "+mapper.writeValueAsString(body));

            } catch (JsonProcessingException e) {
                throw new ITSigfoxConnectionException(HttpStatus.BAD_REQUEST,"Body format is invalid - can't be serialized");
            }
        }
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(
                            url,
                            getHttpMethodFromString(httpMethod),
                            he,
                            String.class
                    );
            if (    responseEntity.getStatusCode() != HttpStatus.OK
                    && responseEntity.getStatusCode() != HttpStatus.NO_CONTENT
                    && responseEntity.getStatusCode() != HttpStatus.CREATED
                    ) {
                log.info("Received an error code from Sigfox's backend : " + responseEntity.getStatusCode());
                throw new ITSigfoxConnectionException(
                        responseEntity.getStatusCode(),
                        responseEntity.getBody()
                );
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    if ( responseEntity.getBody() != null ) {
                        response = mapper.readValue(responseEntity.getBody(), typeRetrunedClass);
                    } else response = null;
                } catch (IOException e) {
                    log.error(responseEntity.getBody());
                    log.error("Impossible to deserialize Sigfox's backend response");
                    e.printStackTrace();
                    response = null;
                }
            }
        } catch ( HttpClientErrorException e ) {
            log.error("Sigfox's backend communication exception :"+e.getStatusCode());
            throw new ITSigfoxConnectionException(
                    e.getStatusCode(),
                    e.getResponseBodyAsString()
            );
        }
        return response;
    }

}
