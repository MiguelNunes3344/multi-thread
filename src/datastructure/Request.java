package datastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


public class Request {

    private static final String USER_AGENT = "Mozilla/5.0";
    private static final String GET_URL = "http://localhost:8090/api/allUsers";

    public static void main(String[] args) throws Exception {
        try {
        	
        	Runnable sendGetTask = new SendGetTask();

            
            Thread thread = new Thread(sendGetTask);
            thread.start();
            sendGet();
        } catch (IOException e) {
            e.printStackTrace();
            throw new Exception();
        }
    }

    
    
    
    public static void sendGet() throws IOException {
    	while (true) {
    		URL obj = new URL(GET_URL);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", USER_AGENT);

            int responseCode =con.getResponseCode();
            if (true) { // success
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();

                // print result
                System.out.println(response.toString());
                System.out.println(responseCode);
            } 
    	}
        
      
    }
    private static class SendGetTask implements Runnable {
        @Override
        public void run() {
            try {
                sendGet();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
