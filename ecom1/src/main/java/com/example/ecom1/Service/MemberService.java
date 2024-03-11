package com.example.ecom1.Service;

import com.example.ecom1.Model.Book;
import com.example.ecom1.Model.Member;
import com.example.ecom1.Repository.BookRepository;
import com.example.ecom1.Repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional; // Import the Optional class

@Service
public class MemberService {
    @Autowired
    private MemberRepository memberRepository;
    private BookRepository bookRepository;
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public List<Book> getBooksForMember(Long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            return new ArrayList<>(member.getBooks());
        } else {
            throw new RuntimeException("Member not found with id: " + memberId);
        }
    }
    public void addBookToMember(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new RuntimeException("Member not found with id: " + memberId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book not found with id: " + bookId));

        member.getBooks().add(book);
        book.getMembers().add(member);

        memberRepository.save(member);
    }

    public void deleteBookFromMember(Long memberId, Long bookId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isPresent()) {
            Member member = memberOptional.get();
            Optional<Book> bookOptional = member.getBooks().stream()
                    .filter(book -> book.getId().equals(bookId))
                    .findFirst();

            bookOptional.ifPresent(book -> member.getBooks().remove(book));
            memberRepository.save(member);
        } else {
            throw new RuntimeException("Member not found with id: " + memberId);
        }
    }

    public void addUser(Member member) {
        //System.out.println("added");
        memberRepository.save(member);
    }

    public String authenticate(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member != null && member.getPassword().equals(password)) {
            return "Success";
        } else {
            return "Incorrect email or password";
        }
    }

    public Member getMember(String email) {
        return memberRepository.findByEmail(email);
    }
}
