package novi.nl.library.controller;

import novi.nl.library.model.BorrowedCopy;
import novi.nl.library.model.Copy;
import novi.nl.library.model.Member;
import novi.nl.library.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping(value = "/members")
    public ResponseEntity<?> getMembers() {
        List<Member> members = memberService.getAllMembers();
        return ResponseEntity.ok(members);
    }

    @GetMapping(value = "/members/{id}")
    public ResponseEntity<?> getMember(@PathVariable("id") long id) {
        return ResponseEntity.ok(memberService.getMember(id));
    }

    @PostMapping(value = "/members")
    public ResponseEntity<?> createMember(@RequestBody Member member) {
        long newId = memberService.save(member);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(value = "/members/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable("id") long id) {
        memberService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping(value = "/members/{id}/borrowed")
    public ResponseEntity<?> getBorrowedCopiesByMember(@PathVariable("id") long id) {

        List<BorrowedCopy> borrowed = memberService.getBorrowedCopies(id);
        return ResponseEntity.ok(borrowed);
    }

    @PatchMapping(value = "/members/{id}/borrowed")
    public ResponseEntity<?> addBorrowedCopiesByMember(
            @PathVariable("id") long id,
            @RequestBody long copyId) {

        memberService.borrowACopy(id, copyId);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(value = "/members/{id}/borrowed/{borrowedCopyId}")
    public ResponseEntity<?> returnBorrowedCopyByMember(
            @PathVariable("id") long id,
            @PathVariable("borrowedCopyId") long borrowedCopyId) {

        memberService.returnBorrowedCopy(id, borrowedCopyId);
        return ResponseEntity.noContent().build();
    }

}
