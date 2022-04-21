package learntogether.Config;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
  Created by Luvbert
*/
@Configuration
public class JacksonConfig {
    @Bean
    public ObjectMapper objectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonSerialize.Inclusion.NON_NULL);
        return mapper;
    }
}
