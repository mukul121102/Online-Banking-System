package main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class sendSMS {

    public String sendSms(int otp) throws IOException {

        // Construct data
        String user = "username=" + "aditya.sharma@st.niituniversity.in";
        String hash = "&hash=" + "ea44648d27405028e0d38ba0003fac4ceed3adac52ad9ba75a2082bb8b6b09b1";
        String message = "&message=" + "OTP" + otp;
        String sender = "&sender=" + "NIIT BANK";
        String numbers = "&numbers=" + "+919571172479";

        // Send data
        HttpURLConnection conn = (HttpURLConnection) new URL("http://api.txtlocal.com/send/?").openConnection();
        String data = user + hash + numbers + message + sender;
        conn.setDoOutput(true);
        conn.setRequestMethod("POST");
        conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
        conn.getOutputStream().write(data.getBytes("UTF-8"));
        final StringBuffer stringBuffer;
        try (BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            stringBuffer = new StringBuffer();
            String line;
            while ((line = rd.readLine()) != null) {
                stringBuffer.append(line);
            }
        }

        return stringBuffer.toString();

    }

    public static void main(String[] args) throws IOException {
    }
}
