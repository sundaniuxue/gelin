package com.glsw.gelin.web;

import com.glsw.gelin.po.Type;
import com.glsw.gelin.service.BlogService;
import com.glsw.gelin.service.TypeService;
import com.glsw.gelin.vo.BlogQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @program: gelin
 * @description:
 * @author: 作者
 * @create: 2020-12-17 11:05
 */
@Controller
public class TypeShowController {

    @Autowired
    private TypeService typeService;


    @Autowired
    private BlogService blogService;


    @GetMapping("/types/{id}")
    public String types(@PageableDefault(size = 10,sort = {"updateTime"},direction = Sort.Direction.DESC) Pageable pageable,
                        @PathVariable Long id, Model model){

        List<Type> types = typeService.listTypeTop(10000);
        if(types.size() != 0){
            if (id == -1){
                id = types.get(0).getId();
            }
        }
        BlogQuery blogQuery = new BlogQuery();
        blogQuery.setTypeId(id);
        model.addAttribute("types",types);
        model.addAttribute("page",blogService.listBlog(pageable,blogQuery));
        model.addAttribute("activeTypeId",id);
        return "types";
    }
}
