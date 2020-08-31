package com.ugo.practice.cafe.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dto.CafeCommentDto;
import com.ugo.practice.cafe.dto.CafeDto;
import com.ugo.practice.cafe.service.CafeService;

@Controller
public class CafeController {
	
	@Autowired
	private CafeService cafeService;
	
	@RequestMapping("/cafe/list")
		public ModelAndView getList(ModelAndView mView,HttpServletRequest request) {
			cafeService.getList(request);
			mView.setViewName("cafe/list");
			return mView;
		}
	@RequestMapping("/cafe/private/insert_form")
		public String insert_form() {
			return "cafe/insert_form";
		}
	@RequestMapping("/cafe/private/insert")
		public ModelAndView insert(ModelAndView mView,CafeDto dto) {
			
			cafeService.insertContent(mView, dto);
			mView.setViewName("cafe/insert");
			return mView;
		}
	@RequestMapping("/cafe/private/detail")
		public ModelAndView detail(ModelAndView mView ,int num,HttpServletRequest request) {
			cafeService.getUserInfo(mView, num,request);
			mView.setViewName("cafe/detail");
			return mView;
		}
	@RequestMapping("/cafe/private/update_form")
		public ModelAndView update_form(ModelAndView mView ,int num,HttpServletRequest request) {
			cafeService.getUserInfo(mView, num ,request);
			mView.setViewName("cafe/update_form");
			return mView;
		}
	@RequestMapping("/cafe/private/update")
		public ModelAndView update(ModelAndView mView,CafeDto dto) {
			cafeService.updateUserInfo(mView, dto);
			mView.setViewName("cafe/update");
			return mView;
		}
	@RequestMapping("/cafe/private/delete")
		public ModelAndView delete(ModelAndView mView,@RequestParam int num,HttpSession session) {
			cafeService.deleteUser(mView, num,session);
			mView.setViewName("cafe/delete");
			return mView;
		}
	@RequestMapping("/cafe/private/comment_insert")
		public ModelAndView insert_comment(HttpServletRequest request,ModelAndView mView,@RequestParam int ref_group){
			cafeService.insertComment(mView,request);
			mView.setViewName("redirect:/cafe/private/detail.do?num="+ref_group);
			return mView;
		}
	@RequestMapping(value="/cafe/private/comment_delete",method = RequestMethod.GET)
	public ModelAndView delete_comment(int num,ModelAndView mView,@RequestParam int ref_group){
			cafeService.deleteComment(mView,num);
			mView.setViewName("redirect:/cafe/private/detail.do?num="+ref_group);
			return mView;
		}
	@RequestMapping(value="/cafe/private/comment_update",method = RequestMethod.POST)
	public ModelAndView comment_update(ModelAndView mView,CafeCommentDto dto){
			cafeService.updateComment(mView, dto);
			mView.setViewName("redirect:/cafe/private/detail.do?num="+dto.getRef_group());
			return mView;
		}
	}
	
	
