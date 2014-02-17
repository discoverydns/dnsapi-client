package com.discoverydns.dnsapiclient.internal.util;

import java.net.MalformedURLException;
import java.net.URL;

public class ResourceURLBuilder {

	public static URL buildResourceURL(String resource)
			throws MalformedURLException {
		URL url = null;
		try {
			url = new URL(resource);
		} catch (MalformedURLException e) {
			if (!resource.startsWith("ftp:") && !resource.startsWith("file:")
					&& !resource.startsWith("jar:")) {
				if (resource.startsWith("./")) {
					resource = resource.substring(2);
				}
				url = new URL("file:" + resource);
			} else {
				throw e;
			}
		}
		return url;
	}

}
