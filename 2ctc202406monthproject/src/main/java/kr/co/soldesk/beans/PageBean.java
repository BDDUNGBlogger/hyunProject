package kr.co.soldesk.beans;

public class PageBean {
	
    // �ּ� ������ ��ȣ
	private int min;
	// �ִ� ������ ��ȣ
	private int max;
	// ���� ��ư�� ������ ��ȣ
	private int prevPage;
	// ���� ��ư�� ������ ��ȣ
	private int nextPage;
	// ��ü ������ ����
	private int pageCnt;
	// ���� ������ ��ȣ
	private int currentPage;
	
	//contentCnt : ��ü�� ����, currentPage: ���� �� ��ȣ 
	//contentPageCnt:�������� ���� ����, paginationCnt: ������ ��ư ����
	public PageBean(int contentCnt, int currentPage, int contentPageCnt, int paginationCnt) {
		
		//���� ������ ��ȣ
		this.currentPage = currentPage;
		
		//��ü ������ ����
		pageCnt = contentCnt / contentPageCnt;
		
		//533/10=53 ... 3 53+1=54
		if(contentCnt % contentPageCnt > 0) {
			pageCnt++;
		}//if
		
		/*
		 1~10(�� �ּ� ��ȣ1 ���� �ִ� ��ȣ 10) : 1
		 11~20 : �ּ� ���� ��ȣ 11
		 21~30 : �ּ� ���� ��ȣ 21
		 
		 // ������ ���̽��� �� ��ȣ ���� (0���� ����)
		   0~9 : 0
		 10~19 : 10
		 20~29 : 20
		 
		 // ������ ��ȣ�� �����ͺ��̽��� �� ��ȣ ������ ���߱� ���� ���
		   ������ ��ȣ 1 : (1-1) * 10 = 0
		   ������ ��ȣ 2 : (2-1) * 10 = 10
		   ������ ��ȣ 3 : (3-1) * 10 = 20
		 
		 // +1�� �����Ͽ� ���� �� ��ȣ ������ ���߱�
		   ������ ��ȣ 1 : 0 + 1 = 1 (�� ��ȣ 1���� ����)
		   ������ ��ȣ 2 : 10 + 1 = 11 (�� ��ȣ 11���� ����)
		   ������ ��ȣ 3 : 20 + 1 = 21 (�� ��ȣ 21���� ����)
		 */
		
		min=((currentPage-1)/contentPageCnt)*contentPageCnt+1;
		max=min+paginationCnt -1; //1+10=11 -1
		
		//64�� => 6������(10��) 7������(4)
		if(max > pageCnt) {
			max = pageCnt;
		}
		prevPage=min-1;
		nextPage=max+1;
		
		//������ �������� �Ѿ�� �ʵ���
		if(nextPage > pageCnt) {	
			nextPage=pageCnt;
		}
		
	}//pageBean
	
	public int getMin() {
		return min;
	}
	public int getMax() {
		return max;
	}
	public int getPrevPage() {
		return prevPage;
	}
	public int getNextPage() {
		return nextPage;
	}
	public int getPageCnt() {
		return pageCnt;
	}
	public int getCurrentPage() {
		return currentPage;
	}

}
