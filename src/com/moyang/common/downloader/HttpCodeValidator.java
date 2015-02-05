package com.moyang.common.downloader;


import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.math.IntRange;


/**
 * A Class to whitelist and validate http status code.
 * 
 * @see HttpCodeValidator
 */

public class HttpCodeValidator {

    private Collection<IntRange> validCodes = new ArrayList<IntRange>();

    /**
     * 
     * @return
     */
    public static HttpCodeValidator newStandardHttpGetValidValues() {
        return new HttpCodeValidator().withHttpCode(200, 301, 302, 304);
    }

    /**
     * 
     * @param codes
     * @return
     */
    public HttpCodeValidator withHttpCode(final int... codes) {
        for (int i = 0; i < codes.length; i++) {
            withHttpCodeRange(codes[i], codes[i]);
        }

        return this;
    }

    /**
     * 
     * @param lowerBound
     * @param upperBound
     * @return
     */
    public HttpCodeValidator withHttpCodeRange(final int lowerBound, final int upperBound) {
        IntRange range = new IntRange(lowerBound, upperBound);
        if (!validCodes.contains(range)) {
            validCodes.add(range);
        }

        return this;
    }

    /**
     * 
     * @param code
     * @throws HttpStatusCodeException
     */
    public void validateHttpCode(final int code) throws HttpStatusCodeException {
        boolean isValid = false;
        for (IntRange r : validCodes) {
            if (r.containsInteger(code)) {
                isValid = true;
                break;
            }
        }
        if (!isValid) {
            throw new HttpStatusCodeException(Integer.toString(Integer.valueOf(code)), validCodes);
        }
    }
    

}