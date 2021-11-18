import 'package:flutter_dotenv/flutter_dotenv.dart';
import 'package:flutter_riverpod/flutter_riverpod.dart';
import 'package:minio/minio.dart';

final momoMinio = Provider<Minio>((ref) {
  final endPoint = dotenv.get('ENDPOINT');
  final accessKey = dotenv.get('ACCESSKEY');
  final secretKey = dotenv.get('SECRETKEY');
  final region = dotenv.get('REGION');

  final minio = Minio(
    endPoint: endPoint,
    accessKey: accessKey,
    secretKey: secretKey,
    region: region,
  );
  return minio;
});
