import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:momo/app/model/group/group_detail.dart';
import 'package:momo/app/model/group/group_summary_response.dart';
import 'package:momo/app/model/group/my_group.dart';
import 'package:momo/app/provider/group/my_group_list.dart';
import 'package:momo/app/repository/user_repository.dart';

final myGroupStateProvider =
    StateNotifierProvider.autoDispose<MyGroupState, MyGroupList>((ref) {
  final userRepository = ref.watch(userRepositoryProvider);
  return MyGroupState(userRepository: userRepository);
});

class MyGroupState extends StateNotifier<MyGroupList> {
  MyGroupState({
    required this.userRepository,
  }) : super(
          MyGroupList(
            isLoading: true,
            myGroups: [],
            participationGroups: [],
          ),
        );

  final UserRepository userRepository;

  Future<void> getMyGroups() async {
    state = state.copyWith(isLoading: true);
    final myGroupResponse = userRepository.getMyGruops();
    final participationResponse = userRepository.getParticipationGroups();

    final groups = await Future.wait([myGroupResponse, participationResponse]);

    state = state.copyWith(
      isLoading: false,
      myGroups: [...(groups[0] as List<MyGroup>)],
      participationGroups: [...(groups[1] as List<GroupSummaryReseponse>)],
    );
  }

  void createGroupCallback(GroupDetail groupDetail) {
    final newGruop = MyGroup(
        id: groupDetail.id,
        name: groupDetail.name,
        imageUrl: groupDetail.imageUrl,
        achievementRate: 0);
    state = state.copyWith(myGroups: [newGruop, ...state.myGroups]);
  }
}
