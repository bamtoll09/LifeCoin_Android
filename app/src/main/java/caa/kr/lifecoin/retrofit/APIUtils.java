package caa.kr.lifecoin.retrofit;

public class APIUtils {

    public static APIInterface getAPIService() {
        return APIClient.getClient().create(APIInterface.class);
    }
}