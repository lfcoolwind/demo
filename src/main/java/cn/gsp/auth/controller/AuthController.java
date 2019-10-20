package cn.gsp.auth.controller;

import cn.gsp.auth.dto.AccessTokenDTO;
import cn.gsp.auth.dto.GitHubUserDTO;
import cn.gsp.auth.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @description:
 * @author: lfcoolwind
 * @date: 2019/10/20 10:04
 */
@Controller
public class AuthController {

    @Value("${oauth.client.id}")
    private String client_id;
    @Value("${oauth.client.secret}")
    private String client_secret;
    @Value("${oauth.scope}")
    private String scope;
    @Value("${oauth.redirect.uri}")
    private String redirect_uri;

    @Autowired
    private GitHubProvider gitHubProvider;
    @GetMapping("/")
    public String getCode(){
        return "index";
    }
    @GetMapping("/callback")
    @ResponseBody
    public String callback(@RequestParam("code") String code,
                           @RequestParam(value = "state") String state){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(client_id);
        accessTokenDTO.setClient_secret(client_secret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setState(state);
        accessTokenDTO.setRedirect_uri(redirect_uri);
        String accessToken = gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUserDTO user = gitHubProvider.getUser(accessToken);
        System.out.println(user);
        return user.getName();
    }

}
