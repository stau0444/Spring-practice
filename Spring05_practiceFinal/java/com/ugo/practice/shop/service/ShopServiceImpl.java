package com.ugo.practice.shop.service;

import java.io.File;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ugo.practice.shop.dao.ShopDao;
import com.ugo.practice.shop.dto.ShopDto;

@Service
public class ShopServiceImpl implements ShopService{

	@Autowired
	private ShopDao shopDao;
	//한 페이지에 나타낼 row 의 갯수
	final int PAGE_ROW_COUNT=9;
	//하단 디스플레이 페이지 갯수
	final int PAGE_DISPLAY_COUNT=5;

	@Override
	public void getList(HttpServletRequest request) {
		
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
		
		ShopDto dto=new ShopDto();
		dto.setStartRowNum(startRowNum);
		dto.setEndRowNum(endRowNum);
		System.out.println("start:"+startRowNum);
		System.out.println("end:"+endRowNum);
		if(!keyword.equals("")){ //만일 키워드가 넘어온다면 
			if(condition.equals("name")){
				//검색 키워드를 FileDto 객체의 필드에 담는다. 
				dto.setName(keyword);	
			}
		}
		int totalRow=shopDao.getMaxRowNum(dto);
		List<ShopDto> list=shopDao.getList(dto);
		//파일 목록 얻어오기
		System.out.println(list.size());
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
		
		System.out.println(totalPageCount);
		System.out.println(startPageNum);
		System.out.println(endPageNum);
		System.out.println(condition);
		System.out.println(keyword);
		System.out.println(encodedK);
		int listSize=list.size();
		
		request.setAttribute("listSize", listSize);
		request.setAttribute("totalRow", totalRow);
		request.setAttribute("list",list);
		request.setAttribute("totalPageCount", totalPageCount);
		request.setAttribute("startPageNum", startPageNum);
		request.setAttribute("endPageNum", endPageNum);
		request.setAttribute("condition", condition);
		request.setAttribute("keyword", keyword);
		request.setAttribute("encodedK", encodedK);
	
		}
	
	@Transactional
	@Override
	public void buy(HttpServletRequest request, ModelAndView mView) {
		String id=(String)request.getSession().getAttribute("id");
		//1. 파라미터로 전달되는 구입할 상품 번호
		int num=Integer.parseInt(request.getParameter("num"));
		//2. 상품의 가격을 얻어온다.
		int price=shopDao.getPrice(num);
		//3. 상품의 가격만큼 계좌 잔액을 줄인다.
		ShopDto dto=new ShopDto();
		dto.setId(id);
		dto.setPrice(price);
		boolean isMinusMoney=shopDao.minusMoney(dto);
//		if(isMinusMoney==false) {//만일 0 이 나오면 
//			throw new AccountEmptyException("계좌 잔액이 부족합니다!");
//		}
		System.out.println("money:"+isMinusMoney);
		//4. 가격의 10% 를 포인트로 적립한다.
		shopDao.plusPoint(dto);
		//5. 재고의 갯수를 1 줄인다.
		boolean isMinusCount=shopDao.minusCount(num);
		System.out.println("count:"+isMinusCount);
		//가격의 1/10만큼 포인트를 올린다.
		
	}

	@Override
	public void insertProduct(ModelAndView mView, ShopDto dto) {
		System.out.println("이미지경로"+dto.getImage());
		boolean isInserted=shopDao.insert(dto);
		mView.addObject("isInserted", isInserted);
	}

	@Override
	public Map<String, Object> saveProductImage(HttpServletRequest request, MultipartFile mFile) {
		//원본 파일명
				String orgFileName=mFile.getOriginalFilename();
				// webapp/upload 폴더 까지의 실제 경로(서버의 파일시스템 상에서의 경로)
				String realPath=request.getServletContext().getRealPath("/upload");
				//저장할 파일의 상세 경로
				String filePath=realPath+File.separator;
				//디렉토리를 만들 파일 객체 생성
				File upload=new File(filePath);
				if(!upload.exists()) {//만일 디렉토리가 존재하지 않으면 
					upload.mkdir(); //만들어 준다.
				}
				//저장할 파일 명을 구성한다.
				String saveFileName=
						System.currentTimeMillis()+orgFileName;
				try {
					//upload 폴더에 파일을 저장한다.
					mFile.transferTo(new File(filePath+saveFileName));
					System.out.println(filePath+saveFileName);
				}catch(Exception e) {
					e.printStackTrace();
				}
				//Map 에 업로드된 이미지 파일의 경로를 담아서 리턴한다
				Map<String, Object> map=new HashMap<String, Object>();
				map.put("imageSrc","/upload/"+saveFileName);
				
				return map;
	}
}
