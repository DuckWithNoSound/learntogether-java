package learntogether.Security;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import learntogether.DTO.UserDetail;
import learntogether.JWT.JwtAuthService;
import learntogether.Service.UserDetailsServiceImpl;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

/*
  Created by Luvbert
*/
@Component
public class CookieAuthenticationFilter extends OncePerRequestFilter {

    private JwtAuthService jwtAuthService;
    private UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper mapper;

    public CookieAuthenticationFilter(JwtAuthService jwtAuthService, UserDetailsServiceImpl userDetailsService, ObjectMapper mapper){
        this.jwtAuthService = jwtAuthService;
        this.userDetailsService = userDetailsService;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Map<String, String> message = new HashMap<>();
        try {
            Cookie[] cookies = request.getCookies();

            String authCookie = Optional.ofNullable(cookies).flatMap(cookieArr -> Arrays.stream(cookieArr)
                    .filter(cookie -> "AuthenticationCookie".equals(cookie.getName()))
                    .findAny())
                    .map(cookie -> cookie.getValue())
                    .orElse(null);
            if(authCookie != null) auth(authCookie, request);

        } catch (MalformedJwtException malformedJwtException){
            message.put("message", "Token is invalid");
        } catch (ExpiredJwtException expiredJwtException) {
            message.put("message", "Token is expiration");
        } catch (UnsupportedJwtException unsupportedJwtException){
            message.put("message", "Token is unsupported");
        } catch (IllegalArgumentException illegalArgumentException){
            message.put("message", "Token claims string is empty");
        } catch (Exception exception) {
            message.put("message", "Failed on set user authentication: " + exception.getMessage());
            System.out.println("Failed on set user authentication: " + exception.getMessage());
            exception.printStackTrace();
        }
        if(!message.isEmpty()){
            mapper.writeValue(response.getWriter(), message);
        }
        filterChain.doFilter(request, response);
    }
    private void auth(String authCookie, HttpServletRequest request){
        if (StringUtils.hasText(authCookie) && jwtAuthService.validateJWT(authCookie)) {
            UserDetail user = (UserDetail) userDetailsService.loadUserByUsername(jwtAuthService.getUsernameFromJWT(authCookie));
            if (user != null) {
                List<GrantedAuthority> authorities = new ArrayList<>();
                authorities.add(new SimpleGrantedAuthority(user.getRole().getId() + " "));
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);

            }
        }
    }
}
