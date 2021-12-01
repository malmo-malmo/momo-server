enum CategoryType {
  health,
  employment,
  selfDevelopment,
  healing,
  asset,
  life,
  hobby,
  rice,
}

extension PostTypeToString on CategoryType {
  String get categoryTypeToName {
    switch (this) {
      case CategoryType.health:
        return '건강';
      case CategoryType.employment:
        return '취업';
      case CategoryType.selfDevelopment:
        return '자기계발';
      case CategoryType.healing:
        return '힐링';
      case CategoryType.asset:
        return '자산';
      case CategoryType.life:
        return '생활';
      case CategoryType.hobby:
        return '취미';
      case CategoryType.rice:
        return '밥약';
      default:
        return '';
    }
  }

  String get categoryTypeToString {
    switch (this) {
      case CategoryType.health:
        return 'HEALTH';
      case CategoryType.employment:
        return 'EMPLOYMENT';
      case CategoryType.selfDevelopment:
        return 'SELF_DEVELOPMENT';
      case CategoryType.healing:
        return 'HEALING';
      case CategoryType.asset:
        return 'ASSET';
      case CategoryType.life:
        return 'LIFE';
      case CategoryType.hobby:
        return 'HOBBY';
      case CategoryType.rice:
        return 'RICE';
      default:
        return '';
    }
  }
}
