package com.demo.service;

import com.demo.model.Criteria;

/**
 * @Git: jaeha-dev
 * @Name: 페이징 서비스 클래스
 * (참고: https://to-dy.tistory.com/90)
 */
public class PageMaker {
    private Criteria criteria; // Criteria

    private int totalCount; // 전체 게시물 개수
    private int startPage; // 시작 페이지 번호
    private int endPage; // 끝 페이지 번호

    private boolean previous; // 이전 페이지 버튼의 생성 여부
    private boolean next; // 다음 페이지 버튼의 생성 여부
    private int displayPageNumber = 5; // 출력할 페이지 개수

    /**
     * @Memo: 페이지 버튼을 생성하기 위한 계산식 메소드
     */
    private void calcData() {

        /**
         * @Memo:
         * criteria.getPage() : 현재 페이지 번호
         * criteria.getPerPageNum() : 페이지당 출력할 게시물의 개수
         *
         * 끝 페이지 번호 = (현재 페이지 번호 / 출력할 페이지 번호의 개수) * 출력할 페이지 번호의 개수
         * endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNum) * displayPageNum);
         *
         * 현재 페이지 번호가 1, 출력할 페이지 번호의 개수가 5 일 경우, (Math.ceil() : 소수점 이하 올림)
         * endPage = Math.ceil(1 / 5) * 5 = 5
         */
        endPage = (int) (Math.ceil(criteria.getPage() / (double) displayPageNumber) * displayPageNumber);

        /**
         * @Memo: 끝 페이지 번호 = 전체 게시물 개수 / 페이지당 출력할 게시믈의 개수
         */
        int tempEndPage = (int) (Math.ceil(totalCount / (double) criteria.getPerPageNumber()));
        if (endPage > tempEndPage) {
            endPage = tempEndPage;
        }

        /**
         * @Memo: 시작 페이지 번호 = (끝 페이지 번호 - 출력할 페이지 번호의 개수) + 1
         */
        startPage = (endPage - displayPageNumber) + 1;
        if (startPage <= 0) {
            startPage = 1;
        }

        /**
         * @Memo:
         * 이전 페이지 버튼의 생성 여부 = 시작 페이지 번호 == 1 ? false : true
         * (시작 페이지 번호가 1 이 아닐 경우, 생성)
         *
         * 다음 페이지 버튼의 생성 여부 = 끝 페이지 번호 * 페이지당 출력할 게시물의 개수 >= 전체 게시물의 수 ? false : true
         */
        previous = startPage == 1 ? false : true;
        next = endPage * criteria.getPerPageNumber() >= totalCount ? false : true;
    }

    public Criteria getCriteria() {
        return criteria;
    }

    public void setCriteria(Criteria criteria) {
        this.criteria = criteria;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        this.calcData(); // 전체 게시물 개수를 설정할 때 calcData() 메소드를 호출하여 계산한다.
    }

    public int getStartPage() {
        return startPage;
    }

    public void setStartPage(int startPage) {
        this.startPage = startPage;
    }

    public int getEndPage() {
        return endPage;
    }

    public void setEndPage(int endPage) {
        this.endPage = endPage;
    }

    public boolean isPrevious() {
        return previous;
    }

    public void setPrevious(boolean previous) {
        this.previous = previous;
    }

    public boolean isNext() {
        return next;
    }

    public void setNext(boolean next) {
        this.next = next;
    }

    public int getDisplayPageNumber() {
        return displayPageNumber;
    }

    public void setDisplayPageNumber(int displayPageNumber) {
        this.displayPageNumber = displayPageNumber;
    }
}
