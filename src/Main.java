import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Main {
    private static boolean hasBeenDetectedBefore;
    private static ArrayList<String> ipaddresses=new ArrayList<String>();
    private static String s;
    public static void main(String args[]) throws IOException, ConnectException {
        Runtime rt = Runtime.getRuntime();
        while (true) {
            try{
                ipaddresses.clear();
                s = checkHosts("192.168.0");
            }catch(ConnectException c){
                c.printStackTrace();
            }
            System.out.println(s);
            if (s.contains("192.168.0.26") && !hasBeenDetectedBefore) {
                hasBeenDetectedBefore=true;
                rt.exec("open https://www.youtube.com/watch?v=oHg5SJYRHA0 -a Safari");
            }else{
                hasBeenDetectedBefore=false;
            }

        }
    }
    public static String checkHosts(String startingnetwork) throws IOException,ConnectException {
        int timeout=150;

        for(int i=1;i<255;i++){
            if(24<i && i<29){
                timeout=400;
            }else{
                timeout=150;
            }
            String ip=startingnetwork+"."+i;
            try {
                if (InetAddress.getByName(ip).isReachable(timeout)) {
                    ipaddresses.add(ip);
                } else {

                }
            }catch(ConnectException c){
                //c.printStackTrace();
            }
        }
        return String.join(", ",ipaddresses);
    }

}