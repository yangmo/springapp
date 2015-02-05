package com.moyang.common.downloader;


import java.util.ArrayList;
import java.util.Collection;

import org.apache.commons.lang.math.IntRange;

 
/**
 * 
 */
public class HttpStatusCodeException extends Exception {

    /**
     * Thrown by the HttpCodeValidator class when there's a problem
     * @see HttpCodeValidator
     * @author frandes Jun 17, 2013
     */
    private static final long serialVersionUID = 4850041867192572836L;
    private final Collection<IntRange> validRanges;

    /**
     * 
     * @param msg
     * @param whiteListedCode
     */
    public HttpStatusCodeException(final String msg, Collection<IntRange> whiteListedCode) {
        super("Invalid http code. Code " + msg + " is not in the whitelist.");

        validRanges = new ArrayList<IntRange>(whiteListedCode);
    }

    /**
     * 
     */
    public String getMessage(){
        StringBuilder whiteListBuilder = new StringBuilder();
        for (IntRange r : validRanges) {
            whiteListBuilder.append("[" + r.getMinimumInteger()+","+r.getMaximumInteger() + "]");
        }

        return super.getMessage() + " WhiteListed ranges: " + whiteListBuilder.toString();
    }
}
