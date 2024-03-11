package com.example.ecom1.Service;

import com.example.ecom1.Model.Book;
import com.example.ecom1.Model.Member;
import com.example.ecom1.Repository.BookRepository;
import com.example.ecom1.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    private MemberRepository memberRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public void addBookToMember(Long memberId, Long bookId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        Optional<Book> bookOptional = bookRepository.findById(bookId);

        if (memberOptional.isPresent() && bookOptional.isPresent()) {
            Member member = memberOptional.get();
            Book book = bookOptional.get();

            member.getBooks().add(book);
            memberRepository.save(member);
        } else {
            throw new RuntimeException("Member or book not found");
        }
    }

    public void addBook(Book book) {
        bookRepository.save(book);
    }



}

