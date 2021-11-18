import 'dart:async';
import 'dart:developer' as dp;

import 'package:flutter_riverpod/flutter_riverpod.dart';

class Logger extends ProviderObserver {
  @override
  void didAddProvider(
    ProviderBase provider,
    Object? value,
    ProviderContainer container,
  ) {
    dp.log(
      '''
providerAdd: ${provider.name ?? provider.runtimeType}
value: $value
''',
      time: DateTime.now(),
      zone: Zone.current,
    );
    super.didAddProvider(provider, value, container);
  }

  @override
  void didUpdateProvider(
    ProviderBase provider,
    Object? previousValue,
    Object? newValue,
    ProviderContainer container,
  ) {
    dp.log(
      '''
providerUpdate: ${provider.name ?? provider.runtimeType}
newValue: $newValue      
      ''',
      time: DateTime.now(),
      zone: Zone.current,
    );
    super.didUpdateProvider(provider, previousValue, newValue, container);
  }

  @override
  void didDisposeProvider(
    ProviderBase provider,
    ProviderContainer containers,
  ) {
    dp.log(
      'providerDispose: ${provider.name ?? provider.runtimeType}',
      time: DateTime.now(),
      zone: Zone.current,
    );
    super.didDisposeProvider(provider, containers);
  }
}
