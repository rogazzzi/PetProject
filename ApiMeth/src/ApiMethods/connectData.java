package ApiMethods;

public class connectData {
    final String Host = "localhost";
    final String Port = "8080";
    public String GetConnectionData(){
        return Host+":"+Port;
    }
}
