package designpattern.builder;

public final class BuilderMain {
    public static void main(String[] args) {
        HttpRequest request = new HttpRequest.Builder()
            .url("https://api.example.com")
            .method("POST")
            .header("Content-Type", "application/json")
            .body("{\"key\":\"value\"}")
            .build();

        System.out.println("Built request to: " + request.getUrl());
    }
}
