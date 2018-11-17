import com.baomidou.mybatisplus.enums.DBType;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.DbType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class testMP {

    @Autowired
    DataSource ds;

    @Test
    public void CreateCode(){
        //1全局设置
        GlobalConfig config = new GlobalConfig();
        config.setActiveRecord(false)//设置是否支持AR模式
                    .setAuthor("hibian")//作者
        .setOutputDir("G:\\IDEAprojects\\SSMTest\\src\\main\\java")//设置输出路径
        .setFileOverride(true)//设置是否覆盖
        .setIdType(IdType.AUTO)//主键策略:自增
        .setServiceName("%sServce").setBaseResultMap(true).setBaseColumnList(true);

        //2数据源设置
        DataSourceConfig dsConfig = new DataSourceConfig();
        dsConfig.setDbType(DbType.MYSQL)//设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl("jdbc:mysql://localhost:3306/didadb?useSSL=false&serverTimezone=UTC&charset=UTF-8")
                .setUsername("root")
                .setPassword("chenxin");

        //3策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)//全局大写命名
                .setDbColumnUnderline(true)//指定表名 字段名是否使用下划线
                .setNaming(NamingStrategy.underline_to_camel)//数据库表映射到实体类的策略
                .setTablePrefix("t_")//表前缀
                .setInclude("t_student"); //生成项目需要的的表

        //4包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent("com.hibian")
                .setMapper("mapper")
                .setService("servce")
                .setController("web")
                .setEntity("bean")
                .setXml("mapper.mapper");

        //5整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(config)
                .setDataSource(dsConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        //6执行
        autoGenerator.execute();
    }
}
