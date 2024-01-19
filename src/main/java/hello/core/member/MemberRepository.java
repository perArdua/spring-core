package hello.core.member;

/**
 * Created by kok8454@gmail.com on 2024-01-14
 * Github : http://github.com/perArdua
 */
public interface MemberRepository {

    void save(Member member);

    Member findById(Long memberId);
}
