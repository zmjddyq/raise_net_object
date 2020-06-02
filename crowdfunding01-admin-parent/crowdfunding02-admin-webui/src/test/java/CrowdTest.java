import com.raise.crowd.entity.Admin;
import com.raise.crowd.entity.Role;
import com.raise.crowd.mapper.AdminMapper;
import com.raise.crowd.mapper.AuthMapper;
import com.raise.crowd.mapper.RoleMapper;
import com.raise.crowd.service.api.AdminService;
import com.raise.crowd.util.CrowdUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.util.List;

/**
 * @author zmj
 * @date 2020/5/24 16:36
 * @Description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    DataSource dataSource;
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AdminService adminService;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    AuthMapper authMapper;



    @Test
    public void selectAssignedAuthNameByAdminIdTest(){
        List<String> strings = authMapper.selectAssignedAuthNameByAdminId(607);
        System.out.println(strings);
    }
    @Test
    public void roleMapperTest(){
        for (int i = 3; i < 520; i++) {
            Role role = new Role(i,"role" + i);
            roleMapper.insert(role);
        }

    }

    @Test
    public void dateTest() {
        String dateTime = CrowdUtil.getDateTime();
        System.out.println(dateTime);
    }

    @Test
    public void mp5Test() {
        String s = CrowdUtil.md5("123456");
        System.out.println(s);
    }

    @Test
    public void txTest() {
        adminService.saveAdmin(new Admin(null, "jxl", "123456", "纪晓岚", "jxl@163.com", null));
    }

    @Test
    public void adminMapperTest() {
        for (int i = 0; i < 520; i++) {
            Admin admin = new Admin(null, "admin" + i, "admin" + i, "管理员" + i, "admin@163.com", CrowdUtil.getDateTime());
            adminMapper.insert(admin);
        }

    }

    @Test
    public void dateSourceTest() throws Exception {

        Connection connection = dataSource.getConnection();
        System.out.println(connection);
    }
}
