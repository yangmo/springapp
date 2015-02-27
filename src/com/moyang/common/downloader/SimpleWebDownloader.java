package com.moyang.common.downloader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.concurrent.Callable;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;


/**
 * Simple web downloader to avoid creating an HttpClient, a GetMethod, getting
 * the response body, releasing the connection, etc Provides static functions
 * that return the content as common formats, eg. download to disk, etc
 * 
 */
public class SimpleWebDownloader {

    private static final String AUTH_SCOPE = "monitor-api.amazon.com";
    private static final String DEFAULT_TEMPORARY_FILE_PREFIX = "downloader";
    private static final String DEFAULT_TEMPORARY_FILE_SUFFIX = ".tmp";

    /**
     * 
     * @author moyang
     * 
     * @param <T>
     */
    private static class InputStreamConsumer<T> implements Callable<T> {
        private InputStream is;

        public void setInputStream(InputStream is) {
            this.is = is;
        }

        public InputStream getInputStream() {
            return this.is;
        }

        @Override
        public T call() throws Exception {
            // TODO Auto-generated method stub
            return null;
        }
    }

    protected SimpleWebDownloader() {

    }

    /**
     * @param uri
     *            the URI you want to download
     * @return a String with the full contents of the downloaded uri
     * @throws Exception
     *             if the URI couldn't be downloaded
     */
    public static String getAsString(final String uri) throws Exception {
        InputStreamConsumer<String> consumer = new InputStreamConsumer<String>() {
            @Override
            public String call() throws Exception {
                return IOUtils.toString(getInputStream());
            }
        };
        return get(uri, consumer);
    }
    public static void main(String [] args) throws Exception{
    	System.out.println(getAsString("http://hq.sinajs.cn/list=sh601006"));
    }
    /**
     * Downloads the URI to a local TEMPORARY file on disk. This file will
     * usually reside in the temp folder and will be deleted on JVM exit as
     * determined by Java's File.createTempFile() We'll use a default prefix and
     * suffix for the temporary file name
     * 
     * @param uri
     *            the URI you want to download
     * @return the full path of the temporary file that we downloaded the uri to
     * @throws Exception
     *             if the URI couldn't be downloaded
     */
    public static String getAsTemporaryFile(final String uri) throws Exception {
        return getAsTemporaryFile(uri, DEFAULT_TEMPORARY_FILE_PREFIX, DEFAULT_TEMPORARY_FILE_SUFFIX);
    }

    /**
     * Downloads the URI to a local TEMPORARY file on disk. This file will
     * usually reside in the temp folder and will be deleted on JVM exit as
     * determined by Java's File.createTempFile()
     * 
     * @param uri
     *            the URI you want to download
     * @param prefix
     *            the prefix you want for the temporary file
     * @param suffix
     *            the sufix you want for the temporary file (remember to add the
     *            dot if your suffix is an extension)
     * @return the full path of the temporary file that we downloaded the uri to
     * @throws Exception
     *             if the URI couldn't be downloaded
     */
    public static String getAsTemporaryFile(final String uri, final String prefix, final String suffix)
        throws Exception {
        File tempFile = File.createTempFile(prefix, suffix);
        tempFile.deleteOnExit();
        getAsFile(uri, tempFile);
        return tempFile.getAbsolutePath();
    }

    /**
     * Downloads the URI to a local file on disk. This file will NOT be removed
     * upon jvm exit
     * 
     * @param uri
     *            the URI you want to download
     * @param file
     *            the local path where you want to download to
     * @throws Exception
     *             if the URI couldn't be downloaded
     */
    public static void getAsFile(final String uri, final File file) throws Exception {
        getAsFile(uri, file.getAbsolutePath());
    }

    /**
     * Downloads the URI to a local file on disk. This file will NOT be removed
     * upon jvm exit
     * 
     * @param uri
     *            the URI you want to download
     * @param file
     *            the local path where you want to download to
     * @throws Exception
     *             if the URI couldn't be downloaded
     */
    public static void getAsFile(final String uri, final String file) throws Exception {
        InputStreamConsumer<Void> consumer = new InputStreamConsumer<Void>() {
            @Override
            public Void call() throws Exception {
                OutputStream os = new FileOutputStream(file);
                try {
                    IOUtils.copy(getInputStream(), os);
                } finally {
                    try {
                        os.close();
                    } catch (Exception e) {
                        System.out.println("Problem closing file " + file + ": " + e.getLocalizedMessage());
                    }
                }
                return null;
            }
        };

        get(uri, consumer);

    }

    /**
     * 
     * @param stream
     */
    public static void closeQuietly(Closeable stream) {
        try {
            if (stream != null) {
                stream.close();
            }
        } catch (Exception e) {
            System.out.println("Could not close stream: " + e.getLocalizedMessage());
        }
    }

    private static <T> T get(final String uri, InputStreamConsumer<T> consumer) throws Exception {
    
        HttpClient httpClient = new HttpClient();

        HttpCodeValidator httpCodeValidator = HttpCodeValidator.newStandardHttpGetValidValues();
        GetMethod getMethod = new GetMethod(uri);
        InputStream is = null;

        try {
            httpClient.executeMethod(getMethod);
            httpClient.setConnectionTimeout(3000);
            httpCodeValidator.validateHttpCode(getMethod.getStatusCode());
            is = getMethod.getResponseBodyAsStream();
            consumer.setInputStream(is);
            return consumer.call();
        } finally {
            closeQuietly(is);
            getMethod.releaseConnection();
        }
    }


}