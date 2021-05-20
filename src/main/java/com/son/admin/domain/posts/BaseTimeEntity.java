package com.son.admin.domain.posts;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

/*
    BaseTimeEntity 클래스는 모든 Entity의 상위 클래스가 되어 Entity들의 createdDate, modifiedDate를 자동 관리
    MappedSuperclass -> JPA Entity클래스들이 BaseTimeEntity를 상속할 경우 필드들도 칼럼으로 인식
    EntityListeners -> BaseTimeEntity 클래스에 Auditing 기능 포함
    CreatedDate -> Entity가 생성되어 저장될 때 시간 자동 저장
    LastModifiedDate -> 조회한 Entity의 값을 변경할 때 시간 자동 저장
 */
@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseTimeEntity {

    @CreatedDate
    private LocalDateTime createdDate;

    @LastModifiedDate
    private LocalDateTime modifiedDate;
}
