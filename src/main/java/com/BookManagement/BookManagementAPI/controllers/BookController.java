package com.BookManagement.BookManagementAPI.controllers;


import com.BookManagement.BookManagementAPI.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final List<Book> books;

    /*public BookController(List<Book> books) {
        this.books = books;
    }
*/
    @PostMapping
    public ResponseEntity <Book> createBook(@RequestBody Book book){
        books.add(book);
        return ResponseEntity.ok(book);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getBook(){
        return ResponseEntity.ok(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable long id){

        Book book = (Book) books.stream()
                .filter(bk -> bk.getId() == id)
                .findFirst()
                .orElse(null);

        if (book == null){
            return ResponseEntity.notFound().build();
            //return bk.getId().equals(id);
        }

        return ResponseEntity.ok(book);
    }


    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable long id){
        Optional<Book> optionalBook = books.stream().filter(bk -> {
            return bk.getId().equals(id);
        }).findFirst();

        Book book;

        if (optionalBook.isPresent()){
            book = optionalBook.get();
        }else {
            book = new Book();
        }

        books.remove(book);
    }


    @PutMapping
    public ResponseEntity<Book> updateBook(@RequestBody Book updateBook){

        books.stream().map(book -> {
            if (book.getId().equals(updateBook.getId())) {
                book.setTitle(updateBook.getTitle());
                book.setAuthor(updateBook.getAuthor());
            }
            return book;
        });
        return ResponseEntity.ok(updateBook);
    }


}
