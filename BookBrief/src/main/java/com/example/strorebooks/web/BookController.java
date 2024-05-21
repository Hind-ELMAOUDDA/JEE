package com.example.strorebooks.web;

import com.example.strorebooks.dao.entities.Book;
import com.example.strorebooks.dao.entities.MyBookList;
import com.example.strorebooks.metier.BookService;
import com.example.strorebooks.metier.MyBookListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Controller
public class BookController {



    @Autowired
    private BookService bookService;

    @Autowired
    private MyBookListService myBookListService;

    @GetMapping("/")
    public String home() {
        return "home";
    }

    @GetMapping("/book_register")
    public String bookRegister() {
        return "bookRegister";
    }

    //@GetMapping("/available_books")
   // public ModelAndView getAllBook() {
      //  List<Book> list=bookService.getAllBook();
//		ModelAndView m=new ModelAndView();
//		m.setViewName("bookList");
//		m.addObject("book",list);
      //  return new ModelAndView("bookList","book",list);
    //}
    @GetMapping("/available_books")
    public ModelAndView getAllBook(@RequestParam(defaultValue = "0") int page) {
        Page<Book> bookPage = bookService.getBooksByPage(page, 5); // Assuming 5 books per page
        ModelAndView mav = new ModelAndView("bookList");
        mav.addObject("bookPage", bookPage);
        mav.addObject("currentPage", page);
        return mav;
    }

    @PostMapping("/save")
    public String addBook(@ModelAttribute Book b) {
        bookService.save(b);
        return "redirect:/available_books";
    }
    @GetMapping("/my_books")
    public String getMyBooks(Model model)
    {
        List<MyBookList>list=myBookListService.getAllMyBooks();
        model.addAttribute("book",list);
        return "myBooks";
    }
    @RequestMapping("/mylist/{id}")
    public String getMyList(@PathVariable("id") int id) {
        Book b=bookService.getBookById(id);
        MyBookList mb=new MyBookList(b.getId(),b.getName(),b.getAuthor(),b.getGenre(),b.getSummary());
        myBookListService.saveMyBooks(mb);
        return "redirect:/my_books";
    }

    @RequestMapping("/editBook/{id}")
    public String editBook(@PathVariable("id") int id,Model model) {
        Book b=bookService.getBookById(id);
        model.addAttribute("book",b);
        return "bookEdit";
    }
    @RequestMapping("/deleteBook/{id}")
    public String deleteBook(@PathVariable("id")int id) {
        bookService.deleteById(id);
        return "redirect:/available_books";
    }

    @GetMapping("/login")
    public String login(Model model){
        return "login";
    }

}
