package com.quan.service;

import com.quan.entity.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @ClassName: BookService
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 9:19
 */
public interface BookService {
    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBookById(int id);

    // 更新一本书
    int updateBook(Books book);

    // 查询一本书
    Books queryBookById(int id);

    // 查询全部书
    List<Books> queryAllBook();
}
