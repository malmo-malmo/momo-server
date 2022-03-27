package com.momo.chat.domain.service.impl;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;

import com.momo.chat.domain.repository.MessageRepository;
import com.momo.chat.domain.response.SendPublishMessageResponse;
import com.momo.chat.domain.service.FindChatMessagesUseCase;
import com.momo.domain.chat.entity.Chat;
import com.momo.domain.chat.repository.ChatRepository;
import com.momo.domain.common.exception.CustomException;
import com.momo.domain.common.exception.ErrorCode;
import com.momo.domain.user.entity.User;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class FindChatMessagesService implements FindChatMessagesUseCase {

    private final ChatRepository chatRepository;

    private final MessageRepository messageRepository;

    @Override
    public List<SendPublishMessageResponse> findChatMessages(Long chatId, User user) {
        Chat chat = chatRepository.findById(chatId)
            .orElseThrow(() -> new CustomException(ErrorCode.INVALID_INDEX_NUMBER));

        Map<Long, User> userMap = Stream.of(chat.getUser(), chat.getManager())
            .collect(toMap(User::getId, Function.identity(), (a, b) -> a));

        return messageRepository.findByChatIdOrderByRegDatetimeAsc(chatId).stream()
            .map(message -> SendPublishMessageResponse.from(message, userMap))
            .collect(toList());
    }
}