package com.sdust.im.controller;
/*
 * @author luhao
 * @date 2018/5/19 20:04
 *
 */

import com.sdust.im.common.ServerResponse;
import com.sdust.im.domin.dao.User;
import com.sdust.im.domin.dto.UserAccount;
import com.sdust.im.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
public class UserController {

    private final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 获取此账户所有联系人
     * @param user
     * @return
     */
    @RequestMapping("fetch")
    public ServerResponse getAllContact(@RequestBody() User user){
        if (null != user){
            String accountId = user.getAccountId();
            logger.info("账户id【{}】", accountId);
            if (null != accountId && !"".equals(accountId)){
                ServerResponse response = userService.findAllContact(accountId);
                return response;
            }else{
                return ServerResponse.createByError();
            }
        }else{
            return ServerResponse.createByError();
        }
    }

    /**
     * 搜索联系人并添加
     * @param userAccount
     * @return
     */
    @RequestMapping("searchContact")
    public ServerResponse searchContact(@RequestBody() UserAccount userAccount){
        if (null != userAccount){
            ServerResponse response = userService.searchContact(userAccount);
            return response;
        }else{
            return ServerResponse.createByErrorMessage("网络错误！");
        }
    }

    /**
     * 删除联系人
     * @param userAccount
     * @return
     */
    @RequestMapping("deleteContact")
    public ServerResponse deleteContact(@RequestBody() UserAccount userAccount){
        if (null != userAccount){
            ServerResponse response = userService.deleteContact(userAccount);
            return response;
        }else{
            return ServerResponse.createByErrorMessage("网络错误！");
        }
    }

}
