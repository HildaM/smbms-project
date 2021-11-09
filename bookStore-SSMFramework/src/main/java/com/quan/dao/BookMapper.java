package com.quan.dao;

import com.quan.entity.Books;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @ClassName: BookMapper
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 9:02
 */
public interface BookMapper {

    // 增加一本书
    int addBook(Books books);

    // 删除一本书
    int deleteBookById(@Param("bookId") int id);

    // 更新一本书
    int updateBook(Books books);

    // 查询一本书
    Books queryBookById(@Param("bookId") int id);

    // 查询全部书
    List<Books> queryAllBook();
}
