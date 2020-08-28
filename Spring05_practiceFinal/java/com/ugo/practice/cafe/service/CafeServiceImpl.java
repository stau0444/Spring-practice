 package com.ugo.practice.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ugo.practice.cafe.dao.CafeCommentDao;
import com.ugo.practice.cafe.dao.CafeDao;
import com.ugo.practice.cafe.dto.CafeCommentDto;
import com.ugo.practice.cafe.dto.CafeDto;
import com.ugo.practice.exception.IncorrectPathException;
import com.ugo.practice.user.dao.UsersDao;

@Service
public class CafeServiceImpl implements CafeService{

	@Autowired
	private CafeDao cafeDao;
	@Autowired
	private UsersDao userDao;
	
	@Autowired
	private CafeCommentDao cafeCommentDao;
	//한 페이지에 나타낼 row 의 갯수
	final int PAGE_ROW_COUNT=5;
	//하단 디스플레이 페이지 갯수
	final int PAGE_DISPLAY_COUNT=5;

	@Override
	public void getList(HttpServletRequest request) {
		
		
		//보여줄 페이지의 번호
				int pageNum=1;
				//보여줄 페이지의 번호가 파라미터로 전달되는지 읽어와 본다.	
				String strPageNum=request.getParameter("pageNum");
				if(strPageNum != null){//페이지 번호가 파라미터로 넘어온다면
					//페이지 번호를 설정한다.
					pageNum=Integer.parseInt(strPageNum);
				}
				//보여줄 페이지 데이터의 시작 ResultSet row 번호
				int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
				//보여줄 페이지 데이터의 끝 ResultSet row 번호
				int endRowNum=pageNum*PAGE_ROW_COUNT;
				/*
					검색 키워드에 관련된 처리 
				*/
				String keyword=request.getParameter("keyword"); //검색 키워드
				String condition=request.getParameter("condition"); //검색 조건
				if(keyword==null){//전달된 키워드가 없다면 
					keyword=""; //빈 문자열을 넣어준다. 
					condition="";
				}
				//인코딩된 키워드를 미리 만들어 둔다. 
				String encodedK=URLEncoder.encode(keyword);
				
				//검색 키워드와 startRowNum, endRowNum 을 담을 FileDto 객체 생성
				CafeDto dto=new CafeDto();
				dto.setStartRowNum(startRowNum);
				dto.setEndRowNum(endRowNum);
				System.out.println("start:"+startRowNum);
				System.out.println("end:"+endRowNum);
				if(!keyword.equals("")){ //만일 키워드가 넘어온다면 
					if(condition.equals("title_content")){
						//검색 키워드를 FileDto 객체의 필드에 담는다. 
						dto.setTitle(keyword);
						dto.setContent(keyword);	
					}else if(condition.equals("title")){
						dto.setTitle(keyword);
						System.out.println(dto.getTitle());
					}else if(condition.equals("writer")){
						dto.setWriter(keyword);
						System.out.println(dto.getWriter());
					}
				}
				//파일 목록 얻어오기
				List<CafeDto> list=cafeDao.getList(dto);
				System.out.println(list.size());
				int totalRow=cafeDao.getCount(dto);
				//전체 row 의 갯수 
				System.out.println(totalRow);
				System.out.println("keyword:"+keyword);
				System.out.println("condition:"+condition);
				//전체 페이지의 갯수 구하기
				int totalPageCount=
						(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
				//시작 페이지 번호
				int startPageNum=
					1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
				//끝 페이지 번호
				int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
				//끝 페이지 번호가 잘못된 값이라면 
				if(totalPageCount < endPageNum){
					endPageNum=totalPageCount; //보정해준다. 
				}
				
				request.setAttribute("list",list);
				request.setAttribute("totalPageCount", totalPageCount);
				request.setAttribute("startPageNum", startPageNum);
				request.setAttribute("endPageNum", endPageNum);
				request.setAttribute("condition", condition);
				request.setAttribute("keyword", keyword);
				request.setAttribute("encodedK", encodedK);
		
	}

	@Override
	public void insertContent(ModelAndView mView, CafeDto dto) {
		
		boolean isInsert=cafeDao.insert(dto);
		mView.addObject("isInsert", isInsert);
	}

	@Override
	public void getUserInfo(ModelAndView mView,@RequestParam int num,HttpServletRequest request) {
		
		String keyword=request.getParameter("keyword"); //검색 키워드
		String condition=request.getParameter("condition"); //검색 조건
		if(keyword==null){//전달된 키워드가 없다면 
			keyword=""; //빈 문자열을 넣어준다. 
			condition="";
		}
		//인코딩된 키워드를 미리 만들어 둔다. 
		String encodedK=URLEncoder.encode(keyword);
		
		//글번호와 검색 키워드를 담을 CafeDto 객체 생성
		CafeDto dto=new CafeDto();
		dto.setNum(num);//글번호 담기 
		
		if(!keyword.equals("")){ //만일 키워드가 넘어온다면 
			if(condition.equals("title_content")){
				//검색 키워드를 FileDto 객체의 필드에 담는다. 
				dto.setTitle(keyword);
				dto.setContent(keyword);	
			}else if(condition.equals("title")){
				dto.setTitle(keyword);
			}else if(condition.equals("writer")){
				dto.setWriter(keyword);
			}
		}
		CafeDto userdto=cafeDao.getContent(num);
		mView.addObject("dto",userdto);
		mView.addObject("condition", condition);
		mView.addObject("keyword", keyword);
		mView.addObject("encodedK", encodedK);
		
	}

	@Override
	public void updateUserInfo(ModelAndView mView, CafeDto dto) {
		boolean isUpdate=cafeDao.update(dto);
		mView.addObject("isUpdate", isUpdate);
		mView.addObject("num", dto.getNum());
	}

	@Override
	public void deleteUser(ModelAndView mView, int num,HttpSession session) {
		String writer=cafeDao.getContent(num).getWriter();
		String id=(String)session.getAttribute("id");
		
		if(!id.equals(writer)) {
			userDao.addwarnnum(id);
			int warnnum=userDao.getUserInfo(id).getwarnnum();
			if(warnnum>3) {
				userDao.delete(id);
				System.out.println("회원 삭제됨");
				session.invalidate();
			}
			throw new IncorrectPathException("예외 경로로 요청하셨습니다");
		}
		boolean isDelete=cafeDao.delete(num);
		mView.addObject("isDelete", isDelete);
	}

	@Override
	public void insertComment(ModelAndView mView,CafeCommentDto dto,HttpSession session) {
		int num=cafeCommentDao.getSequence();
		dto.setWriter((String)session.getAttribute("id"));
		if(dto.getComment_group()==0) {
			dto.setComment_group(num);
		}
		cafeCommentDao.insert(dto);
	}
}
