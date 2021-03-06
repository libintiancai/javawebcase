package itcast.service.impl;

import itcast.dao.impl.UserDaoImpl;
import itcast.domain.PageBean;
import itcast.domain.User;
import itcast.service.UserService;

import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDaoImpl dao = new UserDaoImpl();

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void deleteUser(String id) {
        dao.delete(Integer.parseInt(id));//将字符串类型id强转成int类型
    }

    @Override
    public User findUserById(String id) {
        return dao.findById(Integer.parseInt(id));
    }

    /**
     * 修改用户信息
     * @param user
     */
    @Override
    public void updateUser(User user) {
        dao.update(user);
    }

    /**
     * 删除选中
     * @param ids
     */
    @Override
    public void delSelectedUser(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                dao.delete(Integer.parseInt(id));
            }
        }
    }

    @Override
    public PageBean<User> findUserByPage(String _currentPage, String _rows, Map<String, String[]> condition) {
        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //创建空的PageBean对象
        PageBean<User> pb = new PageBean<User>();

        //调用dao查询总记录数
        int totalCount = dao.findTotalCount(condition);
        pb.setTotalCount(totalCount);

        //计算总页码
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows) + 1;

        if (currentPage <= 0){
            currentPage = 1;
        }

        if (currentPage >= (totalPage + 1)){
            currentPage = totalPage;
        }
        //调用dao查询list集合
        //计算开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> list = dao.findByPage(start,rows,condition);
        //设置参数
        pb.setRows(rows);
        pb.setList(list);
        pb.setCurrentPage(currentPage);
        pb.setTotalPage(totalPage);

        return pb;
    }


}