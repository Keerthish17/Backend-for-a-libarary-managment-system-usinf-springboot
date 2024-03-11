// MemberController.java - REST controller for managing members

package com.example.ecom1.Controller;

import com.example.ecom1.Model.Book;
import com.example.ecom1.Model.Member;
import com.example.ecom1.Service.BookService;
import com.example.ecom1.Service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/members")
public class MemberController {
    @Autowired
    private MemberService memberService;
    private BookService bookService;

    @PostMapping("/add/member")
    public void addUser(@RequestBody Member member){
        memberService.addUser(member);
    }

    @GetMapping("/get")
    public List<Member> getAllMembers() {
        return memberService.getAllMembers();
    }

    @GetMapping("/get/{email}")
    public ResponseEntity<Member> getMember(@PathVariable String email) {
        Member member = memberService.getMember(email);
        if (member != null) {
            return ResponseEntity.ok().body(member);
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    @GetMapping("/{memberId}/books")
    public List<Book> getBooksForMember(@PathVariable Long memberId) {
        return memberService.getBooksForMember(memberId);
    }
    @PostMapping("/{memberId}/books/{bookId}")
    public ResponseEntity<?> addBookToMember(@PathVariable Long memberId, @PathVariable Long bookId) {
        memberService.addBookToMember(memberId, bookId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{memberId}/books/{bookId}")
    public void deleteBookFromMember(@PathVariable Long memberId, @PathVariable Long bookId) {
        memberService.deleteBookFromMember(memberId, bookId);
    }
    @GetMapping("/login/{email}/{password}")
    public ResponseEntity<String> login(@PathVariable String email, @PathVariable String password) {
        String message = memberService.authenticate(email, password);
        if (message.equals("Success")) {
            return ResponseEntity.ok().body("Login successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(message);
        }
    }
}
