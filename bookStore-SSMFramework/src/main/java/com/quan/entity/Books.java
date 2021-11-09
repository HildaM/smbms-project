package com.quan.entity;

/**
 * @ClassName: Books
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 8:58
 */
public class Books {
    private int bookID;
    private String bookName;
    private String detail;
    private int bookCounts;

    public Books() {
    }

    public Books(int bookID, String bookName, String detail, int bookCounts) {
        this.bookID = bookID;
        this.bookName = bookName;
        this.detail = detail;
        this.bookCounts = bookCounts;
    }

    public int getBookID() {
        return bookID;
    }

    public void setBookID(int bookID) {
        this.bookID = bookID;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public int getBookCounts() {
        return bookCounts;
    }

    public void setBookCounts(int bookCounts) {
        this.bookCounts = bookCounts;
    }
}
