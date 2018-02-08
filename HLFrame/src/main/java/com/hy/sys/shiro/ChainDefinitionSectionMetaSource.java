package com.hy.sys.shiro;


import org.apache.shiro.config.Ini;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;

import com.hy.sys.service.SysUserService;

import cn.jeeweb.core.security.utils.Md5Utils;

/**
 * 产生责任链，确定每个url的访问权限
 *
 * 
 */
public class ChainDefinitionSectionMetaSource implements FactoryBean<Ini.Section> {

    @Autowired
	private SysUserService sysUserService;
 
    //静态资源访问权限
    private String filterChainDefinitions = "/static/**=anon";

    @Override
    public Ini.Section getObject() throws Exception {
        /*List<Function> list = functionService.findAll();
        Ini ini = new Ini();
        //加载默认的url
        ini.load(filterChainDefinitions);
        Ini.Section section = ini.getSection(Ini.DEFAULT_SECTION_NAME);
        //循环Resource的url,逐个添加到section中。section就是filterChainDefinitionMap,
        //里面的键就是链接URL,值就是存在什么条件才能访问该链接
        for (Iterator<Function> it = list.iterator(); it.hasNext(); ) {
            Function function = it.next();
            //构成permission字符串
            if (StringUtils.isNotEmpty(function.getValue()) && StringUtils.isNotEmpty(function.getType())) {
                String permission = "";
                switch(function.getType()){
                    case "anon":
                        permission = "anon";
                        break;
                    case "perms":
                        permission = "perms[" + function.getPermission().getPermissionname() + "]";
                        break;
                    case "roles":
                        permission = "roles[" + function.getRole().getRolename() + "]";
                        break;
                    default:
                        break;
                }
                section.put(function.getValue(), permission);
            }

        }
        //所有资源的访问权限，必须放在最后
        section.put("/**", "authc");
        return section;*/
    	return null;
    	
    }

    @Override
    public Class<?> getObjectType() {
        return this.getClass();
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}