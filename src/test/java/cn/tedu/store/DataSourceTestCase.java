package cn.tedu.store;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author L-Horatio
 * @date 2020/5/24
 * @time 17:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DataSourceTestCase {

    @Autowired
    DataSource dataSource;

    @Test
    public void getConnection() throws SQLException {
        System.err.println(dataSource.getConnection());
    }
}
