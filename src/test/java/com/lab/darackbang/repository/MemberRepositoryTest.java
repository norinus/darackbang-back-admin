package com.lab.darackbang.repository;

import com.lab.darackbang.entity.Member;
import com.lab.darackbang.entity.MemberRole;
import com.lab.darackbang.entity.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest // 통합 테스트 어노테이션. Application 모든 빈 로드
@Slf4j
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberRoleRepository memberRoleRepository;

    @Test
    void insertTest(){
        for(int i=1; i<=10 ;i++ ){

            Member member = Member.builder() // 필수 기입 사항 설정
                    .userEmail("user"+String.valueOf(i)+"@test.com")
                    .password("1234")
                    .name("user"+String.valueOf(i))
                    .birthday("20240302")
                    .ageGroup("20")
                    .gender("F")
                    .mobileNo("01028810137")
                    .mileage(0)
                    .createdDate(LocalDate.now())
                    .updatedDate(LocalDate.now())
                    .build();

            List<MemberRole> memberRoles = new ArrayList<>();

            // 유저 권한 생성 및 추가
            MemberRole userRole = new MemberRole();
            userRole.setRole(Role.USER);
            userRole.setMember(member); // 회원 추가
            memberRoles.add(userRole);

            // i >= 5일 경우 관리자 권한 추가
            if (i >= 5) {
                MemberRole managerRole = new MemberRole();
                managerRole.setRole(Role.MANAGER);
                managerRole.setMember(member);
                memberRoles.add(managerRole);
            }

            // i >= 8일 경우 어드민 권한 추가
            if (i >= 8) {
                MemberRole adminRole = new MemberRole();
                adminRole.setRole(Role.ADMIN);
                adminRole.setMember(member);
                memberRoles.add(adminRole);
            }

            // 회원과 권한 리스트 연결
            member.setMemberRoles(memberRoles);

            memberRepository.save(member);
        }
    }

    @Test
    void read(){

        Member member = memberRepository.findById(3L).orElseThrow();

        log.info("사용자 이메일 {}",member.toString());

        log.info("사용자 롤 정보 {}", member.getMemberRoles().toString());

    }

    @Test
    void update(){ // 회원 수정 삭제

        Member member = memberRepository.findById(3L).orElseThrow();

        member.setIsDeleted(true);
        memberRepository.save(member);

    }


}