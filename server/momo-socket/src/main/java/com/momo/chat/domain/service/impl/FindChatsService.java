package com.momo.chat.domain.service.impl;

import static java.util.stream.Collectors.toList;

import com.momo.chat.domain.entity.Message;
import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.response.ChatResponse;
import com.momo.chat.domain.response.ChatResponseCompare;
import com.momo.chat.domain.service.FindChatsUseCase;
import com.momo.domain.chat.entity.Chat;
import com.momo.domain.chat.repository.ChatRepository;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.entity.User;
import com.momo.domain.user.repository.UserRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindChatsService implements FindChatsUseCase {

    private final ChatRepository chatRepository;

    private final UserRepository userRepository;

    private final MessageRepository messageRepository;

    @Override
    public List<ChatResponse> findChats(User user) {
        return chatRepository.findAllByUserOrManager(user, user).stream()
            .map(Chat::getId)
            .map(this::getLatestMessage)
            .map(message -> ChatResponse.from(message,
                getAuthor(message.getUserId(), user).getNickname(),
                getAuthor(message.getUserId(), user).getImageUrl()))
            .sorted(ChatResponseCompare::compare)
            .collect(toList());
    }

    private User getAuthor(Long userId, User user) {
        if (!user.isSameId(userId)) {
            return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
        }
        return user;
    }

    private Message getLatestMessage(Long chatId) {
        return messageRepository
            .findTop1ByChatIdOrderByRegDatetimeDesc(chatId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));
    }
}