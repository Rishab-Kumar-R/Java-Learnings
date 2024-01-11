package com.rishab;

/*
 * URI (Uniform Resource Identifier) - A string of characters used to identify a name or a resource on the internet.
 * URL (Uniform Resource Locator) - A subset of URI that specifies where an identified resource is available and the mechanism for retrieving it.
 *
 * While working with java.net package, a URI is an identifier that might not provide enough information to locate a resource.
 * A URL is an identifier that includes information about how to access the resource it identifies.
 *
 * **Scheme** is the part of URI or URL that appears before the colon.
 * For example, in the URL http://www.google.com, the scheme is http, "file" and "ftp" are other examples of schemes.
 * Another way to define a URL is that it's http URI.
 *
 * When working with low-level APIs, we used the following classes: Socket, ServerSocket, and DatagramSocket.
 * When working with high-level APIs, we use the following classes: URI, URL, URLConnection, and HTTPConnection.
 *
 * URI Syntax: scheme:[//[user:password@]host[:port]][/]path[?query][#fragment]
 * A URI can contain the following components:
 * 1. scheme
 * 2. scheme-specific part
 * 3. authority
 * 4. user info
 * 5. host
 * 6. port
 * 7. path
 * 8. query
 * 9. fragment
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
//            URI uri = new URI("https://example.com/somepage.html");
            URI uri = new URI("https://api.flickr.com/services/feeds/photos_public.gne?tags=dogs");
            URL url = uri.toURL();

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET"); // default is GET
            connection.setRequestProperty("User-Agent", "Chrome");
            connection.setReadTimeout(10000);

            int responseCode = connection.getResponseCode();
            System.out.println("Response code: " + responseCode);

            if (responseCode != 200) {
                System.out.println("Error reading web page");
                System.out.println(connection.getResponseMessage());
                return;
            }

            BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;

            while ((line = inputReader.readLine()) != null) {
                System.out.println(line);
            }
            inputReader.close();

//            URLConnection urlConnection = url.openConnection();
//            urlConnection.setDoOutput(true); // we can write to the connection
//            urlConnection.connect();
//
//            BufferedReader inputStream = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
//            Map<String, List<String>> headerFields = urlConnection.getHeaderFields();
//            for (Map.Entry<String, List<String>> entry : headerFields.entrySet()) {
//                String key = entry.getKey();
//                List<String> values = entry.getValue();
//                System.out.println("-----key = " + key);
//                for (String value : values) {
//                    System.out.println("value = " + value);
//                }
//            }
//            String line;
//
//            while ((line = inputStream.readLine()) != null) {
//                System.out.println(line);
//            }
//            inputStream.close();

//            URI uri = new URI("http://username:password@myserver.com:5000/catalog/phones?os=android#samsung");
//            URI uri = new URI("hello");
//            URI baseURI = new URI("http://username:password@mynewserver.com:5000");
//            URI uri1 = new URI("/catalog/phones?os=android#samsung");
//            URI uri2 = new URI("/catalog/tvs?manufacturer=samsung");
//            URI uri3 = new URI("/stores/locations?zip=12345");
//
//            URI resolvedURI1 = baseURI.resolve(uri1);
//            URI resolvedURI2 = baseURI.resolve(uri2);
//            URI resolvedURI3 = baseURI.resolve(uri3);
//
//            URL url1 = resolvedURI1.toURL();
//            System.out.println("URL 1: " + url1);
//            URL url2 = resolvedURI2.toURL();
//            System.out.println("URL 2: " + url2);
//            URL url3 = resolvedURI3.toURL();
//            System.out.println("URL 3: " + url3);
//
//            URI relativizedURI = baseURI.relativize(resolvedURI2);
//            System.out.println("Relative URI: " + relativizedURI);

//            System.out.println("Scheme: " + uri.getScheme());
//            System.out.println("Scheme-specific-part: " + uri.getSchemeSpecificPart());
//            System.out.println("Authority: " + uri.getAuthority());
//            System.out.println("User info: " + uri.getUserInfo());
//            System.out.println("Host: " + uri.getHost());
//            System.out.println("Port: " + uri.getPort());
//            System.out.println("Path: " + uri.getPath());
//            System.out.println("Query: " + uri.getQuery());
//            System.out.println("Fragment: " + uri.getFragment());
        } catch (URISyntaxException | IOException e) {
            System.out.println("Exception: " + e.getMessage());
        }

    }
}
