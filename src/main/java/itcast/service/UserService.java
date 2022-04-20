package itcast.service;

import itcast.domain.PageBean;
import itcast.domain.User;

import java.util.List;
import java.util.Map;

/**
 * 用户管理的业务接口
 */
public interface UserService {
    /**
     * 查询所有用户信息
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    /**
     * 保存User
     * @param user
     */
    void  addUser(User user);

    /**
     * 删除用户数据
     * @param id
     */
    void deleteUser(String id);

    /**
     *根据id返回用户信息
     * @param id
     * @return
     */
    User findUserById(String id);

    /**
     * 提交修改的用户信息
     * @param user
     */
    void updateUser(User user);

    /**
     * 删除选中用户信息
     * @param ids
     */
    void delSelectedUser(String[] ids);

    /**
     *分页条件查询
     * @param currentPage
     * @param rows
     * @param condition
     * @return
     */
    PageBean<User> findUserByPage(String currentPage, String rows, Map<String, String[]> condition);
}
