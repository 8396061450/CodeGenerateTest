ppackage ${basePath}.${prev}.service;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;

import com.combanc.common.core.service.BaseService;
import com.combanc.common.util.PageBean;
import com.combanc.${prev}.dao.${Name}Dao;
import com.combanc.${prev}.pojo.${Name};


@Service("${name}Service")
public class ${Name}Service extends BaseService<${Name}, String>{
	@Autowired
	private ${Name}Dao ${name}Dao;
	
	public ${Name} getById(String id){
		return ${name}Dao.getById(id);
	}
	
	public void delete(String... ids){
		if(ids != null){
			for(String id:ids){
				${name}Dao.remove(id);
			}
		}
	}
	
	public PageBean queryByPage(PageBean pageBean,${Name} vo){
		
		DetachedCriteria detachedCriteria = bulidCriteria(vo);
		pageBean = eduCourseDao.queryByPage(detachedCriteria, pageBean);
		return pageBean;
	}
	
	public List<${Name}> queryList(${Name} vo){
		DetachedCriteria detachedCriteria = bulidCriteria(vo);
		return ${name}Dao.getAllByCriteria(detachedCriteria);
	}
	
	
	private DetachedCriteria bulidCriteria(${Name} vo){
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(${Name}.class);
		return detachedCriteria;
	}
}