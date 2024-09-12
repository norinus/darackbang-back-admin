package com.lab.darackbang.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.util.List;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@EqualsAndHashCode
@Table(name = "tbl_member")
public class Member {

    // 회원 아이디, 시퀀스 생성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    // 회원 이메일 ( not null, 크기 120 )
    @Column(name = "user_email", nullable = false, length = 120)
    private String userEmail;

    // 패스워드 ( not null, 크기 16, 특수문자 1개 이상 + 숫자 1개 이상 + 대소문자 구분 영문자 8자 이상 ~ 16자 이하 )
    @Column(name = "password", nullable = false, length = 16)
    private String password;

    // 회원 이름 (not null, 크기 15)
    @Column(name = "name", nullable = false, length = 15)
    private String name;

    // 생년월일 (not null, 크기 8)
    @Column(name = "birthday", nullable = false, length = 8)
    private String birthday;

    // 연령대 (생년월일에서 자동 계산)
    @Column(name = "age_group", nullable = false)
    private Integer ageGroup;

    // 성별 (not null, 크기 1 M/F에서 택 1)
    @Column(name = "gender", nullable = false, length = 1)
    private String gender;

    // 휴대폰 번호 ( not null, 크기 11 "-"제외한 11자리)
    @Column(name = "mobile_no", nullable = false, length = 11)
    private String mobileNo;

    // 전화 번호(null, 크기 11 "-"제외한 11자리)
    @Column(name = "phone_no", length = 11)
    private String phoneNo;

    // 주소(null, 크기 150, 6자 이상 150자이하)
    @Column(name = "address", length = 150)
    private String address;

    // 우편번호 (null, 크기 5)
    @Column(name = "post_no", length = 5)
    private String postNo;

    // 기본배송지(null, 크기 150, 6자 이상 150자 이하)
    @Column(name = "shipping_addr", length = 150)
    private String shippingAddr;

    // 기본우편번호(null, 크기 5)
    @Column(name = "ship_post_no", length = 5)
    private String shipPostNo;

    // 추가배송지 (null, 크기 150, 6자 이상 150자 이하)
    @Column(name = "add_shipping_addr", length = 150)
    private String addShippingAddr;

    // 추가우편번호(null, 크기 5)
    @Column(name = "add_post_no", length = 5)
    private String addPostNo;

    // 마일리지(not null, 크기 7)
    @Column(name = "mileage", nullable = false, length = 7)
    private Integer mileage;

    // 삭제 유무 ( default 0 정상유저)
    @Builder.Default
    @Column(name = "is_deleted", nullable = false, length = 1)
    private Boolean isDeleted = false;

    // 블랙컨슈머유무 (default 0 정상 : 1 블랙)
    @Builder.Default
    @Column(name = "is_blacklist", nullable = false, length = 1)
    private Boolean isBlacklist = false;

    // 회원상태 (default 01 정상 : 02 탈퇴 : 03 탈퇴신청)
    @Builder.Default
    @Column(name = "member_state", nullable = false, length = 2)
    private String memberState = "01";

    // 등록일
    @Column(name = "created_date", nullable = false)
    @CreatedDate
    private LocalDate createdDate;

    // 수정일
    @Column(name = "updated_date", nullable = false)
    @LastModifiedDate
    private LocalDate updatedDate;

    // memberRole 테이블 (회원롤) 외래키 설정
    @OneToMany(mappedBy = "member")
    private List<MemberRole> memberRoles;

    // memberCard 테이블 (회원카드정보) 외래키 설정
    @OneToOne(mappedBy = "member")
    private MemberCard memberCard;

    // subscribe 테이블 (구독) 외래키 설정
    @OneToMany(mappedBy = "member")
    private List<Subscribe> subscribes;
}