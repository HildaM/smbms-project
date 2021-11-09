package com.quan.dao;

import com.quan.entity.Books;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * @ClassName: BookMapperImpl
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 9:57
 */
public class BookMapperImpl extends SqlSessionDaoSupport implements BookMapper {
    @Override
    public int addBook(Books books) {
        return 0;
    }

    @Override
    public int deleteBookById(int id) {
        return 0;
    }

    @Override
    public int updateBook(Books books) {
        return 0;
    }

    @Override
    public Books queryBookById(int id) {
        return null;
    }

    @Override
    public List<Books> queryAllBook() {
        return null;
    }
}
