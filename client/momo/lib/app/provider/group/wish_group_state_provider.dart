// import 'package:flutter_riverpod/flutter_riverpod.dart';
// import 'package:momo/app/model/group/group_like_request.dart';
// import 'package:momo/app/model/group/group_list_dto.dart';
// import 'package:momo/app/repository/user_repository.dart';

// class GroupListState extends StateNotifier<GroupListDto> {
//   GroupListState({
//     required this.userRepository,
//   }) : super(
//           GroupListDto(
//             groups: [],
//             hasNext: true,
//             nextPage: 0,
//           ),
//         );
//   final UserRepository userRepository;

//   Future<void> getGroupsByDistrict() async {
//     final response = await userRepository.getFavoriteGroups();
//     state = state.copyWith(
//       groups: [
//         ...state.groups,
//         ...response,
//       ],
//       hasNext: response.length == pageSize,
//       nextPage: page,
//     );
//   }

//   Future<bool> createLike(int groupId) async {
//     state = state.copyWith(
//       groups: [
//         ...state.groups
//             .map(
//               (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
//             )
//             .toList()
//       ],
//     );
//     try {
//       await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));
//       return true;
//     } catch (e) {
//       state = state.copyWith(
//         groups: [
//           ...state.groups
//               .map(
//                 (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
//               )
//               .toList()
//         ],
//       );
//       return false;
//     }
//   }

//   Future<bool> deleteLike(int groupId) async {
//     state = state.copyWith(
//       groups: [
//         ...state.groups
//             .map(
//               (e) => e.id == groupId ? e.copyWith(favoriteGroup: false) : e,
//             )
//             .toList()
//       ],
//     );
//     try {
//       await userRepository.createGroupLike(GroupLikeRequest(groupId: groupId));
//       return true;
//     } catch (e) {
//       state = state.copyWith(
//         groups: [
//           ...state.groups
//               .map(
//                 (e) => e.id == groupId ? e.copyWith(favoriteGroup: true) : e,
//               )
//               .toList()
//         ],
//       );
//       return false;
//     }
//   }
// }
