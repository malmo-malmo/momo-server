package com.momo.chat.entity;

import com.momo.common.entity.BaseEntity;
import com.momo.group.entity.Group;
import com.momo.user.domain.model.User;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity(name = "chat")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Chat extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Group group;

    @ManyToOne(fetch = FetchType.LAZY)
    private User manager;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @Builder
    public Chat(LocalDateTime createdDate, LocalDateTime lastModifiedDate, Long id,
        Group group, User manager, User user) {
        super(createdDate, lastModifiedDate);
        this.id = id;
        this.group = group;
        this.manager = manager;
        this.user = user;
    }

    public static Chat create(Group group, User user) {
        return Chat.builder()
            .group(group)
            .manager(group.getManager())
            .user(user)
            .build();
    }

    public boolean isManager(User user) {
        return this.manager.isSameUser(user);
    }

}
