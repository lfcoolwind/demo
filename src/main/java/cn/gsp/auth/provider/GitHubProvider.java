package cn.gsp.auth.provider;

import cn.gsp.auth.dto.AccessTokenDTO;
import cn.gsp.auth.dto.GitHubUserDTO;
import com.alibaba.fastjson.JSON;
import okhttp3.*;
import org.springframework.stereotype.Component;

/**
 * @description:
 * @author: lfcoolwind
 * @date: 2019/10/20 15:25
 */
@Component
public class GitHubProvider {

    public String getAccessToken(AccessTokenDTO accessTokenDTO){
       final String url="https://github.com/login/oauth/access_token";
        MediaType mediaType=MediaType.get("application/json; charset=utf-8");
        OkHttpClient client=new OkHttpClient();
        RequestBody body=RequestBody.create(mediaType, JSON.toJSONString(accessTokenDTO));
        Request request=new Request.Builder().url(url)
                .post(body)
                .build();
        try{
            Response response=client.newCall(request).execute();
            String token=response.body().string().split("&")[0].split("=")[1];
            return token;
        }catch (Exception ex){
            ex.printStackTrace();
        }
       return "";
    }

    public GitHubUserDTO getUser(String access_token){
        final String url="https://api.github.com/user?access_token="+access_token;
        OkHttpClient client=new OkHttpClient();
        Request request=new Request.Builder().url(url).build();
        try{
            Response response=client.newCall(request).execute();
            String jsonString=response.body().string();
            GitHubUserDTO gitHubUserDTO = JSON.parseObject(jsonString, GitHubUserDTO.class);
            return gitHubUserDTO;

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;

    }
}
