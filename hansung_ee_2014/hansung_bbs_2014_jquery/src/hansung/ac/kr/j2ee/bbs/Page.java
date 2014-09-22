package hansung.ac.kr.j2ee.bbs;

public class Page {
	private int pageNum, count, pageSize, pageBlock;
	private int startRow, endRow;
	private int totalPage, startPage, endPage;
	private StringBuffer sb;
	
	public Page(int pageNum, int count, int pageSize, int pageBlock){
		this.pageNum = pageNum;
		this.count = count;
		this.pageSize = pageSize;
		this.pageBlock = pageBlock;
		
		paging();
		makeLink();
	}

	public void paging() {
		/*
		 * - 총 page 수 구하기
		 * 1. 총 게시물수 / 한 페이지에 들어갈 게시물 수 -> 소수점를 가진 결과물 나옴
		 * 2. 소수점 결과물을 올림
		 * 3. 이를 정수형으로 형변환
		 * 
		 * - startRow 구하기
		 * 1. 현재 pageNum에서 1을 뺌 -> 2 page인 경우에는 1x 부터 시작되어 지므로
		 * 2. 연산된 pageNum에서 한 페이지에 들어갈 게시물 수를 곱함
		 * 3. 해당 자리수에서 1를 더함 -> 2page인 경우 10 + 1 = 11~
		 * 
		 * - endRow 구하기
		 * 1. 현재 pageNum에서 한 페이지에 들어갈 게시물 수 만큼 곱함
		 * 
		 * - 결과
		 *  startRow ~ endRow : (2page인 경우) 11 ~ 20
		 *  해당 page에서 다음과 같이 게시물이 표현되어짐
		 */
		totalPage = (int)Math.ceil((double)count/pageSize);
		startRow = ((pageNum -1) * pageSize) + 1;
		endRow = pageNum * pageSize;
		
		/*
		 * - startPage 구하기
		 * 1. 현재 pageNum에서 1을 뺌 -> pageNum가 20인 경우 11~20의 page Index를 가지기 위해서
		 * 2. 연산된 pageNum에서 표현되기 원하는 page 단위만큼으로 나눔 -> 19 / 10 = 1.9
		 * 3. 정수형으로 형변환 -> (int)1.9 = 1
		 * 4. 형변환된 결과에 표현되기 원하는 page 단위만큼 곱함 -> 1 * 10 = 10
		 * 5. 이후 1을 더함 -> 10 + 1 = 11
		 * 
		 * -endPage 구하기
		 * 1. 시작 page에서 1을 뺌 -> 11 - 1 = 10
		 * 2. 연산된 결과에서 표현되기 원하는 page 단위만큼 더함 -> 10 + 10 = 20
		 * 
		 * - 결과
		 *  startPage ~ endPage : (현재 pageNum가 20인 경우) 11 ~ 20
		 *  다음과 같이 page index란에 표현되어짐
		 */
		startPage = (int)((pageNum -1)/pageBlock) * pageBlock + 1;
		endPage = pageBlock + startPage - 1;
		
		//마지막 page가 totalPage보다 큰 경우, endPage를 totalPage로 정정
		if(endPage > totalPage){
			endPage = totalPage;
		}
	}
	
	public void makeLink(){
		
		sb = new StringBuffer();
		
		if(startPage < pageBlock){
			sb.append("<img src='images/hot.jpg' width='30' height='9'>");
		}else {
			sb.append("<img src='images/hot.jpg' width='30' height='9'");
			sb.append(" onclick='location.href=\"list.bbs?pageNum=");
			sb.append(startPage - pageBlock);
			sb.append("\"' style='cursor:point'>");
		}
		
		sb.append("&nbsp; |");
		
		for(int index= startPage; index <=endPage;index++){
			if(index == pageNum){
				sb.append("&nbsp;&nbsp;<b><font color='#91B7EF'>");
				sb.append(index);
				sb.append("</font></b>");
			}else{
				sb.append("&nbsp;&nbsp;<a href='list.bbs?pageNum=");
				sb.append(index);
				sb.append("'>");
				sb.append(index);
				sb.append("</a>");
			}
		}
		
		sb.append("&nbsp; |");
		
		if(endPage < totalPage){
			sb.append("<img src='images/hot.jpg' width='30' height='9'");
			sb.append(" onclick='location.href=\"list.bbs?pageNum=");
			sb.append(startPage+pageBlock);
			sb.append("\"'  style='cursor:point'>");
		}else{
			sb.append("<img src='images/hot.jpg' width='30' height='9'>");
		}
		
	}

	public int getStartRow() {
		return startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public StringBuffer getSb() {
		return sb;
	}
}
