package cn.gsp.auth.dto;

import lombok.Data;

/**
 * @description:
 * @author: lfcoolwind
 * @date: 2019/10/20 15:52
 */
@Data
public class GitHubUserDTO {
    private String login;
    private String id;
    private String name;
    private String bio;
}
