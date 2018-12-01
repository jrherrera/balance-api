package unsl.utils;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import unsl.entities.User;

@Service
public class RestService {

    /**
     * @param url
     * @return
     * @throws Exception
     */
    public User getUser(String url) throws Exception {
        RestTemplate restTemplate = new RestTemplate();

        User user;

        try {
            user = restTemplate.getForObject(url, User.class);
        }  catch (Exception e){
            Object bodyError = ((HttpClientErrorException) e).getResponseBodyAsString();
            throw new Exception(bodyError.toString());
        }

        return user;
    }

}

