package learntogether.JWT;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import learntogether.DTO.UserDetail;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
  Created by Luvbert
*/
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private JwtAuthService jwtAuthService;
    private UserDetailsServiceImpl userDetailsService;
    private final ObjectMapper mapper;

    public JwtAuthenticationFilter(JwtAuthService jwtAuthService, UserDetailsServiceImpl userDetailsService, ObjectMapper mapper){
        this.jwtAuthService = jwtAuthService;
        this.userDetailsService = userDetailsService;
        this.mapper = mapper;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // a map store the errors
        Map<String, String> message = new HashMap<>();
        try {
            //Get jwt from request
            String jwt = getJwtFromRequest(request);

            if (StringUtils.hasText(jwt) && jwtAuthService.validateJWT(jwt)) {
                String username = jwtAuthService.getUsernameFromJWT(jwt);
                UserDetail user = (UserDetail) userDetailsService.loadUserByUsername(username);
                if (user != null) {
                    List<GrantedAuthority> authorities = new ArrayList<>();
                    authorities.add(new SimpleGrantedAuthority(user.getRole().getId() + " "));
                    UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null, authorities);
                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);

                }
            }
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

    private String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");

        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")){

            //return string exclude "Bearer "
            return bearerToken.substring(7);
        }
        return null;
    }
}
