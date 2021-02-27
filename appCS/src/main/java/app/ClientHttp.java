package app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHeaders;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.nio.charset.StandardCharsets;

public class ClientHttp {
//    public static class Response{
//        private String httpVersion;
//        private int statusCode;
//        private int contentLength;
//        private String body;
//
//        public Response(InputStream input, boolean debug) throws IOException{
//            String line = null;
//            StringBuilder sb = new StringBuilder();
//
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
//                line = reader.readLine();
//                if (debug) {
//                    System.out.println(line);
//                }
//                parseStatusLine(line);
//
//                do {
//                    line = reader.readLine();
//                    parseHeader(line);
//                    if (debug) {
//                        System.out.println(line);
//                    }
//                    if (line.isEmpty()){
//                        break;
//                    }
//                } while (line != null);
//
//                do {
//                    line = reader.readLine();
//                    if (debug) {
//                        System.out.println(line);
//                    }
//                } while (line != null);
//            }
//        }
//
//    private void parseHeader(String line) {
//        if (line.startsWith("Content-Length")) {
//            contentLength = Integer.parseInt(line.split("\\s+")[1]);
//        }
//    }
//
//    private void parseStatusLine(String line){
//            String[] tokens = line.split("\\s+");
//            httpVersion = tokens[0];
//            statusCode = Integer.parseInt(tokens[1]);
//    }
//    }

    public static void main(String[] args) throws IOException{
       // sendRequest("http://localhost:8087/app", 8087, "GET");
        tryGet("http://localhost:8087/app/products");
        tryGet("http://localhost:8087/app/products/all");
        tryGet("http://localhost:8087/app/products/1");
        tryPost("http://localhost:8087/app/products/add_product?product_title=cake&product_price=55");
        tryGet("http://localhost:8087/app/products/edit/2");
       // tryPost("http://localhost:8087/app/products/edit?id=2&title=Milk&price=22");
    }

//    public static void sendRequest(String host, int port, String method) throws IOException {
//        try (Socket socket = new Socket("localhost", 8087)) {
//                StringBuilder output= new StringBuilder();
//                output.append("GET /products HTTP/1.1").append("\r\n");
//                output.append("Host: ").append("localhost:8087").append("\r\n");
//                output.append("Accept: ").append("text/plain;charset=UTF-8").append("\r\n");
//                output.append("Connection: ").append("close").append("\r\n");
//                output.append("Connect-Type: ").append("text/plain;charset=UTF-8").append("\r\n");
//                output.append("\r\n");
//                socket.getOutputStream().write(output.toString().getBytes("UTF-8"));
//            System.out.println(socket.getInputStream());
//                socket.getOutputStream().flush();
//
//                Response response = new Response(socket.getInputStream(), true);
//        }
//    }

    public static void tryGet(String msg) throws ClientProtocolException,IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // создаем объект клиента
            HttpGet request = new HttpGet(msg);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                // получаем статус ответа
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                // если есть тело ответа
                if (entity != null) {
                    // возвращаем строку
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
            } finally {
                // закрываем соединения
                response.close();
            }
        } finally {
            httpClient.close();
        }

    }

    public static void tryPost(String msg) throws ClientProtocolException,IOException{
        CloseableHttpClient httpClient = HttpClients.createDefault();

        try {
            // создаем объект клиента
            HttpPost request = new HttpPost(msg);
            CloseableHttpResponse response = httpClient.execute(request);
            try {
                // получаем статус ответа
                System.out.println(response.getProtocolVersion());              // HTTP/1.1
                System.out.println(response.getStatusLine().getStatusCode());   // 200
                System.out.println(response.getStatusLine().getReasonPhrase()); // OK
                System.out.println(response.getStatusLine().toString());        // HTTP/1.1 200 OK

                HttpEntity entity = response.getEntity();
                // если есть тело ответа
                if (entity != null) {
                    // возвращаем строку
                    String result = EntityUtils.toString(entity);
                    System.out.println(result);
                }
            } finally {
                // закрываем соединения
                response.close();
            }
        } finally {
            httpClient.close();
        }

    }
}


