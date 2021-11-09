package com.quan.controller;

import com.quan.entity.Books;
import com.quan.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @ClassName: BookController
 * @Description:
 * @author: Hilda   Hilda_quan@163.com
 * @date: 2021/11/9 18:57
 */

@Controller
@RequestMapping("/book")
public class BookController {
    // controller 调用 service 层
    @Autowired
    @Qualifier("bookServiceImpl")
    private BookService bookService;

    // 查询全部的书籍，并且返回到书籍展示页面
    @RequestMapping("/allBooks")
    public String list(Model model) {
        List<Books> booksList = bookService.queryAllBook();

        model.addAttribute("booksList", booksList);

        return "allBooks";
    }

    // 跳转到增加书籍页面
    @RequestMapping("/toAddBook")
    public String toAddBook(Model model, Books book) {
        return "addBook";
    }

    // 添加书籍的请求
    @RequestMapping("/addBook")
    public String addBook(Books book) {
        System.out.println("addBook: " + book);
        bookService.addBook(book);
        // 重定向到我们的allBook的请求
        return "redirect:/book/allBooks";
    }
}
