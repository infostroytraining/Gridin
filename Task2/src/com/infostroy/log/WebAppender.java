package com.infostroy.log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Layout;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.appender.AbstractAppender;
import org.apache.logging.log4j.core.appender.AppenderLoggingException;
import org.apache.logging.log4j.core.config.plugins.Plugin;
import org.apache.logging.log4j.core.config.plugins.PluginAttribute;
import org.apache.logging.log4j.core.config.plugins.PluginElement;
import org.apache.logging.log4j.core.config.plugins.PluginFactory;
import org.apache.logging.log4j.core.layout.PatternLayout;

@Plugin(name = "WebAppender", category = "Core", elementType = "appender", printObject = true)
public class WebAppender extends AbstractAppender {

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
    private final Lock readLock = rwLock.readLock();

    protected WebAppender(String name, Filter filter, Layout<? extends Serializable> layout,
            final boolean ignoreExceptions) {
	super(name, filter, layout, ignoreExceptions);
    }

    @PluginFactory
    public static WebAppender createAppender(@PluginAttribute("name") String name,
            @PluginElement("Layout") Layout<? extends Serializable> layout,
            @PluginElement("Filter") final Filter filter, @PluginAttribute("otherAttribute") String otherAttribute) {
	if (name == null) {
	    LOGGER.error("No name provided for MyCustomAppenderImp");
	    return null;
	}
	if (layout == null) {
	    layout = PatternLayout.createDefaultLayout();
	}
	return new WebAppender(name, filter, layout, true);

    }

    @Override
    public void append(LogEvent event) {
	readLock.lock();
	try {
	    final byte[] bytes = getLayout().toByteArray(event);
	    String message = new String(bytes, "UTF-8");
	    String url = "http://localhost:8081/server-log-app/logs";
	    final String USER_AGENT = "Mozilla/5.0";
	    URL obj = new URL(url);
	    HttpURLConnection con = (HttpURLConnection) obj.openConnection();

	    // add reuqest header
	    con.setRequestMethod("POST");
	    con.setRequestProperty("User-Agent", USER_AGENT);
	    con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

	    String urlParameters = "logEvent=" + message;

	    // Send post request
	    con.setDoOutput(true);
	    DataOutputStream wr = new DataOutputStream(con.getOutputStream());
	    wr.writeBytes(urlParameters);
	    wr.flush();
	    wr.close();

	    int responseCode = con.getResponseCode();
	    System.out.println("\nSending 'POST' request to URL : " + url);
	    System.out.println("Post parameters : " + urlParameters);
	    System.out.println("Response Code : " + responseCode);

	    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
	    String inputLine;
	    StringBuffer response = new StringBuffer();

	    while ((inputLine = in.readLine()) != null) {
		response.append(inputLine);
	    }
	    in.close();

	    // print result
	    System.out.println(response.toString());
	} catch (Exception ex) {
	    if (!ignoreExceptions()) {
		throw new AppenderLoggingException(ex);
	    }
	} finally {
	    readLock.unlock();
	}
    }

}
