package com.example.demo.ecommerce.Paging;


import lombok.Getter;

@Getter
public class EzenPaging {
	private int pageNo; //현재 페이지 번호
	private int pageSize; // 한페이지당 보여지는 글의 갯수
	private int totalCount; // 총 데이터 갯수
	
	
	private int startNo; //현재 페이지 게시글 시작 번호
	private int startPage; // 현재 기준 페이지 인덱스 시작점 
	private int endPage; // 현재 기준 페이지 인덱스 끝나는점
	private int totalPage; // 마지막 페이지 인덱스
	
	
	public EzenPaging(int pageNo, int pageSize, int totalCount, int pageIndexLength) {
		
		this.pageSize = pageSize;
		this.totalCount = totalCount;
		
		totalPage = (totalCount-1)/pageSize + 1; // 총 페이지 수 
		
		if(pageNo < 1) {  // pageNo 가 1보다 작으면 1로 초기화
			this.pageNo = 1;
		}
		else if(pageNo > totalPage) {
			this.pageNo = totalPage; // pageNo가 totalPage보다 크면 totalPage로 초기화
		}
		else {
			this.pageNo = pageNo; // 해당하지 않으면 그대로 대입
		}
	
		this.startNo = (this.pageNo-1)*pageSize; // 목록 시작 번호
		startPage = (this.pageNo-1)/pageIndexLength*pageIndexLength +1;  // 페이지 범위 시작점, pageNo가 1~10까지는 startPage가 1, 11~20 까지는 11
		endPage = startPage+(pageIndexLength-1); // 페이지 범위 끝점
		this.endPage = this.endPage>totalPage? totalPage:this.endPage; // 끝점이 totalPage보다 큰경우 totalPage로 초기화
	}
}