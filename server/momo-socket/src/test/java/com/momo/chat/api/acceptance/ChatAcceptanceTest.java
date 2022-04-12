package com.momo.chat.api.acceptance;

import static com.momo.GroupFixture.getGroup;
import static com.momo.UserFixture.getUser;
import static com.momo.chat.api.acceptance.step.ChatAcceptanceStep.requestChatMessageHistory;
import static com.momo.chat.api.acceptance.step.ChatAcceptanceStep.requestChats;
import static com.momo.chat.api.acceptance.step.ChatAcceptanceStep.requestCreateChat;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsCreated;
import static com.momo.common.acceptance.step.AcceptanceStep.assertThatStatusIsOk;
import static com.momo.fixture.MessageFixture.getNormalMessage;

import com.momo.chat.domain.repository.MessageRepository;
import com.momo.common.acceptance.AcceptanceTest;
import com.momo.group.domain.Group;
import com.momo.group.domain.repository.GroupRepository;
import com.momo.user.domain.User;
import com.momo.user.domain.repository.UserRepository;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("채팅 통합/인수 테스트")
public class ChatAcceptanceTest extends AcceptanceTest {

    @Autowired
    private MessageRepository messageRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private GroupRepository groupRepository;

    @Test
    void 채팅방을_생성한다() {
        //given
        String userToken = getAccessToken(getUser());

        User manager = userRepository.save(getUser());
        String managerToken = getAccessToken(manager);
        Group group = groupRepository.save(getGroup(manager));

        //when
        ExtractableResponse<Response> response = requestCreateChat(userToken, group.getId());

        //then
        assertThatStatusIsCreated(response);
    }

    @Test
    void 채팅방_목록을_조회한다() {
        //given
        User user = userRepository.save(getUser());
        User manager = userRepository.save(getUser());
        Group group = groupRepository.save(getGroup(manager));
        Long groupId = group.getId();

        String userToken = getAccessToken(user);
        Long chatId = extractId(requestCreateChat(userToken, groupId));
        sendMessage(chatId, user);

        Long chatId2 = extractId(requestCreateChat(userToken, groupId));
        sendMessage(chatId2, user);

        //when
        ExtractableResponse<Response> response = requestChats(getAccessToken(manager), groupId);

        //then
        assertThatStatusIsOk(response);
    }

    @Test
    void 이전_채팅_기록을_모두_조회한다() {
        //given
        User user = userRepository.save(getUser());
        User manager = userRepository.save(getUser());
        Group group = groupRepository.save(getGroup(manager));
        Long groupId = group.getId();

        String userToken = getAccessToken(user);
        Long chatId = extractId(requestCreateChat(userToken, groupId));
        sendMessage(chatId, user);
        sendMessage(chatId, manager);

        //when
        ExtractableResponse<Response> response = requestChatMessageHistory(userToken, chatId);
        assertThatStatusIsOk(response);
    }

    private void sendMessage(Long chatId, User user) {
        messageRepository.save(getNormalMessage(chatId, user.getId()));
    }
}
