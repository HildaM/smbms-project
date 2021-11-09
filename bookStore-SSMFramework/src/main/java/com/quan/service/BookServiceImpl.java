package com.quan.service;

import com.quan.dao.BookMapper;
import com.quan.entity.Books;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName: BookServiceImpl
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 9:20
 */

@Service
public class BookServiceImpl implements BookService{
    // service层调用dao层
    private BookMapper bookMapper;

    public void setBookMapper(BookMapper bookMapper) {
        this.bookMapper = bookMapper;
    }


    @Override
    public int addBook(Books books) {
        return bookMapper.addBook(books);
    }

    @Override
    public int deleteBookById(int id) {
        return bookMapper.deleteBookById(id);
    }

    @Override
    public int updateBook(Books books) {
        return bookMapper.updateBook(books);
    }

    @Override
    public Books queryBookById(int id) {
        return bookMapper.queryBookById(id);
    }

    @Override
    public List<Books> queryAllBook() {
        return bookMapper.queryAllBook();
    }
}
