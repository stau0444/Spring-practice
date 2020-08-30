 package com.ugo.practice.cafe.service;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
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
				request.setAttribute("pageNum", pageNum);
		
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
		
		/* 아래는 댓글 페이징 처리 관련 비즈니스 로직 입니다.*/
		
		//전체 row 의 갯수를 읽어온다.
		//자세히 보여줄 글의 번호가 ref_group  번호 이다. 
		int totalRow=cafeCommentDao.getCount(num);

		//보여줄 페이지의 번호(만일 pageNum 이 넘어오지 않으면 가장 마지막 페이지)
		String strPageNum=request.getParameter("pageNum");
		//전체 페이지의 갯수 구하기
		int totalPageCount=
						(int)Math.ceil(totalRow/(double)PAGE_ROW_COUNT);
		//일단 마지막 페이지의 댓글 목록을 보여주기로 하고 
		int pageNum=totalPageCount;
		//만일 페이지 번호가 넘어온다면
		if(strPageNum!=null) {
			//넘어온 페이지에 해당하는 댓글 목록을 보여주도록 한다. 
			pageNum=Integer.parseInt(strPageNum);
		}
		//보여줄 페이지 데이터의 시작 ResultSet row 번호
		int startRowNum=1+(pageNum-1)*PAGE_ROW_COUNT;
		//보여줄 페이지 데이터의 끝 ResultSet row 번호
		int endRowNum=pageNum*PAGE_ROW_COUNT;
		
		
		//시작 페이지 번호
		int startPageNum=
			1+((pageNum-1)/PAGE_DISPLAY_COUNT)*PAGE_DISPLAY_COUNT;
		//끝 페이지 번호
		int endPageNum=startPageNum+PAGE_DISPLAY_COUNT-1;
		//끝 페이지 번호가 잘못된 값이라면 
		if(totalPageCount < endPageNum){
			endPageNum=totalPageCount; //보정해준다. 
		}
		
		// CafeCommentDto 객체에 위에서 계산된 startRowNum 과 endRowNum 을 담는다.
		CafeCommentDto commentDto=new CafeCommentDto();
		commentDto.setStartRowNum(startRowNum);
		commentDto.setEndRowNum(endRowNum);
		//ref_group 번호도 담는다.
		commentDto.setRef_group(num);
		
		//DB 에서 댓글 목록을 얻어온다.
		List<CafeCommentDto> commentList=cafeCommentDao.getList(commentDto);
		//request 에 담아준다.
		request.setAttribute("commentList", commentList);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("pageNum", pageNum);
		request.setAttribute("num", num);
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
	public void insertComment(ModelAndView mView,HttpServletRequest request) {
		String content=request.getParameter("content");
		int ref_group=Integer.parseInt(request.getParameter("ref_group"));
		String target_id=request.getParameter("target_id");
		String writer=(String)request.getSession().getAttribute("id");
		String comment_group=request.getParameter("comment_group");
		int seqNum=cafeCommentDao.getSequence();
		
		CafeCommentDto dto=new CafeCommentDto();
		dto.setContent(content);
		dto.setRef_group(ref_group);
		dto.setTarget_id(target_id);
		dto.setWriter(writer);
		dto.setNum(seqNum);
		
		if(comment_group==null) {
			dto.setComment_group(seqNum);
		}else {
			dto.setComment_group(Integer.parseInt(comment_group));
		}
		cafeCommentDao.insert(dto);
	}

	@Override
	public void deleteComment(ModelAndView mView, int num) {
		System.out.println("삭제됨");
		cafeCommentDao.delete(num);
	}

	
}
