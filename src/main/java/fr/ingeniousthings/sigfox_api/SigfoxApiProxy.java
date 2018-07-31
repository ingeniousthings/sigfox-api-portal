/**
 * -------------------------------------------------------------------------------
 * This file is part of IngeniousThings Sigfox-Api.
 *
 * IngeniousThings Sigfox-Api is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * IngeniousThings Sigfox-Api is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Foobar.  If not, see <http://www.gnu.org/licenses/>.
 * -------------------------------------------------------------------------------
 * Author : Paul Pinault aka disk91
 * See https://www.disk91.com
 * ----
 * More information about IngeniousThings : https://www.ingeniousthings.fr
 * ----
 * Commercial license of this software can be obtained contacting ingeniousthings
 * -------------------------------------------------------------------------------
 */
package fr.ingeniousthings.sigfox_api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

public class SigfoxApiProxy<T> {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    private String[] authorizedHeader = {
      "user-agent", "accept", "authorization", "origin", "referer"
    };

    public T proxify(HttpServletRequest request)  throws SigfoxApiProxyException {
        return proxify(request,null);
    }


    public T proxify(HttpServletRequest request, String body)  throws SigfoxApiProxyException {

        T response = null;

        String url = "https://backend.sigfox.com"+request.getRequestURI();
        if ( request.getQueryString() != null && request.getQueryString().length() > 0 )
           url +='?'+request.getQueryString();

        HttpHeaders headers = new HttpHeaders();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            for ( String s : authorizedHeader ) {
                if ( s.compareToIgnoreCase(headerName) == 0) {
                    headers.add(headerName, headerValue);
                    // log.debug("HEADER : "+headerName+" = "+headerValue);
                    break;
                }
            }
        }
        HttpEntity<String> he;
        if (    request.getMethod().compareToIgnoreCase("GET")  == 0
             || request.getMethod().compareToIgnoreCase("DELETE")  == 0
                ) {
            he = new HttpEntity<String>(headers);
        } else {
           // log.info("BODY : "+body);
            he = new HttpEntity<String>(body, headers);
        }

        //log.debug("URL : "+url);
        //log.debug("METHOD : "+request.getMethod());
        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> responseEntity =
                    restTemplate.exchange(
                            url,
                            HttpMethod.GET,
                            new HttpEntity<String>(headers),
                            String.class
                    );
            if (responseEntity.getStatusCode() != HttpStatus.OK) {
               // log.info("Status Code : " + responseEntity.getStatusCode());
                throw new SigfoxApiProxyException(
                        responseEntity.getStatusCode(),
                        responseEntity.getBody()
                );
            } else {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    response = mapper.readValue(responseEntity.getBody(), new TypeReference<T>() {
                    });
                } catch (IOException e) {
                    response = null;
                }
            }
        } catch ( HttpClientErrorException e ) {
            throw new SigfoxApiProxyException(
                    e.getStatusCode(),
                    e.getResponseBodyAsString()
            );
        }
        return response;
    }


}
