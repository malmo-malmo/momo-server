enum PostType { normal, notice }

extension PostTypeToString on PostType {
  String get postTypeToName {
    switch (this) {
      case PostType.normal:
        return '게시물';
      case PostType.notice:
        return '공지사항';
      default:
        return '';
    }
  }
}
