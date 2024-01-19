package hello.core.member;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
public class MemberServiceImpl implements MemberService {

    // MemberServiceImpl은 MemberRepository와 MemoryMemberRepository에 의존한다.
    // MemberRepository는 인터페이스이고, MemoryMemberRepository는 구현체이다.
    // DIP를 지키기 위해 MemberServiceImpl은 MemberRepository 인터페이스에만 의존하도록 한다.
    // MemberRepository 인터페이스를 구현한 MemoryMemberRepository를 생성자를 통해 주입한다.
    private final MemberRepository memberRepository;

    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    // Test용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
