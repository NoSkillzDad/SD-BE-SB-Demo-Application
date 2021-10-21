package novi.nl.library.service;

import novi.nl.library.exception.BadRequestException;
import novi.nl.library.exception.RecordNotFoundException;
import novi.nl.library.model.BorrowedCopy;
import novi.nl.library.model.Copy;
import novi.nl.library.model.Member;
import novi.nl.library.repository.BorrowedCopyRepository;
import novi.nl.library.repository.CopyRepository;
import novi.nl.library.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private CopyRepository copyRepository;

    @Autowired
    private BorrowedCopyRepository borrowedCopyRepository;

    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    public Member getMember(long id) {
        if (memberRepository.existsById(id)) {
            return memberRepository.findById(id).get();
        }
        else {
            throw new RecordNotFoundException("No member with id " + id);
        }
    }

    public long save(Member member) {
        Member saved = memberRepository.save(member);
        return saved.getId();
    }

    public void deleteById(long id) {
        if (memberRepository.existsById(id)) {
            memberRepository.deleteById(id);
        }
        else {
            throw new RecordNotFoundException("No member with id " + id);
        }
    }

    public List<BorrowedCopy> getBorrowedCopies(long id) {
        Member member = getMember(id);
        return member.getBorrowedCopies();
    }

    public void borrowACopy(long memberId, Copy copyWithId) {
        if (copyWithId == null) {
            throw new BadRequestException("No copy specified.");
        }
        long copyId = copyWithId.getId();
        borrowACopy(memberId, copyId);
    }

    public void borrowACopy(long memberId, long copyId) {
        final int MAXIMUM_BORROW_PERIOD = 14;  // in days
        Member member = getMember(memberId);
        Optional<Copy> optionalCopy = copyRepository.findById(copyId);
        if (optionalCopy.isPresent()) {
            Copy storedCopy = optionalCopy.get();
            if (storedCopy.isAvailable()) {
                BorrowedCopy borrowedCopy = new BorrowedCopy();
                borrowedCopy.setMember(member);
                borrowedCopy.setCopy(storedCopy);
                borrowedCopy.setDateBorrowedOn(LocalDateTime.now());
                borrowedCopy.setDateReturnedBy(LocalDateTime.now().plusDays(MAXIMUM_BORROW_PERIOD));
                borrowedCopy.setDateReturnedOn(null);
                borrowedCopyRepository.save(borrowedCopy);

                member.getBorrowedCopies().add(borrowedCopy);
                memberRepository.save(member);

                storedCopy.setAvailable(false);
                copyRepository.save(storedCopy);
            }
        }
        else {
            throw new RecordNotFoundException("No copy with id " + copyId);
        }
    }

    public void returnBorrowedCopy(long memberId, Copy copyWithId) {
        if (copyWithId == null) {
            throw new BadRequestException("No copy specified.");
        }
        long copyId = copyWithId.getId();
        returnBorrowedCopy(memberId, copyId);
    }

    public void returnBorrowedCopy(long memberId, long borrowedCopyId) {
        Member member = getMember(memberId);
        List<BorrowedCopy> borrowedCopies = member.getBorrowedCopies();
        boolean found = false;
        BorrowedCopy foundBorrowedCopy = null;
        for (BorrowedCopy borrowedCopy: borrowedCopies) {
            if (borrowedCopy.getId() == borrowedCopyId) {
                found = true;
                foundBorrowedCopy = borrowedCopy;

                Copy copy = borrowedCopy.getCopy();

                copy.setAvailable(true);
                copyRepository.save(copy);

                borrowedCopy.setDateReturnedOn(LocalDateTime.now());
                borrowedCopyRepository.save(borrowedCopy);
            }
        }
        if (found) {
            member.getBorrowedCopies().remove(foundBorrowedCopy);
            memberRepository.save(member);
        }
        else {
            throw new RecordNotFoundException("No borrowed copy with id " + borrowedCopyId);
        }
    }

}
