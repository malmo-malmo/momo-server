import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:hive_flutter/hive_flutter.dart';
import 'package:momo/app/provider/search/searched_custom_data.dart';
import 'package:momo/app/provider/search/searched_data.dart';
import 'package:momo/app/repository/group_repository.dart';

final searchedDataStateProvider =
    StateNotifierProvider<SearchedDataState, SearchedCustomData>((ref) {
  final groupRepository = ref.watch(groupRepositoryProvider);
  final searchedData = Hive.box('search').get('search');
  return SearchedDataState(
    groupIds: searchedData.groupIds,
    words: searchedData.words,
    groupRepository: groupRepository,
  );
});

class SearchedDataState extends StateNotifier<SearchedCustomData> {
  SearchedDataState({
    required this.groupIds,
    required this.words,
    required this.groupRepository,
  }) : super(SearchedCustomData(
          words: words,
          groupIds: groupIds,
          groups: [],
          isLoading: true,
        ));

  final List<String> words;
  final List<int> groupIds;
  final GroupRepository groupRepository;

  void addSearchedWord(String newWord) async {
    var curWords = state.words;
    curWords.remove(newWord);
    curWords.insert(0, newWord);
    if (curWords.length == 6) {
      curWords.removeLast();
    }
    state = state.copyWith(words: [...curWords]);
    await Hive.box('search').put(
      'searchedData',
      SearchedData(
        words: state.words,
        groupIds: state.groupIds,
      ),
    );
  }

  void deleteSearchedWord(String word) async {
    var curWords = state.words;
    curWords.remove(word);
    state = state.copyWith(words: [...curWords]);
    await Hive.box('search').put(
      'searchedData',
      SearchedData(
        words: state.words,
        groupIds: state.groupIds,
      ),
    );
  }
}
