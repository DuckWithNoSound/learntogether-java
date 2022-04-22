package learntogether.JWT;

import io.jsonwebtoken.*;
import jdk.nashorn.internal.parser.Token;
import learntogether.DTO.UserDetail;
import learntogether.Service.UserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/*
  Created by Luvbert
*/
@Component
public class JwtAuthService {
    private final String JWT_SECRET_KEY = "luvbertttttt";
    private final Long JWT_EXPIRATION = 20 * 60 * 1000l;
    static final String TOKEN_PREFIX = "Bearer ";
    static final String HEADER_STRING = "Authorization";

    private UserDetailsServiceImpl userDetailsService;

    public  JwtAuthService(UserDetailsServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    //Generate a new json web token from user information
    public String generateToken(UserDetail user){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();

        return TOKEN_PREFIX + jwt;
    }

    //Generate a new json web token from user information
    public Map<String, String> generateToken(UserDetail user, Map<String, String> map){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();

        map.put(HEADER_STRING, TOKEN_PREFIX + jwt);
        return map;
    }

    //Generate a new json web token from user information and set into header by httpServletResponse
    public void generateToken(HttpServletResponse res, UserDetail user){
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + JWT_EXPIRATION);

        String jwt = Jwts.builder()
                .setSubject(user.getUsername())
                .setIssuedAt(now).setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET_KEY)
                .compact();
        res.setHeader(HEADER_STRING, TOKEN_PREFIX + jwt);
    }

    public Authentication getAuthFromToken(HttpServletRequest request){
        String token = request.getHeader(HEADER_STRING);
        if(token != null && validateJWT(token)){
            String username = getUsernameFromJWT(token);
            UserDetail userDetail = (UserDetail) userDetailsService.loadUserByUsername(username);
            if (userDetail != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(userDetail.getRole().getId() + " "));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetail, null, authorities);
                return authenticationToken;
            }
        }
        return null;
    }

    //Get username from json web token
    public String getUsernameFromJWT(String jwt_token){
        Claims claims = Jwts.parser()
                            .setSigningKey(JWT_SECRET_KEY)
                            .parseClaimsJws(jwt_token.replace(TOKEN_PREFIX, ""))
                            .getBody();
        return claims.getSubject();
    }

    //Validate a json web token
    public Boolean validateJWT(String jwt_token) throws MalformedJwtException, ExpiredJwtException, UnsupportedJwtException, IllegalArgumentException{
        Jwts.parser().setSigningKey(JWT_SECRET_KEY).parseClaimsJws(jwt_token);
        return true;
    }
}
