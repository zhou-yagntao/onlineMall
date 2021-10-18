package com.zyt.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.io.VFS;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Properties;


/**
 * @ProjectName: springboot_mybatis_druid_pagehelper_logf4j
 * @Package: com.chinaedu.config
 * @ClassName: MasterDataSourceConfig
 * @Author: Justin
 * @Description: 默认的数据源配置类
 * @Date: 11:15 2020/5/18
 * @Version: 1.0
 */
@Configuration
/**开启事务管理的注解*/
@EnableTransactionManagement
//扫描 Mapper 接口并容器管理
@MapperScan(basePackages = "com.zyt.mapper.*" ,sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(com.zyt.config.MasterDataSourceConfig.class);

    @Value("${spring.datasource.url}")
    private String url;
    @Value("${spring.datasource.username}")
    private String username;
    @Value("${spring.datasource.password}")
    private String password;
    @Value("${spring.datasource.driver-class-name}")
    private String driverClass;
    @Value("${spring.datasource.initialSize}")
    private int initialSize;
    @Value("${spring.datasource.min-idle}")
    private int minIdle;
    @Value("${spring.datasource.maxActive}")
    private int maxActive;
    @Value("${spring.datasource.max-wait}")
    private int maxWait;
    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private int timeBetweenEvictionRunsMillis;
    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;
    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;
    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;
    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;
    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;
    @Value("${spring.datasource.poolPreparedStatements}")
    private boolean poolPreparedStatements;
    @Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
    private int maxPoolPreparedStatementPerConnectionSize;
    /** 配置druid进行sql性能监控*/
    @Value("${spring.datasource.filters}")
    private String filters;
    @Value("{spring.datasource.connectionProperties}")
    private Properties connectionProperties;
    @Value("${spring.datasource.useGlobalDataSourceStat}")
    private boolean useGlobalDataSourceStat;
    @Value("${spring.datasources.druidLoginName}")
    private String druidLoginName;
    @Value("${spring.datasources.druidPassword}")
    private String druidPassword;
    /** 配置mybaitis */
    @Value("${mybatis.mapper-locations}")
    private  String mapperLocations;
    @Value("${mybatis.type-aliases-package}")
    private String typeAliasesPackage;
    @Value("${mybatis.config-location}")
    private String configLocation;

    /*
    *数据源,也可以使用这个注解 @ConfigurationProperties(prefix = "")
    *@Primary多数据源时必须加上，表示哪个为主
    * */
    @Bean(name = "masterDataSource")
    @Primary
    public DataSource masterDataSource(){
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(this.url);
        druidDataSource.setUsername(this.username);
        druidDataSource.setPassword(this.password);
        druidDataSource.setDriverClassName(this.driverClass);
        druidDataSource.setInitialSize(this.initialSize);
        druidDataSource.setMaxActive(this.maxActive);
        druidDataSource.setMinIdle(this.minIdle);
        druidDataSource.setMaxWait(this.maxWait);
        druidDataSource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(this.validationQuery);
        druidDataSource.setTestOnBorrow(this.testOnBorrow);
        druidDataSource.setTestOnReturn(this.testOnReturn);
        druidDataSource.setTestWhileIdle(this.testWhileIdle);
        druidDataSource.setPoolPreparedStatements(this.poolPreparedStatements);
        druidDataSource.setMaxPoolPreparedStatementPerConnectionSize(this.maxPoolPreparedStatementPerConnectionSize);
        druidDataSource.setConnectProperties(this.connectionProperties);
        druidDataSource.setUseGlobalDataSourceStat(this.useGlobalDataSourceStat);
        try{
            druidDataSource.setFilters(this.filters);
        }catch (SQLException e){
            logger.error("druid configuration initialization filter", e);
        }
        return  druidDataSource;
    }


    /*
    * 配置分页PageHelper
    * */
    @Bean(name = "pageHelper")
    public PageInterceptor pageHelper(){
        PageInterceptor pageHelper = new PageInterceptor();
        Properties properties = new Properties();
        /*
        *默认false，设置为true时，会将RowBounds第一个参数offset当成pageNum页码使用
        */
        properties.setProperty("offsetAsPageNum","true");
        /*
        *默认false，设置为true时，使用RowBounds分页会进行count查询
        **/
        properties.setProperty("rowBoundsWithCount","true");
        /*
        * 禁用合理化时，如果pageNum<1或pageNum>pages会返回空数据
        * */
        properties.setProperty("reasonable","true");
        /*
        * always总是返回PageInfo类型,check检查返回类型是否为PageInfo,none返回Page
        * */
        properties.setProperty("returnPageInfo","check");
        /*
        * 支持通过Mapper接口参数来传递分页参数
        * */
        properties.setProperty("supportMethodsArguments","false");
        /*
        * 配置数据库的方言
        * */
        properties.setProperty("helperDialect","mysql");
        pageHelper.setProperties(properties);
        return pageHelper;
    }

    /*
    * 会话工厂
    * */
    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") DataSource masterDataSource, @Qualifier("pageHelper") PageInterceptor pageHelper) throws Exception {
        logger.info("load SpringBootVFS");
        VFS.addImplClass(SpringBootVFS.class);
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(masterDataSource);
        sqlSessionFactoryBean.setPlugins(new Interceptor[]{pageHelper});
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources(this.mapperLocations);
        sqlSessionFactoryBean.setMapperLocations(resources);
        sqlSessionFactoryBean.setTypeAliasesPackage(this.typeAliasesPackage);
        sqlSessionFactoryBean.setConfigLocation(new DefaultResourceLoader().getResource(this.configLocation));
        return sqlSessionFactoryBean.getObject();
    }

    /*
    * 事物管理
    * */
    @Bean(name = "masterDataSourceTransactionManager")
    @Primary
    public DataSourceTransactionManager masterDataSourceTransactionManager(@Qualifier("masterDataSource")DataSource masterDataSource){
       return  new DataSourceTransactionManager(masterDataSource);
    }

}
