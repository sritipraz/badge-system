package id_authentication.Models;

public class AuthenticationResponse {

    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }

    public String getToken() {
        return jwt;
    }
}
