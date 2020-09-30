package com.tds.ems.demose;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.acls.AclPermissionEvaluator;
import org.springframework.security.acls.domain.AclAuthorizationStrategy;
import org.springframework.security.acls.domain.AclAuthorizationStrategyImpl;
import org.springframework.security.acls.domain.ConsoleAuditLogger;
import org.springframework.security.acls.domain.DefaultPermissionGrantingStrategy;
import org.springframework.security.acls.domain.EhCacheBasedAclCache;
import org.springframework.security.acls.jdbc.BasicLookupStrategy;
import org.springframework.security.acls.jdbc.JdbcMutableAclService;
import org.springframework.security.acls.jdbc.LookupStrategy;
import org.springframework.security.acls.model.AclService;
import org.springframework.security.acls.model.PermissionGrantingStrategy;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true ,securedEnabled = true)
public class AclMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

	@Value("spring.datasource.driverClassName")
	private String dBdriverClass;

	@Value("spring.datasource.url")
	private String dsUrl;
	
	@Value("spring.datasource.username")
	private String user;	
	
	@Value("spring.datasource.password")
	private String pwd;	
	
    @Autowired
    private MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler;

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
    	System.out.println("inside MethodSecurityExpressionHandler");
        return defaultMethodSecurityExpressionHandler;
    }
    
    @Bean
    public MethodSecurityExpressionHandler defaultMethodSecurityExpressionHandler() {
    	System.out.println("inside defaultMethodSecurityExpressionHandler ");
        var expressionHandler = new DefaultMethodSecurityExpressionHandler();
        var permissionEvaluator = new AclPermissionEvaluator(aclService());
        expressionHandler.setPermissionEvaluator(permissionEvaluator);
        return expressionHandler;
    }
    
    @Bean
    public AclService aclService() {
    	System.out.println("inside AclService");
        JdbcMutableAclService jdbcMutableAclService = new JdbcMutableAclService(dataSource(), lookupStrategy(), aclCache());
        jdbcMutableAclService.setClassIdentityQuery("SELECT SCOPE_IDENTITY() as ScopeIdentity");
        jdbcMutableAclService.setSidIdentityQuery("SELECT SCOPE_IDENTITY() as ScopeIdentity");
        jdbcMutableAclService.setObjectIdentityPrimaryKeyQuery("select acl_object_identity.id from acl_object_identity, acl_class where acl_object_identity.object_id_class = acl_class.id and acl_class.class=? and acl_object_identity.object_id_identity = cast(? as varchar)");
        jdbcMutableAclService.setFindChildrenQuery("select obj.object_id_identity as obj_id, class.class as class from acl_object_identity obj, acl_object_identity parent, acl_class class where obj.parent_object = parent.id and obj.object_id_class = class.id and parent.object_id_identity = cast(? as varchar) and parent.object_id_class = (select id FROM acl_class where acl_class.class = ?)");
        return jdbcMutableAclService;
    }

    @Bean
    public DataSource dataSource() {
    	System.out.println("inside DataSource");
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        ds.setUrl("jdbc:sqlserver://L-156215308:53957;databaseName=testdb");
        ds.setUsername("sa");
        ds.setPassword("admin$$123");
        return ds;
    }
    
    @Bean
    public LookupStrategy lookupStrategy() {
    	System.out.println("inside LookupStrategy ");
        return new BasicLookupStrategy(dataSource(), aclCache(), aclAuthorizationStrategy(), new ConsoleAuditLogger());
    }

    @Bean
    public EhCacheBasedAclCache aclCache() {
    	System.out.println("inside aclCache");
        return new EhCacheBasedAclCache(aclEhCacheFactoryBean().getObject(), permissionGrantingStrategy(),
                aclAuthorizationStrategy());
    }
    
    @Bean
    public EhCacheFactoryBean aclEhCacheFactoryBean() {
    	System.out.println("inside aclEhCacheFactoryBean ");
        EhCacheFactoryBean ehCacheFactoryBean = new EhCacheFactoryBean();
        ehCacheFactoryBean.setCacheManager(aclCacheManager().getObject());
        ehCacheFactoryBean.setCacheName("aclCache");
        return ehCacheFactoryBean;
    }
    
    @Bean
    public EhCacheManagerFactoryBean aclCacheManager() {
    	System.out.println("inside aclCacheManager ");
        return new EhCacheManagerFactoryBean();
    }
    
    @Bean
    public PermissionGrantingStrategy permissionGrantingStrategy() {
    	System.out.println("inside permissionGrantingStrategy");
        return new DefaultPermissionGrantingStrategy(new ConsoleAuditLogger());
    }
    
    @Bean
    public AclAuthorizationStrategy aclAuthorizationStrategy() {
    	System.out.println("inside aclAuthorizationStrategy");
        return new AclAuthorizationStrategyImpl((new SimpleGrantedAuthority("ROLE_ADMIN")));
    }
}
