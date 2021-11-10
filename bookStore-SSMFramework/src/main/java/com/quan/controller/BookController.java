package com.quan.controller;

import com.quan.entity.Books;
import com.quan.service.BookService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
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

    // 跳转到修改页面
    @RequestMapping("/toUpdate/{bookID}")
    public String toUpdateBook(@PathVariable int bookID, Model model) {
        Books book = bookService.queryBookById(bookID);
        model.addAttribute("book", book);
        return "updateBook";
    }

    // 修改书籍
    @PostMapping("/updateBook")
    public String updateBook(Books book) {
        System.out.println("updateBook 修改：" + book.toString());
        bookService.updateBook(book);
//        if (updateCounts > 0) {
//            System.out.println("添加成功！");
//        }
//        List<Books> booksList = bookService.queryAllBook();
//        for (Books books : booksList) {
//            System.out.println(books);
//        }
        // return "allBooks";
        return "redirect:/book/allBooks";
    }

    // 删除书籍
    @RequestMapping("/deleteBook/{bookID}")
    public String deleteBook(@PathVariable int bookID) {
        bookService.deleteBookById(bookID);
        return "redirect:/book/allBooks";
    }

    // 查询书籍
    @PostMapping("/queryBook")
    public String queryBookName(String bookName, Model model) {
        Books book = bookService.queryBookName(bookName);
        List<Books> booksList = new ArrayList<>();
        booksList.add(book);
        model.addAttribute("booksList", booksList);
        return "allBooks";
    }
}
