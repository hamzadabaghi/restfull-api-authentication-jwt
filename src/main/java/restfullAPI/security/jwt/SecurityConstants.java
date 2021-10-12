package restfullAPI.security.jwt;

public class SecurityConstants {

	public static final String SECRET = "9jvsY1NbODzA1kvIy5IkPLuV8xrH1X4farnB4ik0PsTfKT0qeBnCNShPvTKcec76";
	public static final long EXPIRATION_TIME = 864_000_000;
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String HEADER_STRING = "Authorization";
	public static final String SIGN_UP_URL = "/users/**";

}
