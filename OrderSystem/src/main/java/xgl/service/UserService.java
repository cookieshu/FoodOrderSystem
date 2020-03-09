package xgl.service;

import xgl.pojo.User;

import java.util.List;

public interface UserService {

    void add(User c);
    void delete(int id);
    void update(User c);
    User get(int id);

    List<User>list();

    //注册时判断是否重复
    boolean isExist(String name);
    //登录时判断
    User get(String name, String password);
}
