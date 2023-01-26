package nextstep.member;

import lombok.RequiredArgsConstructor;
import nextstep.auth.JwtTokenProvider;
import nextstep.exception.LoginFailException;
import nextstep.exception.NotLoggedInException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberDao memberDao;
    private final JwtTokenProvider jwtTokenProvider;

    public Long create(MemberRequest memberRequest) {
        return memberDao.save(memberRequest.toEntity());
    }

    public Member findByUsername(String username) {
        return memberDao.findByUsername(username).orElseThrow(LoginFailException::new);
    }

    public Member findByToken(String token) {
        if (token == null) {
            throw new NotLoggedInException();
        }
        String principal = jwtTokenProvider.getPrincipal(token);
        return findByUsername(principal);
    }
}
