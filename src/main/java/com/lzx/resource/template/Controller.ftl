package ${basePath}.${prev}.controller;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.combanc.common.core.CommonConstant;
import com.combanc.common.core.annotation.Token;
import com.combanc.common.core.controller.BaseController;
import com.combanc.common.util.PageBean;
import com.combanc.${prev}.pojo.${Name};
import com.combanc.${prev}.service.${Name}Service;

@Controller
@RequestMapping("/${name}")
public class ${Name}Controller extends BaseController{

    @Autowired
	private ${Name}Service ${name}Service;
	
	private String LIST_ACTION = "redirect:/${name}/index";
	
	@RequestMapping(value="index")
	public ModelAndView index(Integer currentPage,Integer pageSize,HttpServletRequest request,${Name} vo){
		ModelAndView mav = new ModelAndView("/${prev}/${name}/index");
		PageBean pageBean = new PageBean();
		if(currentPage != null && pageSize != null){
			pageBean.setCurrentPage(currentPage);
			pageBean.setPageSize(pageSize);
		}
		PageBean pb = ${name}Service.queryByPage(pageBean,vo);
		mav.addObject("pageBean", pb);
		mav.addObject("vo", vo);
		mav.addObject("url", "/${name}/index"+this.getSessionObject(request, "menuType"));//按钮标签中使用
		return mav;
	}
	
	@RequestMapping("/toAdd")
	@Token(save=true)
	public String toAdd(){
		return "/${prev}/${name}/create";
	}
	
	@RequestMapping("/add")
	@Token(remove=true)
	public ModelAndView add(${Name} vo,String saveType,RedirectAttributes redAttr){
		PubUsers user = getSessionUser(request);
		users.setCreaterId(user.getUserId());
		users.setCreaterName(user.getUserTrueName());
		Timestamp time = new Timestamp(System.currentTimeMillis());
		vo.setCreaterTime(time);
		${name}Service.save(vo);
		redAttr.addFlashAttribute(CommonConstant.MESSAGE, getMessage("msg.add.success"));
		if(CommonConstant.SAVE_ADD.equals(saveType)){
			return new ModelAndView("redirect:/${name}/toAdd");
		}else{
			return new ModelAndView(LIST_ACTION);
		}
	}
	
	@RequestMapping(value="/{id}/edit")
	public ModelAndView toEdit(@PathVariable String id,${Name} edu,Integer currentPage,Integer pageSize){
		ModelAndView mav = new ModelAndView("/${prev}/${name}/update");
		${Name} vo = ${name}Service.getById(id);
		mav.addObject("vo", vo);
		mav.addObject("pageSize", pageSize);
		mav.addObject("currentPage", currentPage);
		return mav;
	}
	
	
	@RequestMapping("update")
	public ModelAndView update(${Name} vo,RedirectAttributes redAttr,Integer currentPage,Integer pageSize){
		${name}seService.update(vo);
		redAttr.addFlashAttribute(CommonConstant.MESSAGE, getMessage("msg.update.success"));
		ModelAndView mav = new ModelAndView();
		mav.addObject("pageSize", pageSize);
		mav.addObject("currentPage", currentPage);
		mav.setViewName(LIST_ACTION);
		return mav;
	}
	
	
	@RequestMapping(value="/{id}/detail")
	public ModelAndView detail(@PathVariable String id){
		ModelAndView mav = new ModelAndView("/edu/course/detail");
		${Name} vo = ${name}Service.getById(id);
		mav.addObject("vo", vo);
		return mav;
	}
	
	
	
	@RequestMapping("delete")
	public ModelAndView delete(RedirectAttributes redAttr,Integer currentPage,Integer pageSize,${Name} vo,String... id){
		${name}Service.delete(id);
		request.setAttribute(CommonConstant.MESSAGE, getMessage("msg.delete.success"));
		return index(currentPage, pageSize, request, vo);
	}
	
}

