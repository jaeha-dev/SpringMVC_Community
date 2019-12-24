package com.demo.model;

/**
 * @Git: jaeha-dev
 * @Memo: 페이징 모델 클래스 (참고: https://to-dy.tistory.com/90)
 */
public class Criteria {
    private int page; // 현재 페이지 번호
    private int perPageNumber; // 페이지당 출력할 게시글의 개수

    /**
     * @Memo: 기본 생성자: 목록에 처음 접근할 경우, 첫 번째 페이지를 출력해야 하므로 page = 1 로 지정한다.
     */
    public Criteria() {
        this.page = 1;
        this.perPageNumber = 15;
    }

    /**
     * @Memo:
     * 게시물의 시작 번호는 1번.
     *
     * 1 페이지는  1 ~ 15번까지의 게시물,
     * 2 페이지는 16 ~ 30번까지의 게시물,
     * 3 페이지는 31 ~ 45번까지의 게시물, ...
     *
     * 현재 페이지 번호의 게시물 시작 번호
     * = ( (현재 페이지 번호 - 1) * 페이지당 출력할 게시물의 개수 ) + 1
     *
     * 현재 페이지가 1, 페이지당 출력할 게시물의 개수가 15 일 경우,
     * 현재 페이지 번호의 게시물 시작 번호 = ((1 - 1) * 15 ) + 1 = 1
     *
     * 현재 페이지가 2, 페이지당 출력할 게시물의 개수가 15 일 경우,
     * 현재 페이지 번호의 게시물 시작 번호 = ((2 - 1) * 15 ) + 1 = 16
     */
    public int getPageStart() {
        return (this.page - 1) * perPageNumber;
    }

    public int getPage() {
        return page;
    }

    /**
     * @Memo: page 값이 음수가 될 경우, 1 을 재할당한다. (1 페이지가 되도록)
     */
    public void setPage(int page) {
        if (page <= 0) {
            this.page = 1;
        } else {
            this.page = page;
        }
    }

    public int getPerPageNumber() {
        return perPageNumber;
    }

    /**
     * @Memo: 페이지당 출력될 게시글의 개수가 15개로 고정되도록 지정하는 메소드
     */
    public void setPerPageNumber(int pageCount) {
        // perPageNumber = 15
        int count = this.perPageNumber;

        if (pageCount != count) {
            this.perPageNumber = count;
        } else {
            this.perPageNumber = pageCount;
        }
    }
}