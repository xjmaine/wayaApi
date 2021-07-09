/**
 * Main task : return 76.65% of the amount a customer is depositing with a payment 
 * API endpoint : DepositEndpoint
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class EndPoint {
    private static HttpURLConnection connection;
    public static void main(String[] args) throws IOException{

        //using the java.net.HttpURLConection
        System.out.println("Enter amount: ");
        Scanner scanner = new Scanner(System.in);
        double deposit = scanner.nextDouble();
        // takeDeposit(deposit);

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();
        try {
            // URL url = new URL("https://jsonplaceholder.typicode.com/albums");
            URL url = new URL(String.valueOf(DepositEndpoint(deposit)));
            connection = (HttpURLConnection) url.openConnection();

            // requests
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            // System.out.println(status);

            if(status >299){
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            } else{
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseContent.append(line);
                }
                reader.close();
            }

            System.out.println(responseContent.toString());
            // System.out.println(takeDeposit(deposit));


        } catch (MalformedURLException e) {
            
            e.printStackTrace();
        }
        finally {
            connection.disconnect();
        }

    }

    private static double DepositEndpoint(double deposit){
        // Scanner sc = new Scanner(System.in);
        // System.out.println("Enter amount:");
        // deposit = sc.nextDouble();
        double calc = deposit*0.7665;
        // Map<Integer, Double> depositCash = new HashMap<Integer, Double>();
        // depositCash.put(1, calc);
        System.out.println(calc);
        return calc;
    }
}
