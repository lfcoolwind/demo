package cn.gsp.auth.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @description:
 * @author: lfcoolwind
 * @date: 2019/10/20 10:07
 */
@Data
public class AccessTokenDTO implements Serializable {
    private String client_id;
    private String client_secret;
    private String code;
    private String redirect_uri;
    private String state;

}
