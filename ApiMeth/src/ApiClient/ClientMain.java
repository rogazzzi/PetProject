package ApiClient;

import ApiMethods.ApiMethods;

public class ClientMain {

    public StringBuilder SendRequest(String Method){
        ApiMethods am = new ApiMethods();
        return am.GetRequest(Method);
    }

}