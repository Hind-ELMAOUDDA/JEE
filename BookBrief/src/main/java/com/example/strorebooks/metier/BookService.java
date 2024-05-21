package com.example.strorebooks.metier;

import com.example.strorebooks.dao.entities.Book;
import com.example.strorebooks.dao.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bRepo;

    public void save(Book b) {
        bRepo.save(b);
    }

    public List<Book> getAllBook(){
        return bRepo.findAll();
    }

    public Book getBookById(int id) {
        return bRepo.findById(id).get();
    }
    public void deleteById(int id) {
        bRepo.deleteById(id);
    }
    public Page<Book> getBooksByPage(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return bRepo.findAll(pageable);
    }

}
