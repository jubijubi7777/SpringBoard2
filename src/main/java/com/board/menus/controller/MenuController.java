package com.board.menus.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.board.menus.domain.MenuDTO;
import com.board.menus.mapper.MenuMapper;

@Controller
public class MenuController {

	@Autowired
	private MenuMapper menuMapper;
	
	@RequestMapping("/Menus/List")
	public String list( Model model ) {
		
		List<MenuDTO> menuList = menuMapper.getMenuList();
		System.out.println( menuList );
		
		//                 key(jstl)   value
		model.addAttribute("menuList", menuList);
		
		return "menus/list";
		// src/main/webapp/WEB-INF/views/menus//list.jsp
	}
	
	@RequestMapping("/Menus/WriteForm")
	public String writeForm() {
		return "menus/write";
	}
	
	@RequestMapping("/Menus/Write")
	public String write( MenuDTO menuDTO, Model model ) {
		
		menuMapper.insertMenu( menuDTO );
		
		/*
		List<MenuDTO> menuList = menuMapper.getMenuList();
		model.addAttribute("menuList", menuList);
		return "menus/list"; // rd.forward(request, response)
		*/
		return  "redirect:/Menus/List"; // response.sendRedirect()
	}

    //http://localhost:9090/Menus/Delete?menu_id=MENU01
	@RequestMapping("/Menus/Delete")
	public  String delete( MenuDTO menuDTO ) {
		
		// System.out.println( menuDTO );
		menuMapper.deleteMenu( menuDTO );
		
		return "redirect:Menus/List";
	}
}

