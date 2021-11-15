package com.momo.meeting.domain.model;

import com.momo.common.model.BaseEntity;
import com.momo.common.model.MeetingType;
import com.momo.user.domain.model.User;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Meeting extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "meeting_id")
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id")
  private User manager;

  private String mainImg;

  private String category;

  @Enumerated(EnumType.STRING)
  private MeetingType meetingType;

  private String startDate;

  private String endDate;

  private String university;

  private String area;

  @Lob
  private String introduction;

  private Long recruitmentCnt;

  private Long participantCnt;

  private Long scheduleCnt;

  private Long attendanceCnt;

  @Column(name = "end_flag")
  private Boolean isEnd;
}
